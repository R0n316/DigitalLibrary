package test;

import service.UserService;

import java.util.List;

public class findUserBooksByIdTest {
    public static void main(String[] args) {
        String userid = "4";
        List<String> list = UserService.findUserBooksById(userid);
        for(String book:list){
            System.out.println(book);
        }
    }
}
