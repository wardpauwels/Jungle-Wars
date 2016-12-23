package be.howest.junglewars.gameobjects.enemy.impl;

import be.howest.junglewars.gameobjects.Player;
import be.howest.junglewars.gameobjects.enemy.Enemy;
import be.howest.junglewars.gameobjects.enemy.IEnemyActionType;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

/**
 * Created by jensthiel on 19/12/16.
 */
public class StabbingAction implements IEnemyActionType {
    @Override
    public void attack(Enemy enemy, Vector2 v, float spawnX, float spawnY) {

        if(enemy.checkCollision((List<Player>) enemy.getData().getPlayers().values()).size()!=0){
            for(Player p: enemy.getData().getPlayers().values()){
                p.catchDamage(20);
            }
        }

    }
}
