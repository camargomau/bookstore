package com.camargomau.bookstore.controller;

import com.camargomau.bookstore.model.Publisher;
import com.camargomau.bookstore.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publishers")
public class PublisherController {
    @Autowired
    private PublisherRepository publisherRepository;

    // GET
    @GetMapping
    public Object getPublisherById(@RequestParam(required = false) Integer id) {
        if (id != null) {
            return publisherRepository.findById(id).orElse(null);
        }

        return publisherRepository.findAll();
    }

    // POST (create a new publisher)
    @PostMapping
    public Publisher createPublisher(@RequestParam String name) {
        Publisher newPublisher = new Publisher();
        newPublisher.setName(name);
        return publisherRepository.save(newPublisher);
    }

    // PUT (update an existing publisher)
    @PutMapping
    public Publisher updatePublisher(@RequestParam Integer id, @RequestParam String name) {
        Publisher existingPublisher = publisherRepository.findById(id).orElse(null);

        if (existingPublisher != null) {
            existingPublisher.setName(name);
            return publisherRepository.save(existingPublisher);
        }

        return null;
    }
}
