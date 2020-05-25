package proiect.fis.store.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import proiect.fis.store.model.Customer;
import proiect.fis.store.model.databases.CustomersDB;

import java.io.IOException;

public class AccountDetailsController {


    private Customer customer ;

    @FXML
    private TextField username;
    @FXML
    private TextField name;
    @FXML
    private TextField email;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField address;

    @FXML
    private Button saveChangesButton ;
    @FXML
    private Button backToMainButton;

    public void initialize(){
        System.out.println(customer.getAddress());
        username.setText(customer.getUsername());
        name.setText(customer.getName());
        email.setText(customer.getEmail());
        phoneNumber.setText(customer.getPhone());
        address.setText(customer.getAddress());

        username.setDisable(true);

    }

    @FXML
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


    public void setCustomer(Customer customer){
        this.customer = customer ;
    }

    @FXML
    public void saveChanges(){
        String customerUsername = username.getText();
        String customerName = name.getText();
        String customerPhoneNumber = phoneNumber.getText();
        String customerAddress = address.getText();
        String customerEmail = email.getText();

        CustomersDB customersDB = CustomersDB.getInstance();
        Customer customer = new Customer(customerUsername,customerName,customerEmail,customerPhoneNumber,customerAddress);
        if(customersDB.updateCustomer(customer)){
            this.customer.setName(customerName);
            this.customer.setEmail(customerEmail);
            this.customer.setAddress(customerAddress);
            this.customer.setPhone(customerPhoneNumber);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "The changes were saved", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                alert.close();
            }
            return ;
        }

        Alert alert = new Alert(Alert.AlertType.WARNING, "The changes couldn't be saved", ButtonType.OK);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            alert.close();
        }

    }


    @FXML
    private void setButtonProperties(){
        String customerName = name.getText();
        boolean isDisabled = customerName.isEmpty() || customerName.trim().isEmpty();
        saveChangesButton.setDisable(isDisabled);
    }
}
