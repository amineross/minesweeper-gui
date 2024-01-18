package game;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a player in the Minesweeper game.
 */
public class Player {
    /** Player's name. */
    private String name;

    /** Number of games played by the player. */
    private int nbGamesPlayed;

    /** Number of games won by the player. */
    private int nbGamesWon;

    /**
     * Constructs a new Player with the specified name.
     *
     * @param name The name of the player.
     */
    @JsonCreator
    public Player(@JsonProperty("name") final String name) {
        this.name = name;
        this.nbGamesPlayed = 0;
        this.nbGamesWon = 0;
    }

    /** Increments the number of games played by the player. */
    public void incrementGamesPlayed() {
        this.nbGamesPlayed++;
    }

    /** Increments the number of games won by the player. */
    public void incrementGamesWon() {
        this.nbGamesWon++;
    }

    /**
     * Gets the name of the player.
     *
     * @return The name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the number of games played by the player.
     *
     * @return The number of games played.
     */
    public int getNbGamesPlayed() {
        return nbGamesPlayed;
    }

    /**
     * Gets the number of games won by the player.
     *
     * @return The number of games won.
     */
    public int getNbGamesWon() {
        return nbGamesWon;
    }

    /**
     * Sets the name of the player.
     *
     * @param name The new name of the player.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Sets the number of games played by the player.
     *
     * @param nbGamesPlayed The new number of games played.
     */
    public void setNbGamesPlayed(final int nbGamesPlayed) {
        this.nbGamesPlayed = nbGamesPlayed;
    }

    /**
     * Sets the number of games won by the player.
     *
     * @param nbGamesWon The new number of games won.
     */
    public void setNbGamesWon(final int nbGamesWon) {
        this.nbGamesWon = nbGamesWon;
    }
}
