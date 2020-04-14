package proiect.fis.store.model.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class OrdersDB {
    public static final String DB_NAME = "store.db";
    public static final String TABLE_NAME = "orders";
    private static String filepath = System.getProperty("user.home") + System.getProperty("file.separator") + DB_NAME;
    public static final String CONNECTION_STRING = "jdbc:sqlite:" + filepath;

    public static final String COLUMN_CUSTOMER_NAME = "customer_name";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_DELIVERY_STATUS = "delivery_status";

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
            " (" + COLUMN_NAME + " TEXT," + COLUMN_PRICE + " REAL," + COLUMN_QUANTITY + " INTEGER," +
            COLUMN_DELIVERY_STATUS + " TEXT," + COLUMN_CUSTOMER_NAME + " TEXT" +
            ")";

    private Connection connection;

    private static OrdersDB instance = new OrdersDB();

    private OrdersDB() {
    }

    public static OrdersDB getInstance() {
        return instance;
    }

    public boolean open(){
        try{
            connection = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = connection.createStatement();
            statement.execute(CREATE_TABLE);
            return true ;
        }catch (SQLException e){
            //
            return false;
        }
    }

    public boolean close(){
        try{
            if(connection != null)
                connection.close();
            return true ;
        }catch (SQLException e){
            //
            return false;
        }
    }
}
