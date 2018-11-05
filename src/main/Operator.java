package main;

public interface Operator
{
	public static final char[] OPERATORS = { '+', '-', '*', '/' };
	
	double compute(double input1, double input2);
}
