package com.lucas.client.json;

public class Book implements Cloneable {

    private int id;
    private String title;
    private String description;
    private String isbn;
    private String author;
    private String publisher;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Book clone() {
        Book b = new Book();
        b.setId(this.id);
        b.setTitle(this.title);
        b.setDescription(this.description);
        b.setIsbn(this.isbn);
        b.setAuthor(this.author);
        b.setPublisher(this.publisher);
        return b;
    }
}