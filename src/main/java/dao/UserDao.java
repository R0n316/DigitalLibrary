package dao;

import entity.Status;
import entity.User;
import jakarta.mail.search.OrTerm;
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
    private final static String UPDATE = """
            UPDATE users
            SET %s = '%s'
            WHERE user_id = %s
            """;
    private final static String GET_ATTRIBUTE = """
            SELECT %s
            FROM users
            WHERE user_id = %s
            """;
    private User buildUser(ResultSet resultSet) throws SQLException {
        return  new User(
                resultSet.getObject("user_id",Integer.class),
                resultSet.getObject("user_name",String.class),
                resultSet.getObject("login",String.class),
                resultSet.getObject("pass",String.class),
                resultSet.getObject("img",String.class)
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
    public void changeData(String attribute, String value){
        try(Connection connection = ConnectionManager.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    UPDATE.formatted(attribute,value,UserService.getUser().getUserId()));
            preparedStatement.execute();
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

    @Override
    public String getAttribute(String attributeName) {
        try(Connection connection = ConnectionManager.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement
                    (GET_ATTRIBUTE.formatted(attributeName,UserService.getUser().getUserId()));
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString(attributeName);
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
