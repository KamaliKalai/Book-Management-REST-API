package com.example.bookapi.service;

import com.example.bookapi.model.Book;
import com.example.bookapi.dao.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;  // ✅ Injected automatically by Spring

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public Book getBookById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Book saveBook(Book book) {
        return repository.save(book);
    }

    public Book updateBook(Long id, Book updatedBook) {
        return repository.findById(id)
                .map(book -> {
                    book.setTitle(updatedBook.getTitle());
                    book.setAuthor(updatedBook.getAuthor());
                    book.setPrice(updatedBook.getPrice());
                    return repository.save(book);
                }).orElse(null);
    }

    public void deleteBook(Long id) {
        repository.deleteById(id);
    }
}
