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
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.rowPosition;
        hash = 53 * hash + this.colPosition;
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
        final Poistion other = (Poistion) obj;
        if (this.rowPosition != other.rowPosition) {
            return false;
        }
        return this.colPosition == other.colPosition;
    }


    @Override
    public String toString() {
        return "Poistion{" + "rowPosition=" + rowPosition + ", colPosition=" + colPosition + '}';
    }
}
