package entity;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Book {
    private int bookId;
    private String bookName;
    private String authorName;
    private String isbn;
    private String status;

    public Book(int bookId, String bookName, String authorName, String isbn) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.authorName = authorName;
        this.isbn = isbn;
    }
    public Book(String bookName, String authorName, String isbn) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.isbn = isbn;
    }
}
