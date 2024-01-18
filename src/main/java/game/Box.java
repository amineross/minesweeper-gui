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

    public boolean getContainsBomb()
    {
        return this.containsBomb;
    }

    public boolean isRevealed()
    {
        return revealed;
    }

    public boolean isFlagged() {return flagged; }

    public void setRevealed(boolean revealed)
    {
        this.revealed = revealed;
    }

    public void setFlagged(boolean flagged)
    {
        this.flagged = flagged;
    }

    public void setNbNeighborBombs(int nbNeighborBombs) {
        this.nbNeighborBombs = nbNeighborBombs;
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
