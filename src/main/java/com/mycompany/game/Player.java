/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.game;

import java.util.Scanner;

/**
 *
 * @author USER
 */
public class Player {

    State grid;

    void startGame() {
        grid = initGridLevel4();
        asking();

    }

//    private State initGridLevel7() {
//        Light light1 = new Light(Directions.Left, 11, 5);
//        Target target1 = new Target(4, 2);
//        Wall wall1 = new Wall(5, 3);
//        Wall wall2 = new Wall(10, 4);
//
//        Wall[] walls = {wall1, wall2};
//        Mirror mirror1 = new Mirror(MirrorDirections.topLeft, 4, 5, true, false);
//        Mirror mirror2 = new Mirror(MirrorDirections.topRight, 7, 5, true, false);
//        Mirror mirror3 = new Mirror(MirrorDirections.topRight, 8, 2, true, false);
//        Mirror mirror4 = new Mirror(MirrorDirections.topLeft, 11, 2, true, false);
//
//        Mirror[] mirrors = {mirror1, mirror2, mirror3, mirror4};
//        State grid1 = new State(8, 15, light1, target1, walls, mirrors);
//        return grid1;
//    }

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

    private void asking() {

        grid.turnlight();
        if (!grid.isWinning) {
            askingAboutOperation();

        }
    }

    private void askingAboutOperation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("if you want to turn light press (1)");
        System.out.println("if you want to turn mirror press (2)");
        System.out.println("if you want to move mirror press (3)");
        try {
            int selectedOperation = scanner.nextInt();
            switch (selectedOperation) {
                case 1 ->
                    askingAboutTurningLight();
                case 2 ->
                    askingAboutTurningMirrors();
                case 3 ->

                    askingAboutMovingMirrors();
                default -> {
                    System.out.println("Oops You've just entered an invalid number, please try again");
                    askingAboutOperation();
                }
            }
        } catch (Exception e) {
            System.out.println("Oops You've just entered an invalid value, please try again");
            askingAboutOperation();
        }

    }

    private void askingAboutTurningLight() {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("if you want it turned to right press (1)");
            System.out.println("if you want it turned to left press (2)");
            System.out.println("if you want it turned to Top press (3)");
            System.out.println("if you want it turned to Bottom press (4)");
            System.out.println("if you want it turned to Bottom Left press (5)");
            System.out.println("if you want it turned to Bottom Right press (6)");
            System.out.println("if you want it turned to Top Left press (7)");
            System.out.println("if you want it turned to Top Right press (8)");
            int selectedDirNum = scanner.nextInt();

            switch (selectedDirNum) {
                case 1 ->
                    grid.light.setDirection(Directions.Right);
                case 2 ->
                    grid.light.setDirection(Directions.Left);
                case 3 ->
                    grid.light.setDirection(Directions.Top);
                case 4 ->
                    grid.light.setDirection(Directions.Bottom);
                case 5 ->
                    grid.light.setDirection(Directions.BottomLeft);
                case 6 ->
                    grid.light.setDirection(Directions.BottomRight);
                case 7 ->
                    grid.light.setDirection(Directions.TopLeft);
                case 8 ->
                    grid.light.setDirection(Directions.TopRight);

                default -> {
                    System.out.println("Oops You've just entered an invalid number, please try again");
                    askingAboutTurningLight();
                }
            }

            asking();
        } catch (Exception e) {
            System.out.println("Oops You've just entered an invalid value, please try again");
            askingAboutTurningLight();
        }
    }

    private void askingAboutMovingMirrors() {
        try {
            Scanner scanner = new Scanner(System.in);
            for (int i = 0; i < grid.mirrors.length; i++) {
//                if (grid.mirrors[i].isIsMovable()) {
//                    System.out.println("if you want to move the mirror at the colmun " + grid.mirrors[i].getColPosition() + " and at the row " + grid.mirrors[i].getRowPosition() + " enter (" + i + ")");
//                }
            }
            int selectedMirror = scanner.nextInt();
            System.out.println("if you want it turned to right press (1)");
            System.out.println("if you want it turned to left press (2)");
            System.out.println("if you want it horizintal press (3)");
            System.out.println("if you want it vetical press (4)");
            int slectedDirNum = scanner.nextInt();

            switch (slectedDirNum) {
                case 1 ->
                    grid.mirrors[selectedMirror].setDirection(MirrorDirections.topRight);
                case 2 ->
                    grid.mirrors[selectedMirror].setDirection(MirrorDirections.topLeft);
                case 3 ->
                    grid.mirrors[selectedMirror].setDirection(MirrorDirections.horizintal);
                case 4 ->
                    grid.mirrors[selectedMirror].setDirection(MirrorDirections.vertical);
                default -> {
                    System.out.println("Oops You've just entered an invalid number, please try again");
                    askingAboutTurningMirrors();
                }
            }

            asking();
        } catch (Exception e) {
            System.out.println("Oops You've just entered an invalid value, please try again");
            askingAboutTurningMirrors();
        }

    }

    private void askingAboutTurningMirrors() {
        try {
            boolean thereIsTurnableMirrors = false;
            Scanner scanner = new Scanner(System.in);
            for (int i = 0; i < grid.mirrors.length; i++) {
                if (grid.mirrors[i] instanceof RotatedMirror) {
                    System.out.println("if you want to turn the mirror at the colmun " + grid.mirrors[i].getColPosition() + " and at the row " + grid.mirrors[i].getRowPosition() + " enter (" + i + ")");
                    thereIsTurnableMirrors = true;
                }
            }
            if (!thereIsTurnableMirrors) {
                System.out.println("------>Sorry there is no mirror to turn<------");
                asking();
                return;
            }
            int selectedMirror = scanner.nextInt();
            System.out.println("if you want it turned to right press (1)");
            System.out.println("if you want it turned to left press (2)");
            System.out.println("if you want it horizintal press (3)");
            System.out.println("if you want it vetical press (4)");
            int slectedDirNum = scanner.nextInt();

            switch (slectedDirNum) {
                case 1 ->
                    grid.mirrors[selectedMirror].setDirection(MirrorDirections.topRight);
                case 2 ->
                    grid.mirrors[selectedMirror].setDirection(MirrorDirections.topLeft);
                case 3 ->
                    grid.mirrors[selectedMirror].setDirection(MirrorDirections.horizintal);
                case 4 ->
                    grid.mirrors[selectedMirror].setDirection(MirrorDirections.vertical);
                default -> {
                    System.out.println("Oops You've just entered an invalid number, please try again");
                    askingAboutTurningMirrors();
                }
            }

            asking();
        } catch (Exception e) {
            System.out.println("Oops You've just entered an invalid value, please try again");
            askingAboutTurningMirrors();
        }

    }

