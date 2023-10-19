package service;

import dao.BookDao;
import dto.BookDto;
import entity.Book;

import java.util.List;
import java.util.stream.Collectors;

public class BookService {
    private static final BookDao bookDao = BookDao.getInstance();
    private BookService(){}
    public static List<BookDto> findAll(){
        return bookDao.findAll().stream().map(book -> new BookDto(book.getBookId(),book.getBookName())).collect(Collectors.toList());
    }
    public static String add(String bookName, String authorName, String isbn){
        Book book = new Book(bookName,authorName,isbn);
        return BookDao.getInstance().save(book) ? "The book has been added successfully." : "Invalid parameter values.";
    }
    public static String delete(String id){
        return BookDao.getInstance().delete(Integer.parseInt(id)) ? "The book was successfully deleted." : "Error when trying to delete a book.";
    }
    public static List<String> findAllAvailableBooks(){
        return BookDao.getInstance().findAllAvailableBooks();
    }
}
