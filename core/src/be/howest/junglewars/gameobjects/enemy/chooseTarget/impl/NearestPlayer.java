package be.howest.junglewars.gameobjects.enemy.chooseTarget.impl;

import be.howest.junglewars.gameobjects.enemy.Enemy;
import be.howest.junglewars.gameobjects.enemy.IChooseTargetType;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class NearestPlayer implements IChooseTargetType {
    @Override
    public List<Vector2> chooseTargets(Enemy enemy) {
        ArrayList<Vector2> list = new ArrayList<>();
        Vector2 pos = enemy.getNearest(new ArrayList<>(enemy.getData().getPlayers().values())).getPos();
        list.add(pos);
        return list;
    }
}
