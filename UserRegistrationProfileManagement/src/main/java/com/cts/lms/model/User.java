package com.cts.lms.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name = "usermanagement")
@RequiredArgsConstructor
public class User {
	@Id
	private int userId;
	@NotBlank(message = "Name cannot be empty")
	@Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
	private String name;
	@Column(unique = true, nullable = false)
	@Email(message = "Invalid email format")
	@NotBlank(message = "Email cannot be empty")
	private String email;
	@NotBlank(message = "Password cannot be empty")
	@Size(min = 8, message = "Password must be at least 8 characters long")
	private String password;
	@Enumerated(EnumType.STRING)
	private UserRole role;

	public User(int userId, String name, String email, String password, String string) {
	}

}
