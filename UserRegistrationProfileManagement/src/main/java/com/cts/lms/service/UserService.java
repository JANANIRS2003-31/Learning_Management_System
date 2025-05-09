package com.cts.lms.service;

import java.util.List;
import com.cts.lms.exceptions.UserNotFound;
import com.cts.lms.model.User;

public interface UserService {

	public abstract String saveUser(User user);

	public abstract User updateUser(User user);

	public abstract User getUserByEmail(String email) throws UserNotFound;

	public abstract List<User> getAllUser();

	public abstract String deleteUser(int userId) throws UserNotFound;

	public abstract User getUserById(int userId) throws UserNotFound;

	public abstract Boolean checkUserExist(int userId) throws UserNotFound;

}
