package com.cts.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.lms.model.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
	public abstract List<Enrollment> findByUserId(int userId);
	public abstract List<Enrollment> findByCourseId(int courseId);

}