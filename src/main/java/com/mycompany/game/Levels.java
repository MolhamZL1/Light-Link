/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.game;

import java.util.Random;

/**
 *
 * @author USER
 */
public class Levels {

    public State chooseRandomLevel() {
        int[] levels = {1,4};
        int random = new Random().nextInt(levels.length);

        
        switch (levels[random]) {
            case 1 -> {
                return initGridLevel1();
            }
            case 4 -> {
                return initGridLevel4();
            }
//            case 7 -> {
//                return initGridLevel7();
//            }
//            case 10 -> {
//                return initGridLevel10();
//            }

            default -> {
                throw new AssertionError();
            }
        }

    }

    State initGridLevel1() {
        Light light1 = new Light(new Poistion(10,3),Directions.Bottom);
        Target target1 = new Target( new Poistion(4, 3));
        Wall wall1 = new Wall(new Poistion(4, 2));
        Wall wall2 = new Wall(new Poistion(5, 2));
        Wall wall3 = new Wall(new Poistion(6, 2));
        Wall wall4 = new Wall(new Poistion(7, 2));
        Wall wall5 = new Wall(new Poistion(8, 2));
        Wall wall6 = new Wall(new Poistion(9, 2));
        Wall wall7 = new Wall(new Poistion(10, 2));
         Wall wall8 = new Wall(new Poistion(7, 3));
        Wall[] walls = {wall1, wall2, wall3, wall5, wall4, wall6,wall7,wall8};
        Mirror mirror1 = new RotatedMirror(new Poistion(7, 6),MirrorDirections.horizintal);
       
        Mirror[] mirrors = {mirror1};
        State grid1 = new State(8, 15, light1, target1, walls, mirrors);
        return grid1;
    }

    State initGridLevel4() {
        Light light1 = new Light(new Poistion(4,1),Directions.Bottom);
        Target target1 = new Target(new Poistion(10, 1));
        Wall wall1 = new Wall(new Poistion(4,2));
        Wall wall2 = new Wall(new Poistion(9, 1));
        Wall wall3 = new Wall(new Poistion(5, 4));
        Wall[] walls = {wall1, wall2, wall3};
        RotatedMirror mirror1 = new RotatedMirror(new Poistion(6, 1),MirrorDirections.topRight);
        FixedMirror mirror2 = new FixedMirror(new Poistion(6, 3),MirrorDirections.topRight);
        RotatedMirror mirror3 = new RotatedMirror(new Poistion(4, 3),MirrorDirections.topLeft);
        RotatedMirror mirror4 = new RotatedMirror(new Poistion(4, 5),MirrorDirections.topRight);
        FixedMirror mirror5 = new FixedMirror(new Poistion(10, 5),MirrorDirections.topRight);
        Mirror[] mirrors = {mirror1, mirror2, mirror3, mirror4, mirror5};
        State grid1 = new State(8, 15, light1, target1, walls, mirrors);
        return grid1;
    }
//
//    State initGridLevel7() {
//        Light light1 = new Light(Directions.Right, 4, 2);
//        Target target1 = new Target(11, 5);
//        Wall wall1 = new Wall(5, 3);
//        Wall wall2 = new Wall(10, 4);
//
//        Wall[] walls = {wall1, wall2};
//        MovableMirror mirror1 = new MovableMirror(MirrorDirections.topLeft, 4, 5, MirrorDirections.horizintal, 1);
//        MovableMirror mirror2 = new MovableMirror(MirrorDirections.topRight, 7, 5, MirrorDirections.vertical, 1);
//        MovableMirror mirror3 = new MovableMirror(MirrorDirections.topRight, 8, 2, MirrorDirections.topRight, 1);
//        MovableMirror mirror4 = new MovableMirror(MirrorDirections.topLeft, 11, 2, MirrorDirections.horizintal, 1);
//
//        Mirror[] mirrors = {mirror1, mirror2, mirror3, mirror4};
//        return new State(8, 15, light1, target1, walls, mirrors);
//    }
//
//    State initGridLevel10() {
//        Light light1 = new Light(Directions.Left, 11, 5);
//        Target target1 = new Target(7, 1);
//        Wall wall1 = new Wall(8, 1);
//        Wall wall2 = new Wall(6, 1);
//        Wall wall3 = new Wall(10, 5);
//        Wall wall4 = new Wall(6, 3);
//
//        Wall[] walls = {wall1, wall2, wall3, wall4};
//        MovableMirror mirror1 = new MovableMirror(MirrorDirections.topRight, 9, 5, MirrorDirections.horizintal, 1);
//        MovableMirror mirror2 = new MovableMirror(MirrorDirections.topRight, 4, 2, MirrorDirections.horizintal, 1);
//        RotatedMirror mirror3 = new RotatedMirror(MirrorDirections.topRight, 11, 1);
//        RotatedMirror mirror4 = new RotatedMirror(MirrorDirections.topLeft, 9, 1);
//        RotatedMirror mirror5 = new RotatedMirror(MirrorDirections.topLeft, 7, 2);
//        RotatedMirror mirror6 = new RotatedMirror(MirrorDirections.topLeft, 7, 3);
//        RotatedMirror mirror7 = new RotatedMirror(MirrorDirections.topRight, 7, 5);
//        RotatedMirror mirror8 = new RotatedMirror(MirrorDirections.topRight, 4, 4);
//
//        Mirror[] mirrors = {mirror1, mirror2, mirror3, mirror5, mirror6, mirror7, mirror8, mirror4};
//        return new State(8, 15, light1, target1, walls, mirrors);
//    }

}
