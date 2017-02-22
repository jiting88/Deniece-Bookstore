package servlet;

/**
 * Created by jicl on 16/5/9.
 */
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import manager.OrderManager;
import manager.PayManager;
import entity.User;
import entity.Order;
import manager.UserManager;
import org.apache.commons.collections.map.HashedMap;
import org.codehaus.jackson.map.ObjectMapper;

import java.sql.SQLException;
import java.util.*;

@WebServlet("/PayOrder")
public class PayOrder extends HttpServlet{
    public void doGet(HttpServletRequest request,HttpServletResponse response)
        throws ServletException,IOException{
        double total=Double.parseDouble(request.getParameter("total"));
        int OrderID=Integer.parseInt(request.getParameter("OrderID"));
        HttpSession session=request.getSession();
        int UserID=((User)session.getAttribute("user")).getUserID();
        int num=0;
        PayManager pm=new PayManager();
        try {
            num=pm.creditProcess(UserID,total,OrderID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Order order=new Order();
        OrderManager om=new OrderManager();
        order=om.Search(OrderID);

        UserManager um=new UserManager();
        int balance=um.SearchBalance(UserID);
        List list=new ArrayList();
        list.add(order);
        Map<String,Object> map = new HashMap();
        map.put("total", 1);
        map.put("rows", list);
        map.put("balance",balance);
        ObjectMapper objMap = new ObjectMapper();
        objMap.writeValue(response.getWriter(), map);

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException{
        doGet(request,response);
    }
}
