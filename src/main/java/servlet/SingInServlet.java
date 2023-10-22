package servlet;

import service.UserService;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import util.UrlPath;

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
            HttpSession session = request.getSession();
            session.setAttribute("error","true");
            session.setAttribute("login",login);
            response.sendRedirect(UrlPath.SIGN_IN_PAGE);
        }
        else{
            request.getSession().setAttribute("user",UserService.getUser());
            response.sendRedirect(UrlPath.HOME);
        }
    }
}