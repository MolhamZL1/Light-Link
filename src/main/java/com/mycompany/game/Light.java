/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.game;

import static com.mycompany.game.Directions.Bottom;

/**
 *
 * @author USER
 */
public class Light extends Cell {

    private Directions direction;

    public Light(Poistion poistion, Directions direction) {
        super(poistion);
        this.direction = direction;
    }

    public void copy(Light light) {
        this.setDirection(light.getDirection());
        this.setPoistion(light.getPoistion());
    }

    public Directions getDirection() {
        return direction;
    }

    public void setDirection(Directions direction) {
        this.direction = direction;
    }

    @Override
    public void print() {
        switch (direction) {
            case Left ->
                System.out.print(" < ");
            case Right ->
                System.out.print(" > ");
            case Top ->
                System.out.print(" ^ ");
            case Bottom ->
                System.out.print(" v ");
            case BottomLeft ->
                System.out.print(" //");
            case BottomRight ->
                System.out.print(" \\\\");
            case TopLeft ->
                System.out.print(" \\\\");
            case TopRight ->
                System.out.print(" //");
            default -> {
            }
        }
    }

    static public void printPathLight() {
        System.out.print(" = ");
    }

}
