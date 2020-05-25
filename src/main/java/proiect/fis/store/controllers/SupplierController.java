package proiect.fis.store.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import proiect.fis.store.model.Product;
import proiect.fis.store.model.Supplier;
import proiect.fis.store.model.databases.DemandsDB;

public class SupplierController {
    private Supplier supplier;
    private ObservableList<Product> demands ;

    @FXML
    private Button acceptDemandsButton;

    @FXML
    private TableView<Product> myTable;
    @FXML
    private TableColumn<Product,String> nameColumn ;
    @FXML
    private TableColumn<Product,Integer> quantityColumn ;
    @FXML
    private TableColumn<Product,Double> priceColumn ;

    public void initialize(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        demands = getDemands();

        myTable.setItems(demands);
    }

    private ObservableList<Product> getDemands(){
        ObservableList<Product> products ;

        DemandsDB demandsDB = DemandsDB.getInstance();
        products = demandsDB.getDemands();
        return products;
    }

    public void setSupplier(Supplier supplier){
        this.supplier = supplier;
    }
}
