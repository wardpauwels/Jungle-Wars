package be.howest.junglewars.gameobjects.missile.impl;

import be.howest.junglewars.gameobjects.*;

/**
 * Created by jensthiel on 19/12/16.
 */
public class SlowEffect implements IMissileType {


    @Override
    public void doEffect(Player player) {
        if (!player.isSlowed()) {
            player.setSpeed(player.getSpeed() - 200);
        }
        player.timer = 1;


    }
}
