package servlet;

/**
 * Created by jicl on 16/5/5.
 */
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entity.Cart;
import entity.Book;
import manager.BookManager;
import org.codehaus.jackson.map.ObjectMapper;

@WebServlet("/ModifyBookToCart")
public class ModifyBookToCart extends HttpServlet{
    public void doGet(HttpServletRequest request,HttpServletResponse response)
        throws ServletException,IOException{
        response.setContentType("text/html;charset=UTF-8");
        int quantity=Integer.parseInt(request.getParameter("quantity"));
        int BookID=Integer.parseInt(request.getParameter("BookID"));
        BookManager bm=new BookManager();
        Book bookBean=bm.Search(BookID);
        HttpSession session=request.getSession();
        HashMap cart=(HashMap)session.getAttribute("cart");
        Cart item=(Cart)cart.get(BookID);
        item.setQuantity(quantity);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("title",bookBean.getTitle());
        map.put("author",bookBean.getAuthor());
        map.put("price",bookBean.getPrice());
        map.put("description",bookBean.getDescription());
        map.put("category",bookBean.getCategory());
        map.put("quantity",quantity);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("total", 1);
        List cartlist=new ArrayList();
        cartlist.add(map);
        result.put("rows", cartlist);
        ObjectMapper objMap = new ObjectMapper();
        objMap.writeValue(response.getWriter(), result);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException{
        doGet(request,response);
    }
}
