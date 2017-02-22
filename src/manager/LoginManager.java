package manager;

/**
 * Created by jicl on 16/5/4.
 */
import java.sql.*;
import connect.ConnetManager;
import entity.User;

public class LoginManager extends ConnetManager{
    public User check(String us,String pw){
        User user=null;
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sql="select * from users where username=?";
        try{
            con=ConnetManager.getCon();
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1,us);
            rs=pstmt.executeQuery();
            if(rs.next()&&rs.getString("password").equals(pw)){
                user=new User();
                user.setUserID(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
            }
            else{
                System.out.println("Wrong password!");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.allClose(con, pstmt, rs);
        }
        return user;
    }
}
