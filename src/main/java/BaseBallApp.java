
import db.DBConnection;


import java.sql.Connection;
import java.sql.SQLException;


public class BaseBallApp {
    public static void main(String[] args) throws SQLException {

        Connection connection = DBConnection.getInstance();



    }
}
