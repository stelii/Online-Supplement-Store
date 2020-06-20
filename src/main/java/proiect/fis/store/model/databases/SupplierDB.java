package proiect.fis.store.model.databases;

import proiect.fis.store.model.Supplier;

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

    public static final String SEARCH_SUPPLIER = "SELECT *" + " FROM " + TABLE_NAME + " WHERE " +
            COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + "= ?";

    public static final String CHANGE_PASSWOWRD_CUSTOMER = "UPDATE " + TABLE_NAME + " SET " + COLUMN_PASSWORD + " = ?," +
            COLUMN_PASSWORD_STATUS + " = 1 WHERE " + COLUMN_USERNAME + "= ?";

    private Connection connection;
    private PreparedStatement insertSupplier;

    private static SupplierDB instance = new SupplierDB();

    private SupplierDB() {
    }

    public static SupplierDB getInstance() {
        return instance;
    }

    public boolean openConnection() {

        try {
            connection = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = connection.createStatement();
            statement.execute(CREATE_TABLE);
            return true;
        } catch (SQLException e) {
            System.out.println("create table error");
            return false;
        }
    }

    public boolean add(String username, String name, String password) {
        try {
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
                if (insertSupplier != null)
                    insertSupplier.close();
            } catch (SQLException e) {
                System.out.println("Couldn't close the connection for this prepared statement " + e.getMessage());
            }
        }
    }

    public Supplier searchSupplier(String username, String encryptedPassword) {
        Supplier supplier = null;

        try (PreparedStatement searchCustomer = connection.prepareStatement(SEARCH_SUPPLIER)) {

            searchCustomer.setString(1, username);
            searchCustomer.setString(2, encryptedPassword);

            ResultSet resultSet = searchCustomer.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString(COLUMN_NAME);
                int password_changed = resultSet.getInt(COLUMN_PASSWORD_STATUS);
                supplier = new Supplier(username, name, encryptedPassword, password_changed);
            }
            return supplier;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public boolean updatePassword(String username, String newPassword) {
        try (PreparedStatement updatePass = connection.prepareStatement(CHANGE_PASSWOWRD_CUSTOMER)) {

            updatePass.setString(1, newPassword);
            updatePass.setString(2, username);
            updatePass.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error on database " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    public boolean closeConnection() {
        try {
            if (connection != null)
                connection.close();

            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
