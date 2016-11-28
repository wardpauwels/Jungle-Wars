package be.howest.junglewars.gameobjects.helper;

import be.howest.junglewars.gameobjects.helper.actions.HelperAction;
import be.howest.junglewars.gameobjects.player.Player;
import be.howest.junglewars.screens.GameScreen;

public class ShootingHelper extends Helper {
    public ShootingHelper(GameScreen game, String name, float width, float height, HelperMovementType movementType, HelperAction action, Player owner, String textureUrl) {
        super(game, name, width, height, movementType, action, owner, textureUrl);
    }

//    public ShootingHelper(GameScreen game, String name, float width, float height,
//                          HelperMovementType movementType, Player owner, String textureUrl) {
//        super(game, name, width, height, movementType, new ShootingAction(), owner, textureUrl);
//
//    }
//
//    @Override
//    protected void update(float dt) {
//        movementType.move(this);
//    }
//
//    // TODO: is also in player, in enemy, ... move to one place?
//    private void shoot() {
//
//
//    }

}
