package manager;

/**
 * Created by jicl on 16/5/4.
 */

import java.sql.*;
import entity.Book;
import connect.ConnetManager;
import java.util.*;



public class BookManager extends ConnetManager{
    Connection con=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;

    public int Delete(int BookID){
        int num=0;
        String sql="delete from books where BookID=?";
        try{
            con=this.getCon();
            pstmt=con.prepareStatement(sql);
            pstmt.setInt(1, BookID);
            num=pstmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            this.allClose(con, pstmt, null);
        }
        return num;
    }

    public Book Search(int BookID){
        Book bookBean=new Book();
        String sql="select * from books where BookID=?";
        try {
            con=this.getCon();
            pstmt=con.prepareStatement(sql);
            pstmt.setInt(1,BookID);
            rs=pstmt.executeQuery();
            while(rs.next()){
                bookBean.setBookID(BookID);
                bookBean.setAuthor(rs.getString("author"));
                bookBean.setDescription(rs.getString("description"));
                bookBean.setPrice(rs.getDouble("price"));
                bookBean.setTitle(rs.getString("title"));
                bookBean.setCategory(rs.getString("category"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.allClose(con, pstmt, rs);
        }
        return bookBean;
    }



    public int Update(Book bookBean,int BookID){
        int num=0;
        String sql="update books set BookID=?, title=?,  author=?, price=?,description=?, category=?  where BookID=?";
        try{
            con=this.getCon();
            pstmt=con.prepareStatement(sql);
            pstmt.setInt(1, bookBean.getBookID());
            pstmt.setString(2, bookBean.getTitle());
            pstmt.setString(3, bookBean.getAuthor());
            pstmt.setDouble(4, bookBean.getPrice());
            pstmt.setString(5, bookBean.getDescription());
            pstmt.setString(6, bookBean.getCategory());
            pstmt.setInt(7,BookID);
            num=pstmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            this.allClose(con, pstmt, null);
        }
        return num;
    }
    public int Add(Book bookBean){
        int num=0;
        String sql="insert into books(BookID,title,author,price,description,category) values(?,?,?,?,?,?)";
        try{
            con=this.getCon();
            pstmt=con.prepareStatement(sql);
            pstmt.setInt(1, bookBean.getBookID());
            pstmt.setString(2, bookBean.getTitle());
            pstmt.setString(3, bookBean.getAuthor());
            pstmt.setDouble(4, bookBean.getPrice());
            pstmt.setString(5, bookBean.getDescription());
            pstmt.setString(6, bookBean.getCategory());
            num=pstmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            this.allClose(con, pstmt, null);
        }
        return num;
    }
    public List Show(){
        List list=new ArrayList();
        Book bookBean=null;
        String sql="select * from books";
        try{
            con=ConnetManager.getCon();
            pstmt=con.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
                bookBean=new Book();
                bookBean.setBookID(rs.getInt("BookID"));
                bookBean.setTitle(rs.getString("title"));
                bookBean.setAuthor(rs.getString("author"));
                bookBean.setPrice(rs.getDouble("price"));
                bookBean.setDescription(rs.getString("description"));
                bookBean.setCategory(rs.getString("category"));
                list.add(bookBean);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.allClose(con,pstmt,rs);
        }
        return list;
    }
}

