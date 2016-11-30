package be.howest.junglewars.desktop;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class AtlasPacker {

    public static void main(String[] args) {
        packAll();
    }

    public static void packAll(){
        TexturePacker.process("images/backgrounds", "atlas", "backgrounds");
        TexturePacker.process("images/enemies", "atlas", "enemies");
        TexturePacker.process("images/helpers", "atlas", "helpers");
        TexturePacker.process("images/missiles", "atlas", "missiles");
        TexturePacker.process("images/players", "atlas", "players");
        TexturePacker.process("images/currencies", "atlas", "currencies");
    }

}
