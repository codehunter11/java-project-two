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


public class TwoPairsThirdWheelLevel extends RankTrioLevel {

	protected TwoPairsThirdWheelLevel (TurnsTakenCounterLabel validTurnTime, ScoreCounter scoreCounter, JFrame mainFrame) {
		super(validTurnTime, scoreCounter, mainFrame);
		super.turnsTakenCounter.setDifficultyModeLabel("Two Trio Level");
		cardsToTurnUp = 5;
		cardsPerRow = 10;
		rowsPerGrid = 5;
		totalUniqueCards = cardsPerRow * rowsPerGrid;
		cardsFaceDown = totalUniqueCards;
	}

	@Override
	protected boolean addToTurnedCardsBuffer(Card card) {
		// add the card to the list
		this.turnedCardsBuffer.add(card);
		if (this.turnedCardsBuffer.size() == getCardsToTurnUp()) {
			// We are uncovering the last card in this turn
			// Record the player's turn
			this.turnsTakenCounter.increment();
			
			if (isTwoThird() == true) {
				// Increases the score depending on the rank of the cards
				int sumOfRanks = cardsRankSum();

				this.scoreCounter.increment(800 + sumOfRanks);
				cardsFaceDown -= cardsToTurnUp;
				this.turnedCardsBuffer.clear();						
				
				if (cardsFaceDown == 10) { 
					card.turnUp();
					card.setIcon(card.getFaceIcon());
					card.setAsLastCard();
				}
				
				// take penalty if cards do not match
			} else { 
				this.turnDownTimer.start();
				this.scoreCounter.increment(-5);					}
		}
		Card.resetState(); // Updates the Card class' firstTime variable
		return true;
	}
	
	protected boolean isTwoThird() {

		Card otherCard1 = (Card) this.turnedCardsBuffer.get(0);
		Card otherCard2 = (Card) this.turnedCardsBuffer.get(1);
		Card otherCard3 = (Card) this.turnedCardsBuffer.get(2);
		Card otherCard4 = (Card) this.turnedCardsBuffer.get(3);
		Card otherCard5 = (Card) this.turnedCardsBuffer.get(4);

		if ((otherCard5.getSuit().equals(otherCard1.getSuit()))
				&& (otherCard5.getSuit().equals(otherCard2.getSuit())) 
				&& (otherCard5.getSuit().equals(otherCard3.getSuit())))
			return true;
		else if ((otherCard5.getRank().equals(otherCard4.getRank())))
		return true;
		return false;
	}

	protected int cardsRankSum() {

		Card otherCard1 = (Card) this.turnedCardsBuffer.get(0);
		Card otherCard2 = (Card) this.turnedCardsBuffer.get(1);
		Card otherCard3 = (Card) this.turnedCardsBuffer.get(2);
		Card otherCard4 = (Card) this.turnedCardsBuffer.get(3);
		Card otherCard5 = (Card) this.turnedCardsBuffer.get(4);

		int sumOfRanks = otherCard1.getRankValue() + otherCard2.getRankValue()
				+ otherCard3.getRankValue() + otherCard4.getRankValue()
				+ otherCard5.getRankValue();

		return sumOfRanks;

	}
	
	protected String getMode() {
		return "TwoTrioMode";
	}
}
