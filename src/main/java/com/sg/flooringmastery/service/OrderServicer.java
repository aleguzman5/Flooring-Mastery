/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringAuditDao;
import com.sg.flooringmastery.dao.FlooringPersistenceException;
import com.sg.flooringmastery.dao.OrderDao;
import com.sg.flooringmastery.dao.OrderProdDaoFileImpl;
import com.sg.flooringmastery.dao.ProductDao;
import com.sg.flooringmastery.dao.ProductDaoFileImpl;
import com.sg.flooringmastery.dao.StateDao;
import com.sg.flooringmastery.dao.StateDaoFileImpl;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.State;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Alejandro
 */
public class OrderServicer {

    OrderDao orderDao;
    StateDao stateDao;
    ProductDao productDao;
    private FlooringAuditDao auditDao;
    BigDecimal hund = new BigDecimal(100);
    
    public OrderServicer (OrderDao orderDao, StateDao stateDao, ProductDao productDao, FlooringAuditDao auditDao) {
        this.orderDao = orderDao;
        this.stateDao = stateDao;
        this.productDao = productDao;
        this.auditDao = auditDao;
    }

    public void addOrder(Order order) throws FlooringPersistenceException, OrderDataValidationException {
        validateOrderData(order);
        order.setTotalLaborCost(order.getArea().multiply(order.getSqFtLaborCost()).setScale(2, RoundingMode.HALF_UP));
        order.setTotalMaterialCost(order.getArea().multiply(order.getSqFtMaterialCost()).setScale(2, RoundingMode.HALF_UP));
        order.setTotalTax((order.getTotalLaborCost().add(order.getTotalMaterialCost())).multiply(order.getTaxRate()).divide(hund).setScale(2, RoundingMode.HALF_UP));
        order.setTotalTotal(order.getTotalLaborCost().add(order.getTotalMaterialCost()).add(order.getTotalTax()).setScale(2, RoundingMode.HALF_UP));
        
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyy");
        String date = localDate.format(formatter);
        order.setDate(date);
        orderDao.addOrder(order);
    }

    public List<Order> listOrders(String date) throws FlooringPersistenceException{
        return orderDao.getOrdersFromCertainDate(date);
    }

    public Order getOrder(int orderNum, String date) throws FlooringPersistenceException {
        return orderDao.getOrder(orderNum, date);
    }

    public Order removeOrder(int orderNum, String date) throws FlooringPersistenceException {
        Order removedOrder = orderDao.removeOrder(orderNum, date);
        return removedOrder;
    }

    public Map<Integer, State> getListOfStatesWithTaxes() throws FlooringPersistenceException {
        return stateDao.getAllstates();
    }

    public Map<Integer, Product> getListOfProducts() throws FlooringPersistenceException {
        return productDao.getAllProducts();
    }

    public int getTotalOrders() throws FlooringPersistenceException {
        return orderDao.getTotalOfOrders();
    }

    public void editOrder(Order order) throws OrderDataValidationException {
        validateOrderData(order);
        order.setTotalLaborCost(order.getArea().multiply(order.getSqFtLaborCost()).setScale(2, RoundingMode.HALF_UP));
        order.setTotalMaterialCost(order.getArea().multiply(order.getSqFtMaterialCost()).setScale(2, RoundingMode.HALF_UP));
        order.setTotalTax((order.getTotalLaborCost().add(order.getTotalMaterialCost())).multiply(order.getTaxRate()).divide(hund).setScale(2, RoundingMode.HALF_UP));
        order.setTotalTotal(order.getTotalLaborCost().add(order.getTotalMaterialCost()).add(order.getTotalTax()).setScale(2, RoundingMode.HALF_UP));
        orderDao.editOrder(order);
    }
    
    public void saveWork() throws FlooringPersistenceException {
        orderDao.saveCurrentWork();
    }

    private void validateOrderData(Order order) throws OrderDataValidationException {
        
        if (order.getCustomerName() == null
                || order.getCustomerName().trim().length() == 0 && order.getArea().compareTo(new BigDecimal(0)) <= 0) {
            throw new OrderDataValidationException("ERROR: Customer's Name is required and area has to be greater than 0");
        }
        if (order.getCustomerName() == null
                || order.getCustomerName().trim().length() == 0) {
            throw new OrderDataValidationException("ERROR: Customer's Name is required");
        }
        if (order.getArea().compareTo(new BigDecimal(0)) <= 0) {
            throw new OrderDataValidationException("ERROR: Area has to be greater than 0");
        }
    }
}
