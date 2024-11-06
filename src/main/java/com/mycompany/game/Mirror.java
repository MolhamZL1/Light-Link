package com.mycompany.game;

/**
 * Basic Mirror class with direction, row, and column position.
 */
public class Mirror {

    private MirrorDirections direction;
    private int rowPosition;
    private int colPosition;

    public Mirror(MirrorDirections direction, int rowPosition, int colPosition) {
        this.direction = direction;
        this.rowPosition = rowPosition;
        this.colPosition = colPosition;
    }

    public void print() {
        switch (direction) {
            case horizintal ->
                System.out.print(" - ");
            case vertical ->
                System.out.print(" | ");
            case topLeft ->
                System.out.print(" \\ ");
            case topRight ->
                System.out.print(" / ");
        }
    }

    public void setRowPosition(int rowPosition) {
        this.rowPosition = rowPosition;
    }

    public void setColPosition(int colPosition) {
        this.colPosition = colPosition;
    }

    public MirrorDirections getDirection() {
        return direction;
    }

    public void setDirection(MirrorDirections direction) {
        if (this instanceof RotatedMirror) {
            this.direction = direction;
        }
    }

    public int getRowPosition() {
        return rowPosition;
    }

    public int getColPosition() {
        return colPosition;
    }
}
