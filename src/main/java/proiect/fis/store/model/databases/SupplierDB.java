package proiect.fis.store.model.databases;

import java.sql.*;

public class SupplierDB {

    public static final String TABLE_NAME = "suppliers";
    public static final String DB_NAME = "store.db";
    private static String filepath = System.getProperty("user.home") + System.getProperty("file.separator") + DB_NAME;
    public static final String CONNECTION_STRING = "jdbc:sqlite:" + filepath;

    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_PASSWORD_STATUS = "password_changed";

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
            "(" + COLUMN_USERNAME + " TEXT, " +
            COLUMN_NAME + " TEXT," + COLUMN_PASSWORD + " TEXT," + COLUMN_PASSWORD_STATUS + " INTEGER DEFAULT 0," +
            "UNIQUE " + "(" + COLUMN_USERNAME + ")" + ")";

    public static final String INSERT_SUPPLIER = "INSERT OR IGNORE INTO " + TABLE_NAME + " (" +
            "username" + "," + "name" + "," + "password" + ")" +
            " VALUES " + "(?,?,?)";

    private Connection connection;
    private PreparedStatement insertSupplier ;

    private static SupplierDB instance = new SupplierDB();
    private SupplierDB() {}
    public static SupplierDB getInstance() {
        return instance;
    }

    public boolean openConnection() {

        try{
            connection = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = connection.createStatement();
            statement.execute(CREATE_TABLE);
            return true;
        }
        catch (SQLException e) {
            System.out.println("create table error");
            return false;
        }
    }

    public boolean add(String username,String name,String password){
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
            insertSupplier = connection.prepareStatement(INSERT_SUPPLIER);
            insertSupplier.setString(1, username);
            insertSupplier.setString(2, name);
            insertSupplier.setString(3, password);
            int x = insertSupplier.executeUpdate();
            return x > 0;
        } catch (SQLException e) {
            System.out.println("Failed to register the customer " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try {
                if(insertSupplier != null)
                    insertSupplier.close();
            } catch (SQLException e) {
                System.out.println("Couldn't close the connection for this prepared statement " + e.getMessage());
            }
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
