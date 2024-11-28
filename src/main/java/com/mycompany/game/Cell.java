/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.game;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author USER
 */


public class Cell {
    private Poistion poistion;

    public Cell(Poistion poistion) {
        this.poistion = poistion;
    }

    // Copy constructor for deep copying
    public Cell(Cell other) {
        this.poistion = new Poistion(other.poistion); // Deep copy of Poistion
    }
    public Cell copy(){
    return new Cell(this);
    }

    public Poistion getPoistion() {
        return poistion;
    }

    public void setPoistion(Poistion poistion) {
        this.poistion = poistion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.poistion);
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
        final Cell other = (Cell) obj;
        return Objects.equals(this.poistion, other.poistion);
    }

 

    public void print() {
        System.out.print(" . ");
    }
}
