import main.java.game.Game;
import main.java.game.Application;

public class Main
{
    public static void main(String[] args)
    {
        Application app = new Application(1280, 1280);

        app.startNewGame(10, 10, 20, 10);
        app.startApplication();

        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                app.getCurrentGame().getGrid().revealBox(i, j);
            }
        }

        app.getCurrentGame().getGrid().printGrid();
    }
}