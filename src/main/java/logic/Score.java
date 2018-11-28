package logic;

public class Score implements Comparable<Score> {

	private String name;
	private int value;

	public Score(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public int value() {
		return value;
	}

	@Override
	public int compareTo(Score score) {
		int comparison = Integer.compare(score.value(), this.value());
		if (comparison == 0)
			comparison = this.getName().compareTo(score.getName());
		return comparison;
	}

}
