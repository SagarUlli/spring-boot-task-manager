package com.example.taskmanager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanager.model.Project;
import com.example.taskmanager.service.ProjectService;

@RestController
public class ProjectController {

	private final ProjectService service;

	public ProjectController(ProjectService service) {
		this.service = service;
	}

	@PostMapping("/users/{userId}/projects")
	public Project createProject(@PathVariable Long userId, @RequestBody Project project) {
		return service.createProject(userId, project);
	}

	@GetMapping("/projects")
	public List<Project> getAllProjects() {
		return service.getAllProjects();
	}

	@GetMapping("/users/{userId}/projects")
	public List<Project> getProjectsByUser(@PathVariable Long userId) {
		return service.getProjectsByUser(userId);
	}

	@DeleteMapping("/projects/{projectId}")
	public void deleteProject(@PathVariable Long projectId) {
		service.deleteProject(projectId);
	}
}