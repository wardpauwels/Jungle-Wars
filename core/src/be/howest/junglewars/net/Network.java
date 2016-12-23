package be.howest.junglewars.net;

import be.howest.junglewars.*;
import be.howest.junglewars.data.entities.*;
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
        kryo.register(EnemySpawned.class);
        kryo.register(EnemyMovementState.class);
        kryo.register(EnemyEntity.class);
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
        public boolean isShooting;
        public long playerId;
        public boolean isLookingLeft;
        public Vector2 position;

        public PlayerMovementState() {
        }

        public PlayerMovementState(long playerId, boolean isLookingLeft, boolean isShooting, Vector2 position) {
            this.playerId = playerId;
            this.isLookingLeft = isLookingLeft;
            this.position = position;
            this.isShooting = isShooting;
        }
    }

    static public class PlayerShoot {
        public long playerId;
        public Vector2 position;
        public Vector2 destination;
        public boolean isShooting;

        public PlayerShoot() {
        }

        public PlayerShoot(long playerId, Vector2 position, Vector2 destination, boolean isShooting) {
            this.playerId = playerId;
            this.position = position;
            this.destination = destination;
            this.isShooting = isShooting;
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

    static public class EnemySpawned {
        public long id;
        public EnemyEntity enemy;

        public EnemySpawned() {
        }

        public EnemySpawned(long currentId, EnemyEntity enemy) {
            this.id = currentId;
            this.enemy = enemy;
        }
    }

    static public class EnemyMovementState {
        public long id;
        public Vector2 position;
        public boolean isShooting;

        public EnemyMovementState() {
        }

        public EnemyMovementState(long id, boolean isShooting, Vector2 position) {
            this.id = id;
            this.position = position;
            this.isShooting = isShooting;
        }
    }

}