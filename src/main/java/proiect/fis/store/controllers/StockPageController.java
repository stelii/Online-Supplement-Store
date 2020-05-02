package proiect.fis.store.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import proiect.fis.store.model.Product;
import proiect.fis.store.model.databases.StockDB;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Predicate;

public class StockPageController {
    @FXML
    private TextField filterField;
    @FXML
    private Button backFromStock;
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, Double> priceColumn;
    @FXML
    private TableColumn<Product, Integer> quantityColumn;

    public ObservableList<Product> getProductsList() {
        StockDB database = StockDB.getInstance();
        ObservableList<Product> products = database.getProducts();
        return products;
    }

    private FilteredList<Product> filteredProducts = new FilteredList<>(getProductsList(), new Predicate<Product>() {
        @Override
        public boolean test(Product product) {
            return true ;
        }
    });

    public void initialize(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("Quantity"));

        productTable.setItems(filteredProducts);
    }

    public void searchProduct(){
        filteredProducts.setPredicate(new Predicate<Product>() {
            @Override
            public boolean test(Product product) {
                boolean rezult =  product.getName().toLowerCase().contains(filterField.getText().toLowerCase().trim());
                return rezult ;
            }
        });
    }
    @FXML
    public boolean backToManagerPage() {
        try {
            Stage stage = (Stage) backFromStock.getScene().getWindow();
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
}

