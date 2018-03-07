package filters;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

    private ServletContext context;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String uri = req.getRequestURI();
        this.context.log("Request Resource::" + uri);

        HttpSession session = req.getSession(false);

        if(session == null && (uri.endsWith("html") || uri.endsWith("LoginServlet"))) {
            this.context.log("Unauthorized access request");
            res.sendRedirect("login.html");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    public void destroy() {

    }
}
