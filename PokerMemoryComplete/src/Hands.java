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

import java.util.Vector;

// Returns score for each poker hand
public class Hands {

	public static int checkHand(Vector<Card> turnedCardsBuffer) {
	
		// Full House
		if (HandType.isFullHouse(turnedCardsBuffer))
			return 3500; 
		
		// Steel Wheel
		else if (HandType.isSteelWheel(turnedCardsBuffer))
			return 2500;

		// Two Pairs And Third Wheel
		else if (HandType.isTwoThird(turnedCardsBuffer))
			return 700 + HandType.cardsRankSum(turnedCardsBuffer);

		// Three of a Kind
		else if (HandType.isThreeOfAKind(turnedCardsBuffer))
			return 500; 

		// Two Pair
		else if (HandType.isTwoPair(turnedCardsBuffer))
			return 200;
		
		// One Pair
		else if (HandType.isOnePair(turnedCardsBuffer))
			return 100 + HandType.cardsRankSum(turnedCardsBuffer);

		// None of the above
		else
			return turnedCardsBuffer.get(4).getRankValue();
	}
}