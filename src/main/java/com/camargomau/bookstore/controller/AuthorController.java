package com.camargomau.bookstore.controller;

import com.camargomau.bookstore.model.Author;
import com.camargomau.bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    // GET
    @GetMapping
    public Object getAuthorById(@RequestParam(required = false) Integer id) {
		if (id != null) {
        	return authorRepository.findById(id).orElse(null);
		}

        return authorRepository.findAll();
    }

    // POST (create a new author)
    @PostMapping
    public Author createAuthor(@RequestParam String name) {
		Author newAuthor = new Author();
		newAuthor.setName(name);
        return authorRepository.save(newAuthor);
    }

    // PUT (update an existing author)
    @PutMapping
    public Author updateAuthor(@RequestParam Integer id, @RequestParam String name) {
        Author existingAuthor = authorRepository.findById(id).orElse(null);

        if (existingAuthor != null) {
            existingAuthor.setName(name);
            return authorRepository.save(existingAuthor);
        }

        return null;
    }
}
