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
import manager.UserManager;
import entity.User;
import org.codehaus.jackson.map.ObjectMapper;
import java.util.*;

@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
    public void doGet(HttpServletRequest request,HttpServletResponse response)
        throws ServletException,IOException{
        response.setContentType("text/html;charset=UTF-8");

        Double balance=null;
        String host=null;
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        if(request.getParameter("balance")!=null)
            balance=Double.parseDouble(request.getParameter("balance"));
        HttpSession session=request.getSession();
        if(session.getAttribute("user")!=null)
            host=((User)session.getAttribute("user")).getUsername();
        session.setAttribute("register","successful");
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        if(balance!=null)
            user.setBalance(balance);
        UserManager um=new UserManager();
        int num=um.Add(user);
        if(num>0){
            if(host==null)
                response.sendRedirect("Login.jsp");
            else{
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("UserID", user.getUserID());
                map.put("username",username);
                map.put("password",password);
                map.put("balance",user.getBalance());
                ObjectMapper objMap = new ObjectMapper();
                objMap.writeValue(response.getWriter(), map);
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException{
        doGet(request,response);
    }
}
