package com.camargomau.bookstore.controller;

import com.camargomau.bookstore.model.Author;
import com.camargomau.bookstore.repository.AuthorRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
@Tag(name = "Author", description = "Endpoints for managing authors")
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    @Operation(summary = "Get authors", description = "Retrieve all authors or a specific author by ID")
    public Object getAuthorById(@RequestParam(required = false) Integer id) {
        if (id != null) {
            return authorRepository.findById(id).orElse(null);
        }
        return authorRepository.findAll();
    }

    @PostMapping
    @Operation(summary = "Create an author", description = "Create a new author with the provided name")
    public Author createAuthor(@RequestParam String name) {
        Author newAuthor = new Author();
        newAuthor.setName(name);
        return authorRepository.save(newAuthor);
    }

    @PutMapping
    @Operation(summary = "Update an author", description = "Update an existing author's name by ID")
    public Author updateAuthor(@RequestParam Integer id, @RequestParam String name) {
        Author existingAuthor = authorRepository.findById(id).orElse(null);
        if (existingAuthor != null) {
            existingAuthor.setName(name);
            return authorRepository.save(existingAuthor);
        }
        return null;
    }
}
