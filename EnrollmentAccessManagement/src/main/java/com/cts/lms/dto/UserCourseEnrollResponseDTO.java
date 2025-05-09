package com.cts.lms.dto;

import com.cts.lms.model.Enrollment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCourseEnrollResponseDTO {
	private User user;
	private Course course;
	private Enrollment enroll;
}