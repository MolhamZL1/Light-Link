/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.game;

import static com.mycompany.game.MirrorDirections.horizintal;
import static com.mycompany.game.MirrorDirections.vertical;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 *
 * @author USER
 */
public class State {
    
    int colmuns;
    int rows;
    Light light;
    Cell[][] cells;
    Target target;
    Wall[] walls;
    Mirror[] mirrors;
    boolean isWinning;
    private LinkedList<Cell> pathlight;
    
    public State(int colmuns, int rows, Light light, Target target, Wall[] walls, Mirror[] mirrors) {
        pathlight = new LinkedList<>();
        this.colmuns = colmuns;
        this.rows = rows;
        this.cells = new Cell[rows][colmuns];
        this.walls = walls;
        this.light = light;
        this.target = target;
        this.mirrors = mirrors;
        this.isWinning = false;
        updateState();
        //initCells();
        // printState();

    }

    // Deep copy constructor
    public State(State other) {
        this.colmuns = other.colmuns;
        this.rows = other.rows;
        this.light = other.light.copy(); // Assuming Light has a deepCopy method
        this.target = other.target.copy(); // Assuming Target has a deepCopy method
        this.cells = new Cell[other.rows][other.colmuns];

        // Deep copy cells
        for (int i = 0; i < other.rows; i++) {
            for (int j = 0; j < other.colmuns; j++) {
                this.cells[i][j] = other.cells[i][j].copy(); // Assuming Cell has a deepCopy method
            }
        }

        // Deep copy walls
        this.walls = new Wall[other.walls.length];
        for (int i = 0; i < other.walls.length; i++) {
            this.walls[i] = other.walls[i].copy(); // Assuming Wall has a deepCopy method
        }

        // Deep copy mirrors
        this.mirrors = new Mirror[other.mirrors.length];
        for (int i = 0; i < other.mirrors.length; i++) {
            this.mirrors[i] = other.mirrors[i].copy(); // Will return the correct type
        }

        // Deep copy pathlight
        this.pathlight = new LinkedList<>();
        for (Cell cell : other.pathlight) {
            this.pathlight.add(cell.copy()); // Assuming Cell has a deepCopy method
        }
        
        this.isWinning = other.isWinning;
        updateState();
    }
    
    public State deepCopy() {
        return new State(this);
    }
    
    private void initCells() {
        pathlight.clear();
        for (var i = 0; i < rows; i++) {
            for (var j = 0; j < colmuns; j++) {
                
                if (i == 0 || i == rows - 1 || j == 0 || j == colmuns - 1) {
                    this.cells[i][j] = new Wall(new Poistion(i, j));
                } else if (i == light.getPoistion().getRowPosition() && j == light.getPoistion().getColPosition()) {
                    this.cells[i][j] = new Light(new Poistion(i, j), light.getDirection());
                } else if (i == target.getPoistion().getRowPosition() && j == target.getPoistion().getColPosition()) {
                    this.cells[i][j] = new Target(new Poistion(i, j));
                } else {
                    this.cells[i][j] = new Cell(new Poistion(i, j));
                }
                for (Wall wall : walls) {
                    if (wall.getPoistion().getRowPosition() == i && wall.getPoistion().getColPosition() == j) {
                        this.cells[i][j] = new Wall(new Poistion(i, j));
                        break;
                        
                    }
                }
                for (Mirror mirror : mirrors) {
                    if (mirror.getPoistion().getRowPosition() == i && mirror.getPoistion().getColPosition() == j) {
                        this.cells[i][j] = new Mirror(new Poistion(i, j), mirror.getDirection());
                        break;
                        
                    }
                }
                
            }
        }
    }
    
    public LinkedList<Integer> getIndeciesOfrotatableMirrors() {
        LinkedList<Integer> indesiesOfRotatableMirrors = new LinkedList<>();
        for (int i = 0; i < this.mirrors.length; i++) {
            if (this.mirrors[i] instanceof RotatedMirror) {
                indesiesOfRotatableMirrors.add(i);
            }
        }
        return indesiesOfRotatableMirrors;
    }
    
    public LinkedList<State> getNextState() {
        LinkedList<State> states = new LinkedList<>();
        generateStates(new State(this), 0, states);
        return states;
    }
    
