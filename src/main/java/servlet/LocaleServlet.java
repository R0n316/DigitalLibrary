package servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import util.UrlPath;

import java.io.IOException;

@WebServlet("/locale")
public class LocaleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = request.getParameter("lang");
        request.getSession().setAttribute("lang",language);
        String prevPage = request.getHeader("referer");
        String page  = prevPage!=null ? prevPage : UrlPath.REGISTRATION_PAGE;
        response.sendRedirect(page);
    }
}