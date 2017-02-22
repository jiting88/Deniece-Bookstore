package manager;

/**
 * Created by jicl on 16/5/4.
 */
import java.sql.*;
import connect.ConnetManager;

public class PayManager extends ConnetManager{
    public int creditProcess(int UserID, double total,int OrderID) throws SQLException {
        int num=0;
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        con=ConnetManager.getCon();
        try {
            String strSql="update users set balance=balance-? where UserID=?";
            pstmt=con.prepareStatement(strSql);
            pstmt.setDouble(1,total);
            pstmt.setInt(2, UserID);
            num=pstmt.executeUpdate();
            pstmt=null;
            String sql="update orders set IsPaid='YES' where OrderID=?";
            pstmt=con.prepareStatement(sql);
            pstmt.setInt(1,OrderID);
            num=pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.allClose(con, pstmt, rs);
        }
        return num;
    }
}
