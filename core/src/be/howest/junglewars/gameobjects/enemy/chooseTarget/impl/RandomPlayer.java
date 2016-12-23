package be.howest.junglewars.gameobjects.enemy.chooseTarget.impl;

import be.howest.junglewars.gameobjects.Player;
import be.howest.junglewars.gameobjects.enemy.IChooseTargetType;
import be.howest.junglewars.gameobjects.enemy.Enemy;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomPlayer implements IChooseTargetType {
    @Override
    public List<Vector2> chooseTargets(Enemy enemy) {
        Random random = new Random();
        ArrayList<Vector2> list = new ArrayList<>();
        Player randPlayer = enemy.getData().getPlayers().get(random.nextInt(enemy.getData().getPlayers().size() + 1 ));
        list.add(new Vector2(randPlayer.getBody().getX(),randPlayer.getBody().getY()));
        return list;
    }
}
