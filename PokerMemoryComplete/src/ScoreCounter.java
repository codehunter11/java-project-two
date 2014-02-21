/*
 
 Team Name: UtraCoders
 Team Members/GitHub Username: 
 Eliel Ruiz Rodriguez / codehunter11
 Elliam Espinosa / elliamespinosa
 
 Project02: Poker Memory
 Course: ICOM4015
 Professor: Dr. Bienvenido Velez
 Due Date: November 13, 2013
 
 */

import java.awt.Color;
import javax.swing.JLabel;

// This class will show the score counter on the window.

public class ScoreCounter extends JLabel {

	private static final long serialVersionUID = 1L;
	private int score = 0;
	private String DESCRIPTION = "Score: ";

	public ScoreCounter() {
		super();
		reset();
	}

	public void setDifficultyModeLabel(String difficultyMode) {
		DESCRIPTION = "Score: ";
		setHorizontalTextPosition(JLabel.CENTER);
	}

	public int getScore() {
		return this.score;
	}

	// Update the text label with the current value
	private void update() {

		setForeground(Color.BLACK);	
		setText(DESCRIPTION + Integer.toString(this.score));
		setHorizontalTextPosition(JLabel.CENTER);
	}


	// Increments counter and updates text label. 
	public void increment(int points) {
		this.score = this.score + points;
		update();
	}

	// Resets the counter to zero and updates text label.
	public void reset() {
		this.score = 0;
		update();
	}

}