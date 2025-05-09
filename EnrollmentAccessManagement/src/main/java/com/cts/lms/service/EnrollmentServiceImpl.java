package com.cts.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.lms.dto.Course;
import com.cts.lms.dto.User;
import com.cts.lms.dto.UserCourseEnrollResponseDTO;
import com.cts.lms.feignclient.CourseClient;
import com.cts.lms.feignclient.UserClient;
import com.cts.lms.model.Enrollment;
import com.cts.lms.repository.EnrollmentRepository;
@Service
public class EnrollmentServiceImpl implements EnrollmentService {
	@Autowired
	EnrollmentRepository repository;

	@Autowired
	UserClient userClient;

	@Autowired
	CourseClient courseClient;

	@Override
	public String saveEnrollment(Enrollment enrollment) {
		Boolean responseUser = userClient.checkUserExist(enrollment.getUserId());
		Boolean responseCourse = courseClient.checkCourseExist(enrollment.getCourseId());

		repository.save(enrollment);
		return "Enrollment Successfully Saved";
	}

	@Override
	public Enrollment updateEnrollment(Enrollment enrollment) {
		return repository.save(enrollment);
	}

	@Override
	public String cancelEnrollment(int enrollmentId) {

		repository.delete(repository.findById(enrollmentId).get());
		return "Enrollment Deleted";
	}

	@Override
	public List<Enrollment> getAllEnrollments() {
		return repository.findAll();
	}

	@Override
	public List<Enrollment> getEnrollmentsByUser(int userId) {
		return repository.findByUserId(userId);
	}

	@Override
	public List<User> getUsersByCourseId(int courseId) {
		List<Enrollment> list = repository.findByCourseId(courseId);
		List<User> users = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Enrollment enroll = list.get(i);
			users.add(userClient.getById(enroll.getUserId()));
		}
		return users;
	}

	@Override
	public List<Course> getCoursesByUserId(int userId) {
		List<Enrollment> list = repository.findByUserId(userId);
		List<Course> courses = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Enrollment enroll = list.get(i);
			courses.add(courseClient.getCourse(enroll.getCourseId()));
		}

		return courses;
	}

	@Override
	public UserCourseEnrollResponseDTO getEnrollment(int enrollmentId) {
		Enrollment enrollment = repository.findById(enrollmentId).get();
		int userId = enrollment.getUserId();
		int courseId = enrollment.getCourseId();
		User user = userClient.getById(userId);
		Course course = courseClient.getCourse(courseId);
		UserCourseEnrollResponseDTO responseDTO = new UserCourseEnrollResponseDTO(user, course, enrollment);
		return responseDTO;
	}

}