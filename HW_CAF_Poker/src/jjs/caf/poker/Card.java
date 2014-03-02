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
		return cardToCompareTo.getCardValue().compareTo(this.getCardValue());
	}
	
	public String toString(){
		return getCardValue().toString() + " of " + getCardColor();
	}
}
