package geeky.camp.jpa.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="course")
public class Course implements Serializable{
 
	@Id
    @Column(name="course_id")
    private int courseId;
 
    @Column(name="course_name")
    private String courseName;
 
 
    @Column(name="course_description")
    private String courseDescription;
 
    @ManyToMany
    @JoinTable(
            name="student_course",
            joinColumns = {@JoinColumn(name = "courseID",referencedColumnName = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "facultyNumber", referencedColumnName = "faculty_number")})
    private List<Student>  students;
 
    private static final long serialVersionUID = 1L;
    
	public Course() {
		super();
	}
	    
    public Course(int courseId, String courseName, String courseDescription,List<Student> students){
    	this.courseId = courseId;
		this.courseName = courseName;
		this.courseDescription = courseDescription;
		this.students = students;
    }
    
    public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
        return courseName;
    }
 
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
 
    public String getCourseDescription() {
        return courseDescription;
    }
 
    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public List<Student> getStudents() {
        return students;
    }
 
    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
