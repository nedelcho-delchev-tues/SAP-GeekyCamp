package geeky.camp.jpa.dao;

import geeky.camp.jpa.entities.Course;
import geeky.camp.jpa.entities.Student;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;

public class CourseDAO {
	protected EntityManagerFactory emf;

	public CourseDAO(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	public void deleteCourse(Object primaryKey) {
		EntityManager em = null;
		EntityTransaction tx = null;
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			Course willBeDeleted = em.find(Course.class, primaryKey);
			em.remove(willBeDeleted);
			tx.commit();
			System.out.println("Entry : " + willBeDeleted + " removed from db");
		} catch (RollbackException e) {
			System.out.println("Couldn't commit transaction, db will be reverted");
			e.printStackTrace();
		} finally {
			if (tx != null && tx.isActive())
				tx.rollback();
			if (em != null)
				em.close();
		}
	}

	public void createNewCourse(int courseId, String courseName, String courseDescription, 
			List<Student>  students) {
		Course newCourse = new Course(courseId, courseName, courseDescription, students);
		EntityManager em = null;
		EntityTransaction tx = null;
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			em.persist(newCourse);
			tx.commit();
		} catch (RollbackException e) {
			System.out.println("Couldn't commit transaction, db will be reverted");
			e.printStackTrace();
		} finally {
			if (tx != null && tx.isActive())
				tx.rollback();
			if (em != null)
				em.close();
		}
		System.out.println("Course created " + newCourse);
	}

	public void updateCourse(Course updateInfo) {
		EntityManager em = null;
		EntityTransaction tx = null;
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			Course willBeUpdated = em.find(Course.class,
					updateInfo.getCourseName());
			System.out.println("Course " + willBeUpdated
					+ " will be updated to " + updateInfo);
			updateProps(willBeUpdated, updateInfo);
			tx.commit();
			System.out.println("Course updated with success");
		} catch (RollbackException e) {
			System.out
					.println("Couldn't commit transaction, db will be reverted");
			e.printStackTrace();
		} finally {
			if (tx != null && tx.isActive())
				tx.rollback();
			if (em != null)
				em.close();
		}
	}
	
	public Course findByPrimaryKey(Object primaryKey) { 
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			return em.find(Course.class, primaryKey);
		}  finally {
			if (em != null)
				em.close();
		}
	}

	private void updateProps(Course willBeUpdated, Course updateInfo) {
		willBeUpdated.setCourseId(updateInfo.getCourseId());
		willBeUpdated.setCourseName(updateInfo.getCourseName());
		willBeUpdated.setCourseDescription(updateInfo.getCourseDescription());
	}

}
