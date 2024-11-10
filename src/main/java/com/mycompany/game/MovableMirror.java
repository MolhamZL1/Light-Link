package com.mycompany.game;

import java.util.HashMap;
import java.util.LinkedList;

public class MovableMirror extends Mirror {

    private final MirrorDirections movingDirection;
    private final int movingCellsNum;
    int dynamicMovingCellsPosition;

    public MovableMirror(MirrorDirections direction, Poistion poistion, MirrorDirections movingDirection, int movingCellsNum) {
        super(poistion, direction);
        this.movingDirection = movingDirection;
        this.movingCellsNum = movingCellsNum;
        this.dynamicMovingCellsPosition = 0;
    }

    public LinkedList<HashMap<String, Integer>> getPosiblePositions() {
        LinkedList<HashMap<String, Integer>> positions = new LinkedList<>();
//        int i = dynamicMovingCellsPosition;
//
//        switch (movingDirection) {
//            case horizintal -> {
//                while (i < movingCellsNum) {
//                    HashMap<String, Integer> mapRight = new HashMap<>();
//                    mapRight.put("col", getColPosition() + 1);  
//                    mapRight.put("row", getRowPosition());
//                    positions.add(mapRight);
//                    i++;
//                }
//                i = dynamicMovingCellsPosition;
//                while (i > -movingCellsNum) {
//                    HashMap<String, Integer> mapLeft = new HashMap<>();
//                    mapLeft.put("col", getColPosition() - 1); 
//                    mapLeft.put("row", getRowPosition());
//                    i--;
//                    positions.add(mapLeft);
//
//                }
//            }
//            case vertical -> {
//                while (i < movingCellsNum) {
//                    HashMap<String, Integer> mapDown = new HashMap<>();
//                    mapDown.put("col", getColPosition());
//                    mapDown.put("row", getPoistion().getRowition() + 1);  
//                    positions.add(mapDown);
//                    i++;
//                }
//                i = dynamicMovingCellsPosition;
//                while (i > -movingCellsNum) {
//                    HashMap<String, Integer> mapUp = new HashMap<>();
//                    mapUp.put("col", getPoistion().getColPosition());
//                    mapUp.put("row", getPoistion().getRowPosition() - 1); 
//                    positions.add(mapUp);
//                    i--;
//                }
//            }
//            case topLeft -> {
//                while (i < movingCellsNum) {
//                    HashMap<String, Integer> mapDown = new HashMap<>();
//                    mapDown.put("col", getPoistion().getColPosition()+ 1);
//                    mapDown.put("row", getPoistion().getRowPosition() + 1);  
//                    positions.add(mapDown);
//                    i++;
//                }
//                i = dynamicMovingCellsPosition;
//                while (i > -movingCellsNum) {
//                    HashMap<String, Integer> mapUp = new HashMap<>();
//                    mapUp.put("col", getPoistion().getColPosition()- 1);
//                    mapUp.put("row", getPoistion().getRowPosition() - 1);  
//                    positions.add(mapUp);
//                    i--;
//                }
//            }
//            case topRight -> {
//                while (i < movingCellsNum) {
//                    HashMap<String, Integer> mapDown = new HashMap<>();
//                    mapDown.put("col", getPoistion().getColPosition() - 1);
//                    mapDown.put("row", getPoistion().getRowPosition() + 1);  
//                    positions.add(mapDown);
//                    i++;
//                }
//                i = dynamicMovingCellsPosition;
//                while (i > -movingCellsNum) {
//                    HashMap<String, Integer> mapUp = new HashMap<>();
//                    mapUp.put("col", getPoistion().getColPosition()+ 1);
//                    mapUp.put("row", getPoistion().getRowPosition() - 1);
//                    positions.add(mapUp);
//                    i--;
//                }
//            }
//            default -> {
//            }
//        }

        return positions;
    }

    public MirrorDirections getMovingDirection() {
        return movingDirection;
    }

    public int getMovingCellsNum() {
        return movingCellsNum;
    }

    public int getDynamicmovingCellsPosition() {
        return dynamicMovingCellsPosition;
    }

}
