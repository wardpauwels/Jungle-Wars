package be.howest.junglewars.gameobjects.enemy.chooseTarget.impl;

import be.howest.junglewars.gameobjects.enemy.*;
import com.badlogic.gdx.math.*;

import java.util.*;

/**
 * Created by jensthiel on 19/12/16.
 */
public class RandomPlayer implements IChooseTargetType {
    @Override
    public List<Vector2> chooseTargets(Enemy enemy) {
        Random random = new Random();
        ArrayList<Vector2> list = new ArrayList<>();
        //Player randPlayer = enemy.game.getData().getPlayers().get(random.nextInt(enemy.game.getData().getPlayers().size() + 1 ));
        //list.add(new Vector2(randPlayer.getBody().getX(),randPlayer.getBody().getY()));
        return list;
    }
}
