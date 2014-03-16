package jjs.caf.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents a player with a name and amount of chips.
 * 
 */
//TODO: Use and complete the chip functionality in a poker game, and did fold stuff
public class Player {
	private String name;
	private long chips;
	private boolean fold;
	
	PlayersHand playersHand = new PlayersHand();

	Player(String name) {
		this.name = name;
	}
	
	Player(String name, long chips){
		this(name);
		this.chips = chips;
	}
		
	void addCard(Card card) {
		playersHand.addCard(card);
	}
	
	String showHand() {
		return getName() + "'s Hand: " + playersHand.getCards();
	}

	public String getName() {
		return name;
	}

	protected long getChips() {
		return chips;
	}

	protected void setChips(long chips) {
		this.chips = chips;
	}

	public boolean isFold() {
		return fold;
	}

	public void setFold(boolean fold) {
		this.fold = fold;
	}

	CardCombination getHighestCardCombination(){
		return playersHand.getHighestCardCombination();
	}
	public List<CardCombination> getCardCombinations() {
		return playersHand.getCardCombinations();
	}

	public Card getHighestCard() {
		return playersHand.getHighestCard();
	}

	public List<Card> getCards() {
		return playersHand.getCards();
	}

	public ArrayList<CardCombination> getCardCombinationWithRiver(ArrayList<Card> river) {
		return playersHand.getCardCombinationsWithRiver(river);
	}
	
	CardCombination getHighestCardCombinationsWithRiver(List<Card> river){
		return playersHand.getHighestCardCombinationsWithRiver(river);
	}
}
