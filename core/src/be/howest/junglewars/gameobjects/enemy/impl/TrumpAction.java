package be.howest.junglewars.gameobjects.enemy.impl;

import be.howest.junglewars.gameobjects.enemy.Enemy;
import be.howest.junglewars.gameobjects.enemy.IEnemyActionType;
import be.howest.junglewars.gameobjects.enemy.utils.Brick;
import be.howest.junglewars.gameobjects.enemy.utils.Wall;
import be.howest.junglewars.screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;

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
        Wall wall = new Wall(enemy.game,start,length,curveX,curveY);
        wall.DrawWall();
        enemy.game.getData().getWalls().add(wall);
        enemy.changeSprite(enemy.altSprite);

    }
}
