package be.howest.junglewars.gameobjects.enemy.chooseTarget.impl;

import be.howest.junglewars.gameobjects.Player;
import be.howest.junglewars.gameobjects.enemy.IChooseTargetType;
import be.howest.junglewars.gameobjects.enemy.Enemy;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by jensthiel on 19/12/16.
 */
public class RandomPlayer implements IChooseTargetType {
    @Override
    public List<Vector2> chooseTargets(Enemy enemy) {
        Random random = new Random();
        ArrayList<Vector2> list = new ArrayList<>();
        Player randPlayer = enemy.data.getPlayers().get(random.nextInt(enemy.data.getPlayers().size() + 1 ));
        list.add(new Vector2(randPlayer.getBody().getX(),randPlayer.getBody().getY()));
        return list;
    }
}
