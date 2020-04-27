package proiect.fis.store.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import proiect.fis.store.model.DataSource;
import proiect.fis.store.model.EncryptPassword;

import java.io.IOException;
import java.security.SecureRandom;

public class RegisterController {

    @FXML
    private Button registerButton ;
    @FXML
    private TextField usernameInput ;
    @FXML
    private TextField nameInput ;
    @FXML
    private PasswordField passwordInput ;
    @FXML
    private Button toLoginBtn;

    public void initialize(){
        registerButton.setDisable(true);
    }



    @FXML
    public boolean register(){
        DataSource dataSource = DataSource.getInstance();

        String username = usernameInput.getText().trim();
        String name = nameInput.getText().trim();
//        String pass = passwordInput.getText();
        String generatedPass = generateRandomPassword();
        String hashedPassword = EncryptPassword.encrypt(generatedPass);
        System.out.println("Generated password: " + generatedPass);

        //clear input data
        usernameInput.clear();
        nameInput.clear();
//        passwordInput.clear();
        //clear input data

        if(dataSource.add(username,name,hashedPassword)){
            Alert alert = new Alert(Alert.AlertType.NONE,"You have successfully registered",ButtonType.OK);
            TextArea textField = new TextArea("YOUR PASSWORD IS: \n" + generatedPass);
            textField.setWrapText(true);
            textField.setEditable(false);
            alert.getDialogPane().setContent(textField);


            alert.showAndWait();
            if(alert.getResult() == ButtonType.OK){
                alert.close();
            }
//            System.out.println("Customer registered successfully");
            return true ;
        }

        Alert alert = new Alert(Alert.AlertType.WARNING,"This account already exists", ButtonType.OK);
        alert.showAndWait();
        if(alert.getResult() == ButtonType.OK){
            alert.close();
        }
        return false;
    }

    @FXML
    public void switchToLogin(){
        try{
            Stage stage = (Stage) toLoginBtn.getScene().getWindow();
            Parent loginView = FXMLLoader.load(getClass().getResource("../view/login_page.fxml"));
            stage.setTitle("login page");
            Scene scene = new Scene(loginView,600,400);
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            //
        }
    }

    @FXML
    private void setButtonProperties(){
        String username = usernameInput.getText();
        String name = nameInput.getText();
//        String pass = passwordInput.getText();
        boolean isDisabled = username.isEmpty() || username.trim().isEmpty() ||
                name.isEmpty() || name.trim().isEmpty();
//                            || pass.isEmpty() || pass.trim().isEmpty();

        registerButton.setDisable(isDisabled);
    }

    private String generateRandomPassword(){
        final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom random = new SecureRandom();
        int len = 10 ;

        StringBuilder sb = new StringBuilder(len);
        for(int i = 0 ; i < len ; i++)
            sb.append(AB.charAt(random.nextInt(AB.length())));

        return sb.toString();
    }

}
