package be.howest.junglewars;

import be.howest.junglewars.gameobjects.GameObject;
import be.howest.junglewars.gameobjects.Player;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

enum HelperMovementType {
    CIRCLE_AROUND_PLAYER {
        @Override
        public void move(Helper helper) {
            // TODO: circle around owner
        }

    },
    RANDOM {
        @Override
        public void move(Helper helper) {
            // TODO: randomly run around the map
        }
    },
    POSITION_TO_POSITION {
        @Override
        public void move(Helper helper) {
            // TODO: go from position to position
        }
    },
    STATIC {
        @Override
        public void move(Helper helper) {
            // TODO: stays at one place
        }
    },
    FOLLOW_PLAYER {
        @Override
        public void move(Helper helper) {
            // TODO: stays near player
        }
    };

    public abstract void move(Helper helper);
}

enum HelperSpecialActionType {
    SHOOTER {
        @Override
        public void doSpecialAction(Helper helper) {
            // TODO: shoots at the nearest enemy
        }
    },
    MELEE {
        @Override
        public void doSpecialAction(Helper helper) {
            // TODO: kill enemy on collision
        }
    },
    SHIELD {
        @Override
        public void doSpecialAction(Helper helper) {
            // TODO: blocks every bullet that he gets
        }
    },
    CURRENCY_COLLECTOR {
        @Override
        public void doSpecialAction(Helper helper) {
            // TODO: collects currency on collision
        }
    },
    POWER_COLLECTOR {
        @Override
        public void doSpecialAction(Helper helper) {
            // TODO: collects power on collision
        }
    };

    public abstract void doSpecialAction(Helper helper);
}

public class Helper extends GameObject {
    private Player owner;
    private String name;
    private HelperMovementType movementType;
    private HelperSpecialActionType specialActionType;

    private final int ANIMATION_WINGS_UP = 0;
    private final int ANIMATION_WINGS_DOWN = 1;

    public Helper(String name, float width, float height, HelperMovementType movementType,
                  HelperSpecialActionType specialActionType, Player owner, String textureUrl) {
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
        return new Vector2(owner.getPosition().x - bounds.x, owner.getPosition().y + bounds.y);
    }

    @Override
    protected void update(float dt) {
        specialActionType.doSpecialAction(this);
        movementType.move(this);

    }

    @Override
    protected void draw(SpriteBatch batch) {
        activeSprite.setPosition(position.x, position.y);
    }

}