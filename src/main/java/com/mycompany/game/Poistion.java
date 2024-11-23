/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.game;

/**
 *
 * @author USER
 */
public class Poistion {

    private int rowPosition;
    private int colPosition;

    public Poistion(int rowPosition, int colPosition) {
        this.rowPosition = rowPosition;
        this.colPosition = colPosition;
    }

    // Copy constructor for deep copying
    public Poistion(Poistion other) {
        this.rowPosition = other.rowPosition;
        this.colPosition = other.colPosition;
    }

    public Poistion copy() {
        return new Poistion(this);
    }

    public int getRowPosition() {
        return rowPosition;
    }

    public void setRowPosition(int rowPosition) {
        this.rowPosition = rowPosition;
    }

    public int getColPosition() {
        return colPosition;
    }

    public void setColPosition(int colPosition) {
        this.colPosition = colPosition;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; // Check if both references are the same
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false; // Ensure the object is of the same class
        }
        Poistion poistion = (Poistion) obj; // Cast the object to Poistion
        return rowPosition == poistion.rowPosition && colPosition == poistion.colPosition; // Compare fields
    }

    @Override
    public int hashCode() {
        return 31 * rowPosition + colPosition; // Generate a hash based on fields
    }

    @Override
    public String toString() {
        return "Poistion{" + "rowPosition=" + rowPosition + ", colPosition=" + colPosition + '}';
    }
}
