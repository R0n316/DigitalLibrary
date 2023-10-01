package service;

import dao.BookDao;
import dto.BookDto;
import java.util.List;
import java.util.stream.Collectors;

public class BookService {
    private static final BookDao bookDao = BookDao.getInstance();
    private BookService(){}
    public static List<BookDto> findAll(){
        return bookDao.findAll().stream().map(book -> new BookDto(book.getBook_id(),book.getBook_name())).collect(Collectors.toList());
    }
}
