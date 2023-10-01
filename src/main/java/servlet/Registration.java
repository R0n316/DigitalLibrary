package servlet;

import service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/registration")
public class Registration extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        PrintWriter pw = response.getWriter();
        String id = request.getParameter("id");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
//        String res = UserService.singIn(login,password);
//        pw.println("<h1>"+res+"</h1>");
        pw.println("<h1>"+UserService.registration("name","login","password")+"</h1>");
    }
}