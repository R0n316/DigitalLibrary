package dto;

import java.util.Objects;

public class BookDto {
    private  final int book_id;
    private  final String book_name;

    public BookDto(int book_id, String book_name) {
        this.book_id = book_id;
        this.book_name = book_name;
    }

    public int getBook_id() {
        return book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDto bookDto = (BookDto) o;
        return book_id == bookDto.book_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(book_id);
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "book_id=" + book_id +
                ", book_name='" + book_name + '\'' +
                '}';
    }
}
