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
import entity.Book;
import org.codehaus.jackson.map.ObjectMapper;
import java.util.*;

@WebServlet("/AddBook")
public class AddBook extends HttpServlet {
    public void doGet(HttpServletRequest request,HttpServletResponse response)
        throws ServletException,IOException{
        response.setContentType("text/html;charset=UTF-8");

        String title=request.getParameter("title");
        String author=request.getParameter("author");
        double price=Double.parseDouble(request.getParameter("price"));
        String description=request.getParameter("description");
        String category=request.getParameter("category");

        Book bookBean=new Book();
        bookBean.setTitle(title);
        bookBean.setAuthor(author);
        bookBean.setPrice(price);
        bookBean.setDescription(description);
        bookBean.setCategory(category);

        BookManager bm=new BookManager();
        int num=bm.Add(bookBean);

        List list=new ArrayList();
        list.add(bookBean);
        Map<String,Object> map = new HashMap();
        map.put("total", 1);
        map.put("rows", list);
        ObjectMapper objMap = new ObjectMapper();
        objMap.writeValue(response.getWriter(), map);


    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,IOException{
        doGet(request,response);
    }
}
