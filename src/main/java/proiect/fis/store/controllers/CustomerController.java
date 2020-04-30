package proiect.fis.store.controllers;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import proiect.fis.store.model.Customer;
import proiect.fis.store.model.Product;
import proiect.fis.store.model.databases.StockDB;

import java.util.function.Predicate;

public class CustomerController {
    private Customer customer ;

    @FXML
    private TableView<Product> myTable;
    @FXML
    private TableColumn<Product,String> nameColumn ;
    @FXML
    private TableColumn<Product,Integer> quantityColumn ;
    @FXML
    private TableColumn<Product,Double> priceColumn ;

    private FilteredList<Product> filteredProducts = new FilteredList<>(getProducts(), new Predicate<Product>() {
        @Override
        public boolean test(Product product) {
            return true ;
        }
    });

    public void initialize(){

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        myTable.setItems(filteredProducts);

    }

    private ObservableList<Product> getProducts(){
        ObservableList<Product> products ;

        StockDB stockDB = StockDB.getInstance();
        products = stockDB.returnProducts();

        return products;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }
}
