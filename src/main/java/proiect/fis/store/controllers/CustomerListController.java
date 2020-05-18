package proiect.fis.store.controllers;

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
import proiect.fis.store.model.Customer;
import proiect.fis.store.model.Product;
import proiect.fis.store.model.databases.CustomersDB;

import java.io.IOException;
import java.util.function.Predicate;

public class CustomerListController {
    @FXML
    private Button backButton;
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, String> nameColumn;
    @FXML
    private TableColumn<Customer, String> usernameColumn;
    @FXML
    private TableColumn<Customer, String> emailColumn;
    @FXML
    private TableColumn<Customer, String> numberColumn;
    @FXML
    private TableColumn<Customer, String> addressColumn;
    @FXML
    private TextField searchField;

    private ObservableList<Customer> getCustomerList() {
        CustomersDB db = CustomersDB.getInstance();
        return db.getCustomers();
    }
    @FXML
    public boolean goToManagerPage() {
        try {
            Stage stage = (Stage)backButton.getScene().getWindow();
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
    private FilteredList<Customer> filteredList = new FilteredList<>(getCustomerList(), new Predicate<Customer>() {
        @Override
        public boolean test(Customer customer) {
            return true;
        }
    });
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("Username"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("Phone number"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));
        customerTable.setItems(filteredList);
    }
    public void searchCustomer() {
        filteredList.setPredicate(new Predicate<Customer>() {
            @Override
            public boolean test(Customer customer) {
                boolean result = customer.getName().toLowerCase().contains(searchField.getText().toLowerCase().trim());
                return result;
            }
        });
    }
    @FXML
    public void sortCustomers() {
        nameColumn.setSortType(TableColumn.SortType.DESCENDING);
        System.out.println("Sorted");

    }
}
