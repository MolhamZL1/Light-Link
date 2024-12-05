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
        int[] levels = { 4,7,10};
        int random = new Random().nextInt(levels.length);

        switch (levels[random]) {
//            case 1 -> {
//                return initGridLevel1();
//            }
            case 4 -> {
                return initGridLevel4();
            }
            case 7 -> {
                return initGridLevel7();
            }
            case 10 -> {
                return initGridLevel10();
            }

            default -> {
                throw new AssertionError();
            }
        }

    }

//    State initGridLevel1() {
//        Light light1 = new Light(new Poistion(10, 3), Directions.Bottom);
//        Target target1 = new Target(new Poistion(4, 3));
//        Wall wall1 = new Wall(new Poistion(4, 2));
//        Wall wall2 = new Wall(new Poistion(5, 2));
//        Wall wall3 = new Wall(new Poistion(6, 2));
//        Wall wall4 = new Wall(new Poistion(7, 2));
//        Wall wall5 = new Wall(new Poistion(8, 2));
//        Wall wall6 = new Wall(new Poistion(9, 2));
//        Wall wall7 = new Wall(new Poistion(10, 2));
//        Wall wall8 = new Wall(new Poistion(7, 3));
//        Wall[] walls = {wall1, wall2, wall3, wall5, wall4, wall6, wall7, wall8};
//        RotatedMirror mirror1 = new RotatedMirror(new Poistion(7, 6), MirrorDirections.horizintal);
//
//        Mirror[] mirrors = {mirror1};
//        State grid1 = new State(8, 15, light1, target1, walls, mirrors);
//        return grid1;
//    }

    State initGridLevel4() {
        Light light1 = new Light(new Poistion(4, 1), Directions.Bottom);
        Target target1 = new Target(new Poistion(10, 1));
        Wall wall1 = new Wall(new Poistion(4, 2));
        Wall wall2 = new Wall(new Poistion(9, 1));
        Wall wall3 = new Wall(new Poistion(5, 4));
        Wall[] walls = {wall1, wall2, wall3};
        RotatedMirror mirror1 = new RotatedMirror(new Poistion(6, 1), MirrorDirections.topRight);
        RotatedMirror mirror2 = new RotatedMirror(new Poistion(6, 3), MirrorDirections.topRight);
        RotatedMirror mirror3 = new RotatedMirror(new Poistion(4, 3), MirrorDirections.topLeft);
        RotatedMirror mirror4 = new RotatedMirror(new Poistion(4, 5), MirrorDirections.topRight);
        RotatedMirror mirror5 = new RotatedMirror(new Poistion(10, 5), MirrorDirections.topRight);
        Mirror[] mirrors = {mirror1, mirror2, mirror3, mirror4, mirror5};
        State grid1 = new State(8, 15, light1, target1, walls, mirrors);
        return grid1;
    }

    State initGridLevel7() {
          Light light1 = new Light(new Poistion( 4, 2),Directions.Right);
        
        Target target1 = new Target(new Poistion(11, 5));
        Wall wall1 = new Wall(new Poistion(5, 3));
        Wall wall2 = new Wall(new Poistion(10,4));

        Wall[] walls = {wall1, wall2};
        RotatedMirror mirror1 = new RotatedMirror(new Poistion(4,5),MirrorDirections.horizintal);
        RotatedMirror mirror2 = new RotatedMirror(new Poistion( 7, 5),MirrorDirections.vertical);
        RotatedMirror mirror3 = new RotatedMirror(new Poistion(7,2),MirrorDirections.horizintal );
        RotatedMirror mirror4 = new RotatedMirror(new Poistion(11,2),MirrorDirections.vertical);

        Mirror[] mirrors = {mirror1, mirror2, mirror3, mirror4};
        return new State(8, 15, light1, target1, walls, mirrors);
    }

    State initGridLevel10() {
        Light light1 = new Light(new Poistion(11,4),Directions.Left);
        Target target1 = new Target(new Poistion(7,1));
        Wall wall1 = new Wall(new Poistion(8,1));
        Wall wall2 = new Wall(new Poistion(6,1));
        Wall wall3 = new Wall(new Poistion(10,4));
        Wall wall4 = new Wall(new Poistion(6,3));

        Wall[] walls = {wall1, wall2, wall3, wall4};
        RotatedMirror mirror1 = new RotatedMirror(new Poistion(9,4),MirrorDirections.topRight);
        RotatedMirror mirror2 = new RotatedMirror(new Poistion(4,2),MirrorDirections.topRight);
        RotatedMirror mirror3 = new RotatedMirror(new Poistion(11,1),MirrorDirections.topRight);
        RotatedMirror mirror4 = new RotatedMirror(new Poistion(9,1),MirrorDirections.topLeft);
        RotatedMirror mirror5 = new RotatedMirror(new Poistion(7,2),MirrorDirections.topLeft);
       // FixedMirror mirror6 = new FixedMirror(new Poistion(7,3),MirrorDirections.topLeft);
     //   RotatedMirror mirror7 = new RotatedMirror(new Poistion(7, 5),MirrorDirections.topRight);
        RotatedMirror mirror8 = new RotatedMirror(new Poistion( 4, 4),MirrorDirections.topRight);

        Mirror[] mirrors = {mirror1, mirror2, mirror3, mirror5,  mirror8, mirror4};
        return new State(8, 15, light1, target1, walls, mirrors);
    }

}
