package org.factoriaf5.first_api.books;

public class Book {
    private String isbn;
    private String tittle;
    private String author;

    public Book(String isbn, String name, String author) {
        this.isbn = isbn;
        this.tittle = name;
        this.author = author;
    }


    public String getIsbn() {
        return isbn;
    }

    public String getTittle() {
        return tittle;
    }

    public String getAuthor() {
        return author;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}