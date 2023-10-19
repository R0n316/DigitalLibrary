package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {
    private final static Set<String> PUBLIC_PATH = Set.of("/startPage","/registrationPage","/registration","/signInPage","/signIn","/home","/style");
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String uri = ((HttpServletRequest) servletRequest).getRequestURI();
        if(isPublicPath(uri)){
            filterChain.doFilter(servletRequest,servletResponse);
        }
        else{
            String prevPage = ((HttpServletRequest) servletRequest).getHeader("referer");
            ((HttpServletResponse) servletResponse).sendRedirect(prevPage!=null ? prevPage : "/startPage");
        }
    }
    private static boolean isPublicPath(String uri){
        return PUBLIC_PATH.stream().anyMatch(uri::startsWith);
    }
}
