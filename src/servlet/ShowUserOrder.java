package servlet;

/**
 * Created by jicl on 16/5/7.
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
import entity.User;
import net.sf.json.JSONArray;
import org.codehaus.jackson.map.ObjectMapper;
import net.sf.json.JsonConfig;
import entity.JsonDateValueProcessor;

@WebServlet("/ShowUserOrder")
public class ShowUserOrder extends HttpServlet{
    public void doGet(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException{
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session=request.getSession();
        int UserID=((User)session.getAttribute("user")).getUserID();
        OrderManager om=new OrderManager();
        List orders=om.ShowUser(UserID);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class , new JsonDateValueProcessor());
        JSONArray js=JSONArray.fromObject(orders,jsonConfig);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("total", 1);
        map.put("rows", js);

        ObjectMapper objMap = new ObjectMapper();
        objMap.writeValue(response.getWriter(), map);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException{
        doGet(request,response);
    }
}
