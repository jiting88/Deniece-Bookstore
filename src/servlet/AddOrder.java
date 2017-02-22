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
import entity.Order;
import entity.User;
import manager.OrderManager;
import org.codehaus.jackson.map.ObjectMapper;

@WebServlet("/AddOrder")
public class AddOrder extends HttpServlet{
    public void doGet(HttpServletRequest request,HttpServletResponse response)
        throws ServletException,IOException{
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        if(user==null)
            request.getRequestDispatcher("Login.jsp").forward(request,response);
        else {
            int UserID=user.getUserID();
            int BookID = Integer.parseInt(request.getParameter("BookID"));
            Double total=Double.parseDouble(request.getParameter("total"));
            Order order=new Order();
            order.setUserID(UserID);
            order.setBookID(BookID);
            order.setTotal(total);

            OrderManager om=new OrderManager();
            int num=om.Add(order);

            List list=new ArrayList();
            list.add(order);
            Map<String,Object> map = new HashMap();
            map.put("total", 1);
            map.put("rows", list);
            ObjectMapper objMap = new ObjectMapper();
            objMap.writeValue(response.getWriter(), map);
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException{
        doGet(request,response);
    }
}
