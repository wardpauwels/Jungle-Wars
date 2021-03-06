package be.howest.junglewars.gameobjects.enemy.chooseTarget.impl;

import be.howest.junglewars.gameobjects.Player;
import be.howest.junglewars.gameobjects.enemy.IChooseTargetType;
import be.howest.junglewars.gameobjects.enemy.Enemy;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;


public class AllPlayers implements IChooseTargetType {
    @Override
    public List<Vector2> chooseTargets(Enemy enemy) {
        List<Vector2> list = new ArrayList<>();
        for(Player p : enemy.game.getData().getPlayers()){
            list.add(new Vector2(p.getBody().getX(), p.getBody().getY()));
        }
        return list;

    }
}
