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
public class Product {
    
    private String type;
    private BigDecimal sqFtLaborCost;
    private BigDecimal sqFtMaterialCost;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getSqFtLaborCost() {
        return sqFtLaborCost;
    }

    public void setSqFtLaborCost(BigDecimal sqFtLaborCost) {
        this.sqFtLaborCost = sqFtLaborCost;
    }

    public BigDecimal getSqFtMaterialCost() {
        return sqFtMaterialCost;
    }

    public void setSqFtMaterialCost(BigDecimal sqFtMaterialCost) {
        this.sqFtMaterialCost = sqFtMaterialCost;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.type);
        hash = 17 * hash + Objects.hashCode(this.sqFtLaborCost);
        hash = 17 * hash + Objects.hashCode(this.sqFtMaterialCost);
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
        final Product other = (Product) obj;
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.sqFtLaborCost, other.sqFtLaborCost)) {
            return false;
        }
        if (!Objects.equals(this.sqFtMaterialCost, other.sqFtMaterialCost)) {
            return false;
        }
        return true;
    }
}
