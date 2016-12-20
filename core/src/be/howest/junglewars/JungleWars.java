package be.howest.junglewars;

import be.howest.junglewars.screens.*;
import be.howest.junglewars.util.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;

public class JungleWars extends Game {

    public SpriteBatch batch;
    public TextureAtlas atlas;
    public Skin skin;
    private FPSLogger fpsLogger;
    private Preferences savePrefs;

    @Override
    public void create() {
        Assets.load();
        while (!Assets.manager.update()) {
            //TODO: still loading/updating all files
            System.out.printf("Loading assets: %f%n", Assets.manager.getProgress() * 100); // percentage for progress bar
        }
        //saveFile = Gdx.files.internal("data/myfile.txt");

        fpsLogger = new FPSLogger();

        batch = new SpriteBatch();
        atlas = new TextureAtlas(Assets.IMAGES_ATLAS);
        skin = new Skin(Gdx.files.internal(Assets.SKIN));

        // TODO: loading screen while assets are loading

        // TODO: get level and difficulty from settings/savings

        //setScreen(new GameScreen(this, 1, Difficulty.EASY));
        setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        super.render();

        fpsLogger.log();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose() {
        super.dispose();
        Assets.dispose();
    }
}