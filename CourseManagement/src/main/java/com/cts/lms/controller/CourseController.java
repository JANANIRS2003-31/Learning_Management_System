package com.cts.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.lms.exceptions.CourseNotFound;
import com.cts.lms.model.Course;
import com.cts.lms.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {

	@Autowired
	CourseService service;

	@PostMapping("/create")
	public String saveCourse(@RequestBody @Validated Course course) {
		return service.saveCourse(course);
	}

	@PutMapping("/update")
	public Course updataCourse(@RequestBody @Validated Course course) {
		return service.updataCourse(course);
	}

	@GetMapping("/getbyId/{did}")
	public Course getCourse(@PathVariable("did") int courseId) throws CourseNotFound {
		return service.getCourse(courseId);
	}

	@GetMapping("/getall")
	public List<Course> getAllCourses() {
		return service.getAllCourses();
	}

	@DeleteMapping("/delete/{uid}")
	public String deleteCourse(@PathVariable("uid") int userId) {
		return service.deleteCourse(userId);
	}

}