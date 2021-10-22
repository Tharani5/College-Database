package com.college.department;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//Created restcontroller for department
@RestController
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;

	// To get list of departments in database
	@GetMapping("/departments")
	public List<Department> getDepartments() {
		return departmentService.getDepartments();
	}

	// To add any additional department
	@PostMapping("/addDepartment")
	public ResponseEntity<Department> addDepartments(@RequestBody Department department) {
		Department newDepartment = departmentService.addDepartment(department);
		return new ResponseEntity<>(newDepartment, HttpStatus.CREATED);
	}
}
