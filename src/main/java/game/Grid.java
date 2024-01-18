package game;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Grid
{
    private int dimension;
    private final int nbBombs;
    private Box[][] boxes;

    public Grid( @JsonProperty("dimension") int dimension, @JsonProperty("nbBombs") int nbBombs)
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

            if (!boxes[x][y].getContainsBomb())
            {
                boxes[x][y].setContainsBomb(true);
                nbBombsSet++;
                updateNeighborBoxes(x, y);
            }
        }
    }

    public Box[][] getBoxes()
    {
        return this.boxes;
    }

    public Box getBoxAt(int x, int y)
    {
        return this.boxes[x][y];
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
        if (x < 0 || x >= dimension || y < 0 || y >= dimension)
        {
            return;
        }

        Box box = boxes[x][y];

        if (box.isRevealed() || box.getContainsBomb()) {
            return;
        }

        box.setRevealed(true);

        if (box.getNbNeighborBombs() == 0)
        {
            for (int i = x - 1; i <= x + 1; i++)
            {
                for (int j = y - 1; j <= y + 1; j++)
                {
                    if (i != x || j != y)
                    {
                        revealBox(i, j);
                    }
                }
            }
        }
    }

    public void flagBox(int x, int y)
    {
        if (x < dimension && y < dimension)
        {
            boxes[x][y].setFlagged(true);
        }
    }

    public void printGrid()
    {
        for (int i = 0; i<dimension; i++)
        {
            for (int j = 0; j<dimension; j++)
            {
                Box box = boxes[i][j];
                if (box.isRevealed())
                {
                    if (box.getContainsBomb())
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

    public void resetGrid()
    {
        for (int i = 0; i<dimension; i++)
        {
            for (int j = 0; j<dimension; j++)
            {
                boxes[i][j].setRevealed(false);
            }
        }
    }

    public void revealGrid()
    {
        for (int i = 0; i<dimension; i++)
        {
            for (int j = 0; j<dimension; j++)
            {
                boxes[i][j].setRevealed(true);
            }
        }
    }
    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension)
    {
        this.dimension = dimension;
    }

    public void setBoxes(Box[][] boxes) {
        this.boxes = boxes;
    }
}
