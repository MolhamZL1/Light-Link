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

    // Copy constructor for deep copying
    public Light(Light other) {
        super(other); // Calls Cell's copy constructor
        this.direction = other.direction; // Enum, so safe to copy directly
    }

    @Override
    public Light copy() {
        return new Light(this);
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
            case Left -> System.out.print(" < ");
            case Right -> System.out.print(" > ");
            case Top -> System.out.print(" ^ ");
            case Bottom -> System.out.print(" v ");
            case BottomLeft -> System.out.print(" //");
            case BottomRight -> System.out.print(" \\\\");
            case TopLeft -> System.out.print(" \\\\");
            case TopRight -> System.out.print(" //");
            default -> {}
        }
    }

    public static void printPathLight() {
        System.out.print(" = ");
    }
}
