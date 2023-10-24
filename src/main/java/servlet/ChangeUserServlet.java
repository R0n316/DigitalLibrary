package servlet;

import entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import service.UserService;

import java.io.IOException;

@WebServlet("/change")
public class ChangeUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = UserService.findUser(UserService.getUser().getLogin());
        if(user!=null){
            UserService.changeData(request.getParameter("attribute"),request.getParameter("value"));
            user = UserService.findUser(UserService.getUser().getLogin());
            UserService.singIn(user.getLogin(),user.getPassword());
        }
        response.sendRedirect("/profile");
    }
}