package com.example.taskmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.taskmanager.enums.TaskPriority;
import com.example.taskmanager.enums.TaskStatus;
import com.example.taskmanager.model.Project;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.ProjectRepository;
import com.example.taskmanager.repository.TaskRepository;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

	@Mock
	private TaskRepository taskRepository;

	@Mock
	private ProjectRepository projectRepository;

	@InjectMocks
	private TaskService taskService;

	@Test
	void shouldCreateTaskWithDefaultStatus() {

		Project project = new Project();
		project.setId(1L);

		Task task = new Task();
		task.setTitle("Test Task");
		task.setPriority(TaskPriority.HIGH);

		when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
		when(taskRepository.save(any(Task.class))).thenAnswer(i -> i.getArgument(0));

		Task saved = taskService.createTask(1L, task);

		assertEquals(TaskStatus.TODO, saved.getStatus());
		assertEquals(project, saved.getProject());
	}
}