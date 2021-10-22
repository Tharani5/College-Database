package com.college.student;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	// Query to fetch student by department id.
	public List<Student> findByDepartmentId(int deptid);

	// Query to fetch all students in specific year by sorting students name in
	// ascending order.
	public List<Student> findStudentByYear(int year, Sort sort);

	// Query to find total fee of students in specific department using aggregate
	// function.
	@Query(value = "select sum(fee) from student where deptid=?", nativeQuery = true)
	public Long getFeeTotalByDepartment(int deptid);

}
