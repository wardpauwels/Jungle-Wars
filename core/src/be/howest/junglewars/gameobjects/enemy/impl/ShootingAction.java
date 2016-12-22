package be.howest.junglewars.gameobjects.enemy.impl;

import be.howest.junglewars.gameobjects.*;
import be.howest.junglewars.gameobjects.enemy.*;
import com.badlogic.gdx.math.*;

/**
 * Created by jensthiel on 19/12/16.
 */
public class ShootingAction implements IEnemyActionType {
    @Override
    public void attack(Enemy enemy, Vector2 v,float spawnX, float spawnY) {
        enemy.getData().getMissiles().add(
                new Missile(enemy.BULLET_WIDTH, enemy.BULLET_HEIGHT, spawnX, spawnY, v.x, v.y, "helper-bullet", 5, 300, 5, 4f, MissileType.STANDARD, enemy.getData()));
    }
}
