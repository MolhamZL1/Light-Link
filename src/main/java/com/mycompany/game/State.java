/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.game;

import static com.mycompany.game.MirrorDirections.horizintal;
import static com.mycompany.game.MirrorDirections.vertical;
import java.awt.List;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author USER
 */
public class State {

    int colmuns;
    int rows;
    private int cost;
    private int huristic;
    private int f;
    Light light;
    Cell[][] cells;
    Target target;
    Wall[] walls;
    State father;
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
        cost = 0;
        huristic=0;
        f=cost+huristic;
        updateState();
        //initCells();
        // printState();

    }

    // Deep copy constructor
    public State(State other) {
        this.colmuns = other.colmuns;
        this.rows = other.rows;
        this.light = other.light.copy();
        this.target = other.target.copy();
        this.cells = new Cell[other.rows][other.colmuns];

        // Deep copy cells
        for (int i = 0; i < other.rows; i++) {
            for (int j = 0; j < other.colmuns; j++) {
                this.cells[i][j] = other.cells[i][j].copy();
            }
        }

        // Deep copy walls
        this.walls = new Wall[other.walls.length];
        for (int i = 0; i < other.walls.length; i++) {
            this.walls[i] = other.walls[i].copy();
        }

        this.mirrors = new Mirror[other.mirrors.length];
        for (int i = 0; i < other.mirrors.length; i++) {
            if (this.mirrors[i] instanceof RotatedMirror) {
                this.mirrors[i] = new RotatedMirror((RotatedMirror) other.mirrors[i]);
                return;
            } else {
                this.mirrors[i] = other.mirrors[i].copy();
            }
        }

        this.pathlight = new LinkedList<>();
        for (Cell cell : other.pathlight) {
            this.pathlight.add(cell.copy());
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.pathlight);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final State other = (State) obj;
        return Objects.equals(this.pathlight, other.pathlight);
    }

