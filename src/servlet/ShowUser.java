package servlet;

/**
 * Created by jicl on 16/5/5.
 */
import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import manager.UserManager;
import entity.User;
import net.sf.json.JSONArray;
import org.codehaus.jackson.map.ObjectMapper;

@WebServlet("/ShowUser")
public class ShowUser extends HttpServlet{
    public void doGet(HttpServletRequest request,HttpServletResponse response)
        throws ServletException,IOException{
        response.setContentType("text/html;charset=UTF-8");
        User user=new User();
        UserManager um=new UserManager();
        List users=um.Show();
        JSONArray js=JSONArray.fromObject(users);
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
