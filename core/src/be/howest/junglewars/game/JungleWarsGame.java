package be.howest.junglewars.game;

import be.howest.junglewars.managers.StateManager;
import be.howest.junglewars.screens.GameScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;

public class JungleWarsGame extends Game {
    private FPSLogger fpsLogger; // TODO: development only!

    private SpriteBatch batcher;

    @Override
    public void create() {
        fpsLogger = new FPSLogger(); // TODO: development only!

        batcher = new SpriteBatch();
        setScreen(new GameScreen(this));
    }

    @Override
    public void render() {
        fpsLogger.log(); // TODO: development only!

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sm.update(Gdx.graphics.getDeltaTime());

        sm.render();
    }

    @Override
    public void resize(int width, int height) {
        HEIGHT = height;
        WIDTH = width;
        screenBounds = new Rectangle(0, 0, WIDTH, HEIGHT);

        camera = new OrthographicCamera(WIDTH, HEIGHT);
        camera.translate(WIDTH / 2, HEIGHT / 2);
        camera.update();

        sm.update(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void dispose() {

    }

}
