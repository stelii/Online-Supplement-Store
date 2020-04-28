package proiect.fis.store.model.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DemandsDB {
    public static final String DB_NAME = "store.db";
    private static String filepath = System.getProperty("user.home") + System.getProperty("file.separator") + DB_NAME;
    public static final String CONNECTION_STRING = "jdbc:sqlite:" + filepath;
    public static final String TABLE_NAME = "Demands";
    private Connection connection;

    public static DemandsDB instance = new DemandsDB();

    private DemandsDB() {

    }

    public static DemandsDB getInstance() {
        return instance;
    }

    public boolean openConnection() {
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (name TEXT,price REAL,quantity INTEGER);");
            return true;
        } catch (SQLException e) {
            System.out.println("create table error");
            return false;
        }
    }

    public void closeConnection() {
        try {
            if(connection != null)
                connection.close();

        } catch (SQLException e) {
            //
        }
    }
}