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
public class Light {

    private Directions direction;
    private int rowPosition;
    private int colPosition;

    public Light(Directions direction, int rowPosition, int colPosition) {
        this.direction = direction;
        this.rowPosition = rowPosition;
        this.colPosition = colPosition;
    }

    public Directions getDirection() {
        return direction;
    }

    public void setDirection(Directions direction) {
        this.direction = direction;
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

}
