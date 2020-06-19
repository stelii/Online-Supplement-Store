package proiect.fis.store.model;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;

public class SupplierTest extends ApplicationTest {

    @Test
    public void getUsername() {
        Supplier supplier = new Supplier("testSupplier", "Supplier", "thisIsATest", 0);
        assertNotNull(supplier);
        assertEquals("testSupplier", supplier.getUsername());
    }

    @Test
    public void getName() {
        Supplier supplier = new Supplier("testSupplier", "Supplier", "thisIsATest", 0);
        assertNotNull(supplier);
        assertEquals("Supplier", supplier.getName());
    }

    @Test
    public void getPassword() {
        Supplier supplier = new Supplier("testSupplier", "Supplier", "thisIsATest", 0);
        assertNotNull(supplier);
        assertEquals("thisIsATest", supplier.getPassword());
    }

    @Test
    public void getPassword_changed() {
        Supplier supplier = new Supplier("testSupplier", "Supplier", "thisIsATest", 0);
        assertNotNull(supplier);
        assertEquals(0, supplier.getPassword_changed());
    }
}