package be.howest.junglewars.net;

import be.howest.junglewars.*;
import com.badlogic.gdx.math.*;
import com.esotericsoftware.kryo.*;
import com.esotericsoftware.kryonet.*;

public class Network {

    static public final int portTCP = 54555;
    static public final int portUDP = 54777;

    static public void register(EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();
        kryo.register(Login.class);
        kryo.register(PlayerJoinLeave.class);
        kryo.register(PlayerMovementState.class);
        kryo.register(PlayerShoot.class);
        kryo.register(PlayerWasHit.class);
        kryo.register(WaveEnd.class);
        kryo.register(WaveStart.class);
        kryo.register(PlayerSpawned.class);
        kryo.register(Vector2.class);
        kryo.register(StartNewGame.class);
        kryo.register(Difficulty.class);
    }

    static public class Login {
        public String name;

        public Login() {
        }
        public Login(String name) {
            this.name = name;
        }
    }

    static public class PlayerJoinLeave {
        public long playerId;
        public String name;
        public boolean hasJoined;

        public PlayerJoinLeave() {
        }

        public PlayerJoinLeave(int playerId, String name, boolean hasJoined) {
            this.playerId = playerId;
            this.name = name;
            this.hasJoined = hasJoined;
        }
    }

    static public class PlayerMovementState {
        public long playerId;
        public boolean isLookingLeft;
        public boolean isShooting;
        public Vector2 position;

        public PlayerMovementState() {
        }

        public PlayerMovementState(long playerId, boolean isLookingLeft, boolean isShooting, Vector2 position) {
            this.playerId = playerId;
            this.isLookingLeft = isLookingLeft;
            this.isShooting = isShooting;
            this.position = position;
        }
    }

    static public class MissileMovementState {


        public MissileMovementState() {
        }
    }

    static public class PlayerShoot {
        public long playerId;
        public Vector2 position;
        public Vector2 destination;

        public PlayerShoot() {
        }

        public PlayerShoot(long playerId, Vector2 position, Vector2 destination) {
            this.playerId = playerId;
            this.position = position;
            this.destination = destination;
        }
    }

    static public class PlayerWasHit {
        public long playerId;
        public float damage;

        public PlayerWasHit() {
        }

        public PlayerWasHit(long playerId, float damage) {
            this.playerId = playerId;
            this.damage = damage;
        }
    }

    static public class WaveEnd {

        public WaveEnd() {
        }
    }

    static public class WaveStart {
        public int wave;

        public WaveStart() {
        }

        public WaveStart(int wave) {
            this.wave = wave;
        }
    }

    static public class PlayerSpawned {
        public long playerId;
        public PlayerMovementState playerMovementState;

        public PlayerSpawned() {
        }

        public PlayerSpawned(long playerId, PlayerMovementState playerMovementState) {
            this.playerId = playerId;
            this.playerMovementState = playerMovementState;
        }
    }

    static public class StartNewGame {
        public Difficulty difficulty;
        public boolean isHost;

        public StartNewGame() {
        }

    }

}
