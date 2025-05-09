package com.cts.lms.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.lms.dto.User;

@FeignClient(name = "USERREGISTRATIONPROFILEMANAGEMENT", path = "/user")
public interface UserClient {
	@GetMapping("/checkUserExist/{uid}")
	public Boolean checkUserExist(@PathVariable("uid") int courseId);

	@GetMapping("/fetchById/{uid}")
	public User getById(@PathVariable("uid") int userId);
}