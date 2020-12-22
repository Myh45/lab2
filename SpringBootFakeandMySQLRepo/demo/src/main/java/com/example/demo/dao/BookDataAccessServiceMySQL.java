package com.example.demo.dao;

import com.example.demo.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Qualifier("mysqlDao")
public class BookDataAccessServiceMySQL implements BookDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDataAccessServiceMySQL(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertBook(UUID id, Book book) {
        System.out.println(book.getName());
        jdbcTemplate.update(
                "INSERT INTO book (id, name, author, year, publisher, count) VALUES (?, ?, ?, ?, ?, ?)",
                id.toString(), book.getName(), book.getAuthor(), book.getYear(), book.getPublisher(), book.getCount()
        );
        return 1;
    }

    @Override
    public List<Book> selectAllBooks() {
        final String sql = "SELECT * FROM book";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String author = resultSet.getString("author");
            int year = resultSet.getInt("year");
            String publisher = resultSet.getString("publisher");
            int count = resultSet.getInt("count");
            return new Book(id, name,author,year,publisher,count);
        });
    }

    @Override
    public Optional<Book> selectBookById(UUID id) {
        final String sql = "SELECT * FROM book WHERE id = ?";

        Book book = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id.toString()},
                (resultSet, i) -> {
                    UUID bookId = UUID.fromString(resultSet.getString("id"));
                    String name = resultSet.getString("name");
                    String author = resultSet.getString("author");
                    int year = resultSet.getInt("year");
                    String publisher = resultSet.getString("publisher");
                    int count = resultSet.getInt("count");
                    return new Book(bookId, name,author,year,publisher,count);
                });
        return Optional.ofNullable(book);
    }

    @Override
    public int deleteBookById(UUID id) {
        final String sql = "DELETE FROM book WHERE id = ?";
        Object[] args = new Object[] {id.toString()};
        int row = jdbcTemplate.update(sql, args);
        return row;
    }

    @Override
    public int updateBookById(UUID id, Book book) {
        jdbcTemplate.update(
                "UPDATE book SET id = ?, name = ? , author = ?, year = ?, publisher = ?, count = ? WHERE id = ?",
                id.toString(), book.getName(), book.getAuthor(), book.getYear(), book.getPublisher(), book.getCount(), id.toString()
        );
        return 1;
    }

    @Override
    public int deleteAll() {
        final String sql = "DELETE FROM book";
        Object[] args = new Object[] {};
        int row = jdbcTemplate.update(sql, args);
        return row;
    }

    @Override
    public Optional<Book> getBookByAuthor(String Newauthor) {
        final String sql = "SELECT * FROM book WHERE author = ?";

        Book book = jdbcTemplate.queryForObject(
                sql,
                new Object[]{Newauthor},
                (resultSet, i) -> {
                    UUID bookId = UUID.fromString(resultSet.getString("id"));
                    String name = resultSet.getString("name");
                    String author = resultSet.getString("author");
                    int year = resultSet.getInt("year");
                    String publisher = resultSet.getString("publisher");
                    int count = resultSet.getInt("count");
                    return new Book(bookId, name,author,year,publisher,count);
                });
        return Optional.ofNullable(book);
    }

    @Override
    public Optional<Book> getBookByPublisher(String NewPublisher) {
        final String sql = "SELECT * FROM book WHERE publisher = ?";

        Book book = jdbcTemplate.queryForObject(
                sql,
                new Object[]{NewPublisher},
                (resultSet, i) -> {
                    UUID bookId = UUID.fromString(resultSet.getString("id"));
                    String name = resultSet.getString("name");
                    String author = resultSet.getString("author");
                    int year = resultSet.getInt("year");
                    String publisher = resultSet.getString("publisher");
                    int count = resultSet.getInt("count");
                    return new Book(bookId, name,author,year,publisher,count);
                });
        return Optional.ofNullable(book);
    }
}
