
# 📘 Book Management using REST API

## 🧩 Description
The **Book Management REST API** is a Spring Boot project that provides CRUD (Create, Read, Update, Delete) operations for managing book records.It uses **Spring Boot**, **Spring Data JPA**, and **MySQL**, and follows the **Controller → Service → Repository** layered architecture.

This project demonstrates how to build a RESTful backend using Java and connect it to a MySQL database.



## 💻 Technology Used
- **Java**
- **Spring Boot**
- **Spring Data JPA**
- **MySQL**
- **Maven**
- **Postman** (for testing)



## ✨ Features
✅ Add new books  
✅ Retrieve all books  
✅ Retrieve a book by ID  
✅ Update book details  
✅ Delete a book  
✅ Integrated with MySQL database using JPA  
✅ Tested using Postman  



## 📂 Project Structure
```

bookapi/
├── src/
│   ├── main/
│   │   ├── java/com/example/bookapi/
│   │   │   ├── BookApiApplication.java
│   │   │   ├── model/Book.java
│   │   │   ├── controller/BookController.java
│   │   │   ├── service/BookService.java
│   │   │   ├── dao/BookRepository.java
│   │   └── resources/
│   │       ├── application.properties
└── pom.xml

````



## 🧠 Code Overview

### **1️⃣ Model – Book.java**
```java
package com.example.bookapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String title;
    private String author;
    private double price;
    
    public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
    public Book(Long id, String title, String author, double price) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.price = price;
	}
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}


}

````



### **2️⃣ Repository – BookRepository.java**

```java
package com.example.bookapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.bookapi.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
```



### **3️⃣ Service – BookService.java**

```java
package com.example.bookapi.service;

import com.example.bookapi.model.Book;
import com.example.bookapi.dao.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;  

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

```



### **4️⃣ Controller – BookController.java**

```java
package com.example.bookapi.controller;

import com.example.bookapi.model.Book;
import com.example.bookapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService service; 

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

```





## 🛠️ Database Setup

1. Open MySQL and create a database:

```sql
CREATE DATABASE bookdb;
```

2. Configure your connection in:
   **`src/main/resources/application.properties`**

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bookdb
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.application.name=bookapi
```



## 🧪 Testing with Postman

Before testing the API, open **Postman** and follow these steps:

1. Open **Postman** → Click **New Request**
2. Select **Method** → Choose one (GET, POST, PUT, DELETE)
3. Enter **URL** → e.g., `http://localhost:8080/api.books`
4. For **POST** or **PUT** requests:

   * Go to the **Body** tab
   * Select **raw**
   * Choose **JSON** format
   * Paste this JSON body 👇

### ✅ Example JSON Body

```json
{
  "title": "Spring Boot Essentials",
  "author": "Craig Walls",
  "price": 650.0
}
```



## 🌐 API Endpoints

| Method     | Endpoint          | Description    |
| ---------- | ----------------- | -------------- |
| **GET**    | `/api.books`      | Get all books  |
| **GET**    | `/api.books/{id}` | Get book by ID |
| **POST**   | `/api.books`      | Add a new book |
| **PUT**    | `/api.books/{id}` | Update a book  |
| **DELETE** | `/api.books/{id}` | Delete a book  |



## 📦 Example API Responses

### **1️⃣ Add a Book (POST)**

**URL:** `POST http://localhost:8080/api.books`
**Body:**

```json
{
  "title": "Spring Boot Essentials",
  "author": "Craig Walls",
  "price": 650.0
}
```

**Response:**

```json
{
  "id": 1,
  "title": "Spring Boot Essentials",
  "author": "Craig Walls",
  "price": 650.0
}
```



### **2️⃣ Get All Books (GET)**

**URL:** `GET http://localhost:8080/api.books`
**Response:**

```json
[
  {
    "id": 1,
    "title": "Spring Boot Essentials",
    "author": "Craig Walls",
    "price": 650.0
  }
]
```



### **3️⃣ Update Book (PUT)**

**URL:** `PUT http://localhost:8080/api.books/1`
**Body:**

```json
{
  "title": "Spring Boot Advanced",
  "author": "Craig Walls",
  "price": 750.0
}
```

**Response:**

```json
{
  "id": 1,
  "title": "Spring Boot Advanced",
  "author": "Craig Walls",
  "price": 750.0
}
```



### **4️⃣ Delete Book (DELETE)**

**URL:** `DELETE http://localhost:8080/api.books/1`
**Response:**

```
"Book deleted successfully!"
```



