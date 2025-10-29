package com.example.bookapi.dao;

import com.example.bookapi.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
