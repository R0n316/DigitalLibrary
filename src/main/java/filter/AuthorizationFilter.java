package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.UrlPath;

import java.io.IOException;
import java.util.Set;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {
    private final static Set<String> PUBLIC_PATH = UrlPath.getAllPublicPath();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String uri = ((HttpServletRequest) servletRequest).getRequestURI();
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        if(isPublicPath(uri)||uri.equals("/")){
            if(!uri.equals(UrlPath.UNAVAILABLE_PAGE)){
                session.setAttribute("prevPage",uri);
            }
            filterChain.doFilter(servletRequest,servletResponse);
        }
        else{
            servletRequest.setAttribute("startPage",UrlPath.START_PAGE);
            ((HttpServletResponse) servletResponse).sendRedirect("/unavailablePage");
        }
    }
    private static boolean isPublicPath(String uri){
        return PUBLIC_PATH.stream().anyMatch(uri::startsWith);
    }
}
