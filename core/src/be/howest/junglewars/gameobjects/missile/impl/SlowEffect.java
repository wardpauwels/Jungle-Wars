package be.howest.junglewars.gameobjects.missile.impl;

import be.howest.junglewars.gameobjects.GameObject;
import be.howest.junglewars.gameobjects.IMissileType;
import be.howest.junglewars.gameobjects.MissileType;
import be.howest.junglewars.gameobjects.Player;
import be.howest.junglewars.gameobjects.enemy.Enemy;
import be.howest.junglewars.gameobjects.power.Power;
import be.howest.junglewars.gameobjects.power.PowerType;
import be.howest.junglewars.screens.GameScreen;

public class SlowEffect implements IMissileType {


    @Override
    public void doEffect(GameData g,Player player) {
        if (!player.isSlowed()) {
            player.setSpeed(player.getSpeed() - 200);
        }
        player.timer = 1;


    }
}
