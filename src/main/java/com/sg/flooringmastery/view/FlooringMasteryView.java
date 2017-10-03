/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.view;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.State;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Alejandro
 */
public class FlooringMasteryView {

    UserIO io;
    
    public FlooringMasteryView(UserIO io){
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("*********************************************************");
        io.print("* <<Flooring Program>>");
        io.print("*1. Display Orders");
        io.print("*2. Add an Order");
        io.print("*3. Edit an Order");
        io.print("*4. Remove an Order");
        io.print("*5. Save Current Work");
        io.print("*6. Quit");
        io.print("*********************************************************");

        return io.readInt("Please select from the above choices.", 1, 6);
    }

    public Order getNewOrderInfo(Map<Integer, State> states, Map<Integer, Product> products, int totalOrders) {
        Order currentOrder = new Order(totalOrders);

        String customerName = io.readString("Please enter customer's Name");
        currentOrder.setCustomerName(customerName);

        Set<Integer> keys = states.keySet();
        Set<Integer> productKeys = products.keySet();

        String string = "";
        for (Integer key : keys) {
            string += "\n" + key + ": " + states.get(key).getName();
        }
        io.print(string);
        int state = io.readInt("Please select the number that corresponds with customer's State from the list above: ", 1, states.size());
        currentOrder.setState(states.get(state).getName());
        currentOrder.setTaxRate((states.get(state).getTax()));

        string = "";
        for (Integer key : productKeys) {
            string += "\n" + key + ": " + products.get(key).getType();
        }
        io.print(string);
        int product = io.readInt("Please select the number that corresponds with the Product type from the list above: ", 1, products.size());
        currentOrder.setProductType(products.get(product).getType());
        currentOrder.setSqFtLaborCost(products.get(product).getSqFtLaborCost());
        currentOrder.setSqFtMaterialCost(products.get(product).getSqFtMaterialCost());

        String area = io.readString("Please enter the total area(square Footage)");
        area = validateArea(currentOrder, area);
        currentOrder.setArea(new BigDecimal(area));

        printBeforeConfirmation(currentOrder);

        Order emptyOrder = new Order(0);

        String confirmation = io.readString("Do you want to continue (y/n)? ");

        if (confirmation.equals("y") || confirmation.equals("Y")) {
            return currentOrder;
        } else {
            return emptyOrder;
        }
    }

    public void displayAddOrderBanner() {
        io.print("== Add an Order ===");
    }

    public void displayAddSuccessBanner() {
        io.readString("Please hit enter to continue");
    }

