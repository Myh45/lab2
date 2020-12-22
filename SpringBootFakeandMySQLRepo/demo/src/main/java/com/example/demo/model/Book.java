package com.example.demo.model;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class Book {
    private final UUID id;
    @NotBlank
    private final String name;
    private final String author;
    private final int year;
    private final String publisher;
    private final int count;

    public Book(UUID id, @NotBlank String name, String author, int year, String publisher, int count) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.publisher = publisher;
        this.count = count;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getCount() {
        return count;
    }
}
