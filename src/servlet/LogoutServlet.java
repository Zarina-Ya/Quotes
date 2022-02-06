package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LogoutServlet extends HttpServlet {
        protected void doGet(HttpServletRequest request, HttpServletResponse response)  
                                throws ServletException, IOException {
            response.setContentType("text/html");

            HttpSession session = request.getSession();
            session.invalidate();

            PrintWriter out=response.getWriter();
            out.println("<html><body>");

            request.getRequestDispatcher("index.html").include(request, response);
            out.append("<p class=\"intro_tite\">");
            out.append("You are successfully logged out!");
            out.append("</p>");


            out.println("</html></body>");

            out.close();
    }
}