//    @Override
//    public int compareTo(State other) {
//        return Integer.compare(this.cost, other.cost); // Compare states based on cost
//    }
   
       public State findWinningStateAStar() {
        Set<State> visitedStates = new HashSet<>();
        PriorityQueue<State> priorityQueue = new PriorityQueue<>(new FComprator()); // Uses compareTo from State

        priorityQueue.add(this);

        int i = 0;
        while (!priorityQueue.isEmpty()) {
            State currentState = priorityQueue.poll();
            System.out.println(i);
            i++;
            currentState.printState();

            if (currentState.isIsWinning()) {
                return currentState;
            }

            visitedStates.add(currentState);

            for (State nextState : currentState.getNextStates()) {

                if (!priorityQueue.contains(nextState)&& !visitedStates.contains(nextState)) {
                    nextState.father = currentState;
                    nextState.cost = currentState.cost + calculateCost(currentState, nextState);
                     nextState.huristic =  calculateHuristicDistance();
                    priorityQueue.add(nextState);

                } else if (priorityQueue.contains(nextState)) {
                    State existingState = null;
                    for (State ex : priorityQueue) {
                        if (ex.equals(nextState)) {
                            existingState = ex;
                            break;
                        }

                    }

                    if (existingState != null && nextState.cost < existingState.cost) {
                        priorityQueue.remove(existingState);
                         nextState.father = currentState;
                        priorityQueue.add(nextState);
                       
                    }
                }
                else if (visitedStates.contains(nextState)) {
                    State existingState = null;
                    for (State ex : visitedStates) {
                        if (ex.equals(nextState)) {
                            existingState = ex;
                            break;
                        }

                    }

                    if (existingState != null && nextState.cost < existingState.cost) {
                        priorityQueue.remove(existingState);
                         nextState.father = currentState;
                        priorityQueue.add(nextState);
                       
                    }
                }
            }
        }
        return null;
    }

    public State findWinningStateUcs() {
        Set<State> visitedStates = new HashSet<>();
        PriorityQueue<State> priorityQueue = new PriorityQueue<>(new CostComparator()); // Uses compareTo from State

        priorityQueue.add(this);

        int i = 0;
        while (!priorityQueue.isEmpty()) {
            State currentState = priorityQueue.poll();
            System.out.println(i);
            i++;
            currentState.printState();

            if (currentState.isIsWinning()) {
                return currentState;
            }

            visitedStates.add(currentState);

            for (State nextState : currentState.getNextStates()) {

                if (!visitedStates.contains(nextState)) {
                    nextState.father = currentState;
                    nextState.cost = currentState.cost + calculateCost(currentState, nextState);
                    priorityQueue.add(nextState);

                } else if (priorityQueue.contains(nextState)) {
                    State existingState = null;
                    for (State ex : priorityQueue) {
                        if (ex.equals(nextState)) {
                            existingState = ex;
                            break;
                        }

                    }

                    if (existingState != null && nextState.cost < existingState.cost) {
                        priorityQueue.remove(existingState);
                        priorityQueue.add(nextState);
                        nextState.father = currentState;
                    }
                }
            }
        }
        return null;
    }

    int calculateHuristicDistance() {
        Poistion targetPosition = this.target.getPoistion();
        Poistion currentPoistion = this.pathlight.getLast().getPoistion();
        int xDiffrence = targetPosition.getColPosition() - currentPoistion.getColPosition();
        int yDiffrence = targetPosition.getRowPosition() - currentPoistion.getRowPosition();
        double solution = Math.sqrt((xDiffrence * xDiffrence) + (yDiffrence * yDiffrence));
        return (int) solution;
    }

    public State findWinningStateHillClimbing() {
        Set<State> visitedStates = new HashSet<>();

        State openState = this;

        int i = 0;
        while (openState != null) {

            System.out.println(i);
            i++;
            openState.printState();

            if (openState.isIsWinning()) {
                return openState;
            }

            visitedStates.add(openState);
            int bestHuristicCost = Integer.MAX_VALUE;
            State bestChild = null;

            for (State nextState : openState.getNextStates()) {

                if (!visitedStates.contains(nextState)) {
                    int currentCost = nextState.calculateHuristicDistance();
                  //  System.out.println("cost" + currentCost);
                    if (currentCost < bestHuristicCost) {
                        nextState.father = openState;
                        bestHuristicCost = currentCost;
                        nextState.huristic=currentCost;

                        bestChild = nextState;
                    }

                }
            }
            openState = bestChild;
        }
        return null;
    }

    public State findWinningStateBFS() {
        Set<State> visitedStates = new HashSet<>();
        Queue<State> queue = new LinkedList<>();
        queue.add(this);
        int i = 0;
        while (!queue.isEmpty()) {
            State currentState = queue.poll();
            System.out.println(i);
            i++;
            currentState.printState();

            if (currentState.isIsWinning()) {

                return currentState;
            }

            visitedStates.add(currentState);

            for (State nextState : currentState.getNextStates()) {
                if (!visitedStates.contains(nextState)) {
                    nextState.father = currentState;
                    queue.add(nextState);
                }
            }
        }
        return null;
    }

    public State findWinningStateDFS() {
        Set<State> visitedStates = new HashSet<>();
        Stack<State> stack = new Stack<>();
        stack.push(this);
        int i = 0;
        while (!stack.isEmpty()) {
            State currentState = stack.pop();
            System.out.println(i);
            i++;
            currentState.printState();

            if (currentState.isIsWinning()) {
                return currentState;
            }

            if (!visitedStates.contains(currentState)) {
                visitedStates.add(currentState);

                for (State nextState : currentState.getNextStates()) {
                    if (!visitedStates.contains(nextState)) {
                        nextState.father = currentState;
                        stack.push(nextState);
                    }
                }
            }
        }

        return null;
    }

    public Set<State> getNextStates() {
        HashSet<State> uniqueStates = new HashSet<>();
        // getLastRotatedMirrorHitByLight
        if (this.isWinning) {
            return uniqueStates;
        }
        int lastmirrorIndex = getLastMirrorHittedByLightIndex();
        if (lastmirrorIndex == -1) {
            return uniqueStates;
        }

        for (int mirrorAction : Action.posibleMirrorActions) {
            try {

                State mirrorModifiedState = Action.turnMirrorAction(this, mirrorAction, lastmirrorIndex);
                //  mirrorModifiedState.father = this;

                // mirrorModifiedState.cost = this.cost + calculateCost(this, mirrorModifiedState);
                uniqueStates.add(mirrorModifiedState);

            } catch (Exception e) {
                System.err.println("Error in generating mirror actions: " + e.getMessage());
            }
        }

        return uniqueStates;
    }

    int calculateCost(State parent, State child) {
        if (parent == null) {
            return 0;
        }

        int fatherSize = parent.pathlight.size();
        int childSize = child.pathlight.size();
        LinkedList<Cell> intersectPathLight = new LinkedList<>();
        for (Cell cell1 : child.pathlight) {
            for (Cell cell2 : parent.pathlight) {
                if (cell1.equals(cell2)) {

                    intersectPathLight.add(cell2);
                }
            }

        }

        int finalCost = (fatherSize - intersectPathLight.size()) + (childSize - intersectPathLight.size());

        return finalCost;

    }

    private int getLastMirrorHittedByLightIndex() {
        int lastmirrorIndex = -1;
        for (int i = pathlight.size() - 1; i >= 0; i--) {
            Cell cell = pathlight.get(i);
            for (int j = 0; j < mirrors.length; j++) {

                if (cell.getPoistion().equals(mirrors[j].getPoistion())) {
                    lastmirrorIndex = j;
                    return lastmirrorIndex;
                }
            }

        }
        return lastmirrorIndex;
    }

    boolean isTargetHitedByLight() {
        LinkedList<Poistion> postions = getPostionsRoundTarget();

        for (Cell pathCell : pathlight) {

            for (Poistion postion : postions) {
                if (pathCell.getPoistion().equals(postion)) {

                    return true;
                }
            }

        }
        return false;
    }

    private LinkedList<Poistion> getPostionsRoundTarget() {
        LinkedList<Poistion> postions = new LinkedList<>();
        int col = target.getPoistion().getColPosition();
        int row = target.getPoistion().getRowPosition();
        postions.add(new Poistion(row - 1, col));
        postions.add(new Poistion(row + 1, col));
        postions.add(new Poistion(row, col - 1));
        postions.add(new Poistion(row, col + 1));
        postions.add(new Poistion(row - 1, col - 1));
        postions.add(new Poistion(row + 1, col + 1));
        postions.add(new Poistion(row + 1, col - 1));
        postions.add(new Poistion(row - 1, col + 1));

        return postions;
    }

    private LinkedList<Poistion> getPostionsRoundMirror(Mirror mirror) {
        LinkedList<Poistion> postions = new LinkedList<>();
        int col = mirror.getPoistion().getColPosition();
        int row = mirror.getPoistion().getRowPosition();
        postions.add(new Poistion(row - 1, col));
        postions.add(new Poistion(row + 1, col));
        postions.add(new Poistion(row, col - 1));
        postions.add(new Poistion(row, col + 1));
        postions.add(new Poistion(row - 1, col - 1));
        postions.add(new Poistion(row + 1, col + 1));
        postions.add(new Poistion(row + 1, col - 1));
        postions.add(new Poistion(row - 1, col + 1));

        return postions;
    }

    public State updateState() {
        initCells();
        settingPathLight(light.getDirection(), light.getPoistion());
//         printState();
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
                        pathlight.add(cells[rowpos][colpos]);
                        settingPathLight(Directions.BottomRight, new Poistion(rowpos, colpos));
                        break OUTER;
                    }
                    case vertical -> {
                        pathlight.add(cells[rowpos][colpos]);
                        settingPathLight(Directions.TopLeft, new Poistion(rowpos, colpos));
                        break OUTER;
                    }

                    default -> {
                        pathlight.add(cells[rowpos][colpos]);
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
                        pathlight.add(cells[rowpos][colpos]);
                        settingPathLight(Directions.BottomRight, new Poistion(rowpos, colpos));
                        break OUTER;
                    }
                    case vertical -> {
                        pathlight.add(cells[rowpos][colpos]);
                        settingPathLight(Directions.TopLeft, new Poistion(rowpos, colpos));
                        break OUTER;
                    }

                    default -> {
                        pathlight.add(cells[rowpos][colpos]);
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
                        pathlight.add(cells[rowpos][colpos]);
                        settingPathLight(Directions.BottomLeft, new Poistion(rowpos, colpos));
                        break OUTER;
                    }
                    case vertical -> {
                        pathlight.add(cells[rowpos][colpos]);
                        settingPathLight(Directions.TopRight, new Poistion(rowpos, colpos));
                        break OUTER;
                    }

                    default -> {
                        pathlight.add(cells[rowpos][colpos]);
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
                        pathlight.add(cells[rowpos][colpos]);
                        settingPathLight(Directions.TopRight, new Poistion(rowpos, colpos));
                        break OUTER;
                    }
                    case vertical -> {
                        pathlight.add(cells[rowpos][colpos]);
                        settingPathLight(Directions.BottomLeft, new Poistion(rowpos, colpos));
                        break OUTER;
                    }

                    default -> {
                        pathlight.add(cells[rowpos][colpos]);
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
                        pathlight.add(cells[rowpos][colpos]);
                        settingPathLight(Directions.Top, new Poistion(rowpos, colpos));
                        break OUTER;
                    }
                    case topLeft -> {
                        pathlight.add(cells[rowpos][colpos]);
                        settingPathLight(Directions.Bottom, new Poistion(rowpos, colpos));
                        break OUTER;
                    }

                    default -> {
                        pathlight.add(cells[rowpos][colpos]);
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
                        pathlight.add(cells[rowpos][colpos]);
                        settingPathLight(Directions.Bottom, new Poistion(rowpos, colpos));
                        break OUTER;
                    }
                    case topLeft -> {
                        pathlight.add(cells[rowpos][colpos]);
                        settingPathLight(Directions.Top, new Poistion(rowpos, colpos));
                        break OUTER;
                    }

                    default -> {
                        pathlight.add(cells[rowpos][colpos]);
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
                        pathlight.add(cells[rowpos][colpos]);
                        settingPathLight(Directions.Right, new Poistion(rowpos, colpos));
                        break OUTER;
                    }
                    case topLeft -> {
                        pathlight.add(cells[rowpos][colpos]);
                        settingPathLight(Directions.Left, new Poistion(rowpos, colpos));
                        break OUTER;
                    }

                    default -> {
                        pathlight.add(cells[rowpos][colpos]);
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
                        pathlight.add(cells[rowpos][colpos]);
                        settingPathLight(Directions.Left, new Poistion(rowpos, colpos));
                        break OUTER;
                    }
                    case topLeft -> {
                        pathlight.add(cells[rowpos][colpos]);
                        settingPathLight(Directions.Right, new Poistion(rowpos, colpos));
                        break OUTER;
                    }

                    default -> {
                        pathlight.add(cells[rowpos][colpos]);
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
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

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

}
