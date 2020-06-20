package proiect.fis.store.model.databases;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import proiect.fis.store.model.Product;

import static org.junit.Assert.*;


public class StockDBTest extends ApplicationTest {
    StockDB stockDB = StockDB.getInstance();

    @Before
    public void setUp() throws Exception {
        stockDB.openConnection();
    }

    @After
    public void tearDown() throws Exception {
        stockDB.closeConnection();
    }

    @Test
    public void getInstance() {
        StockDB stockDB = StockDB.getInstance();
        assertNotNull(stockDB);
    }

    @Test
    public void openConnection() {
        StockDB stockDB = StockDB.getInstance();
        assertTrue(stockDB.openConnection());
    }

    @Test
    public void getProducts() {
        StockDB stockDB = StockDB.getInstance();
        assertTrue(stockDB.closeConnection());
    }

    @Test
    public void withdrawQuantity() {
        Product product = new Product("withdrawTest", 50, 50);
        stockDB.addProduct(product);
        assertTrue(stockDB.withdrawQuantity(product, 20));
    }

    @Test
    public void updateProduct() {
        Product product = new Product("test", 50, 50);
        stockDB.addProduct(product);
        assertTrue(stockDB.updateProduct(product));
    }

    @Test
    public void addProduct() {
        assertTrue(stockDB.addProduct(new Product("testProduct", 50, 50)));
    }

    @Test
    public void getProduct() {
        Product product = new Product("Product", 50, 50);
        stockDB.addProduct(product);
        assertEquals(product, stockDB.getProduct(product));
    }

    @Test
    public void closeConnection() {
        StockDB stockDB = StockDB.getInstance();
        assertTrue(stockDB.closeConnection());
    }
}
