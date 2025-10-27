package utill;

import DB.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtill {

    public  static  <T>  T execute(String sql, Object... args) throws SQLException {
        PreparedStatement pstn= DBConnection.getInstance().getConnection().prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            pstn.setObject((i+1),args[i]);


        }
        if (sql.startsWith("SELECT") || sql.startsWith("select")){
            return (T) pstn.executeQuery();
        }
        return (T)(Boolean) (pstn.executeUpdate()>0);

    }
}
