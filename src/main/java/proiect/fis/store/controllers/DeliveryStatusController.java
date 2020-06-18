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




    public void setCustomer(Customer customer){
        this.customer = customer;
    }



}


