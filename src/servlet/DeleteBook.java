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
import manager.BookManager;
import java.util.*;
import org.codehaus.jackson.map.ObjectMapper;

@WebServlet("/DeleteBook")
public class DeleteBook extends HttpServlet{
    public void doGet(HttpServletRequest request,HttpServletResponse response)
        throws ServletException,IOException{
        response.setContentType("text/html;charset=UTF-8");

        int BookID= Integer.parseInt(request.getParameter("BookID"));
        BookManager bm=new BookManager();
        int num=bm.Delete(BookID);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("success", true);
        ObjectMapper objMap = new ObjectMapper();
        objMap.writeValue(response.getWriter(), map);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException{
        doGet(request,response);
    }
}
