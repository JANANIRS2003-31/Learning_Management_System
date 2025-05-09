package com.cts.lms.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
	@Id
	private int courseId;
	private String title;
	private String description;
	private String syllabus;
	private int instructorId;
	private String prerequistes;
}