package manager;

/**
 * Created by jicl on 16/5/4.
 */
import java.sql.*;
import java.sql.Date;
import java.util.*;

import connect.ConnetManager;
import entity.Order;

public class OrderManager extends ConnetManager {
    Connection con=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;
    public int Add(Order order){
        int num=0;
        try{
            con=this.getCon();
            String sql="insert into orders(UserID,BookID,total,time,IsPaid) values(?,?,?,?,?)";
            Date date=new Date(System.currentTimeMillis());
            pstmt=con.prepareStatement(sql);
            pstmt.setInt(1,order.getUserID());
            pstmt.setInt(2,order.getBookID());
            pstmt.setDouble(3,order.getTotal());
            pstmt.setDate(4,date);
            pstmt.setString(5,"NO");
            num=pstmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            this.allClose(con,pstmt,rs);
        }
        return num;
    }
    public List Show(){
        List list=new ArrayList();
        Order order=null;
        String sql="select * from orders";
        try{
            con=ConnetManager.getCon();
            pstmt=con.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
                order=new Order();
                order.setOrderID(rs.getInt("OrderID"));
                order.setUserID(rs.getInt("UserID"));
                order.setBookID(rs.getInt("BookID"));
                order.setTotal(rs.getDouble("total"));
                order.setTime(new java.util.Date(rs.getDate("time").getTime()));
                order.setIsPaid(rs.getString("IsPaid"));
                list.add(order);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.allClose(con,pstmt,rs);
        }
        return list;
    }

    public List ShowUser(int UserID){
        List list=new ArrayList();
        Order order=null;
        String sql="select * from orders where UserID=?";
        try{
            con=ConnetManager.getCon();
            pstmt=con.prepareStatement(sql);
            pstmt.setInt(1,UserID);
            rs=pstmt.executeQuery();
            while(rs.next()){
                order=new Order();
                order.setOrderID(rs.getInt("OrderID"));
                order.setUserID(rs.getInt("UserID"));
                order.setBookID(rs.getInt("BookID"));
                order.setTotal(rs.getDouble("total"));
                //java.sql.Date date=rs.getDate("time");
                order.setTime(new java.util.Date(rs.getDate("time").getTime()));
                order.setIsPaid(rs.getString("IsPaid"));
                list.add(order);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.allClose(con,pstmt,rs);
        }
        return list;
    }

    public Order Search(int OrderID){
        Order order=null;
        String sql="select * from orders where OrderID=?";
        try{
            con=ConnetManager.getCon();
            pstmt=con.prepareStatement(sql);
            pstmt.setInt(1,OrderID);
            rs=pstmt.executeQuery();
            while(rs.next()){
                order=new Order();
                order.setOrderID(rs.getInt("OrderID"));
                order.setUserID(rs.getInt("UserID"));
                order.setBookID(rs.getInt("BookID"));
                order.setTotal(rs.getDouble("total"));
                order.setTime(new java.util.Date(rs.getTime("time").getTime()));
                order.setIsPaid(rs.getString("IsPaid"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.allClose(con,pstmt,rs);
        }
        return order;
    }

    public int CountUser(int UserID){
        Order order=null;
        String sql="select count(*) as UserOrder from orders where UserID=?";
        int count=0;
        try{
            con=ConnetManager.getCon();
            pstmt=con.prepareStatement(sql);
            pstmt.setInt(1,UserID);
            rs=pstmt.executeQuery();
            rs.next();
            count=rs.getInt("UserOrder");
        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.allClose(con,pstmt,rs);
        }
        return count;
    }

    public int CountCategory(String category){
        Order order=null;
        String sql="select count(*) as CategoryOrder from orders natural join books where Category=?";
        int count=0;
        try{
            con=ConnetManager.getCon();
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1,category);
            rs=pstmt.executeQuery();
            rs.next();
            count=rs.getInt("CategoryOrder");
        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.allClose(con,pstmt,rs);
        }
        return count;
    }

    public int CountDate(Date time){
        Order order=null;
        String sql="select count(*) as DateOrder from orders  where time=?";
        int count=0;
        try{
            con=ConnetManager.getCon();
            pstmt=con.prepareStatement(sql);
            pstmt.setDate(1,time);
            rs=pstmt.executeQuery();
            rs.next();
            count=rs.getInt("DateOrder");
        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.allClose(con,pstmt,rs);
        }
        return count;
    }
}
