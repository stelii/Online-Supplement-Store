package proiect.fis.store.model.databases;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import proiect.fis.store.model.Supplier;

import java.sql.*;

import static org.junit.Assert.*;

public class SupplierDBTest extends ApplicationTest {
    SupplierDB instance = SupplierDB.getInstance();

    @Before
    public void setUp() throws SQLException{
        instance.openConnection();
    }

    @After
    public void tearDown() throws Exception {
        instance.closeConnection();
    }

    @Test
    public void openConnection(){
        boolean result = instance.openConnection();
        assertTrue(result);
    }

    @Test
    public void add(){
        boolean result = instance.add("supplier1","name1","parola1");
        assertTrue(result);
    }

    @Test
    public void searchSupplier(){
        Supplier supplier = instance.searchSupplier("supplier1","parola1");
        assertNotNull(supplier);
    }

    @Test
    public void updatePassword(){
        boolean result = instance.updatePassword("supplier1","parolaNoua");
        assertTrue(result);
    }

    @Test
    public void closeConnection(){
        boolean result = instance.closeConnection();
        assertTrue(result);
    }


}