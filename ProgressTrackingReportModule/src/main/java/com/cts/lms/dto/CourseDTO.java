package com.cts.lms.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
	private int courseId;
	private String title;
	private String description;
	private String syllabus;
	private int instructorId;
	private String prerequisites;
}