package service;

import dao.UserDao;
import entity.User;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class UserService {
    private static final UserDao userDao = UserDao.getInstance();
    private UserService(){}

    public static String singIn(String login, String password){
        User user = userDao.checkUserData(login,password);
        return user!=null ? "Hello, "+user.getUserName()+"!": "wrong login or password";
    }
    public static String registration(String userName, String login, String password){
        try(Connection connection = ConnectionManager.getConnection()){
            User user = userDao.findById(login);
            if(user!=null){
                return "A user with this login already exists.";
            }
            String register = """
                INSERT INTO users(user_name, login, pass)
                VALUES
                ('%s', '%s', '%s')
                """.formatted(userName,login,password);
            PreparedStatement statement = connection.prepareStatement(register);
            statement.execute();
            return("You have successfully registered!");
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
