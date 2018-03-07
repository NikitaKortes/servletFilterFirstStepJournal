package session;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final String userID = "admin";
    private final String password = "password";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user = req.getParameter("user");
        String pwd = req.getParameter("pwd");

        if(userID.equals(user) && password.equals(pwd)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", "Pankaj");
            session.setMaxInactiveInterval(30 * 60);

            Cookie userName = new Cookie("user", user);
            userName.setMaxAge(30 * 60);
            resp.addCookie(userName);

            resp.sendRedirect("LoginSuccess.jsp");
        }
        else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = resp.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
            rd.include(req, resp);
        }
    }
}
