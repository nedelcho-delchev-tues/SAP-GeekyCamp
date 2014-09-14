package geeky.camp.jpa;

import geeky.camp.jpa.dao.CourseDAO;
import geeky.camp.jpa.dao.FacultyDAO;
import geeky.camp.jpa.dao.StudentDAO;
import geeky.camp.jpa.entities.Course;
import geeky.camp.jpa.entities.Student;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AppLauncher {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("geeky.camp");
		List<Student> students = new ArrayList<Student>();
		
		StudentDAO studentDAO = new StudentDAO(emf);
		studentDAO.createNewStudent("1234", "mincho", "mincho", new Date(), 17);
		studentDAO.createNewStudent("420", "mitko", "mutata", new Date(), 25);
		
		Student studentByFn = studentDAO.findByPrimaryKey("1234");
		System.out.println("Found student : " + studentByFn);
		
		CourseDAO courseDAO = new CourseDAO(emf);
		courseDAO.createNewCourse(2 ,"Mathematics", "haha", students);
	
		FacultyDAO facultyDAO = new FacultyDAO(emf);
		facultyDAO.createNewFaculty(1 ,"Science", "wibbly wobbly... time-y wimey... stuff.", students);
	}
}
