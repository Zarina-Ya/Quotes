package controller;

import login.LoginDao;
import users.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


public class LoginController extends HttpServlet {
    private LoginDao loginDao;

    public void init() {
        loginDao = new LoginDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authenticate(req, resp);
    }


    private void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        //request.getRequestDispatcher("index.html").include(request, response);

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ( username == "" || password == "") {
            request.getRequestDispatcher("login.html").include(request, response);
            out.append("<p class=\"intro_tite\">");
            out.append("Fill in all the fields!");
            out.append("</p>");
        }
        else {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);


            try {
                if (loginDao.validate(user)) {
                    request.getRequestDispatcher("index.html").include(request, response);
                    HttpSession session = request.getSession();
                    session.setAttribute("ID", user.getID());

                    out.append("<p class=\"intro_tite\">");
                    out.append("Welcome, " + username + " " + user.getID());
                    out.append("</p>");
                } else {

                    request.getRequestDispatcher("req.html").include(request, response);
                    out.append("<p class=\"intro_tite\">");
                    out.append("Sorry, username or password error!");
                    out.append("</p>");

                }



            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        out.println("</html></body>");
        out.close();
    }
}
