package com.cts.lms.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.lms.dto.Course;

@FeignClient(name = "COURSEMANAGEMENT",path="/course")
public interface CourseClient {
	@GetMapping("/checkCourseExist/{cid}")
	public Boolean checkCourseExist(@PathVariable("cid") int courseId );
	
	@GetMapping("/fetchById/{cid}")
	public Course getCourse(@PathVariable("cid") int courseId );
}