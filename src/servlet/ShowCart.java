package servlet;

/**
 * Created by jicl on 16/5/9.
 */
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import entity.Cart;
import entity.Book;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.codehaus.jackson.map.ObjectMapper;

@WebServlet("/ShowCart")
public class ShowCart extends HttpServlet{
    public void doGet(HttpServletRequest request,HttpServletResponse response)
        throws ServletException,IOException{
        HttpSession session=request.getSession();
        Map<Integer,Cart> MyCart=(Map)session.getAttribute("cart");
        Map<String,Object> result = new HashMap<String,Object>();
        List cartlist=new ArrayList();
        for(Map.Entry<Integer,Cart> cart:MyCart.entrySet()){
            Book bookBean=cart.getValue().getBook();
            int quantity=cart.getValue().getQuantity();
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("bookID", cart.getKey());
            map.put("title",bookBean.getTitle());
            map.put("author",bookBean.getAuthor());
            map.put("price",bookBean.getPrice());
            map.put("description",bookBean.getDescription());
            map.put("category",bookBean.getCategory());
            map.put("quantity",quantity);
            cartlist.add(map);
        }
        result.put("total", 1);
        result.put("rows", cartlist);
        ObjectMapper objMap = new ObjectMapper();
        objMap.writeValue(response.getWriter(), result);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException{
        doGet(request,response);
    }
}
