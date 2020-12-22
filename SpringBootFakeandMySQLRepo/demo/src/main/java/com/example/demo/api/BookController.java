package com.example.demo.api;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v2/book")
@RestController
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public void addBook(@Valid @NonNull @RequestBody Book book) {
        bookService.addBook(book);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping(path = "/{id}")
    public Book getBookById(@PathVariable("id") UUID id) {
        return bookService.getBookById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteBookById(@PathVariable("id") UUID id) {
        bookService.deleteBookByID(id);
    }

    @PutMapping(path = "/{id}")
    public void updateBookById(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Book book) {
        bookService.updateBookById(id, book);
    }
    @DeleteMapping
    public void deleteAllBooks(){
        bookService.deleteAllBooks();}

    @GetMapping(path = "/getAuthor/{author}")
    public Book getBookByAuthor(@PathVariable("author") String Newauthor) {
        return bookService.getBookByAuthor(Newauthor)
                .orElse(null);
    }
    @GetMapping(path = "/getPublisher/{publisher}")
    public Book getBookByPublisher(@PathVariable("publisher") String NewPublisher) {
        return bookService.getBookByPublisher(NewPublisher)
                .orElse(null);
    }

}
