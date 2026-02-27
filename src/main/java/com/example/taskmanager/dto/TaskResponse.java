package com.example.taskmanager.dto;

import com.example.taskmanager.enums.TaskPriority;
import com.example.taskmanager.enums.TaskStatus;

import lombok.Data;

//Response
@Data
public class TaskResponse {
	private Long id;
	private String title;
	private String description;
	private TaskStatus status;
	private TaskPriority priority;
	private Long projectId;

	public TaskResponse(Long id, String title, String description, TaskStatus status, TaskPriority priority,
			Long projectId) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
		this.priority = priority;
		this.projectId = projectId;
	}
}