/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.game;

/**
 *
 * @author USER
 */
public class Action {
    static int[] posibleLightActions={1,2,3,4,5,6,7,8};
    static int[] posibleMirrorActions={1,2,3,4};

    static public State turnLightAction(State state, int selectedDirNum) throws Exception {
        State newstate = new State(state);
        Directions newDirection = switch (selectedDirNum) {
            case 1 ->
                Directions.Right;
            case 2 ->
                Directions.Left;
            case 3 ->
                Directions.Top;
            case 4 ->
                Directions.Bottom;
            case 5 ->
                Directions.BottomLeft;
            case 6 ->
                Directions.BottomRight;
            case 7 ->
                Directions.TopLeft;
            case 8 ->
                Directions.TopRight;
            default ->
                null;
        };

        if (newDirection != null) {
           
            newstate.light.setDirection(newDirection);
  newstate.updateState();
        } else {
            throw new Exception();
        }
        return newstate;
    }

    static public State turnMirrorAction(State state, int selectedDirNum, int selectedMirror) throws Exception {
        State newstate = new State(state);
        
        MirrorDirections newDirection = switch (selectedDirNum) {
            case 1 ->
                MirrorDirections.topRight;
            case 2 ->
                MirrorDirections.topLeft;
            case 3 ->
                MirrorDirections.horizintal;
            case 4 ->
                MirrorDirections.vertical;           
                
            default ->
                null;
        };

        if (newDirection != null) {
            newstate.mirrors[selectedMirror].setDirection(newDirection);
          newstate.updateState();

        } else {
            throw new Exception();
        }
      
        return newstate;
    }

}
