package service;

import dao.UserDao;
import entity.User;
import jakarta.servlet.http.Part;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    @Setter
    @Getter
    private static User user = null;
    private static final UserDao userDao = UserDao.getInstance();
    @Getter
    private static final String IMAGE_FOLDER = "users/";
    private UserService(){}

    public static boolean singIn(String login, String password){
        user = userDao.checkUserData(login,password);
        return user!=null;
    }
    public static User findUser(String login){
        return userDao.findById(String.valueOf(login));
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
    public static List<User> findAllUsers(){
        return UserDao.getInstance().findAll();
    }
    public static void changeData(String attribute, String value){
        if(user!=null){
            UserDao.getInstance().changeData(attribute,value);
            user = UserService.findUser(UserService.getUser().getLogin());
            UserService.singIn(user.getLogin(),user.getPassword());
        }
    }
    @SneakyThrows
    public static void uploadImage(Part image){
        ImageService imageService = ImageService.getInstance();
        imageService.upload(IMAGE_FOLDER+image.getSubmittedFileName(),image.getInputStream());
    }
    public static String getAttribute(String attributeName){
        return UserDao.getInstance().getAttribute(attributeName);
    }
}
