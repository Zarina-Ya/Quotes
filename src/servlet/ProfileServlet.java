package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class ProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");

      //  request.getRequestDispatcher("index.html").include(request, response);

        HttpSession session = request.getSession(false);

        if(session != null){

            Integer name = Integer.parseInt(String.valueOf(session.getAttribute("ID")));
            request.getRequestDispatcher("index.html").include(request, response);
            out.append("<p class=\"intro_tite\">");
            out.append("Hello, " + name + " Welcome to Profile");
            out.append("</p>");

            out.append(" <div class=\"header_nav\">");
            out.append("   <form action=\"getQuotes\"method=\"get\">");
            out.append("      <fieldset class=\"search-form-wrap\">");
            out.append("          <p class=\"search-form_info\">");
            out.append("           <button type=\"submit\" class=\"search-form_submit\">Output a quote</button>");
            out.append("       </p>");
            out.append("   </fieldset>");
            out.append("  </form>");
            out.append("   </div>");

        }
        else{
            request.getRequestDispatcher("login.html").include(request, response);
            out.append("<p class=\"intro_tite\">");
            out.append("Please login first");
            out.append("</p>");
        }

        out.println("</html></body>");
        out.close();
    }
}
