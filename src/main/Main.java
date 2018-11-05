package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application 
{
	private static final int NUM_LABELS = 3;
	private static final int DEFAULT_SIZE = 100;
	private Label[] labels;
	
	private OperatorFactory opFactory;
	private double[] inputs;
	private char operator;
	private int index;
	private boolean canReset;

	@Override
	public void start(Stage primaryStage) 
	{
		opFactory = new OperatorFactory();

		GridPane root = new GridPane();
		root.setAlignment(Pos.CENTER);
		
		labels = new Label[NUM_LABELS];
		for (int i = 0; i < NUM_LABELS; i++)
		{
			labels[i] = new Label();
			labels[i].setAlignment(Pos.CENTER);
			labels[i].setStyle("-fx-padding: 5px;");
			labels[i].setFont(new Font(20));
			labels[i].setPrefWidth(DEFAULT_SIZE);
			labels[i].setPrefHeight(DEFAULT_SIZE);
			root.add(labels[i], i, 0);
		}
		Label l = new Label();
		l.setStyle("-fx-padding: 5px;");
		root.add(l, 3, 0);
		clear();
		
		for (int i = 1; i < 10; i++)
		{
			root.add(createDefaultButton("" + i, e -> addToInputNumber(e)), (i-1) % 3, (i-1) / 3 + 1);
		}
		root.add(createDefaultButton("0", e -> addToInputNumber(e)), 1, 4);
		
		for (int i = 0; i < Operator.OPERATORS.length; i++)
		{
			root.add(createDefaultButton("" + Operator.OPERATORS[i], e -> setOperator(e)), 4, i);
		}
		root.add(createDefaultButton("c", e -> clear()), 0, 4);
		root.add(createDefaultButton(".", e -> addToInputNumber(e)), 2, 4);
		root.add(createDefaultButton("=", e -> calculate()), 4, 4);
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("Basic Calculator");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public Button createDefaultButton(String text, EventHandler<ActionEvent> a) 
	{
		Button b = new Button(text);
		b.setPrefWidth(DEFAULT_SIZE);
		b.setPrefHeight(DEFAULT_SIZE);
		b.setOnAction(a);
		return b;
	}
	
	public void addToInputNumber(ActionEvent e)
	{
		if (canReset)
			clear();
		labels[index * 2].setText(labels[index * 2].getText() + ((Button)e.getSource()).getText());
		try {
			inputs[index] = Double.parseDouble(labels[index * 2].getText());
		} catch (Exception ex) {
			inputs[index] = 0;
		}
		canReset = false;
	}
	
	public void setOperator(ActionEvent e)
	{
		operator = ((Button)e.getSource()).getText().charAt(0);
		labels[1].setText("" + operator);
		index = 1;
		canReset = false;
	}
	
	public void clear()
	{
		inputs = new double[2];
		operator = ' ';
		index = 0;
		for (Label l : labels)
		{
			l.setText("");
		}
	}

	public void calculate() 
	{
		if (index == 0)
			return;
		Operator op = opFactory.getOperator(operator);
		double answer = op.compute(inputs[0], inputs[1]);
		clear();
		labels[0].setText("" + answer);
		inputs[0] = answer;
		operator = ' ';
		index = 1;
		canReset = true;
	}

	public static void main(String[] args) 
	{
		launch(args);
	}

}