package com.camargomau.bookstore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Id;

import java.util.List;

@Entity
@Table(name = "authors")
public class Author {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_author")
	private Integer id;
	private String name;

	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
	private List<Book> book;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
