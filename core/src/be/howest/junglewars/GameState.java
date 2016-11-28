package be.howest.junglewars;

public enum GameState {
    READY,
    RUNNING,
    PAUSED,
    GAME_OVER, // TODO: if (all) player(s) is/are dead
    BETWEEN_WAVE; // TODO: if all enemies are dead
}