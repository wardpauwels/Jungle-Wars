package be.howest.junglewars.screens;

import be.howest.junglewars.*;
import be.howest.junglewars.data.da.*;
import be.howest.junglewars.data.entities.EnemyEntity;
import be.howest.junglewars.data.entities.HelperEntity;
import be.howest.junglewars.data.entities.PlayerEntity;
import be.howest.junglewars.data.entities.PowerEntity;
import be.howest.junglewars.gameobjects.*;
import be.howest.junglewars.gameobjects.enemy.*;
import be.howest.junglewars.gameobjects.enemy.utils.*;
import be.howest.junglewars.gameobjects.helper.Helper;
import be.howest.junglewars.gameobjects.helper.HelperActionType;
import be.howest.junglewars.gameobjects.helper.HelperMovementType;
import be.howest.junglewars.gameobjects.power.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.freetype.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.*;

import java.util.ArrayList;

public class GameScreen extends Stage implements Screen {

    //region fields

    public TextureAtlas atlas;
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

    private boolean nextLevel;
    private boolean running;

    private ArrayList<Helper> helpers;
    private ArrayList<Power> powers;
    private ArrayList<Enemy> enemies;

    public ArrayList<Sound> sounds;

    private int upgradeCost;

    private SelectBox<Helper> helperSelectBox;
    private int currentPlayer;
    private JungleWarsDA db;





    //endregion

    public GameScreen(JungleWars game, int wave, Difficulty difficulty) {
        super(new ScreenViewport(), game.batch);
        data = new GameData();
        this.stage = this;
        this.game = game;
        this.atlas = game.atlas;
        this.skin = game.skin;
        this.isGameOver = false;
        this.playerName = game.getPlayer().getName();

        this.nextLevel = true;

        this.upgradeCost = 1;
        this.running = false;
        this.currentPlayer = 0;
        db = JungleWarsDA.getInstance();




        data.setWave(wave);
        data.setDifficulty(difficulty);
        data.setState(GameState.READY);

        helpers = new ArrayList<>();
        powers = new ArrayList<>();
        enemies = new ArrayList<>();

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

        data.getPlayers().add( new Player( this, "harambe", game.getPlayer() ) );

        startingEnemies = 1;
        multiplierEnemies = 0.5f;
        fillEnemiesDB();
        spawnEnemies(false);
        helperSelectBox = new SelectBox<Helper>(skin);
        fillHelperBox();
        innitSounds();
    }

    private void innitSounds(){
        sounds = new ArrayList<>();
        sounds.add(Gdx.audio.newSound(Gdx.files.internal("sound/smallloan.wav")));
        sounds.add(Gdx.audio.newSound(Gdx.files.internal("sound/wall.wav")));
        sounds.add(Gdx.audio.newSound(Gdx.files.internal("sound/grabem.wav")));
        sounds.add(Gdx.audio.newSound(Gdx.files.internal("sound/beauty.wav")));
        sounds.add(Gdx.audio.newSound(Gdx.files.internal("sound/losers.wav")));

    }
    private void fillHelperBox(){

        ArrayList<HelperEntity> helperDB = new ArrayList<>();
        helperDB = db.getHelpers();
        for(HelperEntity h : helperDB){

            helpers.add(new Helper(this,h.getName(),data.getPlayers().get(currentPlayer),h.getTextureFileName(),HelperMovementType.valueOf(h.getMovementType()),HelperActionType.valueOf(h.getActionType())));
        }

    }

    private void fillEnemiesDB(){
        ArrayList<EnemyEntity> enemiesDB = db.getEnemies();
        for(EnemyEntity e : enemiesDB){
            enemies.add(new Enemy(this,e.getName(),e.getWidth(),e.getHeight(),e.getTextureFileName(),e.getAltTextureFileName(),e.getBaseDamage(),e.getBaseSpeed(),e.getBaseHitpoints(),e.getBaseAttackSpeed(),e.getExperienceWhenKilled(),e.getScoreWhenKilled(),e.getRarity(),ChooseTargetType.valueOf(e.getTargetSelectionType()),EnemyMovementType.valueOf(e.getMovementType()),EnemyActionType.valueOf(e.getAttackType())));
        }
    }

    private void checkGameOver() {
        for (Player player : data.getPlayers()) {
            if (player.getHitpoints() > 0) return;
        }
        data.setState(GameState.GAME_OVER);
    }

