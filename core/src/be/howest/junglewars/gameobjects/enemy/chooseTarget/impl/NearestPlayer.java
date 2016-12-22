package be.howest.junglewars.gameobjects.enemy.chooseTarget.impl;

import be.howest.junglewars.gameobjects.enemy.*;
import com.badlogic.gdx.math.*;

import java.util.*;

/**
 * Created by jensthiel on 19/12/16.
 */
public class NearestPlayer implements IChooseTargetType {
    @Override
    public List<Vector2> chooseTargets(Enemy enemy) {
        ArrayList<Vector2> list = new ArrayList<>();
        //list.add(new Vector2(enemy.getNearest(enemy.game.getData().getPlayers()).getBody().getX(),enemy.getNearest(enemy.game.getData().getPlayers()).getBody().getY()));
        return list;


    }
}
