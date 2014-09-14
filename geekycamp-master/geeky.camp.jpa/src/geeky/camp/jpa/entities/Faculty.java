package geeky.camp.jpa.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class Faculty implements Serializable {
	
	@Id
	@Column(name="faculty_id")
	private int facultyId;
	
	@Column(name="faculty_name")
	private String facultyName;
	
	@Column(name="faculty_description")
	private String facultyDescription;

	@OneToMany
	private List<Student> students;
	
	private static final long serialVersionUID = 1L;

	public Faculty() {
		super();
	}

	public Faculty(int facultyId, String facultyName, String facultyDescription, List<Student> students){
		this.facultyId = facultyId;
		this.facultyName = facultyName;
		this.facultyDescription = facultyDescription;
		this.students = students;
	}
	
	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public String getFacultyDescription() {
		return facultyDescription;
	}

	public void setFacultyDescription(String facultyDescription) {
		this.facultyDescription = facultyDescription;
	}
   
}
