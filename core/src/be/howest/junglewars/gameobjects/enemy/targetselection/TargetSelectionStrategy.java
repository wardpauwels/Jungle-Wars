package be.howest.junglewars.gameobjects.enemy.targetselection;

import be.howest.junglewars.gameobjects.player.Player;

public interface TargetSelectionStrategy {

    Player[] getTargets();

}
