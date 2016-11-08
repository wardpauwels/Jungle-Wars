package be.howest.junglewars.game;

import be.howest.junglewars.be.howest.junglewars.managers.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;

public class JungleWarsGame extends ApplicationAdapter {

    public static int WIDTH;
    public static int HEIGHT;

    private OrthographicCamera camera;

    private StateManager sm;

    @Override
    public void create() {
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();

        camera = new OrthographicCamera(WIDTH, HEIGHT);
        camera.update();

        sm = new StateManager();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sm.update(Gdx.graphics.getDeltaTime());

        sm.render();
    }

    @Override
    public void dispose() {

    }

}
