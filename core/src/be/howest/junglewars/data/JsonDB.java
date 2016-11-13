package be.howest.junglewars.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import be.howest.junglewars.data.models.EnemyModel;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class JsonDB extends DB {
    private String name;

    private static JsonDB instance = new JsonDB();

    public static JsonDB getInstance() {
        return instance;
    }

    @Override
    public List<EnemyModel> getAllEnemies() {
        List<EnemyModel> enemies = null;

        try {

            URL url = getClass().getResource("json/enemies.json");
            Path path = Paths.get(url.toURI());
            byte[] jsonData = Files.readAllBytes(path);
            ObjectMapper mapper = new ObjectMapper();
            enemies = Arrays.asList(mapper.readValue(jsonData, EnemyModel[].class));

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        return enemies;
    }

}
