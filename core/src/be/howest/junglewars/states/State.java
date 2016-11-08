package be.howest.junglewars.states;

import be.howest.junglewars.be.howest.junglewars.managers.*;

public abstract class State {

    protected StateManager sm;

    protected State(StateManager sm) {
        this.sm = sm;
    }

    public abstract void init();

    public abstract void update(float dt);

    public abstract void render();

    public abstract void handleInput();

    public abstract void dispose();

}
