package be.howest.junglewars.gameobjects.enemy.chooseTarget.impl;

import be.howest.junglewars.gameobjects.*;
import be.howest.junglewars.gameobjects.enemy.*;
import com.badlogic.gdx.math.*;

import java.util.*;

/**
 * Created by jensthiel on 19/12/16.
 */
public class AllPlayers implements IChooseTargetType {
    @Override
    public List<Vector2> chooseTargets(Enemy enemy) {
        List<Vector2> list = new ArrayList<>();
        for (Map.Entry<Long, Player> p : enemy.getData().getPlayers().entrySet()) {
            list.add(new Vector2(p.getValue().getBody().getX(), p.getValue().getBody().getY()));
        }
        return list;

    }
}
