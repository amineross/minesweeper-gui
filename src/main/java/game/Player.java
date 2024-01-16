package game;

public class Player
{
    private String name;
    private int nbGamesPlayed;
    private int nbGamesWon;

    public Player(String name)
    {
        this.name = name;
        this.nbGamesPlayed = 0;
        this.nbGamesWon = 0;
    }

    public void incrementGamesPlayed()
    {
        this.nbGamesPlayed++;
    }

    public void incrementGamesWon()
    {
        this.nbGamesWon++;
    }

    public String getName()
    {
        return name;
    }

    public int getNbGamesPlayed()
    {
        return nbGamesPlayed;
    }

    public int getNbGamesWon()
    {
        return nbGamesWon;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setNbGamesPlayed(int nbGamesPlayed)
    {
        this.nbGamesPlayed = nbGamesPlayed;
    }

    public void setNbGamesWon(int nbGamesWon)
    {
        this.nbGamesWon = nbGamesWon;
    }
}
