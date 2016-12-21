package be.howest.junglewars.net;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

public class Network {

    static public final int portTCP = 54555;
    static public final int portUDP = 54777;

    static public void register(EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();

    }

    static public class Join {

    }

}
