/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.game;

/**
 *
 * @author USER
 */
public class Wall extends Cell {

    public Wall(Poistion poistion) {
        super(poistion);
    }
    public Wall(Wall other) {
        super(other);
    }
    @Override
    public Wall copy(){
    return new Wall(this);
    }

    @Override
  public void print(){
    System.out.print(" # ");
  }
}
