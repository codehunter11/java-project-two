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
import java.util.Vector;


public class HandType {

	public static int cardsRankSum(Vector<Card> turnedCardsBuffer) {

		Card otherCard1 = (Card) turnedCardsBuffer.get(0);
		Card otherCard2 = (Card) turnedCardsBuffer.get(1);
		Card otherCard3 = (Card) turnedCardsBuffer.get(2);
		Card otherCard4 = (Card) turnedCardsBuffer.get(3);
		Card otherCard5 = (Card) turnedCardsBuffer.get(4);

		int sumOfRanks = otherCard1.getRankValue() + otherCard2.getRankValue()
				+ otherCard3.getRankValue() + otherCard4.getRankValue()
				+ otherCard5.getRankValue();

		return sumOfRanks;

	}

	public static boolean turnedCardsInSequence(Vector<Card> turnedCardsBuffer) {
		// Verify that the sorted cards are in a natural number sequence
		// (Example: 2, 3, 5, 6 and 7 are not in the required sequence, but
		// 10, J, Q, K and A are)
		int i;
		for (i = 0; i < turnedCardsBuffer.size() - 1; i++)
			if ((turnedCardsBuffer.get(i).getRankValue() + 1) != (turnedCardsBuffer.get(i + 1).getRankValue())) {
				break;
			}

		if (i == 4)
			return true;
		else
			return false;
	}

	public static boolean atLeastTwoDistinctSuits(Vector<Card> turnedCardsBuffer) {
		// Verify that the turned cards have at least two distinct suits
		for (int i = 0; i < turnedCardsBuffer.size() - 2; i++)
			if (!turnedCardsBuffer.get(i).getSuit().equals(turnedCardsBuffer.get(i + 1).getSuit())) {
				return true;
			}
		return false;
	}


	public static boolean isFullHouse(Vector<Card> turnedCardsBuffer) {

		if ((turnedCardsBuffer.get(0).getRank().equals(turnedCardsBuffer.get(1).getRank())
			&& turnedCardsBuffer.get(0).getRank().equals(turnedCardsBuffer.get(2).getRank()) 
			&& turnedCardsBuffer.get(3).getRank().equals(turnedCardsBuffer.get(4).getRank()))
			|| (turnedCardsBuffer.get(2).getRank().equals(turnedCardsBuffer.get(3).getRank())
			&& turnedCardsBuffer.get(2).getRank().equals(turnedCardsBuffer.get(4).getRank()) 
			&& turnedCardsBuffer.get(0).getRank().equals(turnedCardsBuffer.get(1).getRank())))
			return true;

		System.out.println("not full house");
		return false;
	}

	public static boolean isSteelWheel(Vector<Card> turnedCardsBuffer) {

		Card otherCard1 = (Card) turnedCardsBuffer.get(0);
		Card otherCard2 = (Card) turnedCardsBuffer.get(1);
		Card otherCard3 = (Card) turnedCardsBuffer.get(2);
		Card otherCard4 = (Card) turnedCardsBuffer.get(3);
		Card otherCard5 = (Card) turnedCardsBuffer.get(4);

		String[] rankOrder = {otherCard1.getRank(), otherCard2.getRank(), otherCard3.getRank(), otherCard4.getRank(), otherCard5.getRank()};

		Arrays.sort(rankOrder);
		if(rankOrder[0].equalsIgnoreCase("2") && rankOrder[1].equalsIgnoreCase("3") && rankOrder[2].equalsIgnoreCase("4") && rankOrder[3].equalsIgnoreCase("5") && rankOrder[4].equalsIgnoreCase("a")){
			return true;
		}
		return false;
	}
	
	public static boolean isTwoThird(Vector<Card> turnedCardsBuffer) {

		Card otherCard1 = (Card) turnedCardsBuffer.get(0);
		Card otherCard2 = (Card) turnedCardsBuffer.get(1);
		Card otherCard3 = (Card) turnedCardsBuffer.get(2);
		Card otherCard4 = (Card) turnedCardsBuffer.get(3);
		Card otherCard5 = (Card) turnedCardsBuffer.get(4);

		if ((otherCard5.getSuit().equals(otherCard1.getSuit()))
			&& (otherCard5.getSuit().equals(otherCard2.getSuit())) 
			&& (otherCard5.getSuit().equals(otherCard3.getSuit())))
			return true;
		else if ((otherCard5.getRank().equals(otherCard4.getRank())))
			return true;
		return false;
	}

	public static boolean isThreeOfAKind(Vector<Card> turnedCardsBuffer) {

		if ((turnedCardsBuffer.get(0).getRank().equals(turnedCardsBuffer.get(1).getRank()) 
			&& turnedCardsBuffer.get(0).getRank().equals(turnedCardsBuffer.get(2).getRank()))
			|| (turnedCardsBuffer.get(1).getRank().equals(turnedCardsBuffer.get(2).getRank()) 
			&& turnedCardsBuffer.get(1).getRank().equals(turnedCardsBuffer.get(3).getRank()))
			|| (turnedCardsBuffer.get(2).getRank().equals(turnedCardsBuffer.get(3).getRank())
			&& turnedCardsBuffer.get(2).getRank().equals(turnedCardsBuffer.get(4).getRank())))
			return true;

		System.out.println("not three of a kind");
		return false;
	}

	public static boolean isTwoPair(Vector<Card> turnedCardsBuffer) {

		if (turnedCardsBuffer.get(0).getRank().equals(turnedCardsBuffer.get(1).getRank())) {
			if (turnedCardsBuffer.get(2).getRank().equals(turnedCardsBuffer.get(3).getRank())) {
				System.out.println("two pair");
				return true;
			} else if (turnedCardsBuffer.get(3).getRank().equals(turnedCardsBuffer.get(4).getRank())) {
				System.out.println("two pair");
				return true;
			}
		}
		if (turnedCardsBuffer.get(1).getRank().equals(turnedCardsBuffer.get(2).getRank())
			&& turnedCardsBuffer.get(3).getRank().equals(turnedCardsBuffer.get(4).getRank()))
			return true;

		System.out.println("not two pair");
		return false;
	}

	public static boolean isOnePair(Vector<Card> turnedCardsBuffer) {

		if (turnedCardsBuffer.get(0).getRank().equals(turnedCardsBuffer.get(1).getRank())
			|| turnedCardsBuffer.get(1).getRank().equals(turnedCardsBuffer.get(2).getRank())
			|| turnedCardsBuffer.get(2).getRank().equals(turnedCardsBuffer.get(3).getRank())
			|| turnedCardsBuffer.get(3).getRank().equals(turnedCardsBuffer.get(4).getRank()))
			return true;

		return false;
	}
}