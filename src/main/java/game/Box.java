package game;

/**
 * Represents a box in the Minesweeper game.
 */
public final class Box {
    /**
     * Indicates whether this box contains a bomb.
     */
    private boolean containsBomb;

    /**
     * Indicates whether this box has been revealed.
     */
    private boolean revealed;

    /**
     * Indicates whether this box has been flagged.
     */
    private boolean flagged;

    /**
     * The number of neighboring bombs around this box.
     */
    private int nbNeighborBombs;

    /**
     * Constructs a new Box with default initial values.
     */
    public Box() {
        this.containsBomb = false;
        this.revealed = false;
        this.flagged = false;
        this.nbNeighborBombs = 0;
    }

    /**
     * Sets whether this box contains a bomb.
     *
     * @param containsBombState True if the box contains a bomb, false otherwise.
     */
    public void setContainsBomb(final boolean containsBombState) {
        this.containsBomb = containsBombState;
    }

    /**
     * Returns whether this box contains a bomb.
     *
     * @return True if the box contains a bomb, false otherwise.
     */
    public boolean getContainsBomb() {
        return this.containsBomb;
    }

    /**
     * Returns whether this box has been revealed.
     *
     * @return True if the box has been revealed, false otherwise.
     */
    public boolean isRevealed() {
        return revealed;
    }

    /**
     * Returns whether this box has been flagged.
     *
     * @return True if the box has been flagged, false otherwise.
     */
    public boolean isFlagged() {
        return flagged;
    }

    /**
     * Sets whether this box is revealed.
     *
     * @param revealedState True if the box is to be revealed, false otherwise.
     */
    public void setRevealed(final boolean revealedState) {
        this.revealed = revealedState;
    }

    /**
     * Sets whether this box is flagged.
     *
     * @param flaggedState True if the box is to be flagged, false otherwise.
     */
    public void setFlagged(final boolean flaggedState) {
        this.flagged = flaggedState;
    }

    /**
     * Sets the number of neighboring bombs.
     *
     * @param neighborBombs The number of neighboring bombs.
     */
    public void setNbNeighborBombs(final int neighborBombs) {
        this.nbNeighborBombs = neighborBombs;
    }

    /**
     * Returns the number of neighboring bombs.
     *
     * @return The number of neighboring bombs.
     */
    public int getNbNeighborBombs() {
        return this.nbNeighborBombs;
    }

    /**
     * Increments the number of neighboring bombs by one.
     */
    public void incrementNbNeighborBombs() {
        this.nbNeighborBombs++;
    }
}