    private void generateStates(State currentState, int mirrorIndex, LinkedList<State> states) {
        try {
            // Apply each possible action for the light to create new states
            for (int lightAction : Action.posibleLightActions) {
                State lightModifiedState = Action.turnLightAction(currentState, lightAction);

                // After modifying the light, recursively handle mirrors
                applyMirrorActions(lightModifiedState, mirrorIndex, states);
            }
        } catch (Exception e) {
            System.err.println("Error in generating light actions: " + e.getMessage());
        }
    }
    
    private void applyMirrorActions(State state, int mirrorIndex, LinkedList<State> states) {
        // If we've finished with all mirrors, add the final state to the list
        if (mirrorIndex >= state.mirrors.length) {
            if (states.contains(state)) {
                states.add(state);
            }
            return;
        }

        // Check if the mirror at the current index is a RotatedMirror
        if (!(state.mirrors[mirrorIndex] instanceof RotatedMirror)) {
            // Skip this mirror and move to the next
            applyMirrorActions(state, mirrorIndex + 1, states);
            return;
        }

        // Apply each possible action to the current mirror
        for (int mirrorAction : Action.posibleMirrorActions) {
            try {
                State mirrorModifiedState = Action.turnMirrorAction(state, mirrorAction, mirrorIndex);

                // Recurse to handle the next mirror
                applyMirrorActions(mirrorModifiedState, mirrorIndex + 1, states);
            } catch (Exception e) {
                System.err.println("Error in generating mirror actions: " + e.getMessage());
            }
        }
    }
    
    public State updateState() {
        initCells();
        settingPathLight(light.getDirection(), light.getPoistion());
        // printState();
        return this;
    }
    
    private void settingPathLight(Directions lightDirction, Poistion poistion) {
        switch (lightDirction) {
            case Right ->
                printLightInRightDir(poistion);
            case Top ->
                printLightInTopDir(poistion);
            case Bottom ->
                printLightInBottomDir(poistion);
            case Left ->
                printLightInLeftDir(poistion);
            case BottomLeft ->
                printLightInBottomLeftDir(poistion);
            case BottomRight ->
                printLightInBottomRightDir(poistion);
            case TopLeft ->
                printLightInTopLeftDir(poistion);
            case TopRight ->
                printLightInTOPRightDir(poistion);
            default -> {
            }
        }
        
    }
    
    private void printLightInTOPRightDir(Poistion poistion) {
        int i = 1;
        int rowpos;
        int colpos;
        OUTER:
        while (true) {
            colpos = poistion.getColPosition() + i;
            rowpos = poistion.getRowPosition() - i;
            if (cells[rowpos][colpos] instanceof Wall) {
                break;
            } else if (cells[rowpos][colpos] instanceof Target) {
                isWinning = true;
                break;
            } else if (cells[rowpos][colpos] instanceof Light) {
                break;
            } else if (cells[rowpos][colpos] instanceof Mirror mirror) {
                
                switch (mirror.getDirection()) {
                    
                    case horizintal -> {
                        settingPathLight(Directions.BottomRight, new Poistion(rowpos, colpos));
                        break OUTER;
                    }
                    case vertical -> {
                        settingPathLight(Directions.TopLeft, new Poistion(rowpos, colpos));
                        break OUTER;
                    }
                    
                    default -> {
                        break OUTER;
                    }
                }
            } else {
                pathlight.add(cells[rowpos][colpos]);
                i++;
            }
            
        }
    }
    
    private void printLightInBottomLeftDir(Poistion poistion) {
        int i = 1;
        int rowpos;
        int colpos;
        OUTER:
        while (true) {
            colpos = poistion.getColPosition() - i;
            rowpos = poistion.getRowPosition() + i;
            if (cells[rowpos][colpos] instanceof Wall) {
                break;
            } else if (cells[rowpos][colpos] instanceof Target) {
                isWinning = true;
                break;
            } else if (cells[rowpos][colpos] instanceof Light) {
                break;
            } else if (cells[rowpos][colpos] instanceof Mirror mirror) {
                switch (mirror.getDirection()) {
                    case horizintal -> {
                        settingPathLight(Directions.BottomRight, new Poistion(rowpos, colpos));
                        break OUTER;
                    }
                    case vertical -> {
                        settingPathLight(Directions.TopLeft, new Poistion(rowpos, colpos));
                        break OUTER;
                    }
                    
                    default -> {
                        break OUTER;
                    }
                }
                
            } else {
                pathlight.add(cells[rowpos][colpos]);
                i++;
            }
            
        }
        
    }
    
