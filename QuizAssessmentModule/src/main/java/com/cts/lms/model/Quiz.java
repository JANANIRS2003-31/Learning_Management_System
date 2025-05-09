package com.cts.lms.model;

import java.util.List;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "quiz_info")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Quiz {
	
	@Id
	@GeneratedValue
	private int quizId;
	private int courseId;
	private String title;
	private int totalMarks;
	@ElementCollection
	private List<String> questions;
	@ElementCollection
	private List<String> correctAnswers;
}