    private void checkCollisions() {
        for (Player player : data.getPlayers()) {
            for (Currency currency : player.checkCollision(data.getCurrencies())) {
                currency.collectedBy(player);
            }
            for (Power power : player.checkCollision(data.getPowers())) {
                power.collectedBy(player);
            }
            for (Missile missile : player.checkCollision(data.getEnemyMissiles())) {
                player.hitBy(missile);
                missile.doEffect(player);
            }
            for (Missile missile : player.getHelper().checkCollision(data.getEnemyMissiles())) {
                player.getHelper().hitBy(missile);
            }
        }

            for (Enemy enemy : data.getEnemies()) {
                for (Player player : data.getPlayers()) {
                    for (Missile missile : enemy.checkCollision(player.getMissiles())) {
                        enemy.hitBy(missile, player);
                        return;
                    }
                }
            }



        for(Wall wall : data.getWalls()) {
            for (Player player : data.getPlayers()) {
                for (Brick brick : wall.returnWall()) {
                    for (Missile missile : brick.checkCollision(player.getMissiles())) {
                        brick.remove = true;
                        missile.remove = true;
                    }
                }
            }
        }

    }

    //region spawners TODO: create spawners

    private void spawnEnemies(boolean nextWave) {
        if (data.getEnemies().size()==0 && running) {
            data.setState(GameState.PRE_WAVE);
        }
        if(data.getEnemies().size()==0 && !running && nextLevel){
                data.setState(GameState.RUNNING);
                amountEnemies = startingEnemies + (startingEnemies * (multiplierEnemies * data.getWave()));
                Enemy newEnemy =enemies.get(0);
                data.getEnemies().add(newEnemy);

                for (int i = 0; i < amountEnemies; i++) {

                    data.getEnemies().add(new Enemy(this, "Zookeeper", 70, 80, "zookeeper3", "zookeeper3-animation", 5, 150, 1, 1.5f, 10, 15, 5, ChooseTargetType.NEAREST_PLAYER, EnemyMovementType.NEAREST_PLAYER, EnemyActionType.CRYING));
                }
                if (nextWave) data.setWave(data.getWave() + 1);
            }
    }



