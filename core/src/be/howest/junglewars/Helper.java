package be.howest.junglewars;

import be.howest.junglewars.gameobjects.GameObject;
import be.howest.junglewars.gameobjects.Player;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

enum HelperMovementType {
    CIRCLE_AROUND_PLAYER {
        @Override
        public void move() {

        }

    },
    RANDOM {
        @Override
        public void move() {

        }
    },
    POSITION_TO_POSITION {
        @Override
        public void move() {

        }
    },
    STATIC {
        @Override
        public void move() {
            
        }
    };

    public abstract void move();
}

enum HelperSpecialActionType {
    SHOOTER {
        @Override
        public void doSpecialAction(Helper helper) {
            // TODO: Shoots at the nearest enemy
        }
    },
    SHIELD {
        @Override
        public void doSpecialAction(Helper helper) {
            // TODO: Blocks every bullet that he gets
        }
    },
    CURRENCY_COLLECTOR {
        @Override
        public void doSpecialAction(Helper helper) {
            // TODO: Collects currency on collision
        }
    },
    POWER_COLLECTOR {
        @Override
        public void doSpecialAction(Helper helper) {
            // TODO: Collects power on collision
        }
    };

    public abstract void doSpecialAction(Helper helper);
}

public class Helper extends GameObject {

    private final int adaptXcoord = -30; // TODO: review this
    private final int adaptYcoord = 70; // TODO: review this
    private Player owner;
    private String name;
    private HelperMovementType movementType;
    private HelperSpecialActionType specialActionType;


    public Helper(String name, float width, float height, HelperMovementType movementType, HelperSpecialActionType specialActionType, Player owner, String textureUrl) {
        super(width, height, textureUrl);
        this.owner = owner;
        this.name = name;
        this.movementType = movementType;
        this.specialActionType = specialActionType;
    }

    @Override
    protected void setAnimationFrames() {

    }

    @Override
    protected Vector2 generateSpawnPosition() {
        return new Vector2(owner.getPosition().x + adaptXcoord, owner.getPosition().y + adaptYcoord);
    }

    @Override
    protected void update(float dt) {
        specialActionType.doSpecialAction(this);
//        position.set(owner.getPosition().x + adaptXcoord, owner.getPosition().y + adaptYcoord);
        movementType.move();

    }

    @Override
    protected void draw(SpriteBatch batch) {
        activeSprite.setPosition(position.x, position.y);
    }

}