package servlet;

import service.BookService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/books")
public class BookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        String id = request.getParameter("id");
        String bookName = request.getParameter("book_name");
        String authorName = request.getParameter("author_name");
        String isbn = request.getParameter("isbn");
        pw.println(BookService.delete(id));
    }
}