package com.college.staff;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaffController {

	@Autowired
	StaffService staffService;

	// Method to delete staff using staff id.
	@DeleteMapping("/deleteStaff/{id}")
	public void deleteStaff(@PathVariable("id") int id) {
		staffService.deleteStaff(id);
	}

	// Method to add newly or update on existing staff.
	@PutMapping("/updateStaff")
	public Staff updateStaff(@RequestBody Staff staff) {
		return staffService.updateStaff(staff);
	}

	// Method to add newly or update on existing staff.
	@PostMapping("/addStaff")
	public Staff addStaff(@RequestBody Staff staff) {
		return staffService.addStaff(staff);
	}

	// Getting list of staffs by specific department using department id.
	@GetMapping("/department/{deptId}/staff")
	public List<Staff> getStaffByDepartment(@PathVariable("deptId") int deptid) {
		return staffService.getStaffByDepartment(deptid);
	}

	// Total salary of staff in specific department id given by this method.
	@GetMapping("/department/{deptId}/totalSalary")
	public Long getSalaryTotalByDepartment(@PathVariable("deptId") int deptId) {
		return staffService.getSalaryTotalByDepartment(deptId);
	}

	// Fetch all students in database.
	@GetMapping("/staff")
	public List<Staff> getStuedents() {
		return staffService.getStaffs();
	}
}
