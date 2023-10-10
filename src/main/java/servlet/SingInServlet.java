package servlet;

import service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/signIn")
public class SingInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        boolean res = UserService.singIn(login, password);
        if(!res){
            response.sendRedirect(request.getContextPath());
        }
        else{
            response.sendRedirect("/DigitalLibrary_war_exploded/home");
        }
    }
}