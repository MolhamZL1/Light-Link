/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.game;

import static com.mycompany.game.MirrorDirections.horizintal;
import static com.mycompany.game.MirrorDirections.topLeft;
import static com.mycompany.game.MirrorDirections.topRight;
import static com.mycompany.game.MirrorDirections.vertical;

/**
 *
 * @author USER
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

    

  public  void print() {
        switch (direction) {
            case horizintal ->
                System.out.print(" - ");
            case vertical ->
                System.out.print(" | ");
            case topLeft ->
                System.out.print(" \\ ");
            case topRight ->
                System.out.print(" / ");
            default -> {
            }
        }

    }
  public MirrorDirections getDirection() {
        return direction;
    }

    public void setDirection(MirrorDirections direction) {
        if(this instanceof RotatedMirror)
        this.direction = direction;
    }

    public int getRowPosition() {
        return rowPosition;
    }


    public int getColPosition() {
        return colPosition;
    }


//    private MirrorDirections direction;
//    private int rowPosition;
//    private int colPosition;
//    private boolean isMovable;
//    private boolean isturnable;
//    private MirrorDirections movingDirction;
//    private int movingCellsNum;
//    private int dynamicmovingCellsPosition;
//
//    public Mirror(MirrorDirections direction, int rowPosition, int colPosition, boolean isMovable, boolean isturnable) {
//        this.direction = direction;
//        this.rowPosition = rowPosition;
//        this.colPosition = colPosition;
//        this.isMovable = isMovable;
//        this.isturnable = isturnable;
//        this.dynamicmovingCellsPosition=0;
//    }
//
//    void print() {
//        switch (getDirection()) {
//            case horizintal ->
//                System.out.print(" - ");
//            case vertical ->
//                System.out.print(" | ");
//            case topLeft ->
//                System.out.print(" \\ ");
//            case topRight ->
//                System.out.print(" / ");
//            default -> {
//            }
//        }
//
//    }
//    public boolean move(){
//      if (isIsMovable()) {
//          if(dynamicmovingCellsPosition<movingCellsNum){
//            this.colPosition++;
//          }
//          
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public boolean setDirection(MirrorDirections direction) {
//        if (isIsturnable()) {
//            this.direction = direction;
//
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public boolean setColPosition(int colPosition) {
//        if (isIsMovable()) {
//            this.colPosition = colPosition;
//            return true;
//        } else {
//            return false;
//        }
//
//    }
//
//    public boolean setRowPosition(int rowPosition) {
//        if (isIsMovable()) {
//            this.rowPosition = rowPosition;
//            return true;
//        } else {
//            return false;
//        }
//
//    }
//
//    public boolean isIsMovable() {
//        return isMovable;
//    }
//
//    public void setIsMovable(boolean isMovable) {
//        this.isMovable = isMovable;
//    }
//
//    public boolean isIsturnable() {
//        return isturnable;
//    }
//
//    public void setIsturnable(boolean isturnable) {
//        this.isturnable = isturnable;
//    }
//
//    public MirrorDirections getDirection() {
//        return direction;
//    }
//
//    public int getRowPosition() {
//        return rowPosition;
//    }
//
//    public int getColPosition() {
//        return colPosition;
//    }

  

}
