package be.howest.junglewars.gameobjects.enemy.chooseTarget.impl;

import be.howest.junglewars.gameobjects.enemy.Enemy;
import be.howest.junglewars.gameobjects.enemy.IChooseTargetType;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jensthiel on 20/12/16.
 */
public class StartingOnEnemy implements IChooseTargetType{
    @Override
    public List<Vector2> chooseTargets(Enemy enemy) {
        List<Vector2> list = new ArrayList<>();
        list.add(new Vector2(enemy.getBody().x, enemy.getBody().y));
        return list;

    }

}
