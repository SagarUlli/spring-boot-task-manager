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

import com.example.taskmanager.dto.TaskRequest;
import com.example.taskmanager.enums.TaskPriority;
import com.example.taskmanager.model.Project;
import com.example.taskmanager.model.User;
import com.example.taskmanager.repository.ProjectRepository;
import com.example.taskmanager.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@SpringBootTest
@AutoConfigureMockMvc
@Data
class TaskControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ProjectRepository projectRepository; // You'll need your repository to save a dummy project

	private Long savedProjectId;

	@Autowired
	private UserRepository userRepository; // You'll need your repository to save a dummy user

	private Long savedUserId;

	@BeforeEach
	void setUp() {
		// Create a real user so the project has a valid parent
		User user = new User();
		user.setName("Test User");
		user.setEmail("test@example.com");
		savedUserId = userRepository.save(user).getId();
		// Create a real project so the Task has a valid parent
		Project project = new Project();
		project.setName("Test Project");
		project.setDescription("Description");
		project.setOwner(user);
		savedProjectId = projectRepository.save(project).getId();
	}

	@Test
	void shouldCreateTask() throws Exception {

		TaskRequest request = new TaskRequest();
		request.setTitle("Integration Task");
		request.setDescription("Description");
		request.setPriority(TaskPriority.HIGH);

		mockMvc.perform(post("/projects/{projectId}/tasks", savedProjectId).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").exists()).andExpect(jsonPath("$.title").value("Integration Task"));
	}

	@Test
	void shouldReturnNotFoundWhenProjectDoesNotExist() throws Exception {

		TaskRequest request = new TaskRequest();
		request.setTitle("Invalid Task");
		request.setDescription("Description");
		request.setPriority(TaskPriority.MEDIUM);

		mockMvc.perform(post("/projects/{projectId}/tasks", 999L).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isNotFound());
	}

	@Test
	void shouldFailWhenTitleIsBlank() throws Exception {

		TaskRequest request = new TaskRequest();
		request.setTitle("");
		request.setDescription("Description");
		request.setPriority(TaskPriority.LOW);

		mockMvc.perform(post("/projects/{projectId}/tasks", savedProjectId).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isBadRequest());
	}
}