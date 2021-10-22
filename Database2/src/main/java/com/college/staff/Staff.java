package com.college.staff;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.GenericGenerator;

import com.college.department.Department;
import com.fasterxml.jackson.annotation.JsonBackReference;

//Create a table for staff.
@Entity
@NamedQuery(name = "Staff.findByDepartmentId", query = "from Staff where deptid = :deptid")
public class Staff {

	// Create columns in staff table.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String designation;
	@Column(nullable = false)
	private int salary;

	// Getting values of parent table.
	@ManyToOne
	@JoinColumn(name = "deptid", nullable = false, insertable = true, updatable = true)
	@JsonBackReference
	private Department department;

	// Generated constructor
	public Staff() {

	}

	public Staff(int id, String name, String designation, int salary, Department department) {
		super();
		this.id = id;
		this.name = name;
		this.designation = designation;
		this.salary = salary;
		this.department = department;
	}

	// Generated getter and setter methods.
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

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

}
