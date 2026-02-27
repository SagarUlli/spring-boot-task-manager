package com.example.taskmanager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanager.dto.ProjectRequest;
import com.example.taskmanager.dto.ProjectResponse;
import com.example.taskmanager.model.Project;
import com.example.taskmanager.service.ProjectService;

import jakarta.validation.Valid;

@RestController
public class ProjectController {

	private final ProjectService service;

	public ProjectController(ProjectService service) {
		this.service = service;
	}

	@PostMapping("/users/{userId}/projects")
	public ProjectResponse createProject(@PathVariable Long userId, @Valid @RequestBody ProjectRequest projectRequest) {
		Project project = new Project();
		project.setName(projectRequest.getName());
		project.setDescription(projectRequest.getDescription());
		Project saved = service.createProject(userId, project);
		return new ProjectResponse(saved.getId(), saved.getName(), saved.getDescription(), saved.getOwner().getId());
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