package be.howest.junglewars.gameobjects.enemy.impl;

import be.howest.junglewars.gameobjects.enemy.*;
import be.howest.junglewars.gameobjects.enemy.utils.*;
import com.badlogic.gdx.math.*;

import java.util.*;

/**
 * Created by jensthiel on 20/12/16.
 */
public class TrumpAction implements IEnemyActionType {
    @Override
    public void attack(Enemy enemy, Vector2 v, float spawnX, float spawnY) {
        Random rand = new Random();
        int length = rand.nextInt((300-20)+1) +20;
        Vector2 start = new Vector2(v.x,v.y);
        int curveX = rand.nextInt((10-(-10)+1)+(-10));
        int curveY = rand.nextInt((10-(-10)+1)+(-10));
        Wall wall = new Wall(start, length, curveX, curveY, enemy.getData());
        wall.DrawWall();
        //enemy.game.getData().getWalls().add(wall);
        enemy.changeSprite(enemy.altSprite);

    }
}
