package com.mycompany.game;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Player {

    private State state;

    public void startGame() {
        state = new Levels().initGridLevel4();
        state.printState();
        askingplayingMethod();

//        Set<State> states = state.getNextStatemodified();
//        System.out.println(states.size());
//        for (State state1 : states) {
//            
//            state1.printState();
//             Set<State> states1 = state1.getNextStatemodified();
//        System.out.println(states.size());
//        for (State state11 : states1) {
//            
//            state11.printState();
//           
//        }System.out.println("-----------------");
//        }
    }

    private void askingplayingMethod() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How do you want to play the game");
        System.out.println("1. Manualy");
        System.out.println("2. Bfs");
        System.out.println("3. Dfs");

        try {
            int selectedOperation = scanner.nextInt();
            switch (selectedOperation) {
                case 1 ->
                    asking();
                case 2 ->
                    state.findWinningStateBFS().printState();
                case 3 ->
                    state.findWinningStateDFS().printState();
                default -> {
                    System.out.println("Invalid number. Please try again.");
                    askingplayingMethod();
                }

            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            askingplayingMethod();
        }
    }

    private void asking() {
        state.updateState();
        if (!state.isWinning) {
            askingAboutOperation();
        } else {

            System.out.println("Congratulations !!,You Win!!");

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
                case 1 ->
                    askingAboutTurningLight();
                case 2 ->
                    askingAboutTurningMirrors();
                case 3 ->
                    askingAboutMovingMirrors();
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

    private void askingAboutTurningLight() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select a direction for the light:");
        System.out.println("1. Right\n2. Left\n3. Top\n4. Bottom\n5. Bottom Left\n6. Bottom Right\n7. Top Left\n8. Top Right");

        try {
            int selectedDirNum = scanner.nextInt();
            state = Action.turnLightAction(state, selectedDirNum);
            asking();
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            askingAboutTurningLight();
        }
    }

    private void askingAboutTurningMirrors() {
        Scanner scanner = new Scanner(System.in);
        LinkedList<Integer> indesiesOfRotatableMirrors = new LinkedList<>();
        for (int i = 0; i < state.mirrors.length; i++) {
            if (state.mirrors[i] instanceof RotatedMirror) {

                System.out.println("Mirror at column " + state.mirrors[i].getPoistion().getColPosition()
                        + " and row " + state.mirrors[i].getPoistion().getRowPosition() + " is rotatable. Enter (" + i + ") to rotate this mirror.");
                indesiesOfRotatableMirrors.add(i);
            }
        }
        if (indesiesOfRotatableMirrors.isEmpty()) {
            System.out.println("No rotatable mirrors available.");
            asking();
            return;
        }

        try {
            int selectedMirror = scanner.nextInt();
            if (!indesiesOfRotatableMirrors.contains(selectedMirror)) {
                throw new Exception();
            }
            System.out.println("Select a new direction for the mirror:");
            System.out.println("1. Top Right\n2. Top Left\n3. Horizontal\n4. Vertical");

            int selectedDirNum = scanner.nextInt();
            state = Action.turnMirrorAction(state, selectedDirNum, selectedMirror);
            asking();
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            askingAboutTurningMirrors();
        }
    }

    private void askingAboutMovingMirrors() {
//        Scanner scanner = new Scanner(System.in);
//        boolean hasMovableMirrors = false;
//
//        for (int i = 0; i < grid.mirrors.length; i++) {
//            if (grid.mirrors[i] instanceof MovableMirror movableMirror) {
//                hasMovableMirrors = true;
//                System.out.println("Mirror at column " + movableMirror.getColPosition() + " and row " + movableMirror.getRowPosition()
//                        + " can move in direction: " + movableMirror.getMovingDirection()
//                        + " within " + movableMirror.getMovingCellsNum() + " cells. Enter (" + i + ") to move this mirror.");
//            }
//        }
//
//        if (!hasMovableMirrors) {
//            System.out.println("No movable mirrors available.");
//            asking();
//            return;
//        }
//
//        try {
//            int selectedMirror = scanner.nextInt();
//            if (selectedMirror >= grid.mirrors.length || !(grid.mirrors[selectedMirror] instanceof MovableMirror)) {
//                System.out.println("Invalid mirror choice. Please try again.");
//                askingAboutMovingMirrors();
//                return;
//            }
//
//            MovableMirror selectedMovableMirror = (MovableMirror) grid.mirrors[selectedMirror];
//            LinkedList<HashMap<String, Integer>> possiblePositions = selectedMovableMirror.getPosiblePositions();
//
//            System.out.println("Possible positions for movement:");
//            int index = 0;
//            for (Map<String, Integer> position : possiblePositions) {
//                System.out.println(index + ": Column " + position.get("col") + ", Row " + position.get("row"));
//                index++;
//            }
//
//            System.out.print("Enter the index of the position you want to move the mirror to: ");
//            int selectedPositionIndex = scanner.nextInt();
//
//            if (selectedPositionIndex < 0 || selectedPositionIndex >= possiblePositions.size()) {
//                System.out.println("Invalid position choice. Please try again.");
//                askingAboutMovingMirrors();
//                return;
//            }
//
//            Map<String, Integer> newPosition = possiblePositions.get(selectedPositionIndex);
//            selectedMovableMirror.setColPosition(newPosition.get("col"));
//            selectedMovableMirror.setRowPosition(newPosition.get("row"));
//
//            System.out.println("Mirror moved to new position: Column " + newPosition.get("col") + ", Row " + newPosition.get("row"));
//
//            asking();
//        } catch (Exception e) {
//            System.out.println("Invalid input. Please try again.");
//            askingAboutMovingMirrors();
//        }
    }

}
