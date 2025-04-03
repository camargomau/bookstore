package com.camargomau.bookstore.controller;

import com.camargomau.bookstore.model.Book;
import com.camargomau.bookstore.repository.BookRepository;
import com.camargomau.bookstore.repository.AuthorRepository;
import com.camargomau.bookstore.repository.PublisherRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/books")
@Tag(name = "Book", description = "Endpoints for managing books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @GetMapping
    @Operation(summary = "Get books", description = "Retrieve all books or a specific book by ID")
    public Object getBookById(@RequestParam(required = false) Integer id) {
        if (id != null) {
            return bookRepository.findById(id).orElse(null);
        }
        return bookRepository.findAll();
    }

    @PostMapping
    @Operation(summary = "Create a book", description = "Create a new book with the provided details")
    public Book createBook(@RequestParam String title,
                           @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date releaseDate,
                           @RequestParam Integer idAuthor,
                           @RequestParam Integer idPublisher) {
        Book newBook = new Book();
        newBook.setTitle(title);
        newBook.setReleaseDate(releaseDate);
        newBook.setAuthor(authorRepository.findById(idAuthor).orElse(null));
        newBook.setPublisher(publisherRepository.findById(idPublisher).orElse(null));
        return bookRepository.save(newBook);
    }

    @PutMapping
    @Operation(summary = "Update a book", description = "Update an existing book's details by ID")
    public Book updateBook(@RequestParam Integer id,
                           @RequestParam(required = false) String title,
                           @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date releaseDate,
                           @RequestParam(required = false) Integer idAuthor,
                           @RequestParam(required = false) Integer idPublisher) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    if (title != null) existingBook.setTitle(title);
                    if (releaseDate != null) existingBook.setReleaseDate(releaseDate);
                    if (idAuthor != null) existingBook.setAuthor(authorRepository.findById(idAuthor).orElse(null));
                    if (idPublisher != null) existingBook.setPublisher(publisherRepository.findById(idPublisher).orElse(null));
                    return bookRepository.save(existingBook);
                })
                .orElse(null);
    }
}