    private void printLightInTopLeftDir(Poistion poistion) {
        int i = 1;
        int rowpos;
        int colpos;
        OUTER:
        while (true) {
            colpos = poistion.getColPosition() - i;
            rowpos = poistion.getRowPosition() - i;
            if (cells[rowpos][colpos] instanceof Wall) {
                break;
            } else if (cells[rowpos][colpos] instanceof Target) {
                isWinning = true;
                break;
            } else if (cells[rowpos][colpos] instanceof Light) {
                break;
            } else if (cells[rowpos][colpos] instanceof Mirror mirror) {
                switch (mirror.getDirection()) {
                    case horizintal -> {
                        settingPathLight(Directions.BottomLeft, new Poistion(rowpos, colpos));
                        break OUTER;
                    }
                    case vertical -> {
                        settingPathLight(Directions.TopRight, new Poistion(rowpos, colpos));
                        break OUTER;
                    }
                    
                    default -> {
                        break OUTER;
                    }
                }
                
            } else {
                pathlight.add(cells[rowpos][colpos]);
                i++;
            }
            
        }
        
    }
    
    private void printLightInBottomRightDir(Poistion poistion) {
        int i = 1;
        int rowpos;
        int colpos;
        OUTER:
        while (true) {
            colpos = poistion.getColPosition() + i;
            rowpos = poistion.getRowPosition() + i;
            if (cells[rowpos][colpos] instanceof Wall) {
                break;
            } else if (cells[rowpos][colpos] instanceof Target) {
                isWinning = true;
                break;
            } else if (cells[rowpos][colpos] instanceof Light) {
                break;
            } else if (cells[rowpos][colpos] instanceof Mirror mirror) {
                switch (mirror.getDirection()) {
                    case horizintal -> {
                        settingPathLight(Directions.TopRight, new Poistion(rowpos, colpos));
                        break OUTER;
                    }
                    case vertical -> {
                        settingPathLight(Directions.BottomLeft, new Poistion(rowpos, colpos));
                        break OUTER;
                    }
                    
                    default -> {
                        break OUTER;
                    }
                }
                
            } else {
                pathlight.add(cells[rowpos][colpos]);
                i++;
            }
            
        }
        
    }
    
    private void printLightInRightDir(Poistion poistion) {
        int i = 1;
        int rowpos = poistion.getRowPosition();
        int colpos;
        OUTER:
        while (true) {
            colpos = poistion.getColPosition() + i;
            
            if (cells[rowpos][colpos] instanceof Wall) {
                break;
            } else if (cells[rowpos][colpos] instanceof Target) {
                isWinning = true;
                break;
            } else if (cells[rowpos][colpos] instanceof Light) {
                break;
            } else if (cells[rowpos][colpos] instanceof Mirror mirror) {
                switch (mirror.getDirection()) {
                    case topRight -> {
                        settingPathLight(Directions.Top, new Poistion(rowpos, colpos));
                        break OUTER;
                    }
                    case topLeft -> {
                        settingPathLight(Directions.Bottom, new Poistion(rowpos, colpos));
                        break OUTER;
                    }
                    
                    default -> {
                        break OUTER;
                    }
                }
                
            } else {
                pathlight.add(cells[rowpos][colpos]);
                i++;
            }
            
        }
        
    }
    
    private void printLightInLeftDir(Poistion poistion) {
        int i = 1;
        int rowpos = poistion.getRowPosition();
        int colpos;
        OUTER:
        while (true) {
            colpos = poistion.getColPosition() - i;
            
            if (cells[rowpos][colpos] instanceof Wall) {
                break;
            } else if (cells[rowpos][colpos] instanceof Target) {
                isWinning = true;
                break;
            } else if (cells[rowpos][colpos] instanceof Light) {
                break;
            } else if (cells[rowpos][colpos] instanceof Mirror mirror) {
                switch (mirror.getDirection()) {
                    case topRight -> {
                        settingPathLight(Directions.Bottom, new Poistion(rowpos, colpos));
                        break OUTER;
                    }
                    case topLeft -> {
                        settingPathLight(Directions.Top, new Poistion(rowpos, colpos));
                        break OUTER;
                    }
                    
                    default -> {
                        break OUTER;
                    }
                }
                
            } else {
                pathlight.add(cells[rowpos][colpos]);
                i++;
            }
            
        }
        
    }
    
