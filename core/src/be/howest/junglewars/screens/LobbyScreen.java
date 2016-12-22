package be.howest.junglewars.screens;

import be.howest.junglewars.JungleWars;
import be.howest.junglewars.net.JWClient;
import be.howest.junglewars.net.JWServer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class LobbyScreen extends Stage implements Screen {

    private JWServer server;
    private JWClient client;

    private JungleWars game;
    private Skin skin;
    private Stage stage;
    private TextureAtlas atlas;
    private SpriteBatch batch;

    private Table playerTable;

    public LobbyScreen(JungleWars game) {
        super(new ScreenViewport(), game.batch);
        this.game = game;
        this.batch = game.batch;
        this.stage = this;
        this.skin = game.skin;
        this.atlas = game.atlas;
    }

    @Override
    public void show() {
        Table table = new Table(skin);
        playerTable = new Table(skin);

        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.top);
        table.setPosition(0, Gdx.graphics.getHeight());

        playerTable.setWidth(stage.getWidth());
        playerTable.align(Align.center | Align.bottom);
        playerTable.setPosition(0, Gdx.graphics.getHeight() / 2);

        TextButton hostButton = new TextButton("host", skin);
        TextButton joinButton = new TextButton("join", skin);
        TextField host = new TextField("localhost", skin);

        table.row();
        table.add(hostButton);
        table.row();
        table.add(host, joinButton);

        stage.addActor(table);
        stage.addActor(playerTable);

        hostButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        playerTable.clearChildren();
        playerTable.add("test");

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
