package com.example.taskmanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String description;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User owner;

	public Project() {
	}

	public Project(String name, String description, User owner) {
		this.name = name;
		this.description = description;
		this.owner = owner;
	}

}