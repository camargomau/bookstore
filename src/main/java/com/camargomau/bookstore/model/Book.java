package com.camargomau.bookstore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

// import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Date;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_book")
    private Integer id;
    private String title;
    private Date releaseDate;

    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_author", nullable = false)
	// Use JsonBackReference to avoid failing on empty beans when FetchType.LAZY is used
	// (https://stackoverflow.com/q/52656517)
	// @JsonBackReference
    private Author author;

    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_publisher", nullable = false)
	// Use JsonBackReference to avoid failing on empty beans when FetchType.LAZY is used
	// (https://stackoverflow.com/q/52656517)
	// @JsonBackReference
    private Publisher publisher;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
}
