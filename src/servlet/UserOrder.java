package servlet;

/**
 * Created by jicl on 16/5/9.
 */
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manager.OrderManager;
import org.codehaus.jackson.map.ObjectMapper;

@WebServlet("/UserOrder")
public class UserOrder extends HttpServlet{
    public void doGet(HttpServletRequest request,HttpServletResponse response)
        throws ServletException,IOException{
        OrderManager om=new OrderManager();
        int UserID=Integer.parseInt(request.getParameter("UserID"));
        int count=om.CountUser(UserID);

        Map<String,Object> map = new HashMap();
        map.put("UserCount", (Integer)count);

        ObjectMapper objMap = new ObjectMapper();
        objMap.writeValue(response.getWriter(), map);

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException{
        doGet(request,response);
    }
}
