/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Alejandro
 */
public class OrderTrainingDaoFileImpl extends OrderProdDaoFileImpl {

    public static final String TOTAL_TRAINING_FILE = "ordersTraining.txt";

    @Override
    public void writeOrders() throws FlooringPersistenceException {
        super.writeOrders(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveCurrentWork() throws FlooringPersistenceException {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadOrders(String date) throws FlooringPersistenceException {
        super.loadOrders(date); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Order> getAllOrders() {
        return super.getAllOrders(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order editOrder(Order order) {
        return super.editOrder(order); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getTotalOfOrders() throws FlooringPersistenceException {
        return super.getTotalOfOrders(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order getOrder(int orderNum, String date) throws FlooringPersistenceException {
        return super.getOrder(orderNum, date); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order removeOrder(int orderNum, String date) throws FlooringPersistenceException {
        return super.removeOrder(orderNum, date); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Order> getOrdersFromCertainDate(String date) throws FlooringPersistenceException {
        return super.getOrdersFromCertainDate(date); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order addOrder(Order order) throws FlooringPersistenceException {
        return super.addOrder(order); //To change body of generated methods, choose Tools | Templates.
    }

//    private void loadTotalOrder() throws FlooringPersistenceException, FileNotFoundException {
//        Scanner sc;
//
//        try {
//            sc = new Scanner(new BufferedReader(new FileReader(TOTAL_TRAINING_FILE)));
//        } catch (FileNotFoundException e) {
//            throw new FlooringPersistenceException("-_- Could not load total orders data into memory.", e);
//        }
//
//        totalOrders = Integer.parseInt(sc.nextLine());
//        sc.close();
//    }
//
//    private void writeTotalOrders(int totalOrders) throws FlooringPersistenceException {
//
//        PrintWriter out;
//
//        try {
//            out = new PrintWriter(new FileWriter(TOTAL_TRAINING_FILE));
//        } catch (IOException e) {
//            throw new FlooringPersistenceException("Could not save total order data.", e);
//        }
//        out.print(totalOrders + 1);
//        out.flush();
//    }

//    private Map<Integer, Order> orders = new HashMap<>();
//    public static final String TOTAL_FILE = "orders.txt";
//    public static final String DELIMITER = "::";
//    int totalOrders;
//
//    @Override
//    public Order addOrder(Order order) throws FlooringPersistenceException {
//        loadTotalOrder();
//        writeTotalOrders(totalOrders);
//        Order newOrder = orders.put(order.getOrderNum(), order);
//        return newOrder;
//    }
//
//    @Override
//    public List<Order> getOrdersFromCertainDate(String date) throws FlooringPersistenceException {
//        loadOrders(date);
//        return new ArrayList<Order>(orders.values());
//    }
//
//    @Override
//    public Order removeOrder(int orderNum, String date) throws FlooringPersistenceException {
//        Order removedOrder = orders.remove(orderNum);
//        return removedOrder;
//    }
//
//    @Override
//    public void saveCurrentWork() throws FlooringPersistenceException {
//    }
//
//    @Override
//    public Order getOrder(int orderNum, String date) throws FlooringPersistenceException {
//        loadOrders(date);
//        return orders.get(orderNum);
//    }
//
//    @Override
//    public int getTotalOfOrders() throws FlooringPersistenceException {
//        loadTotalOrder();
//        return totalOrders;
//    }
//
//    @Override
//    public Order editOrder(Order order) {
//        Order editedOrder = orders.put(order.getOrderNum(), order);
//        return editedOrder;
//    }
//
//    @Override
//    public List<Order> getAllOrders() {
//        return new ArrayList<Order>(orders.values());
//    }
//
//
//
//    public void loadOrders(String date) throws FlooringPersistenceException {
//
//        String ORDER_FILE = "order_" + date + ".txt";
//        Scanner sc;
//
//        try {
//            sc = new Scanner(new BufferedReader(new FileReader(ORDER_FILE)));
//        } catch (FileNotFoundException e) {
//            throw new FlooringPersistenceException("-_- There are no records from that day.", e);
//        }
//
//        String currentLine;
//        String[] currentTokens;
//
//        while (sc.hasNextLine()) {
//
//            currentLine = sc.nextLine();
//            currentTokens = currentLine.split(DELIMITER);
//
//            Order order = new Order();
//            order.setOrderNum(Integer.parseInt(currentTokens[0]));
//            order.setCustomerName(currentTokens[1]);
//            order.setState(currentTokens[2]);
//            order.setTaxRate(new BigDecimal(currentTokens[3]));
//            order.setProductType(currentTokens[4]);
//            order.setArea(new BigDecimal(currentTokens[5]));
//            order.setSqFtMaterialCost(new BigDecimal(currentTokens[6]));
//            order.setSqFtLaborCost(new BigDecimal(currentTokens[7]));
//            order.setTotalLaborCost(new BigDecimal(currentTokens[8]));
//            order.setTotalMaterialCost(new BigDecimal(currentTokens[9]));
//            order.setTotalTax(new BigDecimal(currentTokens[10]));
//            order.setTotalTotal(new BigDecimal(currentTokens[11]));
//            order.setDate(currentTokens[12]);
//            if (orders.containsKey(order.getOrderNum())) {
//                
//            } else {
//                orders.put(order.getOrderNum(), order);
//            }
//        }
//    }
}
