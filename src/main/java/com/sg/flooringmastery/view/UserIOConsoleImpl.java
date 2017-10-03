/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.view;

import java.util.Scanner;

/**
 *
 * @author Alejandro
 */
public class UserIOConsoleImpl implements UserIO {

    Scanner sc = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {

        print(prompt);
        while (!sc.hasNextDouble()) {
            System.out.println("Invalid input, please try again! ");
            sc.next();
        }
        double input = sc.nextDouble();
        sc.nextLine();
        return input;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        do {
            print(prompt);
            double input = sc.nextDouble();
            sc.nextLine();
            if (input >= min && input <= max) {
                return input;
            } else {
                System.out.println("Invlaid input, enter a value from " + min + " to " + max);
            }
        } while (true);
    }

    @Override
    public float readFloat(String prompt) {
        print(prompt);
        float f = sc.nextFloat();
        return f;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        do {
            print(prompt);
            while (!sc.hasNextFloat()) {
                System.out.println("Invalid input, please try again! ");
                sc.next();
            }
            float f = sc.nextFloat();
            sc.nextLine();
            if (f < min || f > max) {
                System.out.println("Invlaid input, enter a value from " + min + " to " + max);
            } else {
                return f;
            }
        } while (true);
    }

    @Override
    public int readInt(String prompt) {
        print(prompt);
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input, please try again! ");
            sc.next();
        }
        int i = sc.nextInt();
        sc.nextLine();
        return i;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        do {
            print(prompt);
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input, please try again! ");
                sc.next();
            }
            int i = sc.nextInt();
            sc.nextLine();
            if (i < min || i > max) {
                System.out.println("Invlaid input, enter a value from " + min + " to " + max);
            } else {
                return i;
            }
        } while (true);
    }

    @Override
    public long readLong(String prompt) {
        print(prompt);
        long l = sc.nextLong();
        sc.nextLine();
        return l;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        do {
            print(prompt);
            long l = sc.nextLong();
            sc.nextLine();
            if (l < min || l > max) {
                System.out.println("Invlaid input, enter a value from " + min + " to " + max);
            } else {
                return l;
            }
        } while (true);
    }

    @Override
    public String readString(String prompt) {
        print(prompt);
        String s = sc.nextLine();
        return s;
    }

    @Override
    public char readChar(String prompt, char a, char b) {
        print(prompt);
        char option;
        do {
            option = sc.nextLine().charAt(0);
        }while (option != a || option != b);
        return option;
    }
}
