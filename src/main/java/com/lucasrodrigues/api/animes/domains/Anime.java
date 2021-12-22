package com.lucasrodrigues.api.animes.domains;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lucasrodrigues.api.animes.domains.main.MainEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "tbanime")
public class Anime extends MainEntity{

	
	@Column(name = "authorid",nullable = false)
	private UUID authorid;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "authorid",foreignKey = @ForeignKey(name = "fk_authorxanimes"),insertable = false, updatable = false)
	private Author author;
}

