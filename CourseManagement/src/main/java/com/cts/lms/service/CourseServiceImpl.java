package com.cts.lms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.lms.exceptions.CourseNotFound;
import com.cts.lms.model.Course;
import com.cts.lms.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	CourseRepository repository;

	@Override
	public String saveCourse(Course course) {
		repository.save(course);
		return "The course is created successfully!!!";
	}

	@Override
	public Course updataCourse(Course course) {
		return repository.save(course);
	}

	@Override
	public Course getCourse(int courseId) throws CourseNotFound {
		Optional<Course> optional = repository.findById(courseId);
		if (optional.isPresent())
			return optional.get();
		else
			throw new CourseNotFound("Invalid Course ID!!!");
	}

	@Override
	public List<Course> getAllCourses() {
		return repository.findAll();
	}

	@Override
	public String deleteCourse(int courseId) {
		repository.deleteById(courseId);
		return "Course deleted Successfully!!!";
	}

}
