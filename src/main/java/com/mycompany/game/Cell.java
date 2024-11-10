/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.game;

/**
 *
 * @author USER
 */
public class Cell {

    private Poistion poistion;

    public Cell(Poistion poistion) {
        this.poistion = poistion;

    }

    public Poistion getPoistion() {
        return poistion;
    }

    public void setPoistion(Poistion poistion) {
        this.poistion = poistion;
    }

    ;
 public void print() {
        System.out.print(" . ");
    }
;

}
