package com.example.taskmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.taskmanager.enums.TaskStatus;
import com.example.taskmanager.exception.ResourceNotFoundException;
import com.example.taskmanager.model.Project;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.ProjectRepository;
import com.example.taskmanager.repository.TaskRepository;

@Service
public class TaskService {

	private final TaskRepository taskRepository;
	private final ProjectRepository projectRepository;

	public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository) {
		this.taskRepository = taskRepository;
		this.projectRepository = projectRepository;
	}

	public Task createTask(Long projectId, Task task) {

		Project project = projectRepository.findById(projectId)
				.orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + projectId));

		if (task.getStatus() == null) {
			task.setStatus(TaskStatus.TODO);
		}

		task.setProject(project);
		return taskRepository.save(task);
	}

	public List<Task> getTasksByProject(Long projectId) {
		if (!taskRepository.existsById(projectId)) {
			throw new ResourceNotFoundException("Task not found with id " + projectId);
		}
		return taskRepository.findByProjectId(projectId);
	}

	public List<Task> getTasksByStatus(TaskStatus status) {
		return taskRepository.findByStatus(status);
	}

	public Task updateTaskStatus(Long taskId, TaskStatus status) {
		Task task = taskRepository.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + taskId));

		task.setStatus(status);
		return taskRepository.save(task);
	}

	public void deleteTask(Long taskId) {
		if (!taskRepository.existsById(taskId)) {
			throw new ResourceNotFoundException("Task not found with id " + taskId);
		}
		taskRepository.deleteById(taskId);
	}
}