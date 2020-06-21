package proiect.fis.store.model.databases;

import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import proiect.fis.store.model.Customer;

import static org.junit.Assert.*;

public class CustomersDBTest {
    CustomersDB instance  = CustomersDB.getInstance();

    @Before
    public void setUp() throws Exception {
        instance.open();
    }

    @After
    public void tearDown() throws Exception {
        instance.close();
    }

    @Test
    public void getCustomers(){
        ObservableList<Customer> customers = instance.getCustomers();
        int dim = customers.size();
        assertEquals(1,dim);
    }

    @Test
    public void open() {
       boolean result = instance.open();
       assertTrue(result);
    }

    @Test
    public void add() {
        boolean result = instance.add("user1","name1","parola1");
        assertTrue(result);
    }

    @Test
    public void searchCustomer() {
        Customer customer = instance.searchCustomer("user1","parola1");
        assertNotNull(customer);
    }

    @Test
    public void updatePassword() {
        boolean result = instance.updatePassword("user1","parolaNoua");
        assertTrue(result);
    }

    @Test
    public void updateCustomer() {
        boolean result = instance.updateCustomer(new Customer("user1",
                "name1","user@gmail.com","0372321612","Ro"));
        assertTrue(result);
    }

    @Test
    public void close() {
        boolean result = instance.close();
        assertTrue(result);
    }
}