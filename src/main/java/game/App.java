package game;

/**
 * This class represents the application for the Minesweeper game.
 * It manages the game state and the application's lifecycle.
 */
public class App {
    private boolean started;
    private Game currentGame;

    /**
     * Constructs an App instance with an initial state of not started.
     */
    public App() {
        this.started = false;
    }

    /**
     * Starts a new game with specified dimensions, number of bombs, and player.
     *
     * @param dimension The dimension for the grid of the game.
     * @param nbBombes  The number of bombs in the game.
     * @param player    The player of the game.
     */
    public void startNewGame(final int dimension, final int nbBombes, final Player player) {
        this.currentGame = new Game(dimension, nbBombes, player);
    }

    /**
     * Starts the application by setting the started flag to true.
     */
    public void startApplication() {
        this.started = true;
    }

    /**
     * Ends the application by setting the started flag to false.
     */
    public void endApplication() {
        this.started = false;
    }

    /**
     * Checks if the application is started.
     *
     * @return true if the application is started, false otherwise.
     */
    public boolean isStarted() {
        return started;
    }

    /**
     * Sets the started status of the application.
     *
     * @param started The new started status.
     */
    public void setStarted(final boolean started) {
        this.started = started;
    }

    /**
     * Gets the current game being played.
     *
     * @return The current game instance.
     */
    public Game getCurrentGame() {
        return currentGame;
    }

    /**
     * Sets the current game being played.
     *
     * @param currentGame The game to be set as the current game.
     */
    public void setCurrentGame(final Game currentGame) {
        this.currentGame = currentGame;
    }
}
