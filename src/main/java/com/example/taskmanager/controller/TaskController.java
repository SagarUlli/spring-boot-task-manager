package com.example.taskmanager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanager.enums.TaskStatus;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.service.TaskService;

import jakarta.validation.Valid;

@RestController
public class TaskController {

	private final TaskService service;

	public TaskController(TaskService service) {
		this.service = service;
	}

	@PostMapping("/projects/{projectId}/tasks")
	public Task createTask(@PathVariable Long projectId, @Valid @RequestBody Task task) {
		return service.createTask(projectId, task);
	}

	@GetMapping("/projects/{projectId}/tasks")
	public List<Task> getTasksByProject(@PathVariable Long projectId) {
		return service.getTasksByProject(projectId);
	}

	@GetMapping("/tasks/status/{status}")
	public List<Task> getTasksByStatus(@PathVariable TaskStatus status) {
		return service.getTasksByStatus(status);
	}

	@PatchMapping("/tasks/{taskId}/status")
	public Task updateStatus(@PathVariable Long taskId, @RequestParam TaskStatus status) {
		return service.updateTaskStatus(taskId, status);
	}

	@DeleteMapping("/tasks/{taskId}")
	public void deleteTask(@PathVariable Long taskId) {
		service.deleteTask(taskId);
	}
}