package chessgamedal;

import java.sql.*;

public final class DatabaseConnection {
    private static String CONNECTIONSTRING = "jdbc:mysql://studmysql01.fhict.local:3306/dbi369008?useSSL=false";
    private static Connection connection;
    private static String driver = "com.myxql.cj.jbdc.Driver";
    private static Statement command;
    private static ResultSet data;

    public static Connection getConnection() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(CONNECTIONSTRING, "dbi382485", "chessgame123");
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return connection;
    }
}
