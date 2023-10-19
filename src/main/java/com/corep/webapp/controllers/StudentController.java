package com.corep.webapp.controllers;

import com.corep.webapp.enums.Status;
import com.corep.webapp.dto.ApiResponse;
import com.corep.webapp.exceptions.StudentNotFoundException;
import com.corep.webapp.models.Student;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class StudentController {

	private List<Student> allStudents;

	@PostConstruct
	public void loadData() {
		allStudents = new ArrayList<>();
		allStudents.add(new Student("Lauretta", "Okolie"));
		allStudents.add(new Student("Jamiu", "Muritala"));
		allStudents.add(new Student("Gabriel", "Solomon"));
		allStudents.add(new Student("Michael", "Akande"));
	}

	@GetMapping("/students")
	public ApiResponse<List<Student>> getAllStudents() {
		return new ApiResponse<>("students data fetched successfully", Status.SUCCESS, allStudents);
	}

	@GetMapping("/students/{id}")
	public ApiResponse<Student> getStudent(@PathVariable Integer id) {
		if(id > allStudents.size() || id < 0){
			throw new StudentNotFoundException("student with id not found "+ id);
		}
		return new ApiResponse<>("student data fetched successfully", Status.SUCCESS,allStudents.get(id));
	}


	@PostMapping("/students")
	public ApiResponse<Student> addStudent(@Valid @RequestBody Student student){
		allStudents.add(student);
		return new ApiResponse<>("student added successfully", Status.SUCCESS, student);
	}

	@DeleteMapping("/students/{id}")
	public ApiResponse<Student> deleteStudent(@PathVariable Integer id){
		Student studentToRemove = allStudents.get(id);
		allStudents.remove(studentToRemove);
		return new ApiResponse<>("student removed successfully", Status.SUCCESS, null);
	}


	@PutMapping("/students/{id}")
	public ApiResponse<Student> updateStudent(@Valid @RequestBody Student student, @PathVariable Integer id){
		Student updatedStudent = allStudents.get(id);
		updatedStudent.setFirstName(student.getFirstName());
		updatedStudent.setLastName(student.getLastName());
		return new ApiResponse<>("student data updated successfully", Status.SUCCESS,allStudents.get(id));
	}
}