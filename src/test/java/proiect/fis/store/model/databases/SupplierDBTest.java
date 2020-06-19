package proiect.fis.store.model.databases;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.sql.*;

import static org.junit.Assert.*;

public class SupplierDBTest extends ApplicationTest {
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_PASSWORD_STATUS = "password_changed";
    public static final String TEST_TABLE = "testTable";
    public static final String DB_NAME = "store.db";
    private static String filepath = System.getProperty("user.home") + System.getProperty("file.separator") + DB_NAME;
    public static final String CONNECTION_STRING = "jdbc:sqlite:" + filepath;
    public static final String TEST_INSERT = "INSERT OR IGNORE INTO " + TEST_TABLE + " (" +
            "username" + "," + "name" + "," + "password" + ")" +
            " VALUES " + "(?,?,?)";
    public static final String CREATE_TEST_TABLE = "CREATE TABLE IF NOT EXISTS " + TEST_TABLE +
            "(" + COLUMN_USERNAME + " TEXT, " +
            COLUMN_NAME + " TEXT," + COLUMN_PASSWORD + " TEXT," + COLUMN_PASSWORD_STATUS + " INTEGER DEFAULT 0," +
            "UNIQUE " + "(" + COLUMN_USERNAME + ")" + ")";
    public static final String DELETE_ALL = "DELETE FROM " + TEST_TABLE;
    public static final String CHANGE_PASSWOWRD_CUSTOMER = "UPDATE " + TEST_TABLE + " SET " + COLUMN_PASSWORD + " = ?," +
            COLUMN_PASSWORD_STATUS + " = 1 WHERE " + COLUMN_USERNAME + "= ?";
    SupplierDB supplierDB = SupplierDB.getInstance();
    private Connection connection;
    PreparedStatement insertSupplier;

    @Before
    public void setUp() throws SQLException{
        connection = DriverManager.getConnection(CONNECTION_STRING);
        Statement statement = connection.createStatement();
        statement.execute(CREATE_TEST_TABLE);
        statement.execute(DELETE_ALL);

    }
    @After
    public void finalize() {
        supplierDB.closeConnection();
    }
    @Test
    public void getInstance() {
        assertNotNull(supplierDB);
    }

    @Test
    public void openConnection() {
        assertNotNull(supplierDB);
        supplierDB.closeConnection();
    }

//    @Test
//    public void add() throws SQLException {
//        String username = "testUsername";
//        String name = "testName";
//        String password = "testPassword";
//        boolean res = supplierDB.add(username, name, password);
////            insertSupplier = connection.prepareStatement(TEST_INSERT);
////            insertSupplier.setString(1, username);
////            insertSupplier.setString(2, name);
////            insertSupplier.setString(3, password);
////            int res = insertSupplier.executeUpdate();
////            System.out.println(res);
//        assertTrue(res);
//
//    }

}