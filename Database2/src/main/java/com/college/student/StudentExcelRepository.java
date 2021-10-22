package com.college.student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentExcelRepository extends JpaRepository<Student, Integer> {

}
