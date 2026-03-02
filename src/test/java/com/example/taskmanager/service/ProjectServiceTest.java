package com.example.taskmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.taskmanager.model.Project;
import com.example.taskmanager.model.User;
import com.example.taskmanager.repository.ProjectRepository;
import com.example.taskmanager.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

	@Mock
	private ProjectRepository projectRepository;

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private ProjectService projectService;

	@Test
	void shouldCreateProject() {
		User user = new User();
		user.setId(1L);

		Project project = new Project();
		project.setName("Test Project");

		when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		when(projectRepository.save(any(Project.class))).thenAnswer(invocation -> invocation.getArgument(0));

		Project saved = projectService.createProject(1L, project);

		assertNotNull(saved);
		assertEquals(user, saved.getOwner());
		verify(projectRepository).save(project);
	}

	@Test
	void shouldThrowExceptionWhenUserNotFound() {
		when(userRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(RuntimeException.class, () -> projectService.createProject(1L, new Project()));
	}

}
