package game;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.File;

/**
 * This class provides static methods to save and load game data.
 */
public class SaveGame {

    /**
     * Saves a game state to a specified file path.
     *
     * @param game The game object to be saved.
     * @param filePath The file path where the game data will be stored.
     */
    public static void saveGame(final Game game, final String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            mapper.writeValue(new File(filePath), game);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a game state from a specified file path.
     *
     * @param filePath The file path from where the game data will be loaded.
     * @return The loaded Game object or null if an error occurs.
     */
    public static Game loadGame(final String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return mapper.readValue(new File(filePath), Game.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
