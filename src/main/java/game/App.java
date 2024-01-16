package game;

public class App
{
    private boolean started;
    private int height;
    private int width;

    private Game currentGame;
    private Player player;
    private Grid grid;

    public App(int height, int width)
    {
        this.height = height;
        this.width = width;
        this.started = false;
    }

    public void startNewGame(int gameHeight, int gameWidth, int nbBombes, int dimension)
    {
        this.currentGame = new Game(gameHeight, gameWidth, nbBombes, dimension);
    }

    public void startApplication()
    {
        this.started = true;
    }
    public void endApplication()
    {
        this.started = false;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }
}
