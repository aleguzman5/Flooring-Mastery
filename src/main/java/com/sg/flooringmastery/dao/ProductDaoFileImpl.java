/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Alejandro
 */
public class ProductDaoFileImpl implements ProductDao {
    
    public static final String PRODUCT_FILE = "product.txt";
    public static final String DELIMITER = "::";

    Map<Integer, Product> productInfo = new HashMap<>();
    Integer productNum = 0;

    @Override
    public Map<Integer, Product> getAllProducts() throws FlooringPersistenceException {
        loadProducts();
        return productInfo;
    }


    @Override
    public Product getProductInfo(Integer product) {
        return productInfo.get(product);
    }
    
    private void loadProducts() throws FlooringPersistenceException {
        Scanner sc;

        try {
            sc = new Scanner(new BufferedReader(new FileReader(PRODUCT_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringPersistenceException("-_- Could not load Product data into memory.", e);
        }

        String currentLine;

        String[] currentTokens;

        while (sc.hasNextLine()) {
            productNum++;

            currentLine = sc.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            Product product = new Product();
            product.setType(currentTokens[0]);
            product.setSqFtLaborCost(new BigDecimal(currentTokens[1]));
            product.setSqFtMaterialCost(new BigDecimal(currentTokens[2]));

            productInfo.put(productNum, product);
        }
        sc.close();
        productNum = 0;
    }
}
