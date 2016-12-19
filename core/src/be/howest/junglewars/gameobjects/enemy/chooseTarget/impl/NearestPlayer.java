package be.howest.junglewars.gameobjects.enemy.chooseTarget.impl;

import be.howest.junglewars.gameobjects.enemy.IChooseTargetType;
import be.howest.junglewars.gameobjects.enemy.Enemy;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jensthiel on 19/12/16.
 */
public class NearestPlayer implements IChooseTargetType {
    @Override
    public List<Vector2> chooseTargets(Enemy enemy) {
        ArrayList<Vector2> list = new ArrayList<>();
        list.add(new Vector2(enemy.getNearest(enemy.game.getPlayers()).getBody().getX(),enemy.getNearest(enemy.game.getPlayers()).getBody().getY()));
        return list;


    }
}
