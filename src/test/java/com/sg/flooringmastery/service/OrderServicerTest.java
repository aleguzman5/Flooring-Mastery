/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dto.Order;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Alejandro
 */
public class OrderServicerTest {

    private OrderServicer service;

    public OrderServicerTest() {
//        OrderDao orderDao = new OrderProdDaoStubImpl();
//        ProductDao productDao = new ProductDaoFileImpl();
//        StateDao stateDao = new StateDaoFileImpl();
//        
//        service = new OrderServicer(orderDao, stateDao, productDao);

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", OrderServicer.class);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addOrder method, of class OrderServicer.
     */
    @Test
    public void testAddOrder() throws Exception {
        Order order = new Order(2);
        order.setCustomerName("Lisa");
        order.setState("MI");
        order.setProductType("Tile");
        order.setArea(new BigDecimal(50));
        order.setDate("092017");
        order.setSqFtLaborCost(BigDecimal.ZERO);
        order.setSqFtMaterialCost(BigDecimal.ZERO);
        order.setTaxRate(BigDecimal.ZERO);
        order.setTotalLaborCost(BigDecimal.ZERO);
        order.setTotalMaterialCost(BigDecimal.ZERO);
        order.setTotalTax(BigDecimal.ONE);
        order.setTotalTotal(BigDecimal.ZERO);
        service.addOrder(order);
    }

    @Test
    public void testCreateOrderInvalidData() throws Exception {
        Order order = new Order(2);
        order.setCustomerName("Nancy");
        order.setState("MI");
        order.setProductType("Tile");
        order.setArea(new BigDecimal(-50));
        order.setDate("092017");
        order.setSqFtLaborCost(BigDecimal.ZERO);
        order.setSqFtMaterialCost(BigDecimal.ZERO);
        order.setTaxRate(BigDecimal.ZERO);
        order.setTotalLaborCost(BigDecimal.ZERO);
        order.setTotalMaterialCost(BigDecimal.ZERO);
        order.setTotalTax(BigDecimal.ONE);
        order.setTotalTotal(BigDecimal.ZERO);

        try {
            service.addOrder(order);
            fail("Expected OrderDataValidationException was not thrown.");
        } catch (OrderDataValidationException e) {
            return;
        }
    }

    /**
     * Test of listOrders method, of class OrderServicer.
     */
    @Test
    public void testListOrders() throws Exception {
        assertEquals(1, service.listOrders("092017").size());
    }

    @Test
    public void testListOrdersWithWrongDate() throws Exception {
        assertEquals(0, service.listOrders("092117").size());
    }

    /**
     * Test of getOrder method, of class OrderServicer.
     */
    @Test
    public void testGetOrder() throws Exception {
        Order order = service.getOrder(1, "092017");
        assertNotNull(order);
        order = service.getOrder(2, "092017");
        assertNull(order);
        order = service.getOrder(1, "092117");
        assertNull(order);
    }

    @Test
    public void testRemoveOrder() throws Exception {
        Order order = service.removeOrder(1, "092017");
        assertNotNull(order);
        order = service.removeOrder(2, "092017");
        assertNull(order);
        order = service.removeOrder(1, "092117");
        assertNull(order);
    }

    /**
     * Test of getListOfStatesWithTaxes method, of class OrderServicer.
     */
    @Test
    public void testGetListOfStatesWithTaxes() throws Exception {
        assertEquals(4, service.getListOfStatesWithTaxes().size());
        assertEquals(new BigDecimal(6.25), service.getListOfStatesWithTaxes().get(1).getTax());
        assertEquals("IN", service.getListOfStatesWithTaxes().get(4).getName());
    }

    /**
     * Test of getListOfProducts method, of class OrderServicer.
     */
    @Test
    public void testGetListOfProducts() throws Exception {
        assertEquals(5, service.getListOfProducts().size());
        assertEquals("Carpet", service.getListOfProducts().get(1).getType());
    }

    /**
     * Test of getTotalOrders method, of class OrderServicer.
     */
    @Test
    public void testGetTotalOrders() throws Exception {
    }

    /**
     * Test of editOrder method, of class OrderServicer.
     */
    @Test
    public void testEditOrder() {
    }

    /**
     * Test of saveWork method, of class OrderServicer.
     */
    @Test
    public void testSaveWork() throws Exception {
    }

}
