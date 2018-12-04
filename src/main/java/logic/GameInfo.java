package logic;

import java.util.Set;
import java.util.TreeSet;

public class GameInfo {
    
    private static final int[] DEFAULT_LIVES = {10, 5, 1};
    
    private static final GameInfo INSTANCE = new GameInfo();
    public static GameInfo getInstance()
    {
        return INSTANCE;
    }
    
    private int score;
    private int numLives;
    private int difficulty;
    private Set<Score> scoreList;

    private GameInfo() {
        difficulty = 1;
        scoreList = new TreeSet<>();
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
    
    public void setScore(int s)
    {
        this.score = s;
    }
    
    public void incrementScore()
    {
        score++;
    }

    public int getNumLives()
    {
        return numLives;
    }

    public void setNumLives(int numLives)
    {
        this.numLives = numLives;
    }

    public void decrementLives()
    {
        numLives--;
    }
    
    public int getDifficulty()
    {
        return this.difficulty;
    }
    
    public void setDifficulty(int difficulty)
    {
        this.difficulty = difficulty;
    }

    public Set<Score> getScores() {
        return scoreList;
    }

}
