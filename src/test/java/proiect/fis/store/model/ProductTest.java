package proiect.fis.store.model;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;

public class ProductTest extends ApplicationTest {
    @Test
    public void getName() {
        Product product = new Product("Bcaa", 39.99, 10);
        assertNotNull(product);
        assertEquals("bcaa", product.getName().toLowerCase());
    }

    @Test
    public void getQuantity() {
        Product product = new Product("Bcaa", 39.99, 10);
        assertNotNull(product);
        assertEquals(10, product.getQuantity());
    }

    @Test
    public void getPrice() {
        Product product = new Product("Bcaa", 39.99, 10);
        assertNotNull(product);
        assertEquals(39.99, product.getPrice(), 0);
    }

    @Test
    public void setName() {
        Product product = new Product("Bcaa", 39.99, 10);
        assertNotNull(product);
        product.setName("testName");
        assertEquals("testname", product.getName().toLowerCase());
    }

    @Test
    public void setQuantity() {
        Product product = new Product("Bcaa", 39.99, 10);
        assertNotNull(product);
        product.setQuantity(50);
        assertEquals(50, product.getQuantity());
    }

    @Test
    public void setPrice() {
        Product product = new Product("Bcaa", 39.99, 10);
        assertNotNull(product);
        product.setPrice(99.99);
        assertEquals(99.99, product.getPrice(), 0);
    }

    @Test
    public void testEquals() {
    }

    @Test
    public void updateQuantity() {
    }
}