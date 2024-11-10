package com.mycompany.game;

/**
 * Basic Mirror class with direction, row, and column position.
 */
public class Mirror extends Cell {

    private MirrorDirections direction;

    public Mirror(Poistion poistion, MirrorDirections direction) {
        super(poistion);
        this.direction = direction;
    }

   
    public MirrorDirections getDirection() {
        return direction;
    }

    public void setDirection(MirrorDirections direction) {
        if (this instanceof RotatedMirror) {
            this.direction = direction;
        }
    }

    @Override
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

}
