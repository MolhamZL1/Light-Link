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
    
    // Copy constructor for deep copying
    public Mirror(Mirror other) {
        super(other); // Calls Cell's copy constructor to deep copy Poistion
        this.direction = other.direction; // Enum, so safe to copy directly
    }
    @Override
    public Mirror copy() {
        // Check if this is an instance of RotatedMirror
        if (this instanceof RotatedMirror) {
            return new RotatedMirror((RotatedMirror) this);
        }
        return new Mirror(this); // Use the Mirror copy constructor
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
