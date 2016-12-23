package be.howest.junglewars.screens;

import be.howest.junglewars.*;
import be.howest.junglewars.net.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.utils.viewport.*;

public class LobbyScreen extends Stage implements Screen {

    private final String name;
    private JWServer server;
    private JWClient client;

    private JungleWars game;
    private Skin skin;
    private Stage stage;
    private TextureAtlas atlas;
    private SpriteBatch batch;

    private Table playerTable;

    public LobbyScreen(JungleWars game, String name) {
        super(new ScreenViewport(), game.batch);
        this.game = game;
        this.batch = game.batch;
        this.stage = this;
        this.skin = game.skin;
        this.atlas = game.atlas;
        this.name = name;
    }

    @Override
    public void show() {
        client = new JWClient(name);

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
        TextField hostField = new TextField("localhost", skin);

        table.row();
        table.add(hostButton);
        table.row();
        table.add(hostField, joinButton);

        stage.addActor(table);
        stage.addActor(playerTable);

        hostButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                startHost("localhost");
            }
        });

        joinButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                joinHost(hostField.getText());
            }
        });

        Gdx.input.setInputProcessor(stage);
    }

    private void startHost(String host) {
        game.setScreen(new GameScreen(game, name, false, Difficulty.EASY, true, host));
    }

    private void joinHost(String host){
        game.setScreen(new GameScreen(game, name, false, Difficulty.EASY, false, host));
    }

    @Override
    public void render(float delta) {
        playerTable.clearChildren();

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
