package dao;

import entity.Book;
import entity.Status;
import entity.User;
import service.UserService;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    private String saveBook(Book entity){
        return """
                INSERT INTO books(book_name, author_name, isbn)
                VALUES
                ('%s','%s','%s')
                """.formatted(entity.getBookName(),entity.getAuthorName(),entity.getIsbn());
    }
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
    public void takeBook(int id){
        try(Connection connection = ConnectionManager.getConnection()){
            User user = UserService.getUser();
            int bookId = BookDao.findAvailableBook(id);
            if(bookId==0){
                return;
            }
            String query = """
                    INSERT INTO rented_books VALUES
                    (%s,%s)
                    """.formatted(user.getUserId(),bookId);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            String updateStatus = """
                    UPDATE books
                    SET status = '%s'
                    WHERE book_id = %s
                    """.formatted(Status.UNAVAILABLE,bookId);
            preparedStatement = connection.prepareStatement(updateStatus);
            preparedStatement.execute();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    public void returnBook(int bookId){
        try(Connection connection = ConnectionManager.getConnection()){
            User user = UserService.getUser();
            String delete = """
                    DELETE FROM rented_books
                    WHERE user_id = %s AND book_id = %s
                    """.formatted(user.getUserId(),bookId);
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.execute();
            String update = """
                    UPDATE books
                    SET status = '%s'
                    WHERE book_id = %s
                    """.formatted(Status.AVAILABLE,bookId);
            preparedStatement = connection.prepareStatement(update);
            preparedStatement.execute();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    public List<Book> findUserBooks(){
        try(Connection connection = ConnectionManager.getConnection()){
            User user = UserService.getUser();
            List<Book> books = new ArrayList<>();
            String findBooks = """
                    SELECT *
                    FROM books
                    JOIN rented_books USING(book_id)
                    WHERE user_id = %s
                    """.formatted(user.getUserId());
            PreparedStatement statement = connection.prepareStatement(findBooks);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                books.add(buildBook(resultSet));
            }
            return books;
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    public List<Book> findUserBooks(String userId){
        try(Connection connection = ConnectionManager.getConnection()){
            User user = UserService.getUser();
            List<Book> books = new ArrayList<>();
            String findBooks = """
                    SELECT *
                    FROM books
                    JOIN rented_books USING(book_id)
                    WHERE user_id = %s
                    """.formatted(userId);
            PreparedStatement statement = connection.prepareStatement(findBooks);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                books.add(buildBook(resultSet));
            }
            return books;
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    public List<Book> findAllAvailableBooks(){
        try(Connection connection = ConnectionManager.getConnection()){
            List<Book> availableBooks = new ArrayList<>();
            String findAvailableBooks = """
                    SELECT*
                    FROM books
                    WHERE status = '%s'
                    """.formatted(Status.AVAILABLE);
            PreparedStatement preparedStatement = connection.prepareStatement(findAvailableBooks);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                availableBooks.add(buildBook(resultSet));
            }
            return availableBooks;
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    public static Integer findAvailableBook(int id){
        try(Connection connection = ConnectionManager.getConnection()){
            Integer bookId = 0;
            String findAvailableBook = """
                    SELECT book_id
                    FROM books
                    WHERE status = '%s' AND book_id = %s;
                    """.formatted(Status.AVAILABLE,id);
            PreparedStatement preparedStatement = connection.prepareStatement(findAvailableBook);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                bookId = resultSet.getObject("book_id",Integer.class);
            }
            return bookId;
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean save(Book entity) {
        if(entity.getBookName()==null||entity.getAuthorName()==null||entity.getIsbn()==null){
            return false;
        }
        try(Connection connection = ConnectionManager.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(saveBook(entity));
            preparedStatement.execute();
            return true;
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getAttribute(String attributeName) {
        return null;
    }

    @Override
    public Book findById(String id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        try(Connection connection = ConnectionManager.getConnection()){
            String delete = """
                    DELETE FROM books
                    WHERE book_id=%s
                    """.formatted(id);
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            int rows = preparedStatement.executeUpdate();
            return rows>0;
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Integer entity) {

    }
}
