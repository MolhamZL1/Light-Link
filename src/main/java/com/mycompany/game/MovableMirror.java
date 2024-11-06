package com.mycompany.game;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class MovableMirror extends Mirror {

    private MirrorDirections movingDirection;
    private int movingCellsNum;
    int dynamicMovingCellsPosition;

    public MovableMirror(MirrorDirections direction, int rowPosition, int colPosition, MirrorDirections movingDirection, int movingCellsNum) {
        super(direction, rowPosition, colPosition);
        this.movingDirection = movingDirection;
        this.movingCellsNum = movingCellsNum;
        this.dynamicMovingCellsPosition = 0;
    }

    // Method to get the possible positions based on the direction and movement limits
    public LinkedList<HashMap<String, Integer>> getPosiblePositions() {
        LinkedList<HashMap<String, Integer>> positions = new LinkedList<>();
int i=dynamicMovingCellsPosition;
        // Check if movement direction is horizontal (left/right)
        if (movingDirection == MirrorDirections.horizintal) {
            // Only move right if the current position is less than the max movement limit
            while (i < movingCellsNum) {
                HashMap<String, Integer> mapRight = new HashMap<>();
                mapRight.put("col", getColPosition() + 1);  // Move to the right
                mapRight.put("row", getRowPosition());
                positions.add(mapRight);
                i++;
                System.out.println("1" + i);
            }
            i=dynamicMovingCellsPosition;
            // Only move left if the current position is greater than the min movement limit
            while (i > -movingCellsNum) {
                HashMap<String, Integer> mapLeft = new HashMap<>();
                mapLeft.put("col", getColPosition() - 1);  // Move to the left
                mapLeft.put("row", getRowPosition());
                i--;
                positions.add(mapLeft);
                System.out.println("2" + i);

            }
        } // Check if movement direction is vertical (up/down)
        else if (movingDirection == MirrorDirections.vertical) {
            // Only move down if the current position is less than the max movement limit
            if (dynamicMovingCellsPosition < movingCellsNum) {
                HashMap<String, Integer> mapDown = new HashMap<>();
                mapDown.put("col", getColPosition());
                mapDown.put("row", getRowPosition() + 1);  // Move down
                positions.add(mapDown);
            }

            // Only move up if the current position is greater than the min movement limit
            if (dynamicMovingCellsPosition > -movingCellsNum) {
                HashMap<String, Integer> mapUp = new HashMap<>();
                mapUp.put("col", getColPosition());
                mapUp.put("row", getRowPosition() - 1);  // Move up
                positions.add(mapUp);
            }
        }

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
