/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Alejandro
 */
public class Order {

    private int orderNum;
    private String customerName;
    private String state;
    private BigDecimal taxRate;
    private String productType;
    private BigDecimal area;
    private BigDecimal sqFtMaterialCost;
    private BigDecimal sqFtLaborCost;
    private BigDecimal totalMaterialCost;
    private BigDecimal totalLaborCost;
    private BigDecimal totalTax;
    private BigDecimal totalTotal;
    private String date;

    public Order(int orderNum) {
        this.orderNum = orderNum;
    }

    public Order() {
        
    }
    
    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getSqFtMaterialCost() {
        return sqFtMaterialCost;
    }

    public void setSqFtMaterialCost(BigDecimal sqFtMaterialCost) {
        this.sqFtMaterialCost = sqFtMaterialCost;
    }

    public BigDecimal getSqFtLaborCost() {
        return sqFtLaborCost;
    }

    public void setSqFtLaborCost(BigDecimal sqFtLaborCost) {
        this.sqFtLaborCost = sqFtLaborCost;
    }

    public BigDecimal getTotalMaterialCost() {
        return totalMaterialCost;
    }

    public void setTotalMaterialCost(BigDecimal totalMaterialCost) {
        this.totalMaterialCost = totalMaterialCost;
    }

    public BigDecimal getTotalLaborCost() {
        return totalLaborCost;
    }

    public void setTotalLaborCost(BigDecimal totalLaborCost) {
        this.totalLaborCost = totalLaborCost;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public BigDecimal getTotalTotal() {
        return totalTotal;
    }

    public void setTotalTotal(BigDecimal totalTotal) {
        this.totalTotal = totalTotal;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.orderNum;
        hash = 23 * hash + Objects.hashCode(this.customerName);
        hash = 23 * hash + Objects.hashCode(this.state);
        hash = 23 * hash + Objects.hashCode(this.taxRate);
        hash = 23 * hash + Objects.hashCode(this.productType);
        hash = 23 * hash + Objects.hashCode(this.area);
        hash = 23 * hash + Objects.hashCode(this.sqFtMaterialCost);
        hash = 23 * hash + Objects.hashCode(this.sqFtLaborCost);
        hash = 23 * hash + Objects.hashCode(this.totalMaterialCost);
        hash = 23 * hash + Objects.hashCode(this.totalLaborCost);
        hash = 23 * hash + Objects.hashCode(this.totalTax);
        hash = 23 * hash + Objects.hashCode(this.totalTotal);
        hash = 23 * hash + Objects.hashCode(this.date);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (this.orderNum != other.orderNum) {
            return false;
        }
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.productType, other.productType)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.taxRate, other.taxRate)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        if (!Objects.equals(this.sqFtMaterialCost, other.sqFtMaterialCost)) {
            return false;
        }
        if (!Objects.equals(this.sqFtLaborCost, other.sqFtLaborCost)) {
            return false;
        }
        if (!Objects.equals(this.totalMaterialCost, other.totalMaterialCost)) {
            return false;
        }
        if (!Objects.equals(this.totalLaborCost, other.totalLaborCost)) {
            return false;
        }
        if (!Objects.equals(this.totalTax, other.totalTax)) {
            return false;
        }
        if (!Objects.equals(this.totalTotal, other.totalTotal)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Order No. " + orderNum + " |Customer's Name: " + customerName + " |State: "
                + state + " | Total Area: " + area + " |Total Cost: " + totalTotal;
    }
    
}
