package com.lucasrodrigues.api.animes.domains;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucasrodrigues.api.animes.domains.main.MainEntity;

@Entity
@Table(name = "tbauthor")
public class Author extends MainEntity {

	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "authorid")
	Set<Anime> setAnimes = new HashSet<>();
}
