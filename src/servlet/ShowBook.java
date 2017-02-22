package servlet;

/**
 * Created by jicl on 16/5/7.
 */
import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manager.BookManager;
import entity.Book;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.codehaus.jackson.map.ObjectMapper;

@WebServlet("/ShowBook")
public class ShowBook extends HttpServlet{
    public void doGet(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException{
        response.setContentType("text/html;charset=UTF-8");

        Book bookBean=new Book();
        BookManager bm=new BookManager();
        List books=bm.Show();
        JSONArray js=JSONArray.fromObject(books);
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