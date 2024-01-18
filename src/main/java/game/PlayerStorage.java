package game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.*;
import java.util.*;

/**
 * Handles the storage and retrieval of player information.
 */
public class PlayerStorage {
    /** The file path for storing player data. */
    private static final String PLAYER_FILE = "players.json";

    /**
     * Saves a player to the storage.
     *
     * @param player The player to be saved.
     */
    public static void savePlayer(final Player player) {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(PLAYER_FILE);
        List<Player> players = new ArrayList<>();

        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                players = mapper.readValue(reader, new TypeReference<List<Player>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (players.stream().noneMatch(p -> p.getName().equals(player.getName()))) {
            players.add(player);

            try (FileWriter writer = new FileWriter(file)) {
                mapper.writeValue(writer, players);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Updates an existing player's information in the storage.
     *
     * @param updatedPlayer The player with updated information.
     */
    public static void updatePlayer(final Player updatedPlayer) {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(PLAYER_FILE);
        List<Player> players = new ArrayList<>();

        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                players = mapper.readValue(reader, new TypeReference<List<Player>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getName().equals(updatedPlayer.getName())) {
                players.set(i, updatedPlayer);
                break;
            }
        }

        try (FileWriter writer = new FileWriter(file)) {
            mapper.writeValue(writer, players);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads and returns a list of all players from the storage.
     *
     * @return A list of players.
     */
    public static List<Player> loadPlayers() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(PLAYER_FILE);
        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                return mapper.readValue(reader, new TypeReference<List<Player>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }
}
