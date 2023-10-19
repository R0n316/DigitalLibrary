package service;

import dao.UserDao;
import entity.User;
import lombok.Getter;
import lombok.Setter;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserService {
    @Setter
    @Getter
    private static User user = null;
    private static final UserDao userDao = UserDao.getInstance();
    private UserService(){}

    public static boolean singIn(String login, String password){
        user = userDao.checkUserData(login,password);
        return user!=null;
    }
    public static boolean registration(String userName, String login, String password){
        try(Connection connection = ConnectionManager.getConnection()){
            user = userDao.findById(login);
            if((userName.isEmpty()||login.isEmpty()||password.isEmpty())||user!=null){
                return false;
            }
            String register = """
                INSERT INTO users(user_name, login, pass)
                VALUES
                ('%s', '%s', '%s')
                """.formatted(userName,login,password);
            PreparedStatement statement = connection.prepareStatement(register);
            statement.execute();
            user = userDao.checkUserData(login,password);
            return true;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public static void takeBook(int bookId){
        UserDao.getInstance().takeBook(bookId);
    }
    public static void returnBook(int bookId){
        UserDao.getInstance().returnBook(bookId);
    }
    public static List<String> findUserBooks(){
        return UserDao.getInstance().findUserBooks();
    }
}
