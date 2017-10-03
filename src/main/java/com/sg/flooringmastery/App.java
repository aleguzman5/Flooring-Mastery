/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery;

import com.sg.flooringmastery.controller.FlooringMasteryController;
import com.sg.flooringmastery.dao.FlooringPersistenceException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Alejandro
 */
public class App {

    public static void main(String[] args) {
        try {
            String mode = loadConfigurationFile();
            if (mode.equalsIgnoreCase("Prod")) {
                ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
                FlooringMasteryController controller = ctx.getBean("controller", FlooringMasteryController.class);
                controller.run();
            } else {
                ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContextTraining.xml");
                FlooringMasteryController controller = ctx.getBean("controller", FlooringMasteryController.class);
                controller.run();
            }
        } catch (FlooringPersistenceException e) {
            e.getMessage();
        }
    }

    private static String loadConfigurationFile() throws FlooringPersistenceException {
        Scanner sc;

        try {
            sc = new Scanner(new BufferedReader(new FileReader("config.txt")));
        } catch (FileNotFoundException e) {
            throw new FlooringPersistenceException("-_- Could not load total orders data into memory.", e);
        }
        String mode = sc.nextLine();
        sc.close();
        return mode;
    }
}
