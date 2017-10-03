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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Alejandro
 */
public class OrderProdDaoFileImpl implements OrderDao {

    private Map<Integer, Order> orders = new HashMap<>();
    private List<String> listOfDates = new ArrayList<>();
    public static final String TOTAL_FILE = "orders.txt";
    public static final String DELIMITER = "::";
    int totalOrders;

    @Override
    public Order addOrder(Order order) throws FlooringPersistenceException {
        loadTotalOrder();
        writeTotalOrders(totalOrders);
        boolean loadedAlready = hasBeenLoaded(order.getDate());
        if (loadedAlready == false) {
            try {
                loadOrders(order.getDate());
            } catch (Exception e) {
                //it's ok if the file doesn't exists, I want to keep going, so I can save (and create a new file) in the future.
            }
        }
        Order newOrder = orders.put(order.getOrderNum(), order);
        return newOrder;
    }

    @Override
    public List<Order> getOrdersFromCertainDate(String date) throws FlooringPersistenceException {

        boolean loadedAlready = hasBeenLoaded(date);
        if (loadedAlready == false) {
            try {
                loadOrders(date);/////////////////
            } catch (Exception e) {
                List<Order> orderList = new ArrayList<Order>(orders.values()
                        .stream()
                        .filter(o -> o.getDate().equals(date))
                        .collect(Collectors.toList()));
                if (orderList.size() > 0) {
                    return orderList;
                } else {
                    throw new FlooringPersistenceException("-_- There are no records from that day.", e);
                }
            }
        }
        return new ArrayList<Order>(orders.values()
                .stream()
                .filter(o -> o.getDate().equals(date))
                .collect(Collectors.toList()));

    }

    @Override
    public Order removeOrder(int orderNum, String date) throws FlooringPersistenceException {
        Order removedOrder = orders.remove(orderNum);
        return removedOrder;
    }

    @Override
    public void saveCurrentWork() throws FlooringPersistenceException {
        writeOrders();
    }

    @Override
    public Order getOrder(int orderNum, String date) throws FlooringPersistenceException {
        boolean loadedAlready = hasBeenLoaded(date);
        if (loadedAlready == false) {
            try {
                loadOrders(date);
            } catch (Exception e) {
                //it's ok if the file doesn't exists, I want to keep going, so I can save (and create a new file) in the future if desired.
            }
        }
        if (orders.get(orderNum) != null && orders.get(orderNum).getDate().equals(date)) {
            return orders.get(orderNum);
        } else {
            return null;
        }
    }

    @Override
    public int getTotalOfOrders() throws FlooringPersistenceException {
        loadTotalOrder();
        return totalOrders;
    }

    @Override
    public Order editOrder(Order order) {
        Order editedOrder = orders.put(order.getOrderNum(), order);
        return editedOrder;
    }

    @Override
    public List<Order> getAllOrders() {
        return new ArrayList<Order>(orders.values());
    }

    private boolean hasBeenLoaded(String date) {
        boolean loadedAlready = false;
        for (String s : listOfDates) {
            if (s.equals(date)) {
                loadedAlready = true;
                break;
            }
        }
        return loadedAlready;
    }

    private void loadTotalOrder() throws FlooringPersistenceException {
        Scanner sc;

        try {
            sc = new Scanner(new BufferedReader(new FileReader(TOTAL_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringPersistenceException("-_- Could not load total orders data into memory.", e);
        }

        totalOrders = Integer.parseInt(sc.nextLine());
        sc.close();
    }

    private void writeTotalOrders(int totalOrders) throws FlooringPersistenceException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(TOTAL_FILE));
        } catch (IOException e) {
            throw new FlooringPersistenceException("Could not save total order data.", e);
        }
        out.print(totalOrders + 1);
        out.flush();
    }

    public void loadOrders(String date) throws FlooringPersistenceException {

        String ORDER_FILE = "order_" + date + ".txt";
        Scanner sc;

        try {
            sc = new Scanner(new BufferedReader(new FileReader(ORDER_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringPersistenceException("-_- There are no records from that day.", e);
        }

        String currentLine;
        String[] currentTokens;

        while (sc.hasNextLine()) {

            currentLine = sc.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            Order order = new Order();
            order.setOrderNum(Integer.parseInt(currentTokens[0]));
            order.setCustomerName(currentTokens[1]);
            order.setState(currentTokens[2]);
            order.setTaxRate(new BigDecimal(currentTokens[3]));
            order.setProductType(currentTokens[4]);
            order.setArea(new BigDecimal(currentTokens[5]));
            order.setSqFtMaterialCost(new BigDecimal(currentTokens[6]));
            order.setSqFtLaborCost(new BigDecimal(currentTokens[7]));
            order.setTotalLaborCost(new BigDecimal(currentTokens[8]));
            order.setTotalMaterialCost(new BigDecimal(currentTokens[9]));
            order.setTotalTax(new BigDecimal(currentTokens[10]));
            order.setTotalTotal(new BigDecimal(currentTokens[11]));
            order.setDate(currentTokens[12]);
            if (orders.containsKey(order.getOrderNum())) {

            } else {
                orders.put(order.getOrderNum(), order);
            }
        }
        listOfDates.add(date);
    }

    public void writeOrders() throws FlooringPersistenceException {

        PrintWriter out;

        List<Order> orderCollection = this.getAllOrders();

        Map<String, List<Order>> ordersByDate = orderCollection.stream()
                .collect(Collectors.groupingBy(o -> o.getDate()));

        Set<String> keys = ordersByDate.keySet();

        for (String key : keys) {
            try {
                out = new PrintWriter(new FileWriter("order_" + key + ".txt"));
            } catch (IOException e) {
                throw new FlooringPersistenceException("Could not save Order data.", e);
            }
            for (Order order : ordersByDate.get(key)) {
                out.println(order.getOrderNum() + DELIMITER
                        + order.getCustomerName() + DELIMITER
                        + order.getState() + DELIMITER
                        + order.getTaxRate() + DELIMITER
                        + order.getProductType() + DELIMITER
                        + order.getArea() + DELIMITER
                        + order.getSqFtMaterialCost() + DELIMITER
                        + order.getSqFtLaborCost() + DELIMITER
                        + order.getTotalLaborCost() + DELIMITER
                        + order.getTotalMaterialCost() + DELIMITER
                        + order.getTotalTax() + DELIMITER
                        + order.getTotalTotal() + DELIMITER
                        + order.getDate());
                out.flush();
            }
            out.close();
        }
    }
}