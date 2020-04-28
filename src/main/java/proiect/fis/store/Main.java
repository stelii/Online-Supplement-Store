package proiect.fis.store;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import proiect.fis.store.model.databases.*;

import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void init() throws Exception {
        openDatabases();
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/login_page.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void openDatabases() {
        CustomersDB customersDB = CustomersDB.getInstance();
        customersDB.open();
        SupplierDB supplierDB = SupplierDB.getInstance();
        supplierDB.openConnection();
        DemandsDB demandsDB = DemandsDB.getInstance();
        demandsDB.openConnection();
        OrdersDB ordersDB = OrdersDB.getInstance();
        ordersDB.open();
        StockDB stockDB = StockDB.getInstance();
        stockDB.openConnection();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
