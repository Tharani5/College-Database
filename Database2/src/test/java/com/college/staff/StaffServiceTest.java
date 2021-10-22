package com.college.staff;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import com.college.department.Department;

@ExtendWith(MockitoExtension.class)
class StaffServiceTest {

	@Mock
	private StaffRepository staffRepository;
	@InjectMocks
	private StaffService staffService;

	@Test
	void testDeleteStaff() {
		staffService.deleteStaff(10);
		verify(staffRepository).deleteById(10);
	}

	@Test
	void testUpdateStaff() {
		Staff staff = new Staff(1, "Praveen M", "Professor", 45000, new Department(3, "", ""));
		staffService.updateStaff(staff);
		verify(staffRepository).save(staff);
	}

	@Test
	void testAddStaff() {
		Staff staff = new Staff(1, "Praveen M", "Professor", 45000, new Department(3, "", ""));
		when(staffRepository.save(ArgumentMatchers.any(Staff.class))).thenReturn(staff);
		Staff staffCreated = staffService.addStaff(staff);
		assertThat(staffCreated).isSameAs(staff);
		verify(staffRepository).save(staff);
	}

	@Test
	void testGetStaffByDepartment() {
		List<Staff> staffs = new ArrayList();
		staffs.add(new Staff(1, "Venky", "Professor", 34000, new Department(6, "", "")));
		staffs.add(new Staff(2, "Gopal", "Professor", 37000, new Department(6, "", "")));
		when(staffRepository.findByDepartmentId(1)).thenReturn(staffs);
		List<Staff> expected = staffService.getStaffByDepartment(1);
		assertThat(staffs).isSameAs(expected);
		verify(staffRepository).findByDepartmentId(1);
	}

	@Test
	void testGetSalaryTotalByDepartment() {
		List<Staff> staffs = new ArrayList();
		staffs.add(new Staff(1, "Venky", "Professor", 40000, new Department(4, "", "")));
		staffs.add(new Staff(2, "Gopal", "Professor", 35000, new Department(4, "", "")));
		when(staffRepository.getSalaryTotalByDepartment(4)).thenReturn((long) 75000);
		long actual = staffRepository.getSalaryTotalByDepartment(4);
		long expected = staffService.getSalaryTotalByDepartment(4);
		assertEquals(expected, actual);
	}

	@Test
	void testGetStaffs() {
		staffService.getStaffs();
		verify(staffRepository).findAll(Sort.by(Sort.Direction.ASC, "id"));
	}

}
