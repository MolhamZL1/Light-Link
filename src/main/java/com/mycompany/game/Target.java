/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.game;

/**
 *
 * @author USER
 */
public class Target extends Cell {

    public Target(Poistion poistion) {
        super(poistion);
    }

    @Override
    public void print() {
        System.out.print(" * ");
    }

}
