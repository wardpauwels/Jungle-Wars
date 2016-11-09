package be.howest.junglewars.game;

import be.howest.junglewars.managers.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;

public class JungleWarsGame extends ApplicationAdapter {

    public static int WIDTH;
    public static int HEIGHT;
    public static Rectangle screenBounds;

    private OrthographicCamera camera;

    private StateManager sm;

    private FPSLogger fpsLogger; // TODO: development only!

    @Override
    public void create() {
        fpsLogger = new FPSLogger(); // TODO: development only!

        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();

        screenBounds = new Rectangle(0, 0, WIDTH, HEIGHT);

        camera = new OrthographicCamera(WIDTH, HEIGHT);
        camera.translate(WIDTH / 2, HEIGHT / 2);
        camera.update();

        sm = new StateManager();
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
