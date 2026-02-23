package com.example.taskmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.taskmanager.model.User;
import com.example.taskmanager.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository repository;

	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	public User createUser(User user) {
		return repository.save(user);
	}

	public List<User> getAllUsers() {
		return repository.findAll();
	}

	public Optional<User> getUserById(Long id) {
		return repository.findById(id);
	}

	public User updateUser(Long id, User updatedUser) {
		return repository.findById(id).map(user -> {
			user.setName(updatedUser.getName());
			user.setEmail(updatedUser.getEmail());
			return repository.save(user);
		}).orElseThrow(() -> new RuntimeException("User not found with id " + id));
	}

	public void deleteUser(Long id) {
		repository.deleteById(id);
	}
}