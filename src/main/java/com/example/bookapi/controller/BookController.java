package com.example.bookapi.controller;

import com.example.bookapi.model.Book;
import com.example.bookapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService service;  // ✅ Injected automatically

    @GetMapping("/api.books")
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    @GetMapping("/api.books/{id}")
    public Book getBookById(@PathVariable Long id) {
        return service.getBookById(id);
    }

    @PostMapping("/api.books")
    public Book createBook(@RequestBody Book book) {
        return service.saveBook(book);
    }

    @PutMapping("/api.books/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        return service.updateBook(id, book);
    }

    @DeleteMapping("/api.books/{id}")
    public String deleteBook(@PathVariable Long id) {
        service.deleteBook(id);
        return "Book deleted successfully!";
    }
}