    public void makeSmallCloseDialogButton(Dialog d) {
        Button closeButton = new Button( skin, "close" );
        closeButton.addListener( new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                d.hide();
            }
        } );
        d.getTitleTable().add( closeButton );
    }

    private void spawnCurrencies() {
        int maxCurrenciesOnField = 2;
        if (data.getCurrencies().size() < maxCurrenciesOnField) {
            data.getCurrencies().add(new Currency(this, 5, "coin"));
        }
    }

    private void spawnPowers() {
        int maxPowersOnField = 5;
        if (data.getPowers().size() < maxPowersOnField) {
            data.getPowers().add(new Power(this, "Damage", "damage", 5, 10, PowerType.DAMAGE_POWER, 40));
            data.getPowers().add(new Power(this, "Movement Speed", "movement-speed", 5, 10, PowerType.MOVEMENT_SPEED_POWER, 50));
            data.getPowers().add(new Power(this, "Attack Speed", "power-up", 5, 10, PowerType.ATTACK_SPEED_POWER, 40));
            data.getPowers().add(new Power(this, "Missle Speed", "missilespeed", 5, 10, PowerType.MISSILE_SPEED_POWER, 40));
            data.getPowers().add(new Power(this, "HP bonus", "HP", 5, 1, PowerType.HITPOINTS_POWER, 100));
            data.getPowers().add(new Power(this, "Armor Bonus", "armor", 5, 10, PowerType.ARMOR_POWER, 20));
        }
    }

    //endregion

    //region updates

    private void updateReady(float dt) {
        // TODO: show some message like "press any key to start wave X"
//        if (Gdx.input.isTouched()) {
        data.setState(GameState.RUNNING);
//        }
    }

    private void updateRunning(float dt) {
        spawnEnemies(true);
        spawnCurrencies();
        spawnPowers();
        System.out.println(data.getPlayers().get(currentPlayer).getHelper().getName());

        for (Player player : data.getPlayers()) {
            player.update(dt);
        }

        for (int i = 0; i < data.getEnemies().size(); i++) {
            data.getEnemies().get(i).update(dt);
            if (data.getEnemies().get(i).shouldRemove()) {
                data.getEnemies().remove(i);
                i--;
            }
        }

        for (int i = 0; i < data.getEnemyMissiles().size(); i++) {
            data.getEnemyMissiles().get(i).update(dt);
            if (data.getEnemyMissiles().get(i).shouldRemove()) {
                data.getEnemyMissiles().remove(i);
                i--;
            }
        }

        for (int i = 0; i < data.getCurrencies().size(); i++) {
            data.getCurrencies().get(i).update(dt);
            if (data.getCurrencies().get(i).shouldRemove()) {
                data.getCurrencies().remove(i);
                i--;
            }
        }

        for (int i = 0; i < data.getPowers().size(); i++) {
            data.getPowers().get(i).update(dt);
            if (data.getPowers().get(i).shouldRemove()) {
                data.getPowers().remove(i);
                i--;
            }
        }

       for (int i = 0; i < data.getWalls().size(); i++) {
            for (int j = 0; j < data.getWalls().get(i).returnWall().size(); j++) {
                data.getWalls().get(i).returnWall().get(j).update(dt);
                if (data.getWalls().get(i).returnWall().get(j).shouldRemove()) {
                    data.getWalls().get(i).returnWall().remove(j);


                }
            }
        }

        checkCollisions();

        checkGameOver();

    }

    private void updatePreWave(float dt) {
        spawnEnemies(true);

        Dialog d = new Dialog("Upgrade your helper", skin) {
            {
                text("UPGRADE YOUR HELPER");
                getButtonTable().row();

                button("upgrade","upgradeHelper");

                text("HELPERS:");
                addActor(helperSelectBox);
                getButtonTable().row();
                button("Coin Collector","pickCoinCollector");
                button("Power Collector","pickPowerCollector");

                getButtonTable().row();

                button("Shield","pickShield");
                button("Shooter","pickShooter");

                getButtonTable().row();
                button("Stabber","pickStabber");

                getButtonTable().row();
                button("next level", "next");
                button("Retry", "retry");



            }



            @Override
            protected void result(Object object) {
                switch (String.valueOf(object)) {
                    case "upgradeHelper":
                        if(data.getPlayers().get(currentPlayer).getCollectedCoins()>=upgradeCost){
                       data.getPlayers().get(currentPlayer).getHelper().upgrade();
                        data.getPlayers().get(currentPlayer).collectedCoins-=upgradeCost;}
                        break;

                    case "next":
                        nextLevel = true;
                        running = false;
                        break;
                    case "pickPowerCollector":
                        data.getPlayers().get(currentPlayer).setHelper(helpers.get(0));
                        break;
                    case "pickCoinCollector":
                        data.getPlayers().get(currentPlayer).setHelper(helpers.get(1));
                        break;
                    case "pickShield":
                        data.getPlayers().get(currentPlayer).setHelper(helpers.get(2));
                        break;
                    case "pickShooter":
                        data.getPlayers().get(currentPlayer).setHelper(helpers.get(3));
                        break;
                    case "pickStabber":
                        data.getPlayers().get(currentPlayer).setHelper(helpers.get(4));
                        break;
                    case "retry":
                        game.setScreen( new GameScreen( game, 1, Difficulty.EASY ) );
                        break;
                }
            }
        };

        d.show(stage).setWidth(400);
        d.setPosition(Gdx.graphics.getWidth() / 2 - d.getWidth() / 2, Gdx.graphics.getHeight() / 2 - d.getHeight() / 2);
        Gdx.input.setInputProcessor(stage);

    }

    private void updatePaused(float dt) {

    }

    private void updateGameOver(float dt) {
        if (!isGameOver) {
            isGameOver = true;
            HighscoreDA.getInstance().addHighscore( data.getPlayers().get( 0 ) );

            Dialog d = new Dialog("Game over", skin) {
                {
                    text("Woops, " + data.getPlayers().get(currentPlayer).getName() + " died... You reached " + data.getPlayers().get(currentPlayer).getScore() + " points!");
                    button("Home", "leave");
                    button("Retry", "retry");

                }


                @Override
                protected void result(Object object) {
                    switch (String.valueOf(object)) {
                        case "leave":
                            game.setScreen(new MainMenuScreen(game));
                            break;
                        case "retry":
                            game.setScreen( new GameScreen( game, 1, Difficulty.EASY ) );
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

    private void renderReady(SpriteBatch batch) {

    }

    private void renderRunning(SpriteBatch batch) {
        // if all players have 0 hitpoints => game over
        boolean isGameOver = true;
        for (Player p : data.getPlayers()) {
            if (p.getHitpoints() >= 0) {
                isGameOver = false;
                break;
            }
        }

        if (isGameOver) {
            data.setState(GameState.GAME_OVER);
            return;
        }

        for (Player player : data.getPlayers()) {
            player.draw(batch);
            player.getHelper().draw(batch);

            // TODO: work with LibGDX Actors instead?
            bigFont.setColor(0, 0, 0, 1);
            bigFont.draw(batch, "Player 1", 20, Gdx.graphics.getHeight() - 20);
            smallFont.draw(batch, "Name: " + player.getName(), 20, Gdx.graphics.getHeight() - 40);
            smallFont.draw(batch, "Score: " + player.getScore(), 20, Gdx.graphics.getHeight() - 60);
            smallFont.draw(batch, "Wave: " + player.getWave(), 20, Gdx.graphics.getHeight() - 80);
            smallFont.draw(batch, "XP: " + player.getXp(), 20, Gdx.graphics.getHeight() - 100); // TODO: xp till next wave
            smallFont.draw(batch, "Coins collected: " + player.getCollectedCoins(), 20, Gdx.graphics.getHeight() - 120);
            smallFont.draw(batch, "Hitpoints: " + player.getHitpoints(), 20, Gdx.graphics.getHeight() - 140);
            smallFont.draw(batch, "Multiplier: " + (float)Math.round(player.getScoreMultiplier()*100)/100, 20, Gdx.graphics.getHeight() -160);
            smallFont.draw(batch, "ACTIVE POWERS: ", 300, Gdx.graphics.getHeight() - 20);
            for (int i = 0; i < player.getPowers().size(); i++) {
                smallFont.draw(batch, player.getPowers().get(i).toString() + " [" + player.getPowers().get(i).getTimeLeft() + " seconds left]", 300, Gdx.graphics.getHeight() - 20 * (i + 2));
            }
            smallFont.draw(batch, "ATTACK SPEED: " + player.getAttackSpeed(), 20, 40);
            smallFont.draw(batch, "DAMAGE: " + player.getDamage(), 20, 60);
            smallFont.draw(batch, "MOVEMENT SPEED: " + player.getSpeed(), 20, 80);
            smallFont.draw(batch, "MISSILE SPEED: " + player.getMissileSpeed(), 20, 100);

        }

        for (Enemy enemy : data.getEnemies()) {
            enemy.draw(batch);
        }

        for (Missile missile : data.getEnemyMissiles()) {
            missile.draw(batch);
        }

        for (Currency currency : data.getCurrencies()) {
            currency.draw(batch);
        }

        for (Power power : data.getPowers()) {
            power.draw(batch);
        }
        for (Wall wall: data.getWalls()){

            for(Brick b:wall.returnWall()){
                b.draw(batch);
            }
        }

        bigFont.draw(batch, "LEVEL " + data.getWave(), Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 20);

    }

    private void renderPreWave(SpriteBatch batch) {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    private void renderPaused(SpriteBatch batch) {

    }

    private void renderGameOver(SpriteBatch batch) {
//        bigFont.draw(batch, "GAME OVER!", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
//        for (Player player : players) {
//            smallFont.draw(batch, player.getName() + ": " + player.getScore() + " points", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2 - 40);
//        }
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    //endregion

    @Override
    public void show() {

    }

    @Override
    public void render(float dt) {
        SpriteBatch batch = game.batch;
        batch.begin();
        batch.disableBlending();
        backgroundSprite.draw(batch);
        batch.enableBlending();

        dt = Math.min(dt, 1 / 60f);

        switch (data.getState()) {
            case READY:
                updateReady(dt);
                renderReady(batch);
                batch.end();
                break;
            case RUNNING:
                running = true;
                updateRunning(dt);
                renderRunning(batch);
                batch.end();
                break;
            case PRE_WAVE:
                batch.end();
                updatePreWave(dt);
                renderPreWave(batch);
                break;
            case PAUSED:
                updatePaused(dt);
                renderPaused(batch);
                batch.end();
                break;
            case GAME_OVER:
                batch.end();
                updateGameOver(dt);
                renderGameOver(batch);
                break;
        }

//        batch.end();
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

    }

    @Override
    public void dispose() {
    }

    public GameData getData(){
        return data;
    }

}