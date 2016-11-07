package be.howest.junglewars;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class JungleWarsGame extends ApplicationAdapter {

    private SpriteBatch batch;
    private Texture harambeTexture;
    private Sprite harambeSprite;
    private Texture backgroundTexture;
    private Sprite backgroundSprite;

    @Override
    public void create() {
        // Get window size
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        batch = new SpriteBatch();

        harambeTexture = new Texture(Gdx.files.internal("characters/harambe_default.png"));
        harambeSprite = new Sprite(harambeTexture);
        harambeSprite.setOriginCenter();
        harambeSprite.setPosition(w * .5f, h * .5f);

        backgroundTexture = new Texture(Gdx.files.internal("backgrounds/jungle-bg.png"));
        backgroundSprite = new Sprite(backgroundTexture);
        backgroundSprite.setPosition(0, 0);
    }

    @Override
    public void render() {
//        System.out.println(Gdx.graphics.getFramesPerSecond());


        // Clear screen
        Gdx.gl.glClearColor(.5f, 1, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        // Render background
        batch.begin();
        batch.disableBlending();
        backgroundSprite.draw(batch);
        batch.end();


        // Render Harambe
        batch.begin();
        batch.enableBlending();
        harambeSprite.draw(batch);
        batch.end();


        // Prevent going out of viewport
        final Rectangle harambeBounds = harambeSprite.getBoundingRectangle();
        float harambeLeft = harambeBounds.getX();
        float harambeBottom = harambeBounds.getY();
        float harambeRight = harambeLeft + harambeBounds.getWidth();
        float harambeTop = harambeBottom + harambeBounds.getHeight();

        final float harambeHalfWidth = harambeBounds.getWidth();
        final float harambeHalfHeight = harambeBounds.getHeight();

        final Rectangle screenBounds = new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        float screenLeft = screenBounds.getX();
        float screenBottom = screenBounds.getY();
        float screenRight = screenLeft + screenBounds.getWidth();
        float screenTop = screenBottom + screenBounds.getHeight();

        // Current position
        float xPos = harambeSprite.getX();
        float yPos = harambeSprite.getY();

        // Prevent going out of bounds
        if(harambeLeft < screenLeft){
            xPos = screenLeft + harambeHalfWidth;
        } else if (harambeRight > screenRight){
            xPos = screenRight - harambeHalfWidth;
        }
        if(harambeBottom < screenBottom){
            yPos = screenBottom + harambeHalfHeight;
        } else if (harambeTop > screenTop){
            yPos = screenTop - harambeHalfHeight;
        }
        harambeSprite.setPosition(xPos, yPos);

        // Handle user input
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            harambeSprite.translate(-10, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            harambeSprite.translate(10, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            harambeSprite.translate(0, 10);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            harambeSprite.translate(0, -10);
        }
    }

    @Override
    public void dispose() {

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

}
