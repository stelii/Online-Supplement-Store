package proiect.fis.store.model.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SupplierDB {

    public static final String TABLE_NAME = "suppliers";
    public static final String DB_NAME = "store.db";
    private static String filepath = System.getProperty("user.home") + System.getProperty("file.separator") + DB_NAME;
    public static final String CONNECTION_STRING = "jdbc:sqlite:" + filepath;

    private Connection connection;
    private static SupplierDB instance = new SupplierDB();
    private SupplierDB() {}
    public static SupplierDB getInstance() {
        return instance;
    }

    public boolean openConnection() {

        try{
            connection = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS " + DB_NAME + " (username TEXT,name TEXT,password TEXT);");
            return true;
        }
        catch (SQLException e) {
            System.out.println("create table error");
            return false;
        }
    }

    public void closeConnection () {
        try {
            if (connection!=null)
                connection.close();
        }
        catch (SQLException e) {
            //
        }
    }
}
