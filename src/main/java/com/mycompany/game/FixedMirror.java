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
public class FixedMirror extends Mirror{
    
    public FixedMirror(MirrorDirections direction, int rowPosition, int colPosition) {
        super(direction, rowPosition, colPosition);
    }

    public MirrorDirections getDirection() {
        return direction;
    }
    
   
    

}
