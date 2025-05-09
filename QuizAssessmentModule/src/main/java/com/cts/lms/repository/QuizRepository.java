package com.cts.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.lms.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
	
	List<Quiz> findByCourseId(int courseId);

}
