package main.java.game;


public class Game
{
    private int status;
    /*
      0: Not started
      1: Started
      2: Won
      3: Lost
    */

    private int gameHeight;
    private int gameWidth;
    private int nbBombes;

    private Grid grid;

    public Game(int gameHeight, int gameWidth, int nbBombes, int dimension)
    {
        this.gameHeight = gameHeight;
        this.gameWidth = gameWidth;
        this.nbBombes = nbBombes;
        this.status = 0;
        this.grid = new Grid(dimension, nbBombes);
    }
    public void startGame()
    {
        this.status = 1;
        // this.grid.placeBombs();
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
}
