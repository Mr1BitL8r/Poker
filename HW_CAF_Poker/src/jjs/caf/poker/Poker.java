package jjs.caf.poker;

import java.util.ArrayList;
import java.util.Random;

public class Poker implements PokerInterface {
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Card> deck = new ArrayList<Card>();
	private Random rand = new Random();
	
	public void addPlayer(String name) {
		players.add(new Player(name));
	}
	
	public void round(){
		
	}

	/**
	 * This method randomly draws a card from the deck and removes it from it.
	 * @return The drawn card from the deck.
	 */
	public Card drawCard() {
		int random = rand.nextInt(deck.size());
		
		Card card = deck.get(random);
		
		// Remove the card from the deck
		deck.remove(random);
		return card;
	}
	
	public void createDeck(){
		for (CardColor cardColor : CardColor.values()) {
			for (CardValue cardValue : CardValue.values()) {
				deck.add(new Card(cardColor, cardValue));
			}
		}
	}
}
