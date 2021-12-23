package com.lucasrodrigues.api.animes.domains.main;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@MappedSuperclass
public class MainEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "name", length = 50, nullable = false)
	private String name;
	
	@Column(name = "dtinsert", nullable = false)
	private LocalDateTime dtInsert;
	
	@Column(name = "dtupdate", nullable = false)
	private LocalDateTime dtUpdate;
	
}
