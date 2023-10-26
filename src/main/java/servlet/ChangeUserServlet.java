package servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import service.UserService;

import java.io.IOException;
@MultipartConfig(fileSizeThreshold = 1024*1024)
@WebServlet("/change")
public class ChangeUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("attribute").equals("img")) {
            Part part = request.getPart("image");
            UserService.uploadImage(part);
            UserService.changeData(request.getParameter("attribute"),part.getSubmittedFileName());
        }
        else{
            UserService.changeData(request.getParameter("attribute"),request.getParameter("value"));
        }
        response.sendRedirect("/profile");
    }
}