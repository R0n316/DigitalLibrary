package servlet;

import service.UserService;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import util.UrlPath;

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
        if(!res){
            response.sendRedirect(request.getContextPath());
        }
        else{
            request.getSession().setAttribute("user",UserService.getUser());
            response.sendRedirect(UrlPath.HOME);
        }
    }
}