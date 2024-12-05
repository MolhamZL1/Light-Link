/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.game;

import java.util.Comparator;

/**
 *
 * @author USER
 */
class FComprator implements Comparator<State> {

    // Overriding compare()method of Comparator 
    // for descending order of cgpa
    @Override
    public int compare(State s1, State s2) {
        if (s1.getF() < s2.getF()) {
            return 1;
        } else if (s1.getF() > s2.getF()) {
            return -1;
        } else {
            if (s1.getCost() > s2.getCost()) {
                return 1;
            } else if (s1.getCost() < s2.getCost()) {
                return -1;
            }
            return 0;
        }
    }
}
