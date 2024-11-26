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
    public boolean equals(Object obj) {
        if (this == obj) return true; // Reference equality
        if (obj == null || getClass() != obj.getClass()) return false; // Type and null check
        Cell cell = (Cell) obj;
        return Objects.equals(poistion, cell.poistion); // Field-level equality
    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(poistion); // Hash based on fields
//    }

    public void print() {
        System.out.print(" . ");
    }
}
