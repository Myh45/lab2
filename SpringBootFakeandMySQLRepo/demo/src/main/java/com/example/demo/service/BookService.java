package com.example.demo.service;

import com.example.demo.dao.BookDao;
import com.example.demo.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {
    private final BookDao bookDao;

    @Autowired
    public BookService(@Qualifier("mysqlDao") BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public int addBook(Book book) {
        return bookDao.insertBook(book);
    }

    public List<Book> getAllBooks() {
        return this.bookDao.selectAllBooks();
    }

    public Optional<Book> getBookById(UUID id) {
        return bookDao.selectBookById(id);
    }

    public int deleteBookByID(UUID id) {
        return bookDao.deleteBookById(id);
    }

    public int updateBookById(UUID id, Book newBook) {
        return bookDao.updateBookById(id, newBook);
    }

     public int deleteAllBooks(){return bookDao.deleteAll();}

    public Optional<Book> getBookByAuthor(String Newauthor) {
        return bookDao.getBookByAuthor(Newauthor);
    }

    public Optional<Book> getBookByPublisher(String NewPublisher) {
        return bookDao.getBookByPublisher(NewPublisher);
    }
}
