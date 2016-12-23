package be.howest.junglewars.gameobjects.enemy.chooseTarget.impl;

import be.howest.junglewars.gameobjects.enemy.IChooseTargetType;
import be.howest.junglewars.gameobjects.enemy.Enemy;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RandomSpot implements IChooseTargetType {
    @Override
    public List<Vector2> chooseTargets(Enemy enemy) {

        Random rand = new Random();
        List<Vector2> list = new ArrayList<>();
        list.add(new Vector2(rand.nextInt(Gdx.graphics.getWidth() + 1),rand.nextInt(Gdx.graphics.getHeight()+1)));
        return list;

    }
}
