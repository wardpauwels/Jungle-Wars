package be.howest.junglewars.screens;

import be.howest.junglewars.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.*;

public class MainMenuScreen extends ScreenAdapter {
    private final JungleWars game;
    private Stage stage;
    private Skin skin;

    public MainMenuScreen(JungleWars game){
        this.game = game;
        create();
    }

    public void create(){
        skin = new Skin(Gdx.files.internal("skins/flat-earth-ui.json"));
        stage = new Stage(new ScreenViewport());

        Gdx.input.setInputProcessor(stage);
    }

    public void render(float dt){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }
    /* ScreenAdapter
     * dispose() -> release all resources
     * hide() -> called when no longer current screen in game (e.g. save game state or sth like that (in game screen))
     * pause() -> android
     * render(dt) -> gameloop
     * resize(w,h) -> resizing
     * show() -> called when current screen in game (e.g. load saved filed)
     */


}
