package proiect.fis.store;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import proiect.fis.store.model.databases.*;

import java.sql.SQLException;

public class Main extends Application {
    CustomersDB customersDB = CustomersDB.getInstance();
    SupplierDB supplierDB = SupplierDB.getInstance();
    DemandsDB demandsDB = DemandsDB.getInstance();
    OrdersDB ordersDB = OrdersDB.getInstance();
    StockDB stockDB = StockDB.getInstance();

    @Override
    public void init() throws Exception {
        openDatabases();
        super.init();
    }

    @Override
    public void stop() throws Exception {
        closeDatabases();
        super.stop();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/login_page.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void openDatabases() {
        customersDB.open();
        supplierDB.openConnection();
        demandsDB.openConnection();
        ordersDB.open();
        stockDB.openConnection();
    }

    private void closeDatabases(){
        customersDB.close();
        supplierDB.closeConnection();
        demandsDB.closeConnection();
        ordersDB.close();
        stockDB.closeConnection();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
