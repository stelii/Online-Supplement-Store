package proiect.fis.store.model.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StockDB {
    public static final String TABLE_NAME = "stock";
    public static final String DB_NAME = "store.db";
    private static String filepath = System.getProperty("user.home") + System.getProperty("file.separator") + DB_NAME;
    public static final String CONNECTION_STRING = "jdbc:sqlite:" + filepath;

    private Connection connection;
    private static StockDB instance = new StockDB();

    private StockDB() {
    }

    public static StockDB getInstance() {
        return instance;
    }

    public void openConnection() {

        try {
            connection = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS" + DB_NAME +
                    " (name TEXT, price REAL, quantity INTEGER, delivery TEXT);");
        } catch (SQLException e) {
            System.out.println("create table error");
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            //
        }
    }
}