package proiect.fis.store.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import proiect.fis.store.model.Product;
import proiect.fis.store.model.Supplier;
import proiect.fis.store.model.databases.DemandsDB;
import proiect.fis.store.model.databases.StockDB;

public class SupplierController {
    private Supplier supplier;
    private ObservableList<Product> demands;

    @FXML
    private Button acceptDemandsButton;

    @FXML
    private TableView<Product> myTable;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, Integer> quantityColumn;
    @FXML
    private TableColumn<Product, Double> priceColumn;

    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        demands = getDemands();

        myTable.setItems(demands);
    }

    private ObservableList<Product> getDemands() {
        ObservableList<Product> products;

        DemandsDB demandsDB = DemandsDB.getInstance();
        products = demandsDB.getDemands();
        return products;
    }

    @FXML
    public void acceptDemands() {
        StockDB stockDB = StockDB.getInstance();
        DemandsDB demandsDB = DemandsDB.getInstance();

        for (Product p : demands) {
            Product searchedProduct = stockDB.getProduct(p);
            if (searchedProduct != null) {
                stockDB.updateProduct(p);
            } else {
                stockDB.addProduct(p);
            }
            demandsDB.deleteDemand(p);
        }

        demands.removeAll(demands);
        myTable.refresh();

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "THE PRODUCTS WERE TRANSFERRED", ButtonType.OK);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            alert.close();
        }
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
