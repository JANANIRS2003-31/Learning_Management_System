package com.cts.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.lms.model.QuizSubmission;

public interface QuizSubmissionRepository extends JpaRepository<QuizSubmission, Integer> {

	List<QuizSubmission> findByUserId(int userId);

	QuizSubmission findByUserIdAndQuizId(int userId, int quizId);
}