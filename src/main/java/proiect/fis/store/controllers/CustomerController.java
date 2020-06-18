package proiect.fis.store.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import proiect.fis.store.model.Customer;
import proiect.fis.store.model.Product;
import proiect.fis.store.model.databases.StockDB;

import java.io.IOException;
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


    private ObservableList<Product> bucket = FXCollections.observableArrayList();
    @FXML
    private Button addToBucketButton;

    @FXML
    private Button viewBucketButton;

    @FXML
    private TextField searchBar ;


    private FilteredList<Product> filteredProducts = new FilteredList<>(getProductsList(), new Predicate<Product>() {
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

    private ObservableList<Product> getProductsList(){
        ObservableList<Product> products ;

        StockDB stockDB = StockDB.getInstance();
        products = stockDB.getProducts();

        return products;
    }

    public void searchProduct(){
        filteredProducts.setPredicate(new Predicate<Product>() {
            @Override
            public boolean test(Product product) {
                boolean rezult =  product.getName().toLowerCase().contains(searchBar.getText().toLowerCase().trim());
                return rezult ;
            }
        });
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }
    public void setBucket(ObservableList<Product> bucket){
        this.bucket = bucket ;
    }

    @FXML
    public void addToBucket(){
        Product product = myTable.getSelectionModel().getSelectedItem();
        if(product.getQuantity() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING,"THE PRODUCT IS NOT AVAILABLE",ButtonType.OK);
            alert.showAndWait();
            if(alert.getResult() == ButtonType.OK){
                alert.close();
            }
            return ;
        }
        product.setQuantity(1);
        bucket.add(product);
        System.out.println(product.getName());
    }

    public void viewBucketPage(){
        Stage stage = (Stage)viewBucketButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/bucket_page.fxml"));
        loader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> param) {
                if(param == BucketController.class){
                    BucketController controller = new BucketController();
                    controller.setData(customer,bucket);
                    return controller;
                }else{
                    try{
                        return param.newInstance();
                    }catch (Exception e){
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        try{
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void viewAccountDetails(){
        Stage stage = (Stage)viewBucketButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/account_details_page.fxml"));
        loader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> param) {
                if(param == AccountDetailsController.class){
                    AccountDetailsController controller = new AccountDetailsController();
                    controller.setCustomer(customer);
                    return controller;
                }else{
                    try{
                        return param.newInstance();
                    }catch (Exception e){
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        try{
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
