package game;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Random;

/**
 * Represents the grid of the Minesweeper game.
 */
public class Grid {
    /**
     * Dimension of the grid.
     */
    private int dimension;

    /**
     * Number of bombs in the grid.
     */
    private final int nbBombs;

    /**
     * Two-dimensional array of boxes.
     */
    private Box[][] boxes;

    /**
     * Constructs a new Grid with specified dimension and number of bombs.
     *
     * @param dimensionParam The dimension of the grid.
     * @param nbBombsParam   The number of bombs in the grid.
     */
    @JsonCreator
    public Grid(@JsonProperty("dimension") final int dimensionParam,
                @JsonProperty("nbBombs") final int nbBombsParam) {
        this.dimension = dimensionParam;
        this.nbBombs = nbBombsParam;
        this.boxes = new Box[dimensionParam][dimensionParam];
        initBoxes();
        setBombs();
    }

    /**
     * Initializes all boxes in the grid.
     */
    private void initBoxes() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                boxes[i][j] = new Box();
            }
        }
    }

    /**
     * Gets a box at the specified coordinates.
     *
     * @param x The x-coordinate of the box.
     * @param y The y-coordinate of the box.
     * @return The box at the specified coordinates.
     */
    public Box getBoxAt(final int x, final int y) {
        if (x >= 0 && x < dimension && y >= 0 && y < dimension) {
            return boxes[x][y];
        }
        return null;
    }

    /**
     * Randomly places bombs in the grid.
     */
    private void setBombs() {
        Random random = new Random();
        int nbBombsSet = 0;

        while (nbBombsSet <= nbBombs) {
            int x = random.nextInt(dimension);
            int y = random.nextInt(dimension);

            if (!boxes[x][y].getContainsBomb()) {
                boxes[x][y].setContainsBomb(true);
                nbBombsSet++;
                updateNeighborBoxes(x, y);
            }
        }
    }

    /**
     * Updates the neighbor boxes when a bomb is set.
     *
     * @param x The x-coordinate of the bomb.
     * @param y The y-coordinate of the bomb.
     */
    private void updateNeighborBoxes(final int x, final int y) {
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i >= 0 && i < dimension && j >= 0 && j < dimension && !(i == x && j == y)) {
                    boxes[i][j].incrementNbNeighborBombs();
                }
            }
        }
    }

    /**
     * Reveals a box at specified coordinates.
     *
     * @param x The x-coordinate of the box to reveal.
     * @param y The y-coordinate of the box to reveal.
     * @return True if a bomb is revealed, otherwise false.
     */
    public boolean revealBox(final int x, final int y) {
        if (x < 0 || x >= dimension || y < 0 || y >= dimension) {
            return false;
        }

        Box box = boxes[x][y];

        if (box.isRevealed()) {
            return false;
        }

        box.setRevealed(true);

        if (box.getContainsBomb()) {
            return true;
        }

        if (box.getNbNeighborBombs() == 0) {
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if (i != x || j != y) {
                        revealBox(i, j);
                    }
                }
            }
        }
        return false;
    }

    /**
     * Flags a box at specified coordinates.
     *
     * @param x The x-coordinate of the box to flag.
     * @param y The y-coordinate of the box to flag.
     */
    public void flagBox(final int x, final int y) {
        if (x < dimension && y < dimension) {
            boxes[x][y].setFlagged(true);
        }
    }

    /**
     * Checks if all safe boxes are revealed.
     *
     * @return True if all safe boxes are revealed, otherwise false.
     */
    public boolean areAllSafeBoxesRevealed() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                Box box = boxes[i][j];
                if (!box.getContainsBomb() && !box.isRevealed()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Prints the current state of the grid to the console.
     */
    public void printGrid() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                Box box = boxes[i][j];
                if (box.isRevealed()) {
                    if (box.getContainsBomb()) {
                        System.out.print("*");
                    } else if (box.getNbNeighborBombs() != 0) {
                        System.out.print(box.getNbNeighborBombs());
                    } else {
                        System.out.print(" ");
                    }
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }

    /**
     * Resets the grid for a new game.
     */
    public void resetGrid() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                boxes[i][j].setRevealed(false);
            }
        }
    }

    /**
     * Reveals the entire grid.
     */
    public void revealGrid() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                boxes[i][j].setRevealed(true);
            }
        }
    }

    /**
     * Reveals all safe boxes in the grid.
     */
    public void winGrid() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                Box box = boxes[i][j];
                if (!box.getContainsBomb()) {
                    box.setRevealed(true);
                }
            }
        }
    }

    /**
     * Gets the dimension of the grid.
     *
     * @return The dimension of the grid.
     */
    public int getDimension() {
        return dimension;
    }

    /**
     * Sets the dimension of the grid.
     *
     * @param dimensionParam The new dimension of the grid.
     */
    public void setDimension(final int dimensionParam) {
        this.dimension = dimensionParam;
    }

    /**
     * Sets the boxes of the grid.
     *
     * @param boxesParam The new set of boxes for the grid.
     */
    public void setBoxes(final Box[][] boxesParam) {
        this.boxes = boxesParam;
    }
}
