package logic;

import java.util.Set;
import java.util.TreeSet;

public class GameInfo {
    
    private static final int[] DEFAULT_LIVES = {10, 5, 1};
    
    private int score;
    private int numLives;
    private int difficulty;
    private Set<Score> scoreList;

    public GameInfo() {
        difficulty = 1;
        reset();
    }
    
    public void reset()
    {
        score = 0;
        numLives = DEFAULT_LIVES[difficulty];
        scoreList = new TreeSet<>();
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

    public Set<Score> getScores() {
        return scoreList;
    }

}
