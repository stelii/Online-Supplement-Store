package proiect.fis.store.model.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomersDB {

    public static final String DB_NAME = "store.db";
    public static final String TABLE_NAME = "customers";
    private static String filepath = System.getProperty("user.home") + System.getProperty("file.separator") + DB_NAME;
    public static final String CONNECTION_STRING = "jdbc:sqlite:" + filepath;

    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_ADDRESS = "addres";
    public static final String COLUMN_PASSWORD_STATUS = "password_changed";

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
            "(" + COLUMN_USERNAME + " TEXT, " +
            COLUMN_NAME + " TEXT," + COLUMN_PASSWORD + " TEXT," + COLUMN_PASSWORD_STATUS + " INTEGER DEFAULT 0," +
            COLUMN_EMAIL + " TEXT," + COLUMN_PHONE + " TEXT," +
            COLUMN_ADDRESS + " TEXT," +
            "UNIQUE " + "(" + COLUMN_USERNAME + ")" + ")";

    private Connection connection;

    private static CustomersDB instance = new CustomersDB();

    private CustomersDB() {
    }

    public static CustomersDB getInstance() {
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
