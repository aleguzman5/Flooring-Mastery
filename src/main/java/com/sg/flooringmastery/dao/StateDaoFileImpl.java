/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.State;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Alejandro
 */
public class StateDaoFileImpl implements StateDao {

    public static final String STATE_FILE = "stateTax.txt";
    public static final String DELIMITER = "::";

    Map<Integer, State> stateInfo = new HashMap<>();
    Integer stateNum = 0;

    @Override
    public Map<Integer, State> getAllstates() throws FlooringPersistenceException {
        loadStates();
        return stateInfo;
    }


    @Override
    public State getStateInfo(Integer state) {
        return stateInfo.get(state);
    }
    
    private void loadStates() throws FlooringPersistenceException {
        Scanner sc;

        try {
            sc = new Scanner(new BufferedReader(new FileReader(STATE_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringPersistenceException("-_- Could not load States data into memory.", e);
        }

        String currentLine;

        String[] currentTokens;

        while (sc.hasNextLine()) {
            stateNum++;

            currentLine = sc.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            State state = new State();
            state.setName(currentTokens[0]);
            state.setTax(new BigDecimal(currentTokens[1]));

            stateInfo.put(stateNum, state);
        }
        sc.close();
        stateNum = 0;
    }
}
