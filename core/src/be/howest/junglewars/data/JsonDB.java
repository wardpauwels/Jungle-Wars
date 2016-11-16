package be.howest.junglewars.data;

import be.howest.junglewars.entities.Enemy;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class JsonDB extends DB {

    @Override
    public List<Enemy> getAllEnemies() {
        List<Enemy> enemies = null;

        try {

            URL url = getClass().getResource("json/enemies.json");
            Path path = Paths.get(url.toURI());
            byte[] jsonData = Files.readAllBytes(path);
            ObjectMapper mapper = new ObjectMapper();
            enemies = Arrays.asList(mapper.readValue(jsonData, Enemy[].class));

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        return enemies;
    }

}
