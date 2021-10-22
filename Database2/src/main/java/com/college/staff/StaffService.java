package com.college.staff;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class StaffService {

	@Autowired
	StaffRepository staffRepository;

	// Method to delete staff by id.
	public String deleteStaff(int id) {
		staffRepository.deleteById(id);
		return "deleted";
	}

	// To add or update staff in data base.
	public Staff updateStaff(Staff staff) {
		return staffRepository.save(staff);
	}

	public Staff addStaff(Staff staff) {
		return staffRepository.save(staff);
	}

	// Get list of staffs by specific department
	public List<Staff> getStaffByDepartment(int deptid) {
		return staffRepository.findByDepartmentId(deptid);
	}

	public Long getSalaryTotalByDepartment(int deptId) {
		return staffRepository.getSalaryTotalByDepartment(deptId);
	}

	public List<Staff> getStaffs() {
		return staffRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}

}