//    private void enteringTargetPoistion() {
//        Scanner scanner = new Scanner(System.in);
//
//        try {
//            System.out.println("Enter colmun index of the target");
//            int c = scanner.nextInt();
//            System.out.println("Enter row index of the target");
//            int r = scanner.nextInt();
//            target = new Target(r, c);
//        } catch (Exception e) {
//            System.out.println("Oops You entered something invalid");
//            System.out.println("Please enter a valid number");
//            enteringTargetPoistion();
//        }
//    }
//
//    private void enteringWallsPositions() {
//        Scanner scanner = new Scanner(System.in);
//        int r;
//        int c;
//        for (int i = 0; i < wallsNum; i++) {
//            try {
//
//                System.out.println("Enter colmun index of the wall " + (i + 1));
//                c = scanner.nextInt();
//
//                System.out.println("Enter row index of the wall " + (i + 1));
//                r = scanner.nextInt();
//
//                walls[i] = new Wall(c, r);
//            } catch (Exception e) {
//                System.out.println("Oops You entered something invalid");
//                System.out.println("Please enter a valid number");
//                i--;
//            }
//        }
//    }
//
//    private void enteringWallsNum() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("How many walls (#) do you want?");
//        try {
//            wallsNum = scanner.nextInt();
//            walls = new Wall[wallsNum];
//        } catch (Exception e) {
//            System.out.println("Oops You entered something invalid");
//            System.out.println("Please enter a valid number");
//            enteringWallsNum();
//        }
//    }
//
//    private void enteringGridSize() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter the size of the grid");
//        try {
//            int size = scanner.nextInt();
//            colmuns = size;
//            rows = size;
//
//        } catch (Exception e) {
//            System.out.println("Oops You entered something invalid");
//            System.out.println("Please enter a valid number");
//            enteringGridSize();
//        }
//
//    }
}
