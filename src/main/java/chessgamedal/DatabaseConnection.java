package chessgamedal;

import chessgameshared.logging.Logger;

import java.sql.Connection;
import java.sql.DriverManager;

public final class DatabaseConnection {
    private static final String CONNECTIONSTRING = "jdbc:mysql://studmysql01.fhict.local:3306/dbi369008?useSSL=false";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection connection;

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(CONNECTIONSTRING, "dbi369008", "chessgame123");
        } catch (Exception exc) {
            Logger.getInstance().log(exc);
        }
        return connection;
    }
}
