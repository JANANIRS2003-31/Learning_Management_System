package com.cts.lms.service;

import java.util.List;

import com.cts.lms.exceptions.CourseNotFound;
import com.cts.lms.model.Course;
public interface CourseService {

	public abstract String saveCourse(Course course);

	public abstract Course updataCourse(Course course);

	public abstract Course getCourse(int courseId) throws CourseNotFound;

	public abstract List<Course> getAllCourses();

	public abstract String deleteCourse(int courseId) throws CourseNotFound ;

	public abstract Boolean checkCourseExist(int courseId) throws CourseNotFound;

}