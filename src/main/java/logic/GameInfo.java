package logic;

public class GameInfo {
    
    private static final int[] DEFAULT_LIVES = {10, 5, 1};
    
    private int score;
    private int numLives;
    private int difficulty;

    public GameInfo() {
        difficulty = 1;
        reset();
    }
    
    public void reset()
    {
        score = 0;
        numLives = DEFAULT_LIVES[difficulty];
    }
    public int getScore()
    {
        return score;
    }
    
    public void incrementScore()
    {
        score++;
    }

    public int getNumLives()
    {
        return numLives;
    }

    public void decrementLives()
    {
        numLives--;
    }
    
    public void setDifficulty(int difficulty)
    {
        this.difficulty = difficulty;
    }

}
