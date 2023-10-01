package entity;

import java.util.Objects;

public class User {
    private int user_id;
    private String user_name;
    private String login;
    private String password;

    public User(int userID, String userName, String login, String password) {
        this.user_id = userID;
        this.user_name = userName;
        this.login = login;
        this.password = password;
    }

    public int getUserID() {
        return user_id;
    }

    public String getUserName() {
        return user_name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return user_id == user.user_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id);
    }

    @Override
    public String toString() {
        return "User{" +
               "userID=" + user_id +
               ", userName='" + user_name + '\'' +
               ", login='" + login + '\'' +
               ", password='" + password + '\'' +
               '}';
    }
}
