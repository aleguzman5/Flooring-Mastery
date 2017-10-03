/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dao.FlooringPersistenceException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.State;
import com.sg.flooringmastery.service.OrderDataValidationException;
import com.sg.flooringmastery.service.OrderServicer;
import com.sg.flooringmastery.view.FlooringMasteryView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Alejandro
 */
public class FlooringMasteryController {

    FlooringMasteryView view;
    OrderServicer service;
    Map<Integer, State> states = new HashMap<>();
    Map<Integer, Product> products = new HashMap<>();

    public FlooringMasteryController(FlooringMasteryView view, OrderServicer service) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing) {

            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    displayOrders();
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    editOrder();
                    break;
                case 4:
                    removeOrder();
                    break;
                case 5:
                    saveWork();
                    break;
                case 6:
                    keepGoing = false;
                    break;
                default:
                    view.displayUnknownCommand();
            }
        }
        view.displayGoodByeMessage();
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void addOrder() {
        view.displayAddOrderBanner();
        try {
            int orderNumber = service.getTotalOrders();
            products = service.getListOfProducts();
            states = service.getListOfStatesWithTaxes();
            Order newOrder = view.getNewOrderInfo(states, products, orderNumber);
            if (newOrder.getOrderNum() != 0) {
                service.addOrder(newOrder);
            }
        } catch (FlooringPersistenceException | OrderDataValidationException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void displayOrders() {
        view.displayDisplayOrdersBanner();
        String date = view.getOrderDate();
        try {
            List<Order> orderList = service.listOrders(date);
            view.displayOrders(orderList);
        } catch (FlooringPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }

    }

    private void editOrder() {
        view.displayEditBanner();
        int orderNumber = view.getOrderNumber();
        String date = view.getOrderDate();
        try {
            products = service.getListOfProducts();
            states = service.getListOfStatesWithTaxes();
            Order orderToEdit = service.getOrder(orderNumber, date);
            if (orderToEdit == null) {
                view.displayNoOrderFound();
            } else {
                Order editedOrder = view.editOrder(orderToEdit, states, products);
                if (editedOrder.getOrderNum() != 0) {
                    service.editOrder(editedOrder);
                }
            }
        } catch (FlooringPersistenceException | OrderDataValidationException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void removeOrder() {
        view.displayRemoveOrderBanner();
        int orderNumber = view.getOrderNumber();
        String date = view.getOrderDate();
        try {
            Order orderToBeRemoved = service.getOrder(orderNumber, date);
            if (orderToBeRemoved != null) {
                view.displaySpecificOrder(orderToBeRemoved);
                String confirmation = view.requestDeleteConfirmation();
                if (confirmation.equalsIgnoreCase("y")) {
                    service.removeOrder(orderNumber, date);
                    view.displayRemoveSuccessBanner();
                }
            } else {
                view.displayNoOrderFound();
            }
        } catch (FlooringPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void saveWork() {
        try {
            service.saveWork();
            view.displaySuccessSavedWork();
        } catch (FlooringPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
}
