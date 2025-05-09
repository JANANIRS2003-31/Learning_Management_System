package com.cts.lms.dto;

import com.cts.lms.model.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private String name;
	private String email;
	private String password;
	private UserRole role;
}