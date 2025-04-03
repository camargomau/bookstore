package com.camargomau.bookstore.controller;

import com.camargomau.bookstore.model.Publisher;
import com.camargomau.bookstore.repository.PublisherRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publishers")
@Tag(name = "Publisher", description = "Endpoints for managing publishers")
public class PublisherController {
    @Autowired
    private PublisherRepository publisherRepository;

    @GetMapping
    @Operation(summary = "Get publishers", description = "Retrieve all publishers or a specific publisher by ID")
    public Object getPublisherById(@RequestParam(required = false) Integer id) {
        if (id != null) {
            return publisherRepository.findById(id).orElse(null);
        }
        return publisherRepository.findAll();
    }

    @PostMapping
    @Operation(summary = "Create a publisher", description = "Create a new publisher with the provided name")
    public Publisher createPublisher(@RequestParam String name) {
        Publisher newPublisher = new Publisher();
        newPublisher.setName(name);
        return publisherRepository.save(newPublisher);
    }

    @PutMapping
    @Operation(summary = "Update a publisher", description = "Update an existing publisher's name by ID")
    public Publisher updatePublisher(@RequestParam Integer id, @RequestParam String name) {
        Publisher existingPublisher = publisherRepository.findById(id).orElse(null);
        if (existingPublisher != null) {
            existingPublisher.setName(name);
            return publisherRepository.save(existingPublisher);
        }
        return null;
    }
}
