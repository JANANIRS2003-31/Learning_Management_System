package com.cts.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.lms.dto.CourseDTO;
import com.cts.lms.dto.CourseProgressDTO;
import com.cts.lms.dto.QuizDTO;
import com.cts.lms.dto.QuizProgressDTO;
import com.cts.lms.dto.QuizSubmissionDTO;
import com.cts.lms.dto.UserDTO;
import com.cts.lms.feignclient.EnrollmentClient;
import com.cts.lms.feignclient.QuizClient;
@Service
public class ProgressTrackingServiceImpl implements ProgressTrackingService {
	@Autowired
	private
	EnrollmentClient enrollmentClient;
	
	@Autowired
	private
	QuizClient quizClient;
	@Override
	public List<CourseDTO> getCourseByUserId(int userId) {
		return getEnrollmentClient().getCoursesByUserId(userId);
	}
	@Override
	public UserDTO getProgressByUserId(int userId) {
		List<CourseDTO> courses = getEnrollmentClient().getCoursesByUserId(userId);
		List<CourseProgressDTO> courseProgressDTOs = new ArrayList<>();
		for(CourseDTO course:courses) {
			int courseId = course.getCourseId();
			List<QuizDTO> quizzes =  getQuizClient().getQuizByCourseId(courseId);
			List<QuizProgressDTO> quizProgressDTOs = new ArrayList<>();
			for(QuizDTO quiz:quizzes) {
				QuizSubmissionDTO submissionDTO = getQuizClient().getQuizSubmissionByUserId(userId,quiz.getQuizId());
				int totalMarks = quiz.getTotalMarks();
				int score = submissionDTO!=null?submissionDTO.getScore():0;
                double progressPercentage = (double) score / totalMarks * 100;
                quizProgressDTOs.add(new QuizProgressDTO(quiz.getQuizId(),totalMarks,score,progressPercentage));				
			}
			courseProgressDTOs.add(new CourseProgressDTO(courseId,course.getTitle(),course.getDescription(), quizProgressDTOs));
		}
		return new UserDTO(userId,courseProgressDTOs);
	}
	public EnrollmentClient getEnrollmentClient() {
		return enrollmentClient;
	}
	public void setEnrollmentClient(EnrollmentClient enrollmentClient) {
		this.enrollmentClient = enrollmentClient;
	}
	public QuizClient getQuizClient() {
		return quizClient;
	}
	public void setQuizClient(QuizClient quizClient) {
		this.quizClient = quizClient;
	}

}
