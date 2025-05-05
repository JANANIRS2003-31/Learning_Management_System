package com.cts.lms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name = "course_info")
@RequiredArgsConstructor
public class Course {
	@Id
	private int courseId;
	@NotBlank(message="Course Title should not be null or blank")
	@NotNull
	@NotEmpty
	private String title;
	@NotBlank(message="Course Description cannot be Empty")
	@NotNull
	@NotEmpty
	private String description;
	@NotBlank(message="Course syllabus cannot be Empty")
	@NotNull
	@NotEmpty
	private String syllabus;
//	@NotBlank(message="Instructor ID should be filled, it cannot be empty")
//	@NotNull
//	@NotEmpty
	private int instructorId;
	@NotBlank(message="Prerequisites should be filled, it cannot be empty")
	@NotNull
	@NotEmpty
	private String prerequisites;
	public Course(int courseId, String title, String description, String syllabus, int instructorId, String prerequisites) {
		super();
		this.courseId=courseId;
		this.title = title;
		this.description = description;
		this.syllabus = syllabus;
		this.instructorId = instructorId;
		this.prerequisites = prerequisites;
	}
	
	

}
