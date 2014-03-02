package jjs.caf.poker;

import java.util.ArrayList;

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
	
	public void sortHand() {
//		Collections.sort(cards);
//		Collections.
//		Collections.sort(list);
		
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
