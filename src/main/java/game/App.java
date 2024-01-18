package game;

public class App
{
    private boolean started;

    private Game currentGame;

    public App()
    {
        this.started = false;
    }

    public void startNewGame(int dimension, int nbBombes, Player player)
    {
        this.currentGame = new Game(dimension, nbBombes, player);
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

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }


}
