package be.howest.junglewars.managers;

import be.howest.junglewars.states.*;

public class StateManager {

    public static final int PLAY = 0;
    public static final int MENU = 1;

    private State state;

    public StateManager() {
        setState(PLAY);
    }

    public void setState(int state) {
        if (this.state != null) {
            this.state.dispose();
        }
        switch (state) {
            case PLAY:
                this.state = new PlayGameState(this);
        }
    }

    public void update(float dt) {
        this.state.update(dt);
    }

    public void render() {
        this.state.render();
    }
}