    public void displayOrders(List<Order> orderList) {
        for (Order currentOrder : orderList) {
            io.print("==========================================");
            io.print("         Order Number: " + String.valueOf(currentOrder.getOrderNum()));
            io.print("        Customer Name: " + currentOrder.getCustomerName());
            io.print("     Customer's State: " + currentOrder.getState());
            io.print("         Product type: " + currentOrder.getProductType());
            io.print("             Tax Rate: " + currentOrder.getTaxRate());
            io.print("        Material cost: $" + currentOrder.getSqFtMaterialCost() + " per Sq. Ft.");
            io.print("           Labor cost: $" + currentOrder.getSqFtLaborCost() + " per Sq. Ft.");
            io.print("            Total Tax: $" + currentOrder.getTotalTax());
            io.print("           Total Cost: $" + currentOrder.getTotalTotal());
            io.print("           Order date: " + currentOrder.getDate());
            io.print("==========================================");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayDisplayOrdersBanner() {
        io.print("=== Display Orders ====");
    }

    public void displayDisplayEditBanner() {
        io.print("=== Edit Order ===");
    }

    public int getOrderNumber() {
        int orderNumber = io.readInt("Please enter the Order Number: ");
        return orderNumber;
    }

    public String getOrderDate() {
        LocalDate dateEnteredByUser;
        Boolean keepGoing = true;
        do {
            String input = io.readString("Please enter the order date (MM/DD/YY): ");
            try {
                dateEnteredByUser = LocalDate.parse(input, DateTimeFormatter
                        .ofPattern("MM/dd/yy"));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyy");
                String date = dateEnteredByUser.format(formatter);
                keepGoing = false;
                return date;

            } catch (Exception e) {
                keepGoing = true;
            }
        } while (keepGoing);
        return null;
    }

    public void displayEditBanner() {
        io.print("=== Edit Order ===");
    }

    public Order editOrder(Order order, Map<Integer, State> states, Map<Integer, Product> products) {
        if (order != null) {
            String customerName = io.readString("Customer Name: ");
            if (customerName.length() != 0) {
                order.setCustomerName(customerName);
            }
            Set<Integer> keys = states.keySet();
            Set<Integer> productKeys = products.keySet();

            String string = "";
            for (Integer key : keys) {
                string += "\n" + key + ": " + states.get(key).getName();
            }
            io.print(string);
            boolean keepGoing = true;
            String state = io.readString("Select a State from the list above: ");
            
            while (keepGoing) {
                if (state.length() != 0 && states.get(Integer.parseInt(state)) == null) {
                    io.readString("Not a valid State, hit enter to continue.");
                    state = io.readString("State: ");
                } else if (state.length() != 0 && states.get(Integer.parseInt(state)) != null) {
                    order.setState(states.get(Integer.parseInt(state)).getName());
                    order.setTaxRate(states.get(Integer.parseInt(state)).getTax());
                    keepGoing = false;
                } else {
                    keepGoing = false;
                }
            }

            string = "";
            for (Integer key : productKeys) {
                string += "\n" + key + ": " + products.get(key).getType();
            }
            io.print(string);
            keepGoing = true;
            String productType = io.readString("Select a Product from the list above: ");

            while (keepGoing) {
                if (productType.length() != 0 && products.get(Integer.parseInt(productType)) == null) {
                    io.readString("Not a valid Product, hit enter to continue.");
                    productType = io.readString("Product: ");
                } else if (productType.length() != 0 && products.get(Integer.parseInt(productType)) != null) {
                    order.setProductType(products.get(Integer.parseInt(productType)).getType());
                    order.setSqFtLaborCost(products.get(Integer.parseInt(productType)).getSqFtLaborCost());
                    order.setSqFtMaterialCost(products.get(Integer.parseInt(productType)).getSqFtMaterialCost());
                    keepGoing = false;
                } else {
                    keepGoing = false;
                }
            }

            String area = io.readString("Enter total area(square Footage)");
            if (area.length() != 0) {
                order.setArea(new BigDecimal(area));
            }
            return order;
        } else {
            io.readString("No such Order. Please hit Enter to continue");
            Order emptyOrder = new Order(0);
            return emptyOrder;
        }
    }

    public void displaySpecificOrder(Order order) {
        io.print("==========================================");
        io.print("         Order Number: " + String.valueOf(order.getOrderNum()));
        io.print("        Customer Name: " + order.getCustomerName());
        io.print("     Customer's State: " + order.getState());
        io.print("         Product type: " + order.getProductType());
        io.print("             Tax Rate: " + order.getTaxRate());
        io.print("        Material cost: $" + order.getSqFtMaterialCost() + " per Sq. Ft.");
        io.print("           Labor cost: $" + order.getSqFtLaborCost() + " per Sq. Ft.");
        io.print("            Total Tax: $" + order.getTotalTax());
        io.print("           Total Cost: $" + order.getTotalTotal());
    }

    public String requestDeleteConfirmation() {
        return io.readString("Are you sure you want to remove this order? (y/n) ");
    }
    
    public void displaySuccessSavedWork() {
        io.print("All work has been saved successfully!");
        io.readString("Please hit enter to continue");
    }

    public void displayRemoveOrderBanner() {
        io.print("=== Remove Order ===");
    }

    public void displayRemoveSuccessBanner() {
        io.readString("Order succesfully removed. Please hit enter to continue");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("Error");
        io.print(errorMsg);
    }

    public void displayGoodByeMessage() {
        io.print("Thank you! See you next time!");
    }

    public void displayUnknownCommand() {
        io.print("UNKNOWN COMMAND!");
    }
    
    public void displayNoOrderFound() {
        io.print("We couldn't find any records with those specifications.");
        io.readString("Please hit enter to continue");
    }

    private String validateArea(Order order, String area) {
        boolean isValid = true;
        do {
            try {
                order.setArea(new BigDecimal(area));
                isValid = false;
            } catch (NumberFormatException e) {
                area = io.readString("Please enter a valid number for the area: ");
            }
        } while (isValid == true);
        return area;
    }

    private void printBeforeConfirmation(Order order) {
        io.print("Order Number: " + order.getOrderNum());
        io.print("Name: " + order.getCustomerName());
        io.print("State: " + order.getState());
        io.print("Product Type: " + order.getProductType());
        io.print("Total Area: " + order.getArea());
    }
}
