package jjs.caf.poker;

public class Card implements Comparable<Card> {
	private CardColor cardColor;
	private CardValue cardValue;
	
	Card(CardColor cardColor, CardValue cardValue){
		this.cardColor = cardColor;
		this.cardValue = cardValue;
	}

	public CardColor getCardColor() {
		return cardColor;
	}

	public CardValue getCardValue() {
		return cardValue;
	}

	public int compareTo(Card cardToCompareTo) {
		// Just redirect the comparison to the enum because it implicitly knows the order
		// The sorting order is descending, so the highest card will be at position 0
		return cardToCompareTo.getCardValue().compareTo(this.getCardValue());
	}
	
	public String toString(){
		return getCardValue().toString() + " of " + getCardColor();
	}
	
	public boolean equals(Card card) {
		if(this.cardColor == card.getCardColor() && this.cardValue == card.getCardValue()) {
			return true;
		}
		return false;
	}
}
