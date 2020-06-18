package proiect.fis.store.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import proiect.fis.store.model.Customer;
import proiect.fis.store.model.Order;
import proiect.fis.store.model.Product;
import proiect.fis.store.model.databases.OrdersDB;

import java.io.IOException;

public class DeliveryStatusController {
    private Customer customer ;

    @FXML
    private TableView<Order> myTable;
    @FXML
    private TableColumn<Order,String> nameColumn ;
    @FXML
    private TableColumn<Order,Integer> quantityColumn ;
    @FXML
    private TableColumn<Order,Double> priceColumn ;
    @FXML
    private TableColumn<Order,String> statusColumn;

    @FXML
    private Button backToMainButton ;

    public void initialize(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("deliveryStatus"));
//
        myTable.setItems(getOrders());
    }


    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    private ObservableList<Order> getOrders(){
        OrdersDB ordersDB = OrdersDB.getInstance();
        return ordersDB.getOrders(customer);
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

