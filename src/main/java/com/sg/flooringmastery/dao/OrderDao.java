/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Alejandro
 */
public interface OrderDao {
    
    Order addOrder(Order order) throws FlooringPersistenceException;
    
    List<Order> getOrdersFromCertainDate(String date) throws FlooringPersistenceException;
    
    Order removeOrder(int orderNum, String date) throws FlooringPersistenceException;
    
    void saveCurrentWork() throws FlooringPersistenceException;
    
    Order getOrder(int orderNum, String date) throws FlooringPersistenceException;
    
    int getTotalOfOrders() throws FlooringPersistenceException;
    
    Order editOrder (Order order);
    
    List<Order> getAllOrders();
}
