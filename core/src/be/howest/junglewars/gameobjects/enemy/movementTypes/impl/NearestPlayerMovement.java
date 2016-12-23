package be.howest.junglewars.gameobjects.enemy.movementTypes.impl;

import be.howest.junglewars.gameobjects.enemy.Enemy;
import be.howest.junglewars.gameobjects.enemy.IChooseTargetType;
import be.howest.junglewars.gameobjects.enemy.IEnemyMovementType;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jensthiel on 21/12/16.
 */
public class NearestPlayerMovement implements IEnemyMovementType {
    @Override
    public Vector2 returnMovement(Enemy enemy) {


                return new Vector2(enemy.getNearest((List<Enemy>)enemy.getData().getPlayers()).getBody().getX(),enemy.getNearest((List<Enemy>)enemy.getData().getPlayers()).getBody().getY());



            }


}