    private void printLightInTopDir(Poistion poistion) {
        int i = 1;
        int rowpos;
        int colpos = poistion.getColPosition();
        OUTER:
        while (true) {
            rowpos = poistion.getRowPosition() - i;
            
            if (cells[rowpos][colpos] instanceof Wall) {
                break;
            } else if (cells[rowpos][colpos] instanceof Target) {
                isWinning = true;
                break;
            } else if (cells[rowpos][colpos] instanceof Light) {
                break;
            } else if (cells[rowpos][colpos] instanceof Mirror mirror) {
                switch (mirror.getDirection()) {
                    case topRight -> {
                        settingPathLight(Directions.Right, new Poistion(rowpos, colpos));
                        break OUTER;
                    }
                    case topLeft -> {
                        settingPathLight(Directions.Left, new Poistion(rowpos, colpos));
                        break OUTER;
                    }
                    
                    default -> {
                        break OUTER;
                    }
                }
                
            } else {
                pathlight.add(cells[rowpos][colpos]);
                i++;
            }
            
        }
        
    }
    
    private void printLightInBottomDir(Poistion poistion) {
        int i = 1;
        int rowpos;
        int colpos = poistion.getColPosition();
        OUTER:
        while (true) {
            rowpos = poistion.getRowPosition() + i;
            
            if (cells[rowpos][colpos] instanceof Wall) {
                break;
            } else if (cells[rowpos][colpos] instanceof Target) {
                isWinning = true;
                break;
            } else if (cells[rowpos][colpos] instanceof Light) {
                break;
            } else if (cells[rowpos][colpos] instanceof Mirror mirror) {
                switch (mirror.getDirection()) {
                    case topRight -> {
                        settingPathLight(Directions.Left, new Poistion(rowpos, colpos));
                        break OUTER;
                    }
                    case topLeft -> {
                        settingPathLight(Directions.Right, new Poistion(rowpos, colpos));
                        break OUTER;
                    }
                    
                    default -> {
                        break OUTER;
                    }
                }
                
            } else {
                pathlight.add(cells[rowpos][colpos]);
                i++;
            }
            
        }
        
    }
    
    public void printState() {
        printColNum();
        for (var i = 0; i < rows; i++) {
            for (var j = 0; j < colmuns; j++) {
                if (cells[i][j] instanceof Wall wall) {
                    wall.print();
                } else if (cells[i][j] instanceof Target targetcasting) {
                    targetcasting.print();
                } else if (cells[i][j] instanceof Light lightcasting) {
                    lightcasting.print();
                } else if (cells[i][j] instanceof Mirror mirror) {
                    mirror.print();
                } else {
                    if (pathlight.contains(cells[i][j])) {
                        Light.printPathLight();
                    } else {
                        cells[i][j].print();
                    }
                    
                }
                
                printRowsNum(j, i);
            }
            System.out.println();
        }
        System.out.println("-------------------------------------------------------------------------");
        
    }
    
    private void printRowsNum(int j, int i) {
        if (j == colmuns - 1) {
            System.out.print(i);
            
        }
    }
    
    private void printColNum() {
        
        for (var j = 0; j < colmuns; j++) {
            if (j > 9) {
                System.out.print(j + " ");
            } else {
                System.out.print(" " + j + " ");
            }
        }
        System.out.println();
    }
    
    public LinkedList<Cell> getPathlight() {
        return pathlight;
    }
    
    public Mirror[] getMirrors() {
        return mirrors;
    }
    
    public void setMirrors(Mirror[] mirrors) {
        this.mirrors = mirrors;
    }
    
    public boolean isIsWinning() {
        // or
        return isWinning;
//        Cell lastElement = pathlight.getLast();
//        if (lastElement.getRowPosition()== target.getRowPosition() && lastElement.getColPosition()== target.getColPosition()) {
//            isWinning=true;
//            return true;
//        }
//        else 
//           return false;
    }
    
    public Wall[] getWalls() {
        return walls;
    }
    
    public void setWalls(Wall[] walls) {
        this.walls = walls;
    }
    
    public int getColmuns() {
        return colmuns;
    }
    
    public int getRows() {
        return rows;
    }
    
    public Light getLight() {
        return light;
    }
    
    public void setLight(Light light) {
        this.light = light;
    }
    
    public Cell[][] getCells() {
        return cells;
    }
    
    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }
    
    public Target getTarget() {
        return target;
    }
    
    public void setTarget(Target target) {
        this.target = target;
    }
    
}
