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
import proiect.fis.store.model.Product;

import java.io.IOException;

public class DemandsPageController {
    private ObservableList<Product> demandsList;
    @FXML
    private TableView<Product> demands;
    @FXML
    private TableColumn<Product,String> nameColumn ;
    @FXML
    private TableColumn<Product,Integer> quantityColumn ;
    @FXML
    private TableColumn<Product,Double> priceColumn ;
    @FXML
    private javafx.scene.control.Button mainPageButton;
    @FXML
    private Button stockPageButton;

    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        demands.setItems(demandsList);
    }
    public void setData (ObservableList<Product> demands) {
        this.demandsList = demands;
    }
    @FXML
    public boolean goToManagerPage() {
        try {
            Stage stage = (Stage)mainPageButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/manager_page.fxml"));
            Parent managerPage = loader.load();
            Scene ManagerPage = new Scene(managerPage);
            stage.setScene(ManagerPage);
            stage.setTitle("Manager Page");
            stage.show();
            return true;
        } catch (IOException e) {
            System.out.println("Error");
            return false;
        }
    }
    @FXML
    public boolean goToStockPage() {
        try{
            Stage stage = (Stage)stockPageButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/stock_page.fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Stock Page");
            stage.show();
            return true;
        }catch (IOException e) {}
        System.out.println("Error");
        return false;
    }
}
