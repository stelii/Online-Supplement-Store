package proiect.fis.store.controllers;

import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import proiect.fis.store.model.Customer;
import proiect.fis.store.model.Order;
import proiect.fis.store.model.Product;
import proiect.fis.store.model.databases.OrdersDB;
import proiect.fis.store.model.databases.StockDB;

import java.io.IOException;
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
    private Label quantitySize ;
    @FXML
    private Button plusButton;
    @FXML
    private Button minusButton ;

    @FXML
    private Button deleteButton ;
    @FXML
    private Button backToMainButton;

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
                System.out.println("ok");
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

    public void changeQuantity(ActionEvent e){
        int value = Integer.parseInt(quantitySize.getText());
        if(e.getSource().equals(plusButton)){
            value = value + 1 ;
            quantitySize.setText(value + "");
        }else if(e.getSource().equals(minusButton) && value > 0){
            value = value - 1 ;
            quantitySize.setText(value + "");
        }
    }

    public void saveQuantity(){
        int value = Integer.parseInt(quantitySize.getText());
        quantitySize.setText("1");
        int position = myTable.getSelectionModel().getSelectedIndex();
        if(position < 0){
            Alert alert = new Alert(Alert.AlertType.WARNING,"PLEASE SELECT AN ITEM", ButtonType.OK);
            alert.showAndWait();
            if(alert.getResult() == ButtonType.OK){
                alert.close();
            }
            return ;
        }
        bucketList.get(position).setQuantity(value);
        myTable.refresh();
    }

    public void deleteProduct(){
        int position = myTable.getSelectionModel().getSelectedIndex();
        bucketList.remove(position);
        myTable.refresh();
    }

    public void backToMain(){
        Stage stage = (Stage) backToMainButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/customer_page.fxml"));
        loader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> param) {
                if(param == CustomerController.class){
                    CustomerController controller = new CustomerController();
                    controller.setCustomer(customer);
                    controller.setBucket(bucketList);
                    return controller;
                }else{
                    try{
                        return param.newInstance();
                    }catch (Exception e){
                        return new RuntimeException(e);
                    }
                }
            }
        });

        try{
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            //
        }
    }








}
