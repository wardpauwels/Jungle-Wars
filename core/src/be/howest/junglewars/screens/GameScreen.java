package be.howest.junglewars.screens;

import be.howest.junglewars.*;
import be.howest.junglewars.gameobjects.*;
import be.howest.junglewars.net.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.freetype.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class GameScreen extends Stage implements Screen {

    //region fields

    public TextureAtlas atlas;
    public boolean isHost;
    public JWClient client;
    public JWServer server;
    public String ip;
    private Skin skin;
    private Stage stage;
    private Sprite backgroundSprite;
    private BitmapFont smallFont;
    private BitmapFont bigFont;
    private JungleWars game;
    private GameData data;
    private int startingEnemies;
    private float multiplierEnemies;
    private float amountEnemies;
    private boolean isGameOver;
    private String playerName;

    //endregion

    public GameScreen(JungleWars game, boolean singleplayer, Difficulty difficulty, boolean isHost, String ip) {
        super(new ScreenViewport(), game.batch);
        this.isHost = isHost;
        if (!ip.isEmpty()) {
            this.ip = ip;
        } else {
            this.ip = "localhost";
        }

        this.server = server;
        this.client = client;
        //data = new GameData();
        this.stage = this;
        this.game = game;
        this.atlas = game.atlas;
        this.skin = game.skin;
        this.isGameOver = false;
        this.playerName = "user" + ThreadLocalRandom.current().nextInt(0, 100000);

//        data.setWave(1);
//        data.setDifficulty(difficulty);
//        data.setState(GameState.READY);

        // create full screen background
        backgroundSprite = atlas.createSprite("background/game");
        backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // TODO: https://github.com/libgdx/libgdx/wiki/Managing-your-assets#loading-a-ttf-using-the-assethandler
        // TODO: work with Actors for GUI layout (buttons, menu, etc...)?
        // Fonts
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/roboto-regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 20;
        smallFont = generator.generateFont(parameter);
        parameter.size = 24;
        bigFont = generator.generateFont(parameter);
        generator.dispose();

        //data.getPlayers().add( new Player( this, "harambe", game.getPlayer() ) );

        startingEnemies = 1;
        multiplierEnemies = 0.5f;
    }

    @Override
    public void show() {

        client = new JWClient(playerName);
        data = client.getData();

        if (isHost) {
            // Start server
            System.out.println("Starting server...");
            try {
                server = new JWServer();
                client.connect("localhost");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Can't start server. Already running?");
                game.setScreen(new MainMenuScreen(game));
            }
        } else {
            client.connect(ip);
        }
    }

    private void checkGameOver() {
        for (Map.Entry<Long, Player> playerEntry : data.getPlayers().entrySet()) {
            if (playerEntry.getValue().getHitpoints() > 0) return;
        }
        data.setState(GameState.GAME_OVER);
    }

    private void checkCollisions() {
//        for (Player player : data.getPlayers()) {
//            for (Currency currency : player.checkCollision(data.getCurrencies())) {
//                currency.collectedBy(player);
//            }
//            for (Power power : player.checkCollision(data.getPowers())) {
//                power.collectedBy(player);
//            }
//            for (Missile missile : player.checkCollision(data.getEnemyMissiles())) {
//                player.hitBy(missile);
//                missile.doEffect(player);
//            }
//
//        }
//
//        for (Enemy enemy : data.getEnemies()) {
//            for (Player player : data.getPlayers()) {
//                for (Missile missile : enemy.checkCollision(player.getMissiles())) {
//                    enemy.hitBy(missile, player);
//                    return;
//                }
//            }
//        }
//
//        for(Wall wall : data.getWalls()) {
//            for (Player player : data.getPlayers()) {
//                for (Brick brick : wall.returnWall()) {
//                    for (Missile missile : brick.checkCollision(player.getMissiles())) {
//                        brick.remove = true;
//                    }
//                }
//            }
//        }


    }

    //region spawners TODO: create spawners



    //endregion

    //region updates

    private void updateReady(float dt) {
        // TODO: show some message like "press any key to start wave X"
//        if (Gdx.input.isTouched()) {
        data.setState(GameState.RUNNING);
//        }
    }

    private void updateRunning(float dt) {

//        for (Player player : data.getPlayers()) {
//            player.update(dt);
//        }
//
//        for (int i = 0; i < data.getEnemies().size(); i++) {
//            data.getEnemies().get(i).update(dt);
//            if (data.getEnemies().get(i).shouldRemove()) {
//                data.getEnemies().remove(i);
//                i--;
//            }
//        }
//
//        for (int i = 0; i < data.getEnemyMissiles().size(); i++) {
//            data.getEnemyMissiles().get(i).update(dt);
//            if (data.getEnemyMissiles().get(i).shouldRemove()) {
//                data.getEnemyMissiles().remove(i);
//                i--;
//            }
//        }
//
//        for (int i = 0; i < data.getCurrencies().size(); i++) {
//            data.getCurrencies().get(i).update(dt);
//            if (data.getCurrencies().get(i).shouldRemove()) {
//                data.getCurrencies().remove(i);
//                i--;
//            }
//        }
//
//        for (int i = 0; i < data.getPowers().size(); i++) {
//            data.getPowers().get(i).update(dt);
//            if (data.getPowers().get(i).shouldRemove()) {
//                data.getPowers().remove(i);
//                i--;
//            }
//        }
//
//       for (int i = 0; i < data.getWalls().size(); i++) {
//            for (int j = 0; j < data.getWalls().get(i).returnWall().size(); j++) {
//                data.getWalls().get(i).returnWall().get(j).update(dt);
//                if (data.getWalls().get(i).returnWall().get(j).shouldRemove()) {
//                    data.getWalls().get(i).returnWall().remove(j);
//
//
//                }
//            }
//        }

        checkCollisions();

        checkGameOver();

    }

    private void updatePreWave(float dt) {

    }

    private void updatePaused(float dt) {

    }

    private void updateGameOver(float dt) {
        if (!isGameOver) {
            isGameOver = true;
            //HighscoreDA.getInstance().addHighscore( data.getPlayers().get( 0 ) );

            Dialog d = new Dialog("Game over", skin) {
                {
                    //text("Woops, " + data.getPlayers().get(0).getName() + " died... You reached " + data.getPlayers().get(0).getScore() + " points!");
                    button("Home", "Back to menu");
//                    button("Retry", "retry");
                }

                @Override
                protected void result(Object object) {
                    switch (String.valueOf(object)) {
                        case "leave":
                            game.setScreen(new MainMenuScreen(game));
                            break;
                        case "retry":
//                            game.setScreen( new GameScreen( game, 1, Difficulty.EASY ) );
                            break;
                    }
                }
            };
            d.show(stage).setWidth(500);
            d.setPosition(Gdx.graphics.getWidth() / 2 - d.getWidth() / 2, Gdx.graphics.getHeight() / 2 - d.getHeight() / 2);
            Gdx.input.setInputProcessor(stage);
        }
    }

    //endregion

    //region renders

//    private void renderReady(SpriteBatch batch) {
//
//    }
//
//    private void renderRunning(SpriteBatch batch) {
//        // if all players have 0 hitpoints => game over
//        boolean isGameOver = true;
//        for (Map.Entry<Long, Player> p : data.getPlayers().entrySet()) {
//            if (p.getValue().getHitpoints() >= 0) {
//                isGameOver = false;
//                break;
//            }
//        }
//
//        if (isGameOver) {
//            data.setState(GameState.GAME_OVER);
//            return;
//        }
//

//        for (Enemy enemy : data.getEnemies()) {
//            enemy.render(batch);
//        }
//
//        for (Missile missile : data.getEnemyMissiles()) {
//            missile.render(batch);
//        }
//
//        for (Currency currency : data.getCurrencies()) {
//            currency.render(batch);
//        }
//
//        for (Power power : data.getPowers()) {
//            power.render(batch);
//        }
//        for (Wall wall: data.getWalls()){
//
//            for(Brick b:wall.returnWall()){
//                b.render(batch);
//            }
//        }
//
//        bigFont.draw(batch, "LEVEL " + data.getWave(), Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 20);
//
//    }

    private void renderPreWave(SpriteBatch batch) {

    }

    private void renderPaused(SpriteBatch batch) {

    }

    private void renderGameOver(SpriteBatch batch) {
//        bigFont.render(batch, "GAME OVER!", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
//        for (Player player : players) {
//            smallFont.render(batch, player.getName() + ": " + player.getScore() + " points", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2 - 40);
//        }
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    //endregion

    @Override
    public void render(float dt) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        data.update(dt);
        data.render();

        if (isHost) {
            server.update(dt);
        }
    }

    @Override
    public void resize(int width, int height) {
        System.out.println("Resized");
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.input.setCatchBackKey(false);
        client.shutdown();
        if (server != null)
            server.shutdown();
        data.dispose();
    }

    @Override
    public void dispose() {
    }

    public GameData getData(){
        return data;
    }

}