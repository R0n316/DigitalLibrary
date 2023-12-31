package servlet;

import entity.Book;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import service.BookService;
import service.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet("/userBooksServlet")
public class UserBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        List<Book> list = BookService.findUserBooks(userId);
        req.getSession().setAttribute("userBooks",list);
        resp.sendRedirect("/userBooks");
    }
}