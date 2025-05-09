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
	void saveCourseTest() {
		Course course = new Course(1, "Data Science", "Intro to AI & ML", "Statistics, Python, ML Basics", 128,
				"Basic programming knowledge");
		Mockito.when(repository.save(course)).thenReturn(course);

		String response = service.saveCourse(course);
		assertEquals("The course is created successfully!!!", response);
	}

	@Test
	void updateCourseTest() {
		Course course = new Course(1, "Updated Data Science", "Advanced AI & ML", "Deep Learning, NLP", 128,
				"Python basics");
		Mockito.when(repository.save(course)).thenReturn(course);

		Course updatedCourse = service.updataCourse(course);
		assertEquals(course, updatedCourse);
	}

	@Test
	void deleteCourseTest() throws CourseNotFound {
		int courseId = 2;
		Mockito.when(repository.findById(courseId)).thenReturn(Optional.of(new Course(courseId, "Cybersecurity",
				"Learn Ethical Hacking", "Network Security, Pen Testing", 145, "Networking Basics")));
		Mockito.doNothing().when(repository).deleteById(courseId);

		String response = service.deleteCourse(courseId);
		assertEquals("Course deleted Successfully!!!", response);
	}

	@Test
	void getCourseByIdTest() throws CourseNotFound {
		int courseId = 1;
		Course course = new Course(courseId, "Cloud Computing", "Learn AWS & GCP", "AWS, GCP, Azure", 135,
				"Networking basics");
		Mockito.when(repository.findById(courseId)).thenReturn(Optional.of(course));

		Course foundCourse = service.getCourse(courseId);
		assertEquals(course, foundCourse);
	}

	@Test
	void getAllCoursesTest() {
		List<Course> courses = Arrays.asList(
				new Course(1, "Data Science", "Intro to AI & ML", "Statistics, Python, ML Basics", 128,
						"Basic programming knowledge"),
				new Course(2, "Cloud Computing", "AWS & GCP Training", "AWS, GCP, Azure", 135, "Networking basics"));

		Mockito.when(repository.findAll()).thenReturn(courses);

		List<Course> allCourses = service.getAllCourses();
		assertEquals(courses, allCourses);
	}

	@Test
	void checkCourseExistTest() throws CourseNotFound {
		int courseId = 1;
		Mockito.when(repository.existsById(courseId)).thenReturn(true);

		Boolean exists = service.checkCourseExist(courseId);
		assertEquals(true, exists);
	}
}
