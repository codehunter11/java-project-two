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

import java.util.Arrays;

import javax.swing.JFrame;

public class SteelWheelLevel extends TwoPairsThirdWheelLevel{

	protected SteelWheelLevel (TurnsTakenCounterLabel validTurnTime, ScoreCounter scoreCounter, JFrame mainFrame) {

		super(validTurnTime, scoreCounter, mainFrame);
		super.turnsTakenCounter.setDifficultyModeLabel("Steel Wheel Level");
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

			if (isSteel() == true && sameSuit() == true) {
				// Increases the score depending on the rank of the cards
				int suitValue = cardsSuitValue();

				this.scoreCounter.increment(1100 + suitValue);
				cardsFaceDown -= cardsToTurnUp;
				this.turnedCardsBuffer.clear();

				if (cardsFaceDown == 10) {
					// end game if high score was obtained
					card.turnUp();
					card.setIcon(card.getFaceIcon());
					card.setAsLastCard();
				}

				// take penalty if cards do not match
			} else {
				this.turnDownTimer.start();
				this.scoreCounter.increment(-5);                                        }
		}
		Card.resetState(); // Updates the Card class' firstTime variable
		return true;
	}

	protected boolean sameSuit() {

		Card otherCard1 = (Card) this.turnedCardsBuffer.get(0);
		Card otherCard2 = (Card) this.turnedCardsBuffer.get(1);
		Card otherCard3 = (Card) this.turnedCardsBuffer.get(2);
		Card otherCard4 = (Card) this.turnedCardsBuffer.get(3);
		Card otherCard5 = (Card) this.turnedCardsBuffer.get(4);

		if (((otherCard1.getSuit().equals(otherCard2.getSuit()))
				&& (otherCard2.getSuit().equals(otherCard3.getSuit()))
				&& (otherCard3.getSuit().equals(otherCard4.getSuit()))
				&& (otherCard4.getSuit().equals(otherCard5.getSuit()))))

			return true;

		return false;
	}

	protected boolean isSteel() {

		Card otherCard1 = (Card) this.turnedCardsBuffer.get(0);
		Card otherCard2 = (Card) this.turnedCardsBuffer.get(1);
		Card otherCard3 = (Card) this.turnedCardsBuffer.get(2);
		Card otherCard4 = (Card) this.turnedCardsBuffer.get(3);
		Card otherCard5 = (Card) this.turnedCardsBuffer.get(4);

		String[] rankOrder = {otherCard1.getRank(), otherCard2.getRank(), otherCard3.getRank(), otherCard4.getRank(), otherCard5.getRank()};

		Arrays.sort(rankOrder);
		if(rankOrder[0].equalsIgnoreCase("2") && rankOrder[1].equalsIgnoreCase("3") && rankOrder[2].equalsIgnoreCase("4") && rankOrder[3].equalsIgnoreCase("5") && rankOrder[4].equalsIgnoreCase("a")){
			return true;
		}
		return false;
	}

	protected int cardsSuitValue() {

		// Hex letter values
		// s = 53
		// c = 43
		// d = 44
		// h = 48

		Card otherCard1 = (Card) this.turnedCardsBuffer.get(0);
		int suitValue = 0;

		if(otherCard1.getSuit().equals("s")){
			suitValue = 53;

		}else{
			if(otherCard1.getSuit().equals("c")){
				suitValue = 43;

			}else{
				if(otherCard1.getSuit().equals("d")){
					suitValue = 44;

				}else{
					if(otherCard1.getSuit().equals("h")){
						suitValue = 48;

					}
				}
			}
		}


		return suitValue;

	}

	protected String getMode() {
		return "SteelWheelMode";
	}
}