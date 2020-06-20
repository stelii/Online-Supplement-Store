package proiect.fis.store.model.databases;

import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import proiect.fis.store.model.Customer;
import proiect.fis.store.model.Order;

import static org.junit.Assert.*;

public class OrdersDBTest extends ApplicationTest{
    OrdersDB instance = OrdersDB.getInstance();

    @Before
    public void setUp() throws Exception {
        instance.open();
    }

    @After
    public void tearDown() throws Exception {
        instance.close();
    }

    @Test
    public void open() {
    boolean result = instance.open();
    assertTrue(result);
    }

    @Test
    public void close() {
        boolean result = instance.close();
        assertTrue(result);
    }

    @Test
    public void add() {
        boolean result =
                instance.add(new Order("name1",20.5,5,"Delivered","user1"));
        assertTrue(result);
    }

    @Test
    public void updateOrder() {
        boolean result =
                instance.updateOrder(new Order("name1",20.5,44,"Delivered","user1"));
        assertTrue(result);
    }

    @Test
    public void getOrder() {
        Order order =
                instance.getOrder(new Order("name1",20.5,44,"Delivered","user1"));
        assertNotNull(order);
    }

    @Test
    public void getOrders() {
        ObservableList<Order> orders =
                instance.getOrders(new Customer("user1","name1","parolaNoua",0));
        int dim = orders.size();
        assertEquals(1,dim);
    }
}