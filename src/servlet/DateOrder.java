package servlet;

/**
 * Created by jicl on 16/5/9.
 */
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manager.OrderManager;
import net.sf.json.JsonConfig;
import org.codehaus.jackson.map.ObjectMapper;
import entity.JsonDateValueProcessor;
import java.text.SimpleDateFormat;

@WebServlet("/DateOrder")
public class DateOrder extends HttpServlet{
    public void doGet(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException{
        OrderManager om=new OrderManager();
        Date time=new Date();
        JsonDateValueProcessor tool=new JsonDateValueProcessor();
        JsonConfig jsonConfig = new JsonConfig();
        String test=request.getParameter("time");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            time=sdf.parse(test);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int count=om.CountDate(new java.sql.Date(time.getTime()));
        Map<String,Object> map = new HashMap();
        map.put("DateCount", (Integer)count);

        ObjectMapper objMap = new ObjectMapper();
        objMap.writeValue(response.getWriter(), map);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException{
        doGet(request,response);
    }
}
