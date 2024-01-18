package game;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Game
{
    private int status;
    /*
      0: Not started
      1: Started
      2: Won
      3: Lost
    */

    private int dimension;
    private int nbBombes;

    private int timer;

    private Player player;

    private Grid grid;

    @JsonCreator
    public Game(@JsonProperty("dimension") int dimension,
                @JsonProperty("nbBombes") int nbBombes,
                @JsonProperty("player") Player player) {
        this.status = 0;
        this.dimension = dimension;
        this.nbBombes = nbBombes;
        this.player = player;
        this.grid = new Grid(dimension, nbBombes);
    }

    public Grid getGrid()
    {
        return this.grid;
    }
    public void startGame()
    {
        this.status = 1;
        this.timer = 0;
    }

    public boolean isGameOver()
    {
        return this.status == 2 || this.status == 3;
    }

    public void revealCase(int x, int y)
    {
        grid.revealBox(x, y);
    }

    public void flagCase(int x, int y)
    {
        grid.flagBox(x, y);
    }

    public void resetGame()
    {
        timer = 0;
        grid.resetGrid();
    }

    public void revealGame()
    {
        grid.revealGrid();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public int getNbBombes() {
        return nbBombes;
    }

    public void setNbBombes(int nbBombes) {
        this.nbBombes = nbBombes;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
