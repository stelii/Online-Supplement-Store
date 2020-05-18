package proiect.fis.store.model.databases;

import proiect.fis.store.model.Product;

import java.sql.*;

public class DemandsDB {
    public static final String DB_NAME = "store.db";
    private static String filepath = System.getProperty("user.home") + System.getProperty("file.separator") + DB_NAME;
    public static final String CONNECTION_STRING = "jdbc:sqlite:" + filepath;
    public static final String TABLE_NAME = "Demands";
    public static final String ADD_DEMAND = "INSERT INTO " + TABLE_NAME + " VALUES (?,?,?)";
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
    public boolean addDemand(Product product) {
        String productName = product.getName();
        double productPrice = product.getPrice();
        int productQuantity = product.getQuantity();
        try(PreparedStatement preparedStatement = connection.prepareStatement(ADD_DEMAND)) {
            preparedStatement.setString(1, productName);
            preparedStatement.setDouble(2, productPrice);
            preparedStatement.setInt(3, productQuantity);
            int flag = preparedStatement.executeUpdate();
            return flag > 0;

        }catch (SQLException e) {
            System.out.println("Error");
            return false;
        }
    }
}