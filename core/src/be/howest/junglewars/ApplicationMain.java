package be.howest.junglewars;

import be.howest.junglewars.screens.GameScreen;
import be.howest.junglewars.util.Assets;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ApplicationMain extends Game {

    private FPSLogger fpsLogger;

    public SpriteBatch batch;

    @Override
    public void create() {
        Assets.load();
        while (!Assets.manager.update()) {
            //TODO: still loading/updating all files
            System.out.printf("Loading assets: %f%n", Assets.manager.getProgress() * 100); // percentage for progress bar
        }

        fpsLogger = new FPSLogger();
        batch = new SpriteBatch();

        // TODO: loading screen while assets are loading

        // TODO: get level and difficulty from settings/savings
        GameData gameData = new GameData(1, Difficulty.EASY);

        setScreen(new GameScreen(gameData));
    }

    @Override
    public void render() {
        fpsLogger.log();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();

    }

    @Override
    public void dispose() {
        super.dispose();
        Assets.dispose();
    }

}
