package com.cts.lms.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "enrollments_info")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Enrollment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int enrollmentId;
	private int userId;
	private int courseId;
	private LocalDateTime enrollmentDate = LocalDateTime.now();
	
	public Enrollment(int enrollmentId, int userId, int courseId) {
		super();
		this.enrollmentId = enrollmentId;
		this.userId = userId;
		this.courseId = courseId;
	}

}