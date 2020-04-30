package proiect.fis.store.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import proiect.fis.store.model.Customer;
import proiect.fis.store.model.Order;
import proiect.fis.store.model.Product;
import proiect.fis.store.model.databases.OrdersDB;
import proiect.fis.store.model.databases.StockDB;

import java.util.Observable;
import java.util.Random;

public class BucketController {
    private Customer customer ;
    private ObservableList<Product> bucketList;

    @FXML
    private TableView<Product> myTable;
    @FXML
    private TableColumn<Product,String> nameColumn ;
    @FXML
    private TableColumn<Product,Integer> quantityColumn ;
    @FXML
    private TableColumn<Product,Double> priceColumn ;

    @FXML
    private Button placeOrderButton;

    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        myTable.setItems(bucketList);
    }


    public void placeOrder(){
        ObservableList<Product> productsToBeOrdered = myTable.getItems();

        OrdersDB ordersDB = OrdersDB.getInstance();
        StockDB stockDB = StockDB.getInstance();

        for(int i = 0 ; i < productsToBeOrdered.size(); i++){
            String name = productsToBeOrdered.get(i).getName();
            int quantity = productsToBeOrdered.get(i).getQuantity();
            double price = productsToBeOrdered.get(i).getPrice();
            String username = customer.getUsername();
            String deliveryStatus = deliveryRandom();

            Order order = new Order(name,price,quantity,deliveryStatus,username);

            if(ordersDB.add(order)){
                stockDB.withdrawQuantity(productsToBeOrdered.get(i),quantity);
            }else{
                //
            }
        }
    }

    public void setData (Customer customer, ObservableList<Product> bucket) {
        this.customer = customer;
        this.bucketList = bucket;
    }

    private String deliveryRandom(){
        Random random = new Random();
        int number = random.nextInt(3);
        System.out.println(number);

        switch (number){
            case 0 : return "Processing";
            case 1 : return "On Its way";
            case 2 : return "Delivered";
            default :return null ;
        }
    }















}
