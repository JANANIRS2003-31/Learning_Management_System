package com.cts.lms.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cts.lms.exceptions.UserNotFound;
import com.cts.lms.model.User;
import com.cts.lms.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository repository;

	@Override
	public String saveUser(User user) {
		repository.save(user);
		return "The User details is created successfully!!!";
	}

	@Override
	public User updateUser(User user) {
		return repository.save(user);
	}

	@Override
	public User getUserByEmail(String email) throws UserNotFound {
		Optional<User> optional = repository.findByEmail(email);
		if (optional.isPresent())
			return optional.get();
		else
			throw new UserNotFound("Invalid User Email!!!");
	}

	@Override
	public List<User> getAllUser() {
		return repository.findAll();
	}

	@Override
	public String deleteUser(int userId) throws UserNotFound {
		Optional<User> optional = repository.findById(userId);
		if (optional.isPresent()) {
			repository.deleteById(userId);
			return "User Details deleted Successfully!!!";
		} else {
			throw new UserNotFound("Invalid Course ID!!!");
		}
	}

	@Override
	public User getUserById(int userId) throws UserNotFound {

		Optional<User> optional = repository.findById(userId);
		if (optional.isPresent())
			return optional.get();
		else
			throw new UserNotFound("User Id is Invalid...");

	}

	@Override
	public Boolean checkUserExist(int userId) throws UserNotFound {
		boolean response = repository.existsById(userId);
		if (response)
			return response;
		else
			throw new UserNotFound("User Id is Invalid...");
	}

}
