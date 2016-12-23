package be.howest.junglewars.gameobjects.missile.impl;

import be.howest.junglewars.gameobjects.IMissileType;
import be.howest.junglewars.gameobjects.Player;
import be.howest.junglewars.screens.GameScreen;


public class SlowEffect implements IMissileType {


    @Override
    public void doEffect(GameScreen g,Player player) {
        if (!player.isSlowed()) {
            player.setSpeed(player.getSpeed() - 200);
        }
        player.timer = 1;


    }
}
