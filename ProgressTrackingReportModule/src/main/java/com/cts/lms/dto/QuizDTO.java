
package com.cts.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDTO {
	public QuizDTO(int quizId2, String string, int i, int totalMarks2) {
		// TODO Auto-generated constructor stub
	}
	private int quizId;
	private int courseId;
	private String title;
	private int totalMarks;
}
