package be.howest.junglewars.net;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

import java.util.ArrayList;

public class Network {

    static public final int portTCP = 54555;
    static public final int portUDP = 54777;

    static public void register(EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();
        kryo.register(PlayerJoinLobby.class);
        kryo.register(ArrayList.class);
    }

    static public class PlayerJoinLobby {
        public int id;
        public String name;
        public ArrayList<String> players;

        PlayerJoinLobby(){}
        PlayerJoinLobby(int id, String name, ArrayList<String> players) {
            this.id = id;
            this.name = name;
            this.players = players;
        }
    }

    static public class UpdateGameData{

    }

}
