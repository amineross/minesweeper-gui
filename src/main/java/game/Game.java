package game;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a game in the Minesweeper application.
 */
public final class Game {
    /**
     * Current status of the game.
     */
    private int status;

    /**
     * Dimension of the game grid.
     */
    private int dimension;

    /**
     * Number of bombs in the game.
     */
    private int nbBombes;

    /**
     * Timer for the game.
     */
    private int timer;

    /**
     * Player playing the game.
     */
    private Player player;

    /**
     * Grid of the game.
     */
    private Grid grid;

    /**
     * Constructs a new Game with specified dimensions and player.
     *
     * @param dimension Dimension of the game grid.
     * @param nbBombes  Number of bombs in the game.
     * @param player    Player of the game.
     */
    @JsonCreator
    public Game(@JsonProperty("dimension") final int dimension,
                @JsonProperty("nbBombes") final int nbBombes,
                @JsonProperty("player") final Player player) {
        this.dimension = dimension;
        this.nbBombes = nbBombes;
        this.player = player;
        this.grid = new Grid(dimension, nbBombes);
        startGame();
    }

    /**
     * Gets the game grid.
     *
     * @return The game grid.
     */
    public Grid getGrid() {
        return this.grid;
    }

    /**
     * Starts the game.
     */
    public void startGame() {
        this.status = 1;
        this.timer = 0;
    }

    /**
     * Checks if the game is over.
     *
     * @return True if the game is over, false otherwise.
     */
    public boolean isGameOver() {
        return this.status == 2 || this.status == 3;
    }

    /**
     * Reveals a case at a specific position.
     *
     * @param x The x-coordinate of the case.
     * @param y The y-coordinate of the case.
     */
    public void revealCase(final int x, final int y) {
        boolean bombRevealed = grid.revealBox(x, y);
        if (bombRevealed) {
            this.status = 3;
        } else if (grid.areAllSafeBoxesRevealed()) {
            this.status = 2;
        }
    }

    /**
     * Flags a case at a specific position.
     *
     * @param x The x-coordinate of the case.
     * @param y The y-coordinate of the case.
     */
    public void flagCase(final int x, final int y) {
        grid.flagBox(x, y);
    }

    /**
     * Resets the game to its initial state.
     */
    public void resetGame() {
        timer = 0;
        grid.resetGrid();
    }

    /**
     * Reveals all boxes in the game grid.
     */
    public void revealGame() {
        grid.revealGrid();
    }

    /**
     * Marks the game as won, revealing all grid boxes.
     */
    public void winGame() {
        grid.winGrid();
    }

    /**
     * Gets the current status of the game.
     *
     * @return The current status of the game.
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the status of the game.
     *
     * @param status The new status to be set.
     */
    public void setStatus(final int status) {
        this.status = status;
    }

    /**
     * Gets the dimension of the game grid.
     *
     * @return The dimension of the game grid.
     */
    public int getDimension() {
        return dimension;
    }

    /**
     * Sets the dimension of the game grid.
     *
     * @param dimension The new dimension of the grid.
     */
    public void setDimension(final int dimension) {
        this.dimension = dimension;
    }

    /**
     * Gets the number of bombs in the game grid.
     *
     * @return The number of bombs.
     */
    public int getNbBombes() {
        return nbBombes;
    }

    /**
     * Sets the number of bombs in the game grid.
     *
     * @param nbBombes The new number of bombs.
     */
    public void setNbBombes(final int nbBombes) {
        this.nbBombes = nbBombes;
    }

    /**
     * Sets the grid of the game.
     *
     * @param grid The new game grid.
     */
    public void setGrid(final Grid grid) {
        this.grid = grid;
    }

    /**
     * Gets the player of the game.
     *
     * @return The player of the game.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets the player of the game.
     *
     * @param player The new player.
     */
    public void setPlayer(final Player player) {
        this.player = player;
    }
}