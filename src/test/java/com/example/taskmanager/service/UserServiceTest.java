package com.example.taskmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.taskmanager.model.User;
import com.example.taskmanager.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;

	@Test
	void shouldCreateUser() {
		User user = new User();
		user.setId(1L);
		user.setName("JOHN");
		user.setEmail("example@gmail.com");

		when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

		User saved = userService.createUser(user);

		assertNotNull(saved);
		assertEquals("JOHN", saved.getName());
		verify(userRepository).save(user);
	}

}
