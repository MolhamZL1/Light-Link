/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.game;

/**
 *
 * @author USER
 */
public class Target {
    private int rowPosition;
  private int colPosition;

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
 public void print() {
        System.out.print(" * ");
    }
    public Target(int rowPosition, int colPosition) {
        this.rowPosition = rowPosition;
        this.colPosition = colPosition;
    }
   
    
    
}
