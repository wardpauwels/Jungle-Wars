package be.howest.junglewars.gameobjects.enemy;

import com.badlogic.gdx.math.Vector2;

public interface IEnemyActionType {

    void attack(Enemy enemy, Vector2 v,float spawnX, float spawnY);


}
