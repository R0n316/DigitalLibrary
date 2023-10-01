package servlet;

import dto.BookDto;
import service.BookService;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/books")
public class BookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        List<BookDto> books = BookService.findAll();
        pw.println("<ul>");
        for(BookDto book:books){
            pw.println("<li>"+book.getBook_name()+"</li>");
        }
        pw.println("</ul>");
    }
}