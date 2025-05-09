package com.cts.lms.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseProgressDTO {
	private int courseId;
	private String title;
	private String description;
	private List<QuizProgressDTO> quizzes;
}