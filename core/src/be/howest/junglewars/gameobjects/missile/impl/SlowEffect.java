package be.howest.junglewars.gameobjects.missile.impl;

import be.howest.junglewars.gameobjects.GameObject;
import be.howest.junglewars.gameobjects.IMissileType;
import be.howest.junglewars.gameobjects.MissileType;
import be.howest.junglewars.gameobjects.Player;
import be.howest.junglewars.gameobjects.enemy.Enemy;
import be.howest.junglewars.gameobjects.power.Power;
import be.howest.junglewars.gameobjects.power.PowerType;
import be.howest.junglewars.screens.GameScreen;

/**
 * Created by jensthiel on 19/12/16.
 */
public class SlowEffect implements IMissileType {


    @Override
    public void doEffect(GameScreen g,Player player) {
        if (!player.isSlowed()) {
            player.setSpeed(player.getSpeed() - 200);
        }
        player.timer = 1;


    }
}
