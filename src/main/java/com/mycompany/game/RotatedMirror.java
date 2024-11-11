/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.game;

/**
 *
 * @author USER
 */
public class RotatedMirror extends Mirror {

    public RotatedMirror(Poistion poistion, MirrorDirections direction) {
        super(poistion, direction);
    }
// Copy constructor for deep copying
    public RotatedMirror(RotatedMirror other) {
        super(other);
    }

    @Override
    public RotatedMirror copy() {
        return new RotatedMirror(this); // Calls the copy constructor for RotatedMirror
    }
}
