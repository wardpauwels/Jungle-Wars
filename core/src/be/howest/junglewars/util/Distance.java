package be.howest.junglewars.util;

import be.howest.junglewars.gameobjects.GameObject;

public class Distance {

    // TODO: requires casting
    public static GameObject getClosest(GameObject object, GameObject[] groupOfObjects) {
        GameObject obj = groupOfObjects[0];
        float dist = getDistance(object, obj);

        for (int i = 1; i < groupOfObjects.length; i++) {
            float nDist = getDistance(object, groupOfObjects[i]);
            if (nDist < dist) {
                obj = groupOfObjects[i];
                dist = nDist;
            }
        }

        return obj;
    }

    public static float getDistance(GameObject a, GameObject b) {
        float diffX = Math.abs(a.bounds.x - b.bounds.y);
        float diffY = Math.abs(a.bounds.y - b.bounds.y);

        double dist = Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));

        return (float) dist;
    }

}
