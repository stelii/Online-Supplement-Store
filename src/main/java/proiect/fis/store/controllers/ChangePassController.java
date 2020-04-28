package proiect.fis.store.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import proiect.fis.store.model.Customer;
import proiect.fis.store.model.HashPassword;
import proiect.fis.store.model.Supplier;
import proiect.fis.store.model.databases.CustomersDB;
import proiect.fis.store.model.databases.SupplierDB;

import java.io.IOException;

public class ChangePassController {
    @FXML
    private Button backToMainPage;
    @FXML
    private Button changePassBtn;
    @FXML
    private PasswordField passwordInput;

    private Customer customer ;
    private Supplier supplier;

    public void initialize(){
        backToMainPage.setDisable(true);
    }

    public void setSupplier(Supplier supplier){
        this.supplier = supplier;
    }
    public void setCustomer(Customer customer){
        this.customer = customer;
    }


    @FXML
    public void switchToMainPage() {
        if(customer != null){
            try {
                Stage stage = (Stage) backToMainPage.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/customer_page.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {

            }
        }else{
            try {
                Stage stage = (Stage) backToMainPage.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/supplier_page.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {

            }
        }

    }

    public boolean changePassword(){
        String username;
        String password = passwordInput.getText();
        passwordInput.clear();

        String hashedPassword = HashPassword.encrypt(password);

        if(customer != null){
            username = customer.getUsername();
            CustomersDB customersDB = CustomersDB.getInstance();

            if(customersDB.updatePassword(username,hashedPassword)){
                backToMainPage.setDisable(false);
                return true;
            }

            return false;
        }
        username = supplier.getUsername();
        SupplierDB supplierDB = SupplierDB.getInstance();
        if(supplierDB.updatePassword(username,hashedPassword)){
            backToMainPage.setDisable(false);
            return true ;
        }

        return false ;
    }

}
