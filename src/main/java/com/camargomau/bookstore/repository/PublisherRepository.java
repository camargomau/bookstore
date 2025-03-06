package com.camargomau.bookstore.repository;

import com.camargomau.bookstore.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
}
