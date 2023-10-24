package test;

import entity.Book;
import service.BookService;
import service.UserService;

import java.util.List;

public class findUserBooksByIdTest {
    public static void main(String[] args) {
        String userid = "4";
        List<Book> list = BookService.findUserBooks(userid);
        for(Book book:list){
            System.out.println(book.getBookName());
        }
    }
}
