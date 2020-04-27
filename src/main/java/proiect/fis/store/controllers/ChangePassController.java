package proiect.fis.store.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import proiect.fis.store.model.Customer;
import proiect.fis.store.model.Supplier;

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
        try {
            Stage stage = (Stage) backToMainPage.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/customer_page.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {

        }
    }

//    public void changePassword(){
//        DataSource dataSource = DataSource.getInstance();
//        String username = customer.getUsername();
//        String password = passwordInput.getText();
//        System.out.println("new password: " + password);
//
//        passwordInput.clear();
//
//        String hashedPassword = EncryptPassword.encrypt(password);
//        if(dataSource.updatePassword(username,hashedPassword)){
//            backToMainPage.setDisable(false);
//            return ;
//        }
//
//        System.out.println("i didn't succeed");
//    }

}
