package logic;

import java.util.Objects;

public class Score implements Comparable<Score> {

	private String name;
	private int difficulty;
	private int value;

	public Score(String name, int difficulty, int value) {
		this.name = name;
        this.difficulty = difficulty;
		this.value = value;
	}

	public String getName()
	{
		return name;
	}
	
	public String getDifficulty()
	{
        switch (difficulty)
        {
            case 0:
                return "EASY";
            case 1:
                return "NORMAL";
            case 2:
                return "HARD";
            default:
                return "";
        }
	}

	public int value()
	{
		return value;
	}

	@Override
	public int compareTo(Score score)
	{
		int comparison = Integer.compare(score.value(), this.value());
		if (comparison == 0)
		    comparison = Integer.compare(score.difficulty, this.difficulty);
		if (comparison == 0)
			comparison = this.getName().compareTo(score.getName());
		return comparison;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Score)
		{
			return this.compareTo((Score)obj) == 0;
		}
		return false;
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(this.getName(), this.getDifficulty(), this.value());
	}

}
