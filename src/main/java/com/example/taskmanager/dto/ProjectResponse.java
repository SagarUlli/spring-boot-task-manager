package com.example.taskmanager.dto;

import lombok.Data;

//Response
@Data
public class ProjectResponse {
	private Long id;
	private String name;
	private String description;
	private Long ownerId;

	public ProjectResponse(Long id, String name, String description, Long ownerId) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.ownerId = ownerId;
	}
}