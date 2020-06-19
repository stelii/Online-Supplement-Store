package proiect.fis.store.model;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;

public class OrderTest extends ApplicationTest {

    @Test
    public void getName() {
        Order order = new Order("Order", 49.99, 50,"onItsWay", "testUser");
        assertNotNull(order);
        assertEquals("Order", order.getName());
    }

    @Test
    public void getPrice() {
        Order order = new Order("Order", 49.99, 50,"onItsWay", "testUser");
        assertNotNull(order);
        assertEquals(49.99, order.getPrice(), 0);
    }

    @Test
    public void getQuantity() {
        Order order = new Order("Order", 49.99, 50,"onItsWay", "testUser");
        assertNotNull(order);
        assertEquals(50, order.getQuantity());
    }

    @Test
    public void getDeliveryStatus() {
        Order order = new Order("Order", 49.99, 50,"onItsWay", "testUser");
        assertNotNull(order);
        assertEquals("onItsWay", order.getDeliveryStatus());
    }

    @Test
    public void getUsername() {
        Order order = new Order("Order", 49.99, 50,"onItsWay", "testUser");
        assertNotNull(order);
        assertEquals("testUser", order.getUsername());
    }
}