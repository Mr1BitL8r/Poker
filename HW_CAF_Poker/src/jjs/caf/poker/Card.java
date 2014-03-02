package jjs.caf.poker;

public class Card {
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
}
