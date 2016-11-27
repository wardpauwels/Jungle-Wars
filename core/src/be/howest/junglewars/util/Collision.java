package be.howest.junglewars.util;

import be.howest.junglewars.gameobjects.GameObject;

import java.util.HashSet;

public class Collision {

    // TODO: for now this method returns all GameObjects which has collision... (not sure if OK, review this!)
    public static HashSet<GameObject> checkCollision(GameObject[] a, GameObject[] b) {
        HashSet<GameObject> collisionObjects = new HashSet<>();

        for (GameObject objA : a) {
            for (GameObject objB : b) {
                if (objA.getBounds().overlaps(objB.getBounds())) {
                    collisionObjects.add(objA);
                    collisionObjects.add(objB);
                }
            }
        }

        return collisionObjects;
    }

}
