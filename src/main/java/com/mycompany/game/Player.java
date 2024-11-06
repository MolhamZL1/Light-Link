package com.mycompany.game;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class Player {

    private State grid;

    public void startGame() {
        grid = initGridLevel7();
        asking();
    }
      private State initGridLevel4() {
        Light light1 = new Light(Directions.Bottom, 4, 1);
        Target target1 = new Target(10, 1);
        Wall wall1 = new Wall(4, 2);
        Wall wall2 = new Wall(9, 1);
        Wall wall3 = new Wall(5, 4);
        Wall[] walls = {wall1, wall2, wall3};
        RotatedMirror mirror1 = new RotatedMirror(MirrorDirections.topRight, 6, 1);
        FixedMirror mirror2 = new FixedMirror(MirrorDirections.topRight, 6, 3);
        RotatedMirror mirror3 = new RotatedMirror(MirrorDirections.topLeft, 4, 3);
        RotatedMirror mirror4 = new RotatedMirror(MirrorDirections.topRight, 4, 5);
        FixedMirror mirror5 = new FixedMirror(MirrorDirections.topRight, 10, 5);
        Mirror[] mirrors = {mirror1, mirror2, mirror3, mirror4, mirror5};
        State grid1 = new State(8, 15, light1, target1, walls, mirrors);
        return grid1;
    }

   

    private State initGridLevel7() {
        Light light1 = new Light(Directions.Right, 4, 2);
        Target target1 = new Target(11, 5);
        Wall wall1 = new Wall(5, 3);
        Wall wall2 = new Wall(10, 4);

        Wall[] walls = {wall1, wall2};
        MovableMirror mirror1 = new MovableMirror(MirrorDirections.topLeft, 4, 5, MirrorDirections.horizintal, 2);
        MovableMirror mirror2 = new MovableMirror(MirrorDirections.topRight, 7, 5, MirrorDirections.vertical, 1);
        MovableMirror mirror3 = new MovableMirror(MirrorDirections.topRight, 8, 2, MirrorDirections.topRight, 1);
        MovableMirror mirror4 = new MovableMirror(MirrorDirections.topLeft, 11, 2, MirrorDirections.topLeft, 1);

        Mirror[] mirrors = {mirror1, mirror2, mirror3, mirror4};
        return new State(8, 15, light1, target1, walls, mirrors);
    }

    private void asking() {
        grid.turnlight();
        if (!grid.isWinning) {
            askingAboutOperation();
        }
    }

    private void askingAboutOperation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose an action:");
        System.out.println("1. Turn light direction");
        System.out.println("2. Rotate mirror");
        System.out.println("3. Move mirror");

        try {
            int selectedOperation = scanner.nextInt();
            switch (selectedOperation) {
                case 1 -> askingAboutTurningLight();
                case 2 -> askingAboutTurningMirrors();
                case 3 -> askingAboutMovingMirrors();
                default -> {
                    System.out.println("Invalid number. Please try again.");
                    askingAboutOperation();
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            askingAboutOperation();
        }
    }

    private void askingAboutMovingMirrors() {
        Scanner scanner = new Scanner(System.in);
        boolean hasMovableMirrors = false;

        for (int i = 0; i < grid.mirrors.length; i++) {
            if (grid.mirrors[i] instanceof MovableMirror movableMirror) {
                hasMovableMirrors = true;
                System.out.println("Mirror at column " + movableMirror.getColPosition() + " and row " + movableMirror.getRowPosition() + 
                                   " can move in direction: " + movableMirror.getMovingDirection() +
                                   " within " + movableMirror.getMovingCellsNum() + " cells. Enter (" + i + ") to move this mirror.");
            }
        }

        if (!hasMovableMirrors) {
            System.out.println("No movable mirrors available.");
            asking();
            return;
        }

        try {
            int selectedMirror = scanner.nextInt();
            if (selectedMirror >= grid.mirrors.length || !(grid.mirrors[selectedMirror] instanceof MovableMirror)) {
                System.out.println("Invalid mirror choice. Please try again.");
                askingAboutMovingMirrors();
                return;
            }

            MovableMirror selectedMovableMirror = (MovableMirror) grid.mirrors[selectedMirror];
            LinkedList<HashMap<String, Integer>> possiblePositions = selectedMovableMirror.getPosiblePositions();

            System.out.println("Possible positions for movement:");
            int index = 0;
            for (Map<String, Integer> position : possiblePositions) {
                System.out.println(index + ": Column " + position.get("col") + ", Row " + position.get("row"));
                index++;
            }

            System.out.print("Enter the index of the position you want to move the mirror to: ");
            int selectedPositionIndex = scanner.nextInt();
           if(selectedPositionIndex==0)selectedMovableMirror.dynamicMovingCellsPosition++;

            if (selectedPositionIndex < 0 || selectedPositionIndex >= possiblePositions.size()) {
                System.out.println("Invalid position choice. Please try again.");
                askingAboutMovingMirrors();
                return;
            }

            Map<String, Integer> newPosition = possiblePositions.get(selectedPositionIndex);
            selectedMovableMirror.setColPosition(newPosition.get("col"));
            selectedMovableMirror.setRowPosition(newPosition.get("row"));

            System.out.println("Mirror moved to new position: Column " + newPosition.get("col") + ", Row " + newPosition.get("row"));

            asking();
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            askingAboutMovingMirrors();
        }
    }

    private void askingAboutTurningLight() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select a direction for the light:");
        System.out.println("1. Right\n2. Left\n3. Top\n4. Bottom\n5. Bottom Left\n6. Bottom Right\n7. Top Left\n8. Top Right");

        try {
            int selectedDirNum = scanner.nextInt();
            Directions newDirection = switch (selectedDirNum) {
                case 1 -> Directions.Right;
                case 2 -> Directions.Left;
                case 3 -> Directions.Top;
                case 4 -> Directions.Bottom;
                case 5 -> Directions.BottomLeft;
                case 6 -> Directions.BottomRight;
                case 7 -> Directions.TopLeft;
                case 8 -> Directions.TopRight;
                default -> null;
            };

            if (newDirection != null) {
                grid.light.setDirection(newDirection);
                asking();
            } else {
                System.out.println("Invalid direction. Please try again.");
                askingAboutTurningLight();
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            askingAboutTurningLight();
        }
    }

    private void askingAboutTurningMirrors() {
        Scanner scanner = new Scanner(System.in);
        boolean hasRotatableMirrors = false;

        for (int i = 0; i < grid.mirrors.length; i++) {
            if (grid.mirrors[i] instanceof RotatedMirror) {
                hasRotatableMirrors = true;
                System.out.println("Mirror at column " + grid.mirrors[i].getColPosition() + 
                                   " and row " + grid.mirrors[i].getRowPosition() + " is rotatable. Enter (" + i + ") to rotate this mirror.");
            }
        }

        if (!hasRotatableMirrors) {
            System.out.println("No rotatable mirrors available.");
            asking();
            return;
        }

        try {
            int selectedMirror = scanner.nextInt();
            System.out.println("Select a new direction for the mirror:");
            System.out.println("1. Top Right\n2. Top Left\n3. Horizontal\n4. Vertical");

            int selectedDirNum = scanner.nextInt();
            MirrorDirections newDirection = switch (selectedDirNum) {
                case 1 -> MirrorDirections.topRight;
                case 2 -> MirrorDirections.topLeft;
                case 3 -> MirrorDirections.horizintal;
                case 4 -> MirrorDirections.vertical;
                default -> null;
            };

            if (newDirection != null) {
                grid.mirrors[selectedMirror].setDirection(newDirection);
                asking();
            } else {
                System.out.println("Invalid direction. Please try again.");
                askingAboutTurningMirrors();
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            askingAboutTurningMirrors();
        }
    }
}
