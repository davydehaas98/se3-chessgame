package chessgamedal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public final class DatabaseConnection {
    private static String CONNECTIONSTRING = "jdbc:mysql://studmysql01.fhict.local:3306/dbi369008?useSSL=false";
    private static Connection connection;
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static Statement command;
    private static ResultSet data;

    public static Connection getConnection() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(CONNECTIONSTRING, "dbi369008", "chessgame123");
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return connection;
    }
}
