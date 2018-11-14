package main;

import java.util.Arrays;

public class OperatorFactory {
	public class Adder implements Operator
	{
		public double compute(double input1, double input2) {
			return input1 + input2;
		}
	}
	
	public class Subtractor implements Operator
	{
		public double compute(double input1, double input2) {
			return input1 - input2;
		}
	}
	
	public class Multiplier implements Operator
	{
		public double compute(double input1, double input2) {
			return input1 * input2;
		}
	}
	
	
	public class Divider implements Operator
	{
		public double compute(double input1, double input2) {
			try {
				return input1 / input2;
			}
			catch (Exception e)
			{
				return Double.POSITIVE_INFINITY;
			}
		}
	}
	
	public class NullOperator implements Operator
	{
		public double compute(double input1, double input2) {
			return 0;
		}
	}

	public Operator getOperator(char c)
	{
		switch (c)
		{
			case '+':
				return new Adder();
			case '-':
				return new Subtractor();
			case '*':
				return new Multiplier();
			case '/':
				return new Divider();
			default:
				return new NullOperator();
		}
	}
	
	public boolean isOperator(char c)
	{
		for (char operator : Operator.OPERATORS)
		{
			if (c == operator)
				return true;
		}
		return false;
	}
}
