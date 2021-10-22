package com.college.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StudentService {
	@Autowired
	StudentRepository studentRepository;

	@Autowired
	StudentExcelRepository studentExcelRepository;

	// Method to delete student by id.
	public void deleteStudent(int id) {
		studentRepository.deleteById(id);
	}

	// To add or update student in data base.
	public Student addStudent(Student student) {
		return studentRepository.save(student);
	}

	public Student updateStudent(Student student) {
		return studentRepository.save(student);
	}

	// Get list of students by specific department
	public List<Student> getStudentByDepartment(int deptid) {
		return studentRepository.findByDepartmentId(deptid);
	}

	public Long getFeeTotalByDepartment(int deptId) {
		return studentRepository.getFeeTotalByDepartment(deptId);
	}

	// To get all students in specific year by sorting students name in ascending
	// order.
	public List<Student> getStudentByYear(int year) {
		List<Student> student = studentRepository.findStudentByYear(year, Sort.by(Sort.Direction.ASC, "name"));
		return student;
	}

	public List<Student> getStudents() {
		return studentRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}

	public void save(MultipartFile file) {
		try {
			StudentExcel studentExcel = new StudentExcel();
			List<Student> students = studentExcel.excelToStudents(file.getInputStream());
			studentExcelRepository.saveAll(students);
		} catch (Exception e) {
			System.out.println("fail to store excel data: " + e.getMessage());
			throw new RuntimeException("fail to store excel data: " + e.getMessage());
		}
	}

}
