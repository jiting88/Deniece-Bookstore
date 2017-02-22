package servlet;

/**
 * Created by jicl on 16/5/5.
 */
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manager.LoginManager;
import entity.User;

@WebServlet("/Login")
public class Login extends HttpServlet{
    public void doGet(HttpServletRequest request,HttpServletResponse response)
        throws ServletException,IOException{
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        LoginManager lm=new LoginManager();
        User user=lm.check(username,password);
        if(user!=null){
            HttpSession session=request.getSession();
            session.setAttribute("user",user);
            request.setAttribute("username",username);
            if(username.equals("Admin"))
                response.sendRedirect("Admin.jsp");
            else{
                response.sendRedirect("Books.jsp");
            }

        }
        else
            response.sendRedirect("Login.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException{
        doGet(request,response);
    }
}
