package com.college.department;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

	@Autowired
	DepartmentRepository departmentRepository;

	// To get all departments
	public List<Department> getDepartments() {
		return departmentRepository.findAll();
	}

	// Delete any specific department
	public Department addDepartment(Department department) {
		return departmentRepository.save(department);
	}

}
