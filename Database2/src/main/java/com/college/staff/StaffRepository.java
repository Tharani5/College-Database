package com.college.staff;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StaffRepository extends JpaRepository<Staff, Integer> {

	// Query to fetch student by department id.
	public List<Staff> findByDepartmentId(int deptid);

	// Query to find total fee of students in specific department using aggregate
	// function.
	@Query(value = "select sum(salary) from staff where deptid=?", nativeQuery = true)
	public Long getSalaryTotalByDepartment(int deptId);
}
