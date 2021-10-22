package com.college.student;

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
class StudentServiceTest {

	@Mock
	private StudentRepository studentRepository;
	@InjectMocks
	private StudentService studentService;

	@Test
	void testDeleteStudent() {
		studentService.deleteStudent(6);
		verify(studentRepository).deleteById(6);
	}

	@Test
	void testAddStudent() {
		Student student = new Student();
		student.setId(1);
		student.setName("Venky");
		student.setFee(87900);
		student.setYear(2);
		student.setCourse("AI");
		student.setDepartment(new Department(3, "", ""));
		when(studentRepository.save(ArgumentMatchers.any(Student.class))).thenReturn(student);
		Student studentCreated = studentService.addStudent(student);
		assertThat(studentCreated.getName()).isSameAs(student.getName());
		verify(studentRepository).save(student);
	}

	@Test
	void testUpdateStudent() {
		Student student = new Student(1, "Venky M", "AI", 98760, 2, new Department(3, "", ""));
		studentService.updateStudent(student);
		verify(studentRepository).save(student);
	}

	@Test
	void testGetStudentByDepartment() {
		List<Student> students = new ArrayList();
		students.add(new Student(1, "Venky", "AI", 87906, 4, new Department(6, "", "")));
		students.add(new Student(2, "Gopal", "MECH", 87906, 4, new Department(6, "", "")));
		when(studentRepository.findByDepartmentId(6)).thenReturn(students);
		List<Student> expected = studentService.getStudentByDepartment(6);
		assertThat(students).isSameAs(expected);
		verify(studentRepository).findByDepartmentId(6);
	}

	@Test
	void testGetFeeTotalByDepartment() {
		List<Student> students = new ArrayList();
		students.add(new Student(1, "Venky", "AI", 87906, 4, new Department(6, "", "")));
		students.add(new Student(2, "Gopal", "MECH", 87906, 4, new Department(6, "", "")));
		when(studentRepository.getFeeTotalByDepartment(4)).thenReturn((long) 175812);
		long actual = studentRepository.getFeeTotalByDepartment(4);
		long expected = studentService.getFeeTotalByDepartment(4);
		assertEquals(expected, actual);
	}

	@Test
	void testGetStudentByYear() {
		List<Student> students = new ArrayList();
		students.add(new Student(1, "Venky", "AI", 87906, 4, new Department(6, "", "")));
		students.add(new Student(2, "Gopal", "MECH", 87906, 4, new Department(6, "", "")));
		when(studentRepository.findStudentByYear(4, Sort.by(Sort.Direction.ASC, "name"))).thenReturn(students);
		List<Student> expected = studentService.getStudentByYear(4);
		assertThat(students).isSameAs(expected);
		verify(studentRepository).findStudentByYear(4, Sort.by(Sort.Direction.ASC, "name"));
	}

	@Test
	void testGetStudents() {
		studentService.getStudents();
		verify(studentRepository).findAll(Sort.by(Sort.Direction.ASC, "id"));
	}
}
