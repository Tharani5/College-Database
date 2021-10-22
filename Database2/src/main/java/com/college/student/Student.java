package com.college.student;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.GenericGenerator;

import com.college.department.Department;
import com.fasterxml.jackson.annotation.JsonBackReference;

//Create a table for Student
@Entity
@NamedQuery(name = "Student.findByDepartmentId", query = "from Student where deptid = :deptid")
@NamedQuery(name = "Student.getStudentByYear", query = "from Student where year = :year")
public class Student {
	// Column name are created
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String course;
	@Column(nullable = false)
	private int year;
	@Column(nullable = false)
	private int fee;

	// Getting values from parent table
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "deptid", nullable = true, insertable = true, updatable = true)
	@JsonBackReference
	private Department department;

	// Constructor is created
	public Student() {

	}

	public Student(int id, String name, String course, int year, int fee, Department department) {
		super();
		this.id = id;
		this.name = name;
		this.course = course;
		this.year = year;
		this.fee = fee;
		this.department = department;
	}

	// Generated getter and setter methods
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public int getId() {
		return id;
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

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

}
