
package com.cts.lms.service;

import java.util.List;

import com.cts.lms.dto.CourseDTO;
import com.cts.lms.dto.UserDTO;

public interface ProgressTrackingService {

	public abstract List<CourseDTO> getCourseByUserId(int userId);

	public abstract UserDTO getProgressByUserId(int userId);

}
