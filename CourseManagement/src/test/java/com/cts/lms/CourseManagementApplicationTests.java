package com.cts.lms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.lms.exceptions.CourseNotFound;
import com.cts.lms.model.Course;
import com.cts.lms.repository.CourseRepository;
import com.cts.lms.service.CourseServiceImpl;

@SpringBootTest
class CourseManagementApplicationTests {
	
	@Mock
	CourseRepository repository;
	
	@InjectMocks
	CourseServiceImpl service;
	
	@Test
	void createCourseTest() {
		Course course = new Course(1,"Java Basics","An introductory course on Java programming.", "Introduction, OOP Concepts, Basics of Java, Control Statements, Arrays, Classes and Objects.",123,"Basic knowledge of programming concepts.");
		Mockito.when(repository.save(course)).thenReturn(course);
		
		String response = service.saveCourse(course);
		assertEquals("The course is created successfully!!!", response);
	}
	
	 @Test
	    void updateCourseTest() {
	        Course course = new Course(1,"Java Basics", "Intro to Java", "OOP, Arrays", 123, "None");
	        course.setCourseId(1);
	        Mockito.when(repository.save(course)).thenReturn(course);
	        Course updatedCourse = service.updataCourse(course);
	        assertEquals(course, updatedCourse);
	    }

	    @Test
	    void deleteCourseTest() {
	        int courseId = 1;
	        Mockito.doNothing().when(repository).deleteById(courseId);
	        String response = service.deleteCourse(courseId);
	        assertEquals("Course deleted Successfully!!!", response);
	    }

	    @Test
	    void getCourseByIdTest() throws CourseNotFound{
	        int courseId = 1;
	        Course course = new Course(1, "Java Basics", "Intro to Java", "OOP, Arrays", 123, "None");
	        course.setCourseId(courseId);
	        Mockito.when(repository.findById(courseId)).thenReturn(Optional.of(course));

	        Course foundCourse = service.getCourse(courseId);
	        assertEquals(course, foundCourse);
	    }

	    @Test
	    void getAllCoursesTest() {
	        List<Course> courses = Arrays.asList(
	                new Course(1, "Java Basics", "Intro to Java", "OOP, Arrays", 123, "None"),
	                new Course(2, "Python Basics", "Intro to Python", "Data Types, Loops", 124, "None"));

	        Mockito.when(repository.findAll()).thenReturn(courses);

	        List<Course> allCourses = service.getAllCourses();
	        assertEquals(courses, allCourses);
	    }
}
