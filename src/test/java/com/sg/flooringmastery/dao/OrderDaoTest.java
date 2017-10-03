/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alejandro
 */
public class OrderDaoTest {

    private OrderDao dao = new OrderProdDaoFileImpl();

    public OrderDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws FlooringPersistenceException {

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addOrder method, of class OrderDao.
     */
    @Test
    public void testAddOrder() throws Exception {

        Order order = new Order(1);
        order.setCustomerName("Alejandro");
        order.setProductType("Wood");
        order.setState("IN");
        order.setArea(new BigDecimal(10));
        order.setDate("010101");
        
        dao.addOrder(order);
   
        assertEquals(2, dao.getAllOrders().size());
    }

    /**
     * Test of getOrdersFromCertainDate method, of class OrderDao.
     */
    @Test
    public void testGetOrdersFromCertainDate() throws Exception {
        dao.getOrdersFromCertainDate("010101");       
        assertEquals(1, dao.getAllOrders().size());
    }

    /**
     * Test of removeOrder method, of class OrderDao.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        dao.getOrder(9, "010101");
        dao.removeOrder(9, "010101");
        assertEquals(0, dao.getAllOrders().size());
    }

    /**
     * Test of saveCurrentWork method, of class OrderDao.
     */
    @Test
    public void testSaveCurrentWork() throws Exception {
        Order order = new Order(1);
        order.setCustomerName("Alejandro");
        order.setProductType("Wood");
        order.setState("IN");
        order.setArea(new BigDecimal(10));
        order.setDate("092017");
        order.setSqFtLaborCost(BigDecimal.ZERO);
        order.setSqFtMaterialCost(BigDecimal.ZERO);
        order.setTaxRate(BigDecimal.ZERO);
        order.setTotalLaborCost(BigDecimal.ZERO);
        order.setTotalMaterialCost(BigDecimal.ZERO);
        order.setTotalTax(BigDecimal.ONE);
        order.setTotalTotal(BigDecimal.ZERO);
        
        dao.addOrder(order);
        dao.saveCurrentWork();
        
        assertEquals(2, dao.getOrdersFromCertainDate("092017").size());
    }

    /**
     * Test of getOrder method, of class OrderDao.
     */
    @Test
    public void testGetOrder() throws Exception {
        assertEquals("Camry", dao.getOrder(9, "010101").getCustomerName());
    }

    /**
     * Test of getTotalOfOrders method, of class OrderDao.
     */
    @Test
    public void testGetTotalOfOrders() throws Exception {
        int total = dao.getTotalOfOrders();
        assertEquals(total, dao.getTotalOfOrders());
    }

    /**
     * Test of editOrder method, of class OrderDao.
     */
    @Test
    public void testEditOrder() throws FlooringPersistenceException {
    }

    /**
     * Test of getAllOrders method, of class OrderDao.
     */
    @Test
    public void testGetAllOrders() {
    }

}
