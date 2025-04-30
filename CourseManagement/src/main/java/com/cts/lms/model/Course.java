package com.cts.lms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "course_info")
@AllArgsConstructor
@NoArgsConstructor
public class Course {
	@Id
	@GeneratedValue
	@Column(name="cid")
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

}
