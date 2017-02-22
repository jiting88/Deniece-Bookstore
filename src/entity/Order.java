package entity;
import java.util.Date;
/**
 * Created by jicl on 16/5/4.
 */
public class Order {
    int OrderID;
    int UserID;
    int BookID;
    double total;
    Date time;
    String IsPaid;

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID){
        this.OrderID=OrderID;
    }

    public int getUserID(){
        return UserID;
    }

    public void setUserID(int UserID){
        this.UserID=UserID;
    }

    public int getBookID(){
        return BookID;
    }

    public void setBookID(int BookID){
        this.BookID=BookID;
    }

    public double getTotal(){
        return total;
    }

    public void setTotal(double total){
        this.total=total;
    }

    public Date getTime(){
        return time;
    }

    public void setTime(Date time){
        this.time=time;
    }

    public String getIsPaid(){
        return IsPaid;
    }

    public void setIsPaid(String IsPaid){
        this.IsPaid=IsPaid;
    }
}

