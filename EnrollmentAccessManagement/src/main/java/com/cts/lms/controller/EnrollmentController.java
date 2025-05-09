package com.cts.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.lms.dto.Course;
import com.cts.lms.dto.User;
import com.cts.lms.dto.UserCourseEnrollResponseDTO;
import com.cts.lms.model.Enrollment;
import com.cts.lms.service.EnrollmentService;

@RestController
@RequestMapping("/enroll")
public class EnrollmentController {
	@Autowired
	EnrollmentService service;

	@PostMapping("/save")
	public String saveEnrollment(@RequestBody Enrollment enrollment) {
		return service.saveEnrollment(enrollment);
	}

	@PutMapping("/update")
	public Enrollment updateEnrollment(@RequestBody Enrollment enrollment) {
		return service.updateEnrollment(enrollment);
	}

	@GetMapping("/fetchById/{eid}")
	public UserCourseEnrollResponseDTO getEnrollment(@PathVariable("eid") int enrollmentId) {
		return service.getEnrollment(enrollmentId);
	}

	@GetMapping("/fetchByUser/{uid}")
	public List<Enrollment> getEnrollmentsByUser(@PathVariable("uid") int userId) {
		return service.getEnrollmentsByUser(userId);
	}

	@GetMapping("/fetchUsersByCourseId/{cid}")
	public List<User> getUsersByCourseId(@PathVariable("cid") int courseId) {
		return service.getUsersByCourseId(courseId);
	}
	
	@GetMapping("/fetchCoursesByUserId/{uid}")
	public List<Course> getCoursesByUserId(@PathVariable("uid") int userId) {
		return service.getCoursesByUserId(userId);
	}

	@GetMapping("/fetchAll")
	public List<Enrollment> getAllEnrollments() {
		return service.getAllEnrollments();
	}

	@DeleteMapping("/cancel/{eid}")
	public String cancelEnrollment(@PathVariable("eid") int enrollmentId) {
		return service.cancelEnrollment(enrollmentId);
	}

}