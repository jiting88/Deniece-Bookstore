package servlet;

/**
 * Created by jicl on 16/5/8.
 */
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import manager.UserManager;
import entity.User;
import org.codehaus.jackson.map.ObjectMapper;
import java.util.*;

@WebServlet("/ModifyUser")
public class ModifyUser extends HttpServlet {
    public void doGet(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException{
        response.setContentType("text/html;charset=UTF-8");

        int UserID=Integer.parseInt(request.getParameter("UserID"));
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        Double balance=Double.parseDouble(request.getParameter("balance"));

        User user=new User();
        user.setUserID(UserID);
        user.setUsername(username);
        user.setPassword(password);
        user.setBalance(balance);

        UserManager um=new UserManager();
        int num=um.Update(user,UserID);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("UserID", user.getUserID());
        map.put("username",username);
        map.put("password",password);
        map.put("balance",user.getBalance());
        ObjectMapper objMap = new ObjectMapper();
        objMap.writeValue(response.getWriter(), map);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException{
        doGet(request,response);
    }
}
