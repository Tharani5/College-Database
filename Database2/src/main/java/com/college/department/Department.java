package com.college.department;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.college.staff.Staff;
import com.college.student.Student;
import com.fasterxml.jackson.annotation.JsonManagedReference;

//creating table for department
@Entity
@Table(name = "department")
public class Department {
	// defining all the columns in department table.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id", nullable = false)
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "head")
	private String head;

	// Mapping relation to child tables
	@OneToMany(mappedBy = "department")
	@JsonManagedReference
	private List<Student> student;

	@OneToMany(mappedBy = "department")
	@JsonManagedReference
	private List<Staff> staff;

	// Generating getters and setters
	public Department() {

	}

	public Department(int id, String name, String head) {
		super();
		this.id = id;
		this.name = name;
		this.head = head;
	}

	public void setStudent(List<Student> student) {
		this.student = student;
	}

	public List<Student> getStudent() {
		return student;
	}

	public int getId() {
		return id;
	}

	public List<Staff> getStaff() {
		return staff;
	}

	public void setStaff(List<Staff> staff) {
		this.staff = staff;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

}
