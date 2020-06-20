package proiect.fis.store.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Callback;
import proiect.fis.store.model.Product;

import java.io.IOException;

public class ManagerController {
    @FXML
    private Button logoutButton;
    @FXML
    private Button clientsPageButton;
    @FXML
    private Button demandsPageButton;
    @FXML
    private Button stockPageButton;

    private ObservableList<Product> demandsList;

    @FXML
    public boolean logout() {
        try {
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login_page.fxml"));
            Parent logout = loader.load();
            Scene scene = new Scene(logout);
            stage.setScene(scene);
            stage.setTitle("Login Page");
            stage.show();
            return true;
        } catch (IOException e) {
            System.out.println("Error");
            return false;
        }
    }
    public void setData(ObservableList<Product> demands) {
        this.demandsList = demands;
    }
    @FXML
    public boolean goToClientsPage() {
        try {
            Stage stage = (Stage) clientsPageButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/customer_list_page.fxml"));
            Parent cp = loader.load();
            Scene scene = new Scene(cp);
            stage.setScene(scene);
            scene.getStylesheets().add("/tableviewCSS.css");
            stage.setTitle("View Clients");
            stage.show();
            return true;
        } catch (IOException e) {
            System.out.println("Error");
            return false;
        }
    }

    @FXML
    public boolean goToDemandsPage() {
//        Stage stage = (Stage)viewBucketButton.getScene().getWindow();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/bucket_page.fxml"));
//        loader.setControllerFactory(new Callback<Class<?>, Object>() {
//            @Override
//            public Object call(Class<?> param) {
//                if(param == BucketController.class){
//                    BucketController controller = new BucketController();
//                    controller.setData(customer,bucket);
//                    return controller;
//                }else{
//                    try{
//                        return param.newInstance();
//                    }catch (Exception e){
//                        throw new RuntimeException(e);
//                    }
//                }
//            }
//        });
//        try{
//            Parent root = loader.load();
//            Scene scene = new Scene(root);
       // scene.getStylesheets().add("/tableviewCSS.css");
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
        Stage stage = (Stage)demandsPageButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/demands_page.fxml"));
        loader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> param) {
                if(param == DemandsPageController.class) {
                    DemandsPageController controller = new DemandsPageController();
                    controller.setData(demandsList);
                    return controller;
                }else{
                    try {
                        return param.newInstance();
                    }catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        try {
            Parent dp = loader.load();
            Scene scene = new Scene(dp);
            stage.setScene(scene);
            scene.getStylesheets().add("/tableviewCSS.css");
            stage.setTitle("View Demands");
            stage.show();
            return true;
        } catch (IOException e) {
            System.out.println("Error");
            return false;
        }
    }

    @FXML
    public boolean goToStockPage() {
//        try {
//            Stage stage = (Stage) stockPageButton.getScene().getWindow();
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/stock_page.fxml"));
//            Parent viewStock = loader.load();
//            Scene viewStockScene = new Scene(viewStock);
//            stage.setScene(viewStockScene);
//            stage.setTitle("View Stock");
//            stage.show();
//            return true;
//        } catch (IOException e) {
//            System.out.println("Error");
//            return false;
//        }
        Stage stage = (Stage) stockPageButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/stock_page.fxml"));
        loader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> param) {
                if(param == StockPageController.class) {
                    StockPageController controller = new StockPageController();
                    controller.setData(demandsList);
                    return controller;
                }else {
                    try{
                        return param.newInstance();
                    }catch (Exception e) {
                        throw new RuntimeException();
                    }
                }
            }
        });
        try {
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            scene.getStylesheets().add("/tableviewCSS.css");
            stage.setTitle("Stock Page");
            stage.show();
            return true;
        }catch (IOException e) {
            System.out.println(e.getStackTrace());
            return false;
        }

    }
}