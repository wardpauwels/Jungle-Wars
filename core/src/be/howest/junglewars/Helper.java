package be.howest.junglewars;

import be.howest.junglewars.gameobjects.GameObject;
import be.howest.junglewars.gameobjects.Player;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Helper extends GameObject {

    private Player owner;
    private String name;

    private HelperType helperType;

    private final int adaptXcoord = -30;
    private final int adaptYcoord = 70;


    public Helper(String name, float width, float height, HelperType helperType, Player owner, String textureUrl) {
        super(width, height, textureUrl);
        this.owner = owner;
        this.name = name;
        this.helperType = helperType;
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
        helperType.doSpecialAbility();
//        position.set(owner.getPosition().x + adaptXcoord, owner.getPosition().y + adaptYcoord);
        helperType.move();

    }

    @Override
    protected void draw(SpriteBatch batch) {
        activeSprite.setPosition(position.x, position.y);
    }


}

enum HelperType {
    SHOOTER {
        @Override
        public void move() {

        }

        @Override
        public void doSpecialAbility() {

        }
    },
    SHIELD {
        @Override
        public void move() {

        }

        @Override
        public void doSpecialAbility() {

        }
    },
    CURRENCY_COLLECTOR {
        @Override
        public void move() {

        }

        @Override
        public void doSpecialAbility() {

        }
    },
    POWER_COLLECTOR {
        @Override
        public void move() {

        }

        @Override
        public void doSpecialAbility() {

        }
    };

    public abstract void move();

    public abstract void doSpecialAbility();
}