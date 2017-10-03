/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.State;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Alejandro
 */
public interface StateDao {
    
    State getStateInfo(Integer state);
    
    Map<Integer, State> getAllstates() throws FlooringPersistenceException;
}
