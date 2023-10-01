package entity;

import java.util.Objects;

public class Book {
    private int book_id;
    private String book_name;
    private String author_name;
    private String isbn;

    public Book(int book_id, String book_name, String author_name, String isbn) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.author_name = author_name;
        this.isbn = isbn;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return book_id == book.book_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(book_id);
    }

    @Override
    public String toString() {
        return "Book{" +
               "book_id=" + book_id +
               ", book_name='" + book_name + '\'' +
               ", author_name='" + author_name + '\'' +
               ", isbn='" + isbn + '\'' +
               '}';
    }
}
