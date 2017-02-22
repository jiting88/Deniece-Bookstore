package manager;

/**
 * Created by jicl on 16/5/4.
 */
import java.sql.*;
import java.util.*;

import com.mysql.jdbc.ConnectionGroupManager;
import connect.ConnetManager;
import entity.User;

public class UserManager extends ConnetManager{
    Connection con=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;

    public int Add(User user){
        int num=0;
        String sql="insert into users(username,password,balance) values(?,?,?)";
        try{
            con=ConnetManager.getCon();
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1,user.getUsername());
            pstmt.setString(2,user.getPassword());
            pstmt.setDouble(3,user.getBalance());
            num=pstmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.allClose(con,pstmt,rs);
        }
        return num;
    }

    public List Show(){
        List list=new ArrayList();
        User user=null;
        String sql="select * from users";
        try{
            con=ConnetManager.getCon();
            pstmt=con.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
                user=new User();
                user.setUserID(rs.getInt("UserID"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setBalance(rs.getInt("balance"));
                list.add(user);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.allClose(con,pstmt,rs);
        }
        return list;
    }

    public int Delete(int UserID){
        int num=0;
        String sql="delete from users where UserID=?";
        try{
            con=ConnetManager.getCon();
            pstmt=con.prepareStatement(sql);
            pstmt.setInt(1,UserID);
            num=pstmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.allClose(con,pstmt,rs);
        }
        return num;
    }

    public int Update(User user,int UserID){
        int num=0;
        String sql="update users set UserID=?,username=?,password=?,balance=? where UserID=?";
        try{
            con=ConnetManager.getCon();
            pstmt=con.prepareStatement(sql);
            pstmt.setInt(1,user.getUserID());
            pstmt.setString(2,user.getUsername());
            pstmt.setString(3,user.getPassword());
            pstmt.setDouble(4,user.getBalance());
            pstmt.setInt(5,UserID);
            num=pstmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.allClose(con,pstmt,rs);
        }
        return num;
    }

    public int SearchBalance(int UserID){
        User user=new User();
        String sql="select balance from users where UserID=?";
        int balance=0;
        try {
            con=this.getCon();
            pstmt=con.prepareStatement(sql);
            pstmt.setInt(1,UserID);
            rs=pstmt.executeQuery();
            rs.next();
            balance=rs.getInt("balance");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.allClose(con, pstmt, rs);
        }
        return balance;
    }
}
