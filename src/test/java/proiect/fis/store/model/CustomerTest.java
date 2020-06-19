package proiect.fis.store.model;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;

public class CustomerTest extends ApplicationTest {
    @Test
    public void getUsername() {
        Customer customer = new Customer("testCustomer", "Customer", "thisIsATest", 0);
        assertNotNull(customer);
        assertEquals("testCustomer", customer.getUsername());
    }

    @Test
    public void getName() {
        Customer customer = new Customer("testCustomer", "Customer", "thisIsATest", 0);
        assertNotNull(customer);
        assertEquals("Customer", customer.getName());
    }

    @Test
    public void getPassword() {
        Customer customer = new Customer("testCustomer", "Customer", "thisIsATest", 0);
        assertNotNull(customer);
        assertEquals("thisIsATest", customer.getPassword());
    }

    @Test
    public void getEmail() {
        Customer customer = new Customer("testCustomer", "Customer", "testemail@customer.com", "123456789", "Physical Address Null");
        assertNotNull(customer);
        assertEquals("testemail@customer.com", customer.getEmail());
    }

    @Test
    public void getPhone() {
        Customer customer = new Customer("testCustomer", "Customer", "testemail@customer.com", "123456789", "Physical Address Null");
        assertNotNull(customer);
        assertEquals("123456789", customer.getPhone());
    }

    @Test
    public void getAddress() {
        Customer customer = new Customer("testCustomer", "Customer", "testemail@customer.com", "123456789", "Physical Address Null");
        assertNotNull(customer);
        assertEquals("Physical Address Null", customer.getAddress());
    }

    @Test
    public void getPassword_changed() {
        Customer customer = new Customer("testCustomer", "Customer", "thisIsATest", 0);
        assertNotNull(customer);
        assertEquals(0, customer.getPassword_changed());
    }

    @Test
    public void setName() {
        Customer customer = new Customer("testCustomer", "Customer", "thisIsATest", 0);
        assertNotNull(customer);
        assertEquals("Customer", customer.getName());
        customer.setName("testName");
        assertEquals("testName", customer.getName());

    }

    @Test
    public void setEmail() {
        Customer customer = new Customer("testCustomer", "Customer", "testemail@customer.com", "123456789", "Physical Address Null");
        assertNotNull(customer);
        assertEquals("testemail@customer.com", customer.getEmail());
        customer.setEmail("testemail2@customer.com");
        assertEquals("testemail2@customer.com", customer.getEmail());
    }

    @Test
    public void setPhone() {
        Customer customer = new Customer("testCustomer", "Customer", "testemail@customer.com", "123456789", "Physical Address Null");
        assertNotNull(customer);
        assertEquals("123456789", customer.getPhone());
        customer.setPhone("987654321");
        assertEquals("987654321", customer.getPhone());
    }

    @Test
    public void setAddress() {
        Customer customer = new Customer("testCustomer", "Customer", "testemail@customer.com", "123456789", "Physical Address Null");
        assertNotNull(customer);
        assertEquals("Physical Address Null", customer.getAddress());
        customer.setAddress("Physical Address Null 2");
        assertEquals("Physical Address Null 2", customer.getAddress());
    }
}