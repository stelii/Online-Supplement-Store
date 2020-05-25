package proiect.fis.store.model.databases;

import javafx.collections.ObservableList;
import proiect.fis.store.model.Product;

import java.sql.*;

public class DemandsDB {
    public static final String DB_NAME = "store.db";
    private static String filepath = System.getProperty("user.home") + System.getProperty("file.separator") + DB_NAME;
    public static final String CONNECTION_STRING = "jdbc:sqlite:" + filepath;
    public static final String TABLE_NAME = "Demands";
    public static final String ADD_DEMAND = "INSERT INTO " + TABLE_NAME + " VALUES (?,?,?)";
    public static final String SEARCH_PRODUCT = "SELECT *" + " FROM " + TABLE_NAME + " WHERE " +
            "name =" + "?";
    public static final String UPDATE_QUANTITY = "UPDATE " + TABLE_NAME + " SET " + "quantity" + " = quantity + ? " + "WHERE " + "name " + "= ?";
//    public static final String CHANGE_PASSWOWRD_CUSTOMER = "UPDATE " + TABLE_NAME + " SET " + COLUMN_PASSWORD + " = ?," +
//            COLUMN_PASSWORD_STATUS + " = 1 WHERE " + COLUMN_USERNAME + "= ?";
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

    public ObservableList<Product> getDemands(){
        return null;
    }

    public boolean addDemand(Product product) {
        if(searchProduct(product)) {
            return updateProductQuantity(product);
        }
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
    public boolean searchProduct(Product product) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_PRODUCT)) {
            preparedStatement.setString(1, product.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet.getString("name"));
            if(resultSet.next()) {
                return true;
            }
        }catch (SQLException e) {
            //System.out.println("Error searchProduct");
            return false;
        }
        return false;
    }
    public boolean updateProductQuantity(Product product) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUANTITY)) {
            preparedStatement.setInt(1, product.getQuantity());
            preparedStatement.setString(2, product.getName());
            int flag = preparedStatement.executeUpdate();
            return flag > 0;
        }catch (SQLException e) {
            System.out.println("Error updateProductQuantity");
            return false;
        }

    }
}