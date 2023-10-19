package entity;

import lombok.*;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Setter
@Getter
public class User {
    private int userId;
    private String userName;
    private String login;
    private String password;
}
