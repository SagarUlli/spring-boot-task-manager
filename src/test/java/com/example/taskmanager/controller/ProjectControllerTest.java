package com.example.taskmanager.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.taskmanager.dto.ProjectRequest;
import com.example.taskmanager.model.User;
import com.example.taskmanager.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@SpringBootTest
@AutoConfigureMockMvc
@Data
class ProjectControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private UserRepository userRepository;// You'll need your repository to save a dummy user

	private Long savedUserId;

	@BeforeEach
	void setUp() {
		// Create a real user so the project has a valid parent
		User user = new User();
		user.setName("Test User");
		user.setEmail("test@example.com");
		savedUserId = userRepository.save(user).getId();
	}

	@Test
	void shouldCreateProject() throws Exception {

		ProjectRequest request = new ProjectRequest();
		request.setName("Sprint Project");
		request.setDescription("Description");

		mockMvc.perform(post("/users/{userId}/projects", savedUserId).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").exists()).andExpect(jsonPath("$.name").value("Sprint Project"));
	}

	@Test
	void shouldReturnNotFoundWhenUserDoesNotExist() throws Exception {

		ProjectRequest request = new ProjectRequest();
		request.setName("Invalid Project");
		request.setDescription("Description");

		mockMvc.perform(post("/users/{userId}/projects", 999L).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isNotFound());
	}
}