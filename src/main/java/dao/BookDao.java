package dao;

import entity.Book;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDao implements Dao<Integer, Book> {
    private static final BookDao INSTANCE  = new BookDao();
    public static BookDao getInstance(){
        return INSTANCE;
    }
    private BookDao(){}
    private static final String FIND_ALL  = """
        SELECT *
        FROM books
""";
    private Book buildBook(ResultSet resultSet) throws SQLException {
        return new entity.Book(
                resultSet.getObject("book_id",Integer.class),
                resultSet.getObject("book_name",String.class),
                resultSet.getObject("author_name",String.class),
                resultSet.getObject("isbn",String.class)
        );
    }
    @Override
    public List<Book> findAll() {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Book> books = new ArrayList<>();
            while (resultSet.next()){
                books.add(buildBook(resultSet));
            }
            return books;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book findById(String id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(Integer entity) {

    }

    @Override
    public Book save(Book entity) {
        return null;
    }
}
