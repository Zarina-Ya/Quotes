// для обработки параметров HTTP-запроса и перенаправления на соответствующую страницу после запроса
package controller;
import login.LoginDao;
import quotes.QuotesGen;
import users.User;
import users.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class UserController extends HttpServlet {

    private UserDao userDao;


    public void init() {
        userDao = new UserDao();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        register(req, resp);
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        out.println("<html><body>");
      //  request.getRequestDispatcher("index.html").include(request, response);
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ( username == "" || password == ""){
            request.getRequestDispatcher("req.html").include(request, response);
            out.append("<p class=\"intro_tite\">");
            out.append("Fill in all the fields!");
            out.append("</p>");
        }else{

            User employee = new User();
            UserDao loginDao = new UserDao();
            employee.setUsername(username);
            employee.setPassword(password);

            try {
                if(loginDao.validate(employee)){
                    request.getRequestDispatcher("login.html").include(request, response);
                    out.append("<p class=\"intro_tite\">");
                    out.append("You are already registered!");
                    out.append("</p>");
                }
                else{
                 //   Integer id = QuotesGen.getRandomNumberInts(10,10000);
                    request.getRequestDispatcher("index.html").include(request, response);
                    employee.setID(genNumID());
                    userDao.registerEmployee(employee);
                    HttpSession session = request.getSession();
                    session.setAttribute("ID", employee.getID());

                    out.append("<p class=\"intro_tite\">");
                    out.append("Welcome, " + username + " " + employee.getID());
                    out.append("</p>");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }


        out.println("</html></body>");

        out.close();
    }

    public boolean checkNum(Integer id,  Map<String,String> mapInFile){
        Boolean status = false;

        for (Map.Entry<String, String> book: mapInFile.entrySet()) {
            if (Integer.parseInt(book.getKey()) == id) {
                status = true;
            }
        }
        return status;
    }


    public Integer genNumID(){
       Integer id = QuotesGen.getRandomNumberInts(10,10000);
       Map<String,String> mapInFile=new HashMap<String,String>();
       while(checkNum(id,mapInFile)){
           id =  QuotesGen.getRandomNumberInts(10,10000);
       }
       return id;
    }



}
