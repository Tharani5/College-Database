package com.college.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class StudentController {

	@Autowired
	StudentService studentService;

	// Method to delete student using student id.
	@DeleteMapping("/deleteStudent/{id}")
	public void deleteStudent(@PathVariable("id") int id) {
		studentService.deleteStudent(id);
	}

	// Method to add newly or update on existing student.
	@PostMapping("/addStudent")
	public Student addStudent(@RequestBody Student student) {
		return studentService.addStudent(student);
	}

	@PutMapping("/updateStudent")
	public Student updateStudent(@RequestBody Student student) {
		return studentService.updateStudent(student);
	}

	// Getting list of students by specific department using department id.
	@GetMapping("/department/{deptid}/student")
	public List<Student> getStudentByDepartment(@PathVariable("deptid") int deptid) {
		return studentService.getStudentByDepartment(deptid);
	}

	// Total fee of students in specific department id given by this method.
	@GetMapping("/department/{deptId}/feeTotal")
	public Long getFeeTotalByDepartment(@PathVariable("deptId") int deptId) {
		return studentService.getFeeTotalByDepartment(deptId);
	}

	// Get list of students by year.
	@GetMapping("/studentsByYear/{year}")
	public List<Student> getStudentByYear(@PathVariable("year") int year) {
		return studentService.getStudentByYear(year);
	}

	// Fetch all students in database.
	@GetMapping("/student")
	public List<Student> getStuedents() {
		return studentService.getStudents();
	}

	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";
		StudentExcel studentExcel = new StudentExcel();
		// Check that uploaded file is excel format or not.
		if (studentExcel.hasExcelFormat(file)) {
			try {
				studentService.save(file);
				message = "Uploaded the file successfully: " + file.getOriginalFilename();
				return new ResponseEntity<String>(message, HttpStatus.OK);
			} catch (Exception e) {
				message = "Could not upload the file: " + e.getMessage();
				return new ResponseEntity<String>(message, HttpStatus.EXPECTATION_FAILED);
			}
		}
		message = "Does not match format... Upload excel file";
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

}
