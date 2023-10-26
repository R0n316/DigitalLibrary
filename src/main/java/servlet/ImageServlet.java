package servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lombok.SneakyThrows;
import service.ImageService;

import java.io.IOException;
import java.io.InputStream;

@WebServlet("/images/*")
public class ImageServlet extends HttpServlet {
    private final ImageService imageService = ImageService.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String imagePath = requestURI.replaceAll("/images","");
        imageService.get(imagePath).ifPresentOrElse(image -> {
                    response.setContentType("application/octet-stream");
                    writeImage(image,response);
                },
                () -> response.setStatus(404));
    }
    @SneakyThrows
    public void writeImage(InputStream image, HttpServletResponse response){
        try(image; ServletOutputStream outputStream = response.getOutputStream()){
            int currentByte;
            while ((currentByte = image.read())!=-1){
                outputStream.write(currentByte);
            }
        }
    }
}