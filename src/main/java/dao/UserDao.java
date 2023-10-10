package dao;

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

public class UserDao implements Dao<Integer,User>{
    private UserDao(){}
    private static final UserDao INSTANCE = new UserDao();
    public static UserDao getInstance(){
        return INSTANCE;
    }

    private final static String FIND_ALL = """
            SELECT *
            FROM users
""";
    private User buildUser(ResultSet resultSet) throws SQLException {
        return  new User(
                resultSet.getObject("user_id",Integer.class),
                resultSet.getObject("user_name",String.class),
                resultSet.getObject("login",String.class),
                resultSet.getObject("pass",String.class)
        );
    }
    @Override
    public List<User> findAll() {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_ALL);
            List<User> users = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                users.add(buildUser(resultSet));
            }
            return users;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<String> findUserBooks(){
        try(Connection connection = ConnectionManager.getConnection()){
            User user = UserService.getUser();
            List<String> books = new ArrayList<>();
            String findBooks = """
                    SELECT book_name
                    FROM users
                    JOIN rented_books USING(user_id)
                    JOIN books USING (book_id)
                    WHERE user_id = %s
                    """.formatted(user.getUserID());
            PreparedStatement preparedStatement = connection.prepareStatement(findBooks);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                books.add(resultSet.getObject("book_name", String.class));
            }
            return books;
        } catch(SQLException e){
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
                    """.formatted(user.getUserID(),bookId);
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
                    """.formatted(user.getUserID(),bookId);
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
    @Override
    public User findById(String id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String find = """
                    SELECT*
                    FROM users
                    WHERE login='%s'
                    """.formatted(id);
            PreparedStatement statement = connection.prepareStatement(find);
            ResultSet resultSet = statement.executeQuery();
            User user = null;
            while (resultSet.next()){
                user = buildUser(resultSet);
            }
            return user;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public User checkUserData(String login, String password){
        try (Connection connection = ConnectionManager.getConnection()) {
            String find = """
                    SELECT*
                    FROM users
                    WHERE login = '%s' AND pass = '%s'
                    """.formatted(login,password);
            PreparedStatement statement = connection.prepareStatement(find);
            ResultSet resultSet = statement.executeQuery();
            User user = null;
            while (resultSet.next()){
                user = buildUser(resultSet);
            }
            return user;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(Integer entity) {

    }

    @Override
    public boolean save(User entity) {
        return false;
    }
}
