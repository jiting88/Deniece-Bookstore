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
import entity.Book;
import entity.Cart;
import entity.User;
import manager.BookManager;

@WebServlet("/AddBookToCart")
public class AddBookToCart extends HttpServlet {
    public void doGet(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException{
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        if(user==null)
            request.getRequestDispatcher("Login.jsp").forward(request,response);
        else{
            int BookID=Integer.parseInt(request.getParameter("BookID"));
            BookManager bm=new BookManager();
            Book bookBean=bm.Search(BookID);
            Map cart=(Map)session.getAttribute("cart");
            if(cart==null) {
                cart = new HashMap();
                session.setAttribute("cart", cart);
            }
            Cart item=(Cart)cart.get(BookID);
            if(item!=null)
                item.setQuantity(item.getQuantity()+1);
            else
                cart.put(bookBean.getBookID(),new Cart(bookBean,1));
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException{
        doGet(request,response);
    }
}
