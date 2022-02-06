package servlet;
import quotes.QuotesGen;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class QuotesServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        QuotesGen quotesGen = new QuotesGen();
        String quoteOut = quotesGen.generation();
        req.getRequestDispatcher("index.html").include(req, resp);
        out.append("<p class=\"intro_tite\">");
        out.append(quoteOut);
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
        out.println("</html></body>");
        out.close();
    }
}
