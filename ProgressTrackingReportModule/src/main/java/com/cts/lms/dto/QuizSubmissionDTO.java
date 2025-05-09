
package com.cts.lms.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class QuizSubmissionDTO {
	
	private int submissionId;
	private int quizId;
	private int userId;
	private List<String> responses;
	private int score;
	private boolean passed;
	
	public QuizSubmissionDTO(int i, int j, int k, int l, Object object) {
		
	}
	
}
