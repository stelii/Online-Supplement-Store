package proiect.fis.store.model.databases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import proiect.fis.store.model.Product;

import java.sql.*;

public class StockDB {
    public static final String TABLE_NAME = "stock";
    public static final String DB_NAME = "store.db";
    private static String filepath = System.getProperty("user.home") + System.getProperty("file.separator") + DB_NAME;
    public static final String CONNECTION_STRING = "jdbc:sqlite:" + filepath;

    private Connection connection;
    private static StockDB instance = new StockDB();
    private StockDB() {}
    public static StockDB getInstance() {
        return instance;
    }

    public boolean openConnection() {

        try{
            connection = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (name TEXT,price REAL,quantity INTEGER);");
            return true;
        }
        catch (SQLException e) {
            System.out.println("create table error");
            return false;
        }

    }

    public ObservableList<Product> returnProducts(){
        ObservableList<Product> products = FXCollections.observableArrayList();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + TABLE_NAME );
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String name = resultSet.getString(1);
                double price = resultSet.getDouble(2);
                int quantity = resultSet.getInt(3);
                Product product = new Product(name,price,quantity);
                products.add(product);
            }
            return products;
        }catch (SQLException e){
            //
            return null ;
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
