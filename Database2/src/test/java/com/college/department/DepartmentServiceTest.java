package com.college.department;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

	@Mock
	private DepartmentRepository departmentRepository;
	@InjectMocks
	private DepartmentService departmentService;

	@Test
	void testGetDepartments() {
		departmentService.getDepartments();
		verify(departmentRepository).findAll();
	}

	@Test
	void testAddDepartment() {
		Department department = new Department(1, "CSE", "Lenin");
		when(departmentRepository.save(ArgumentMatchers.any(Department.class))).thenReturn(department);
		Department departmentCreated = departmentService.addDepartment(department);
		assertThat(departmentCreated).isSameAs(department);
		verify(departmentRepository).save(department);
	}

}
