package be.howest.junglewars.util;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {

    public static final AssetManager manager = new AssetManager();

    public static final String
            BACKGROUNDS = "atlas/backgrounds.atlas",
            ENEMIES = "atlas/enemies.atlas",
            HELPERS = "atlas/helpers.atlas",
            MISSILES = "atlas/missiles.atlas",
            PLAYERS = "atlas/players.atlas";

    public static void load() {
        manager.load(BACKGROUNDS, TextureAtlas.class);
        manager.load(ENEMIES, TextureAtlas.class);
        manager.load(HELPERS, TextureAtlas.class);
        manager.load(MISSILES, TextureAtlas.class);
        manager.load(PLAYERS, TextureAtlas.class);
    }

    public static void dispose() {
        manager.dispose();
    }

}
