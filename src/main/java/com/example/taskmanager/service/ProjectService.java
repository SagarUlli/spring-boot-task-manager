package com.example.taskmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.taskmanager.model.Project;
import com.example.taskmanager.model.User;
import com.example.taskmanager.repository.ProjectRepository;
import com.example.taskmanager.repository.UserRepository;

@Service
public class ProjectService {

	private final ProjectRepository projectRepository;
	private final UserRepository userRepository;

	public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
		this.projectRepository = projectRepository;
		this.userRepository = userRepository;
	}

	public Project createProject(Long userId, Project project) {

		User owner = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found with id " + userId));

		project.setOwner(owner);
		return projectRepository.save(project);
	}

	public List<Project> getAllProjects() {
		return projectRepository.findAll();
	}

	public List<Project> getProjectsByUser(Long userId) {
		return projectRepository.findByOwnerId(userId);
	}

	public void deleteProject(Long projectId) {
		projectRepository.deleteById(projectId);
	}
}