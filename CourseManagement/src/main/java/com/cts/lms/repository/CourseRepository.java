package com.cts.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.lms.model.Course;
public interface CourseRepository extends JpaRepository<Course, Integer>{
	
}
