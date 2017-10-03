/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Alejandro
 */
public class OrderProdDaoStubImpl implements OrderDao {

    Order onlyOrder;
    List<Order> orderList = new ArrayList<>();

    public OrderProdDaoStubImpl() {
        onlyOrder = new Order(1);
        onlyOrder.setCustomerName("John Doe");
        onlyOrder.setState("IN");
        onlyOrder.setProductType("Wood");
        onlyOrder.setArea(new BigDecimal(10));
        onlyOrder.setDate("092017");
        onlyOrder.setSqFtLaborCost(BigDecimal.ZERO);
        onlyOrder.setSqFtMaterialCost(BigDecimal.ZERO);
        onlyOrder.setTaxRate(BigDecimal.ZERO);
        onlyOrder.setTotalLaborCost(BigDecimal.ZERO);
        onlyOrder.setTotalMaterialCost(BigDecimal.ZERO);
        onlyOrder.setTotalTax(BigDecimal.ONE);
        onlyOrder.setTotalTotal(BigDecimal.ZERO);

        orderList.add(onlyOrder);
    }

    @Override
    public Order addOrder(Order order) throws FlooringPersistenceException {
        if (order.getOrderNum() == onlyOrder.getOrderNum()) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public List<Order> getOrdersFromCertainDate(String date) throws FlooringPersistenceException {
        return orderList.stream()
                .filter(o -> o.getDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public Order removeOrder(int orderNum, String date) throws FlooringPersistenceException {
        if (orderNum == onlyOrder.getOrderNum() && date.equals(onlyOrder.getDate())) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public void saveCurrentWork() throws FlooringPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order getOrder(int orderNum, String date) throws FlooringPersistenceException {
        if (orderNum == onlyOrder.getOrderNum() && date.equals(onlyOrder.getDate())) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public int getTotalOfOrders() throws FlooringPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order editOrder(Order order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Order> getAllOrders() {
        return orderList;
    }

}
