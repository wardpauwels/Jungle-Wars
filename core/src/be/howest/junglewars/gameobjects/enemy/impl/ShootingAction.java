package be.howest.junglewars.gameobjects.enemy.impl;

import be.howest.junglewars.gameobjects.Missile;
import be.howest.junglewars.gameobjects.MissileType;
import be.howest.junglewars.gameobjects.enemy.Enemy;
import be.howest.junglewars.gameobjects.enemy.IEnemyActionType;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by jensthiel on 19/12/16.
 */
public class ShootingAction implements IEnemyActionType {
    @Override
    public void attack(Enemy enemy, Vector2 v,float spawnX, float spawnY) {
        enemy.game.getEnemyMissiles().add(
                new Missile(enemy.game, enemy.BULLET_WIDTH, enemy.BULLET_HEIGHT, spawnX, spawnY, v.x, v.y, "helper-bullet", 5, 300, 5, 4f,MissileType.TEAR));
    }
}
