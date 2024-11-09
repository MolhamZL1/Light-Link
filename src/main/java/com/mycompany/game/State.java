/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.game;

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
        pathlight = new LinkedList<Cell>();
        this.colmuns = colmuns;
        this.rows = rows;
        this.cells = new Cell[rows][colmuns];
        this.walls = walls;
        this.light = light;
        this.target = target;
        this.mirrors = mirrors;
        this.isWinning = false;
        initCells();
        printState();

    }

    private void initCells() {
        pathlight.clear();
        for (var i = 0; i < rows; i++) {
            for (var j = 0; j < colmuns; j++) {

                if (i == 0 || i == rows - 1 || j == 0 || j == colmuns - 1) {
                    this.cells[i][j] = new Cell(CellType.WallType, i, j);
                } else if (i == light.getRowPosition() && j == light.getColPosition()) {
                    this.cells[i][j] = new Cell(CellType.Light, i, j);
                } else if (i == target.getRowPosition() && j == target.getColPosition()) {
                    this.cells[i][j] = new Cell(CellType.Target, i, j);
                } else {
                    this.cells[i][j] = new Cell(CellType.Empty, i, j);
                }
                for (Wall wall : walls) {
                    if (wall.getColPosition() == j && wall.getRowPosition() == i) {
                        this.cells[i][j] = new Cell(CellType.WallType, i, j);
                        break;

                    }
                }
                for (Mirror mirror : mirrors) {
                    if (mirror.getColPosition() == j && mirror.getRowPosition() == i) {
                        this.cells[i][j] = new Cell(CellType.MirrorType, i, j);
                        break;

                    }
                }

            }
        }
    }

    public HashSet<State> getNextState(State state) {
        HashSet<State> states = new HashSet<State>();

        for (int i = 0; i < state.mirrors.length; i++) {
            if (state.mirrors[i] instanceof RotatedMirror) {
                for (MirrorDirections dir : MirrorDirections.values()) {
                    System.out.print(state.mirrors[i].getRowPosition());

                    System.out.println(dir);
                    state.mirrors[i].setDirection(dir);

                    State nextState = state.turnlight();

                    states.add(nextState);

                }
                getNextState(state);
            }
        }

        return states;
    }

    boolean v(LinkedList<Cell> pathlight1, LinkedList<Cell> pathlight2) {
        for (Cell path1 : pathlight1) {
            for (Cell path2 : pathlight2) {
                if (path1.getRowPosition() != path2.getRowPosition() || path1.getColPosition() == path2.getColPosition()) {
                    System.out.println("hiii");
                    return false;

                }
            }
        }
        return true;
    }

    public State turnlight() {
        initCells();
        settingPathLight(light.getDirection(), light.getRowPosition(), light.getColPosition());
        printState();
        return this;
    }

    private void settingPathLight(Directions dir, int startingLightrow, int startingLightCol) {
        switch (dir) {
            case Right ->
                printLightInRightDir(startingLightrow, startingLightCol);
            case Top ->
                printLightInTopDir(startingLightrow, startingLightCol);
            case Bottom ->
                printLightInBottomDir(startingLightrow, startingLightCol);
            case Left ->
                printLightInLeftDir(startingLightrow, startingLightCol);
            case BottomLeft ->
                printLightInBottomLeftDir(startingLightrow, startingLightCol);
            case BottomRight ->
                printLightInBottomRightDir(startingLightrow, startingLightCol);
            case TopLeft ->
                printLightInTopLeftDir(startingLightrow, startingLightCol);
            case TopRight ->
                printLightInTOPRightDir(startingLightrow, startingLightCol);
            default -> {
            }
        }

    }

    private void printLightInTOPRightDir(int startingLightrow, int startingLightCol) {
        int i = 1;

        CellType cellType;
        int rowpos = startingLightrow;
        int colpos = startingLightCol;
        OUTER:
        while (true) {
            colpos = startingLightCol + i;
            rowpos = startingLightrow - i;
            cellType = cells[rowpos][colpos].getCellType();

            switch (cellType) {
                case Empty, PathLight -> {
                    cells[rowpos][colpos].setCellType(CellType.PathLight);
                    pathlight.add(cells[rowpos][colpos]);
                    i++;
                }
                case WallType -> {
                    break OUTER;
                }
                case Target -> {
                    isWinning = true;
                    break OUTER;
                }
                case MirrorType -> {
                    MirrorDirections mirrorDir
                            = knowsMirrorDir(colpos, rowpos);
                    switch (mirrorDir) {
                        case horizintal -> {
                            settingPathLight(Directions.BottomRight, rowpos, colpos);
                            break OUTER;
                        }
                        case vertical -> {
                            settingPathLight(Directions.TopLeft, rowpos, colpos);
                            break OUTER;
                        }

                        default -> {
                            break OUTER;
                        }
                    }
                }
                default -> {
                }
            }

        }
    }

    private void printLightInBottomLeftDir(int startingLightrow, int startingLightCol) {
        int i = 1;

        CellType cellType;
        int rowpos = startingLightrow;
        int colpos = startingLightCol;
        OUTER:
        while (true) {
            colpos = startingLightCol - i;
            rowpos = startingLightrow + i;
            cellType = cells[rowpos][colpos].getCellType();

            switch (cellType) {
                case Empty, PathLight -> {
                    cells[rowpos][colpos].setCellType(CellType.PathLight);
                    pathlight.add(cells[rowpos][colpos]);
                    i++;
                }
                case WallType -> {
                    break OUTER;
                }
                case Target -> {
                    isWinning = true;
                    break OUTER;
                }
                case MirrorType -> {
                    MirrorDirections mirrorDir
                            = knowsMirrorDir(colpos, rowpos);
                    switch (mirrorDir) {
                        case horizintal -> {
                            settingPathLight(Directions.TopLeft, rowpos, colpos);
                            break OUTER;
                        }
                        case vertical -> {
                            settingPathLight(Directions.BottomRight, rowpos, colpos);
                            break OUTER;
                        }

                        default -> {
                            break OUTER;
                        }
                    }
                }
                default -> {
                }
            }

        }
    }

    private void printLightInTopLeftDir(int startingLightrow, int startingLightCol) {
        int i = 1;

        CellType cellType;
        int rowpos = startingLightrow;
        int colpos = startingLightCol;
        OUTER:
        while (true) {
            colpos = startingLightCol - i;
            rowpos = startingLightrow - i;
            cellType = cells[rowpos][colpos].getCellType();

            switch (cellType) {
                case Empty, PathLight -> {
                    cells[rowpos][colpos].setCellType(CellType.PathLight);
                    pathlight.add(cells[rowpos][colpos]);
                    i++;
                }
                case WallType -> {
                    break OUTER;
                }
                case Target -> {
                    isWinning = true;
                    break OUTER;
                }
                case MirrorType -> {
                    MirrorDirections mirrorDir
                            = knowsMirrorDir(colpos, rowpos);
                    switch (mirrorDir) {
                        case horizintal -> {
                            settingPathLight(Directions.BottomLeft, rowpos, colpos);
                            break OUTER;
                        }
                        case vertical -> {
                            settingPathLight(Directions.TopRight, rowpos, colpos);
                            break OUTER;
                        }

                        default -> {
                            break OUTER;
                        }
                    }
                }
                default -> {
                }
            }

        }
    }

    private void printLightInBottomRightDir(int startingLightrow, int startingLightCol) {
        int i = 1;

        CellType cellType;
        int rowpos = startingLightrow;
        int colpos = startingLightCol;
        OUTER:
        while (true) {
            colpos = startingLightCol + i;
            rowpos = startingLightrow + i;
            cellType = cells[rowpos][colpos].getCellType();

            switch (cellType) {
                case Empty, PathLight -> {
                    cells[rowpos][colpos].setCellType(CellType.PathLight);
                    pathlight.add(cells[rowpos][colpos]);
                    i++;
                }
                case WallType -> {
                    break OUTER;
                }
                case Target -> {
                    isWinning = true;
                    break OUTER;
                }
                case MirrorType -> {
                    MirrorDirections mirrorDir
                            = knowsMirrorDir(colpos, rowpos);
                    switch (mirrorDir) {
                        case horizintal -> {
                            settingPathLight(Directions.TopRight, rowpos, colpos);
                            break OUTER;
                        }
                        case vertical -> {
                            settingPathLight(Directions.BottomLeft, rowpos, colpos);
                            break OUTER;
                        }

                        default -> {
                            break OUTER;
                        }
                    }
                }
                default -> {
                }
            }

        }
    }

    private void printLightInRightDir(int startingLightrow, int startingLightCol) {
        int i = 1;

        CellType cellType;
        int rowpos = startingLightrow;
        int colpos;
        OUTER:
        while (true) {
            colpos = startingLightCol + i;
            cellType = cells[rowpos][colpos].getCellType();

            switch (cellType) {
                case Empty, PathLight -> {
                    cells[rowpos][colpos].setCellType(CellType.PathLight);
                    pathlight.add(cells[rowpos][colpos]);
                    i++;
                }
                case WallType -> {
                    break OUTER;
                }
                case Target -> {
                    isWinning = true;
                    break OUTER;
                }
                case MirrorType -> {
                    MirrorDirections mirrorDir
                            = knowsMirrorDir(colpos, rowpos);
                    switch (mirrorDir) {
                        case topRight -> {
                            settingPathLight(Directions.Top, rowpos, colpos);
                            break OUTER;
                        }
                        case topLeft -> {
                            settingPathLight(Directions.Bottom, rowpos, colpos);
                            break OUTER;
                        }
                        default -> {
                            break OUTER;
                        }
                    }
                }
                default -> {
                }
            }

        }
    }

    private void printLightInLeftDir(int startingLightrow, int startingLightCol) {
        int i = 1;

        CellType cellType;
        int rowpos = startingLightrow;
        int colpos;
        OUTER:
        while (true) {
            colpos = startingLightCol - i;
            cellType = cells[rowpos][colpos].getCellType();

            switch (cellType) {
                case Empty, PathLight -> {
                    cells[rowpos][colpos].setCellType(CellType.PathLight);
                    pathlight.add(cells[rowpos][colpos]);
                    i++;
                }
                case WallType -> {
                    break OUTER;
                }
                case Target -> {
                    isWinning = true;
                    break OUTER;
                }
                case MirrorType -> {
                    MirrorDirections mirrorDir
                            = knowsMirrorDir(colpos, rowpos);
                    switch (mirrorDir) {
                        case topRight -> {
                            settingPathLight(Directions.Bottom, rowpos, colpos);
                            break OUTER;
                        }
                        case topLeft -> {
                            settingPathLight(Directions.Top, rowpos, colpos);
                            break OUTER;
                        }
                        default -> {
                            break OUTER;
                        }
                    }
                }
                default -> {
                }
            }

        }
    }

    private void printLightInTopDir(int startingLightrow, int startingLightCol) {
        int i = 1;

        CellType cellType;
        int colpos = startingLightCol;
        int rowpos;
        OUTER:
        while (true) {
            rowpos = startingLightrow - i;
            cellType = cells[rowpos][colpos].getCellType();

            switch (cellType) {
                case Empty, PathLight -> {
                    cells[rowpos][colpos].setCellType(CellType.PathLight);
                    pathlight.add(cells[rowpos][colpos]);
                    i++;
                }
                case WallType -> {
                    break OUTER;
                }
                case Target -> {
                    isWinning = true;
                    break OUTER;
                }
                case MirrorType -> {
                    MirrorDirections mirrorDir
                            = knowsMirrorDir(colpos, rowpos);
                    switch (mirrorDir) {
                        case topRight -> {
                            settingPathLight(Directions.Right, rowpos, colpos);
                            break OUTER;
                        }
                        case topLeft -> {
                            settingPathLight(Directions.Left, rowpos, colpos);
                            break OUTER;
                        }
                        default -> {
                            break OUTER;
                        }
                    }

                }
                default -> {

                }
            }

        }
    }

    private void printLightInBottomDir(int startingLightrow, int startingLightCol) {
        int i = 1;

        CellType cellType;
        int colpos = startingLightCol;
        int rowpos;
        OUTER:
        while (true) {
            rowpos = startingLightrow + i;
            cellType = cells[rowpos][colpos].getCellType();

            switch (cellType) {
                case Empty, PathLight -> {
                    cells[rowpos][colpos].setCellType(CellType.PathLight);
                    pathlight.add(cells[rowpos][colpos]);
                    i++;
                }
                case WallType -> {
                    break OUTER;
                }
                case Target -> {
                    isWinning = true;
                    break OUTER;
                }
                case MirrorType -> {
                    MirrorDirections mirrorDir
                            = knowsMirrorDir(colpos, rowpos);
                    switch (mirrorDir) {
                        case topRight -> {
                            settingPathLight(Directions.Left, rowpos, colpos);
                            break OUTER;
                        }
                        case topLeft -> {
                            settingPathLight(Directions.Right, rowpos, colpos);
                            break OUTER;
                        }
                        default -> {
                            break OUTER;
                        }
                    }
                }
                default -> {
                }
            }

        }
    }

    private MirrorDirections knowsMirrorDir(int colpos, int rowpos) {

        for (Mirror mirror : mirrors) {
            if (mirror.getColPosition() == colpos && mirror.getRowPosition() == rowpos) {
                return mirror.getDirection();

            }
        }
        return MirrorDirections.horizintal;
    }

    private void printState() {
        printColNum();
        for (var i = 0; i < rows; i++) {
            for (var j = 0; j < colmuns; j++) {

                switch (cells[i][j].getCellType()) {
                    case Empty ->
                        System.out.print(" . ");
                    case WallType ->
                        Wall.print();
                    case Target ->
                        target.print();
                    case Light ->
                        light.print();
                    case MirrorType ->
                        printMirrorDir(i, j);
                    case PathLight ->
                        System.out.print(" = ");

                    default -> {
                    }
                }
                printRowsNum(j, i);
            }
            System.out.println();
        }
        System.out.println("-------------------------------------------------------------------------");
        if (isIsWinning()) {
            System.out.println("Congratulations !!,You Win!!");

        }
    }

    void printMirrorDir(int row, int col) {
        for (Mirror mirror : mirrors) {
            if (mirror.getColPosition() == col && mirror.getRowPosition() == row) {
                mirror.print();
            }
        }
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
