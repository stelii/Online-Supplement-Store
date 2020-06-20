package proiect.fis.store.model.databases;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import proiect.fis.store.model.Product;

import static org.junit.Assert.*;


public class DemandsDBTest extends ApplicationTest {
    DemandsDB demandsDB = DemandsDB.getInstance();


    @Before
    public void setUp() throws Exception {
        demandsDB.openConnection();
    }

    @After
    public void tearDown() throws Exception {
        demandsDB.closeConnection();
    }

    @Test
    public void getInstance() {
        DemandsDB demandsDB = DemandsDB.getInstance();
        assertNotNull(demandsDB);
    }

    @Test
    public void openConnection() {
        DemandsDB demandsDB = DemandsDB.getInstance();
        assertTrue(demandsDB.openConnection());
    }

    @Test
    public void closeConnection() {
        DemandsDB demandsDB = DemandsDB.getInstance();
        assertTrue(demandsDB.closeConnection());
    }

    @Test
    public void getDemands() {
        //  assertEquals(1, demandsDB.getDemands().size());
    }

    @Test
    public void deleteDemand() {
        Product testProduct = new Product("testProduct", 39.99, 50);
        assertNotNull(testProduct);
        demandsDB.addDemand(testProduct);
        assertTrue( demandsDB.deleteDemand(testProduct));
    }

    @Test
    public void addDemand() {
        Product testProduct = new Product("testProduct", 39.99, 50);

        assertTrue(demandsDB.addDemand(testProduct));
    }

    @Test
    public void searchProduct() {
        Product testProduct = new Product("testProduct", 39.99, 50);
        assertNotNull(testProduct);
        demandsDB.addDemand(testProduct);
        assertTrue(demandsDB.searchProduct(testProduct));

    }

    @Test
    public void updateProductQuantity() {
        Product testProduct = new Product("testProduct", 39.99, 50);
        assertNotNull(testProduct);
        demandsDB.addDemand(testProduct);
        assertTrue(demandsDB.updateProductQuantity(testProduct));
    }
}