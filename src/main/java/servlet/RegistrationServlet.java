package servlet;

import service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String error = request.getParameter("error");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        boolean res = UserService.registration(name,login,password);
//        String error = request.getParameter("error");
        if(!res){
            response.sendRedirect(request.getContextPath());
        }
        else{
            response.sendRedirect("/DigitalLibrary_war_exploded/home");
        }
    }
}