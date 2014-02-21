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

public class EqualPairLevel extends EasyLevel {

	protected int cardsFaceDown;

	protected EqualPairLevel(TurnsTakenCounterLabel validTurnTime, ScoreCounter scoreCounter, JFrame mainFrame) {
		super(validTurnTime, scoreCounter, mainFrame);
		super.turnsTakenCounter.setDifficultyModeLabel("Medium Level");
		cardsFaceDown = this.totalUniqueCards; }

	@Override
	protected boolean addToTurnedCardsBuffer(Card card) {
		this.turnedCardsBuffer.add(card);
		if (this.turnedCardsBuffer.size() == getCardsToTurnUp()) {  // there are two cards faced up
			this.turnsTakenCounter.increment(); // record the player's turn

			Card otherCard = (Card) this.turnedCardsBuffer.get(0); // get the other card already up

			if (otherCard.getNum() == card.getNum()) { // the cards match, keep them up
				this.turnedCardsBuffer.clear();
				this.scoreCounter.increment(50);
				cardsFaceDown -= this.cardsToTurnUp;

				if (cardsFaceDown == 0) {
					card.turnUp();
					card.setIcon(card.getFaceIcon());
					card.setAsLastCard();
				}
			}

			// take penalty if cards do not match
			else { 	

				this.turnDownTimer.start(); 
				this.scoreCounter.increment(-5);
			}

		}
		Card.resetState(); // updates the Card.java class firstTime variable
		return true;
	}

	@Override
	protected boolean turnUp(Card card) {
		// the card may be turned
		if(this.turnedCardsBuffer.size() < getCardsToTurnUp()) 
		{
			return this.addToTurnedCardsBuffer(card);
		}
		// there are already the number of EasyMode (two face up cards) in the turnedCardsBuffer
		return false;
	}

	@Override
	protected String getMode() {
		// TODO Auto-generated method stub
		return "MediumMode";
	}



}
