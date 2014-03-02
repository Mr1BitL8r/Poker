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

	void addCard(Card card){
		cards.add(card);
	}
	
	String showHand(){
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
		Card highestCard = null;
		
		if (cards.size() > 0) {
			highestCard = cards.get(0);
		
			for (Card card : cards) {
//				if()
			}
		}
		return highestCard;
	}
}
