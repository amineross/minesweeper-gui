package main.java.game;
import java.util.Random;
public class Grid
{
    private final int dimension;
    private final int nbBombs;
    private Box[][] boxes;

    public Grid(int dimension, int nbBombs)
    {
        this.dimension = dimension;
        this.nbBombs = nbBombs;
        this.boxes = new Box[dimension][dimension];
        initBoxes();
        setBombs();
    }

    private void initBoxes()
    {
        for (int i = 0; i<dimension; i++)
        {
            for (int j = 0; j<dimension; j++)
            {
                boxes[i][j] = new Box();
            }
        }
    }

    private void setBombs()
    {
        Random random = new Random();
        int nbBombsSet = 0;

        while (nbBombsSet <= nbBombs)
        {
            int x = random.nextInt(dimension);
            int y = random.nextInt(dimension);

            if (!boxes[x][y].containsBomb())
            {
                boxes[x][y].setContainsBomb(true);
                nbBombsSet++;
                updateNeighborBoxes(x, y);
            }
        }
    }

    private void updateNeighborBoxes(int x, int y)
    {
        for (int i = x - 1; i <= x+1 ; i++)
        {
            for (int j = y - 1; j <= y+1; j++)
            {
                if (i >= 0 && i < dimension && j >= 0 && j < dimension && !(i == x && j == y))
                {
                    boxes[i][j].incrementNbNeighborBombs();
                }
            }
        }
    }
    public void revealBox(int x, int y)
    {
        if (x >= dimension || y >= dimension)
        {
            System.exit(0);
        }

        Box box = boxes[x][y];
        if (!box.revealed())
        {
            box.setRevealed();
        }

        if (box.containsBomb())
        {
            //box.setRevealed(); TODO: gameover
        }
    }

    public void flagBox(int x, int y)
    {
        if (x < dimension && y < dimension)
        {
            boxes[x][y].setFlagged();
        }
    }

    public void printGrid()
    {
        for (int i = 0; i<dimension; i++)
        {
            for (int j = 0; j<dimension; j++)
            {
                Box box = boxes[i][j];
                if (box.revealed())
                {
                    if (box.containsBomb())
                    {
                        System.out.print("*");
                    } else if (box.getNbNeighborBombs() != 0)
                    {
                        System.out.print(box.getNbNeighborBombs());
                    } else
                    {
                        System.out.print("");
                    }
                } else
                {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
}
