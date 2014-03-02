package jjs.caf.poker;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Moi
 * 
 */
public class Player {
	private ArrayList<Card> cards = new ArrayList<Card>();
	private String name;

	Player(String name) {
		this.name = name;
	}

	void addCard(Card card) {
		cards.add(card);
	}

	String showHand() {
		return cards.toString();
	}

	public String getName() {
		return name;
	}

	/**
	 * Sort the hand cards by the given order of the CardValue-enum
	 */
	public void sortHand() {
		Collections.sort(cards);
	}

	Card getHighestCard() {
		// Make sure that the deck order is sorted in descending order of the card value
		sortHand();
		// Get the highest card, which is the first entry
		Card highestCard = cards.get(0);
		return highestCard;
	}
}
