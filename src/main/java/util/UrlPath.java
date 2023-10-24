package util;

import java.util.Set;

public class UrlPath {
    public static final String START_PAGE = "/startPage";
    public static final String REGISTRATION_PAGE = "/registrationPage";
    public static final String REGISTRATION = "/registration";
    public static final String SIGN_IN_PAGE = "/signInPage";
    public static final String SIGN_IN = "/signIn";
    public static final String HOME = "/home";
    public static final String SIGN_OUT = "/signOut";
    public static final String STYLE = "/style";
    public static final String LOCALE = "/locale";
    public static final String USERS = "/users";
    public static final String USER_INFO = "/userInfo";
    public static final String UNAVAILABLE_PAGE = "/unavailablePage";
    public static final String USER_BOOK_SERVLET = "/userBookServlet";
    public static final String USER_BOOKS = "/userBooks";
    public static final String USER_PROFILE = "/profile";
    public static final String CHANGE = "/change";
    private UrlPath(){}
    public static Set<String> getAllPublicPath(){
        return Set.of(START_PAGE,REGISTRATION_PAGE,REGISTRATION,SIGN_IN_PAGE,SIGN_IN,HOME,
                SIGN_OUT,STYLE,LOCALE,USERS,USER_INFO,UNAVAILABLE_PAGE,USER_BOOK_SERVLET,
                USER_BOOKS,USER_PROFILE,CHANGE);
    }
}
