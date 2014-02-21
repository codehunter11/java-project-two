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

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class RankTrioLevel extends EqualPairLevel {

	// TRIO LEVEL: The goal is to find, on each turn, three cards with the same rank

	protected RankTrioLevel(TurnsTakenCounterLabel validTurnTime, ScoreCounter scoreCounter, JFrame mainFrame) {
		super(validTurnTime, scoreCounter, mainFrame);
		super.turnsTakenCounter.setDifficultyModeLabel("Trio Level");
		cardsToTurnUp = 3;
		cardsPerRow = 10;
		totalUniqueCards = cardsPerRow * rowsPerGrid;
		cardsFaceDown = totalUniqueCards;

	}

	@Override
	protected void makeDeck() {
		// In Trio level the grid consists of distinct cards, no repetitions
		ImageIcon cardIcon[] = this.loadCardIcons();

		//back card
		ImageIcon backIcon = cardIcon[TotalCardsPerDeck];

		int cardsToAdd[] = new int[getRowsPerGrid() * getCardsPerRow()];
		for(int i = 0; i < (getRowsPerGrid() * getCardsPerRow()); i++)
		{
			cardsToAdd[i] = i;
		}

		// randomize the order of the deck
		this.randomizeIntArray(cardsToAdd);

		// make each card object
		for(int i = 0; i < cardsToAdd.length; i++)
		{
			// number of the card, randomized
			int num = cardsToAdd[i];
			// make the card object and add it to the panel
			String rank = cardNames[num].substring(0, 1);
			String suit = cardNames[num].substring(1, 2);
			this.grid.add( new Card(this, cardIcon[num], backIcon, num, rank, suit));
		}
	}

	@Override
	protected boolean addToTurnedCardsBuffer(Card card) {
		// add the card to the list
		this.turnedCardsBuffer.add(card);
		if (this.turnedCardsBuffer.size() == getCardsToTurnUp()) {
			// We are uncovering the last card in this turn
			// Record the player's turn
			this.turnsTakenCounter.increment();
			// get the other card (which was already turned up)
			Card otherCard1 = (Card) this.turnedCardsBuffer.get(0);
			Card otherCard2 = (Card) this.turnedCardsBuffer.get(1);

			if ((card.getRank().equals(otherCard1.getRank()))
					&& (card.getRank().equals(otherCard2.getRank()))) {
				this.turnedCardsBuffer.clear(); // three cards match, keep them up

				//  score depending on the rank of the trio
				int rankValue = card.getRankValue();
				this.scoreCounter.increment(100 + 3 * rankValue);
				cardsFaceDown -= this.cardsToTurnUp;						
				
				if (cardsFaceDown == 10) { 
					
					// end game if high score was obtained
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
	
	protected String getMode() {
		// TODO Auto-generated method stub
		return "TrioMode";
	}

}
