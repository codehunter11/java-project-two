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

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class ComboLevel extends SteelWheelLevel {

	public ComboLevel(TurnsTakenCounterLabel validTurnTime, ScoreCounter scoreCounter, JFrame mainFrame) {
		super(validTurnTime, scoreCounter, mainFrame);
		super.turnsTakenCounter.setDifficultyModeLabel("Combo Level");

	}

	protected boolean addToTurnedCardsBuffer(Card card) {
		// add the card to the list
		this.turnedCardsBuffer.add(card);

		if (this.turnedCardsBuffer.size() == getCardsToTurnUp()) {
			// We are uncovering the last card in this turn
			// Record the player's turn
			this.turnsTakenCounter.increment();
			// Sort turned cards by increasing rank values
			sortTurnedCards();
			// Set score
			int score = Hands.checkHand(turnedCardsBuffer);
			card.turnUp();
			card.setIcon(card.getFaceIcon());
			setScore(score, card, getPokerHand(score));
			
		}
		Card.resetState(); // Updates the Card class' firstTime variable
		return true;
	}
	
	protected String getPokerHand(int score) {

		String hand = null;
		if (score > 3000) {
			hand = "Full House";
		} else if (score > 1000) {
			hand = "Steel Wheel";
		} else if (score > 700) {
			hand = "Two Pairs and Third Wheel";
		} else if (score > 400) {
			hand = "Three of a Kind";
		} else if (score > 150) {
			hand = "Two Pair";
		} else if (score > 50) {
			hand = "One Pair";
		} else {
			hand = "High Card";
		}

		return hand;

	}
	
	protected void sortTurnedCards() {
		for (int i = 0; i < this.turnedCardsBuffer.size() - 1; i++)
			for (int j = 0; j < (this.turnedCardsBuffer.size() - 1) - i; j++)
				if (this.turnedCardsBuffer.get(j).getRankValue() > this.turnedCardsBuffer.get(j + 1).getRankValue()) {
					Card temp = this.turnedCardsBuffer.get(j);
					this.turnedCardsBuffer.set(j, this.turnedCardsBuffer.get(j + 1));
					this.turnedCardsBuffer.set(j + 1, temp);
				}
	}

	public void setScore(int score, Card card, String hand) {
		boolean userMadeChoice = false;
		while (!userMadeChoice) {
			int decision = JOptionPane.showOptionDialog(null, "Poker Hand: "
					+ hand + "\nPoints worth: " + Integer.toString(score)
					+ "\nDo you want to keep the hand?",
					"Poker Memory Ultra", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.INFORMATION_MESSAGE, null, new String[] { "No", "Yes" }, "default");
			if (decision == 1) // 1 means 'Yes'
			{
				userMadeChoice = true;
				this.scoreCounter.increment(score);
				this.turnedCardsBuffer.clear();

				cardsFaceDown -= cardsToTurnUp;
				if (cardsFaceDown == 0) {
					card.setAsLastCard();
				}

			} else if (decision == 0) // 0 means 'No'
			{
				this.scoreCounter.increment(-50);
				this.turnDownTimer.start();
				userMadeChoice = true;
			} else // user pressed the close button
			{
			}
		}
	}

	protected String getMode() {
		return "ComboMode";
	}
}
