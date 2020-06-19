package proiect.fis.store.model.databases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import proiect.fis.store.model.Customer;
import proiect.fis.store.model.Order;
import proiect.fis.store.model.Product;

import java.sql.*;

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

    public static final String ADD_QUANTITY = "UPDATE " +
            TABLE_NAME + " SET quantity = quantity + ? WHERE name = ?";

    public static final String INSERT_PRODUCT = "INSERT INTO " + TABLE_NAME + " VALUES (?,?,?,?,?)";
    public static final String SEARCH_PRODUCT = "SELECT * FROM " + TABLE_NAME + " WHERE name = ?";


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

    public boolean add(Order order) {
        String username = order.getUsername();
        String productName = order.getName();
        double price = order.getPrice();
        int quantity = order.getQuantity();
        String deliveryStatus = order.getDeliveryStatus();

        try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT)){
            preparedStatement.setString(1,productName);
            preparedStatement.setDouble(2,price);
            preparedStatement.setInt(3,quantity);
            preparedStatement.setString(4,deliveryStatus);
            preparedStatement.setString(5,username);

            int rez = preparedStatement.executeUpdate();
            return rez > 0;
        }catch (SQLException e){
            //
            return false;
        }
    }

    public boolean updateOrder(Order order){
        String name = order.getName();
        int quantity = order.getQuantity();

        try(PreparedStatement preparedStatement = connection.prepareStatement(ADD_QUANTITY)){
            preparedStatement.setInt(1,quantity);
            preparedStatement.setString(2,name);
            int rez = preparedStatement.executeUpdate();
            return rez > 0 ;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public Order getOrder(Order order){
        String name = order.getName();

        try(PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_PRODUCT)){
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return order;
            }
            return null;
        }catch (SQLException e){
            return null ;
        }
    }




    public ObservableList<Order> getOrders(Customer customer){
        String username = customer.getUsername();
        System.out.println(username);
        ObservableList<Order> orders = FXCollections.observableArrayList();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " +
                    TABLE_NAME + " WHERE customer_name = ?");
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String name = resultSet.getString(1);
                double price = resultSet.getDouble(2);
                int quantity = resultSet.getInt(3);
                String deliveryStatus = resultSet.getString(4);
                Order order = new Order(name,price,quantity,deliveryStatus,username);
                orders.add(order);
            }
            return orders;
        }catch (SQLException e){
            //
            return null ;
        }
    }


}
