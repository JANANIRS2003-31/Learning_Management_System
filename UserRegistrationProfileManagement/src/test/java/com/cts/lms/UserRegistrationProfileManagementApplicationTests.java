package com.cts.lms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import com.cts.lms.exceptions.UserNotFound;
import com.cts.lms.model.User;
import com.cts.lms.repository.UserRepository;
import com.cts.lms.service.UserServiceImpl;

@SpringBootTest
class UserManagementApplicationTests {

	@Mock
	UserRepository repository;

	@InjectMocks
	UserServiceImpl service;

	@Test
	void createUserTest() {
		User user = new User(1, "John Doe", "john.doe@example.com", "securePassword123", "STUDENT");
		Mockito.when(repository.save(user)).thenReturn(user);

		String response = service.saveUser(user);
		assertEquals("The User details is created successfully!!!", response);
	}

	@Test
	void updateUserTest() {
		User user = new User(1, "John Doe", "john.doe@example.com", "securePassword123", "INSTRUCTOR");
		user.setUserId(1);
		Mockito.when(repository.save(user)).thenReturn(user);

		User updatedUser = service.updateUser(user);
		assertEquals(user, updatedUser);
	}

	@Test
	void deleteUserTest() throws UserNotFound {
		int userId = 2;
		User user = new User(userId, "Jane Smith", "jane.smith@example.com", "strongPass456", "INSTRUCTOR");

		Mockito.when(repository.findById(userId)).thenReturn(Optional.of(user)); // ✅ Mock findById
		Mockito.doNothing().when(repository).deleteById(userId); // ✅ Mock delete

		String response = service.deleteUser(userId);
		assertEquals("User Details deleted Successfully!!!", response);
	}

	@Test
	void getUserByEmailTest() throws UserNotFound {
		String email = "john.doe@example.com";
		User user = new User(1, "John Doe", email, "securePassword123", "STUDENT");
		Mockito.when(repository.findByEmail(email)).thenReturn(Optional.of(user));

		User foundUser = service.getUserByEmail(email);
		assertEquals(user, foundUser);
	}

	@Test
	void getAllUsersTest() {
		List<User> users = Arrays.asList(
				new User(1, "John Doe", "john.doe@example.com", "securePassword123", "STUDENT"),
				new User(2, "Jane Smith", "jane.smith@example.com", "strongPass456", "INSTRUCTOR"));

		Mockito.when(repository.findAll()).thenReturn(users);

		List<User> allUsers = service.getAllUser();
		assertEquals(users, allUsers);
	}
}
