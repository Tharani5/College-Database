package com.college.student;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.college.department.Department;

public class StudentExcel {

	// MIME type of xlsx format.
	public String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	String SHEET = "Sheet1";

	// Method to check uploaded format is excel or any other format.
	public boolean hasExcelFormat(MultipartFile file) {
		if (!TYPE.equals(file.getContentType())) {
			return false;
		}
		return true;
	}

	// Getting data from excel and stored in a list to save in database.
	public List<Student> excelToStudents(InputStream inputStream) {
		try {
			Workbook workbook = new XSSFWorkbook(inputStream);

			// Get specific sheet in workbook.
			Sheet sheet = workbook.getSheet(SHEET);

			// To iterate each row in sheet.
			Iterator<Row> rows = sheet.iterator();

			// Create a student list to store all the values from excel.
			List<Student> students = new ArrayList<Student>();
			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();

				// Skip the header of sheet.
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				// Iterate to every cells in a row.
				Iterator<Cell> cellsInRow = currentRow.iterator();

				Student student = new Student();

				int cellIndex = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();

					// Get values from specific cells.
					switch (cellIndex) {
					case 0:
						// student.setId((int) currentCell.getNumericCellValue());
						break;

					case 1:
						student.setCourse(currentCell.getStringCellValue());
						break;

					case 2:
						student.setFee((int) currentCell.getNumericCellValue());
						break;

					case 3:
						student.setName(currentCell.getStringCellValue());
						break;

					case 4:
						student.setYear((int) currentCell.getNumericCellValue());
						break;

					case 5:
						student.setDepartment(new Department((int) currentCell.getNumericCellValue(), "", ""));
						break;

					default:
						break;
					}

					// Moves to next cell.
					cellIndex++;
				}

				students.add(student);
			}

			workbook.close();

			return students;
		} catch (Exception e) {
			System.out.println("fail to parse Excel file: " + e.getMessage());
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}
	}
}
