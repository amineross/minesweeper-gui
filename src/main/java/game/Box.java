package game;

public class Box
{
    private boolean containsBomb;
    private boolean revealed;
    private boolean flagged;
    private int nbNeighborBombs;

    public Box()
    {
        this.containsBomb = false;
        this.revealed = false;
        this.flagged = false;
        this.nbNeighborBombs = 0;
    }

    public void setContainsBomb(boolean containsBomb)
    {
        this.containsBomb = containsBomb;
    }

    public boolean containsBomb()
    {
        return this.containsBomb;
    }

    public boolean revealed()
    {
        return revealed;
    }

    public void setRevealed()
    {
        this.revealed = true;
    }

    public void setFlagged()
    {
        this.flagged = !this.flagged;
    }

    public int getNbNeighborBombs()
    {
        return this.nbNeighborBombs;
    }

    public void incrementNbNeighborBombs()
    {
        this.nbNeighborBombs++;
    }
}
