package com.cts.lms.service;

import java.util.List;

import com.cts.lms.dto.Course;
import com.cts.lms.dto.User;
import com.cts.lms.dto.UserCourseEnrollResponseDTO;
import com.cts.lms.model.Enrollment;

public interface EnrollmentService {
	public abstract String saveEnrollment(Enrollment enrollment);

	public abstract Enrollment updateEnrollment(Enrollment enrollment);

	public abstract String cancelEnrollment(int enrollmentId);

	public abstract List<Enrollment> getAllEnrollments();

	public abstract List<Enrollment> getEnrollmentsByUser(int userId);

	public abstract List<User> getUsersByCourseId(int courseId);

	public abstract UserCourseEnrollResponseDTO getEnrollment(int enrollmentId);

	public abstract List<Course> getCoursesByUserId(int userId);
}