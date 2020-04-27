package proiect.fis.store.model.databases;

import proiect.fis.store.model.Customer;

import java.sql.*;

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

    public static final String INSERT_CUSTOMER = "INSERT OR IGNORE INTO " + TABLE_NAME + " (" +
            COLUMN_USERNAME + "," + COLUMN_NAME + "," + COLUMN_PASSWORD + ")" +
            " VALUES " + "(?,?,?)";

    public static final String SEARCH_CUSTOMER = "SELECT *" + " FROM " + TABLE_NAME + " WHERE " +
            COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + "= ?";

    private Connection connection;
    private PreparedStatement insertCustomer ;
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

    public boolean add(String username, String name, String password) {
        try {
            try {
                if (connection == null) {
                    connection = DriverManager.getConnection(CONNECTION_STRING);
                }
            } catch (SQLException e) {
                System.out.println("Error connecting to database " + e.getMessage());
                e.printStackTrace();
                return false;
            }
            insertCustomer = connection.prepareStatement(INSERT_CUSTOMER);
            insertCustomer.setString(1, username);
            insertCustomer.setString(2, name);
            insertCustomer.setString(3, password);
            int x = insertCustomer.executeUpdate();
            return x > 0;
        } catch (SQLException e) {
            System.out.println("Failed to register the customer " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try {
                if(insertCustomer != null)
                    insertCustomer.close();
            } catch (SQLException e) {
                System.out.println("Couldn't close the connection for this prepared statement " + e.getMessage());
            }
        }
    }

    public Customer searchCustomer(String username, String encryptedPassword) {
        Customer customer = null;

        try (PreparedStatement searchCustomer = connection.prepareStatement(SEARCH_CUSTOMER)) {
            searchCustomer.setString(1, username);
            searchCustomer.setString(2, encryptedPassword);

            ResultSet resultSet = searchCustomer.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString(COLUMN_NAME);
                int password_changed = resultSet.getInt(COLUMN_PASSWORD_STATUS);
                customer = new Customer(username, name, encryptedPassword, password_changed);
            }
            return customer;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database " + e.getMessage());
            e.printStackTrace();
            return null;
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
