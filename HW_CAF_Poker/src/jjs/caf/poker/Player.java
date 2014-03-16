package jjs.caf.poker;

import java.util.ArrayList;
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
		return playersHand.getHighestCardCombinations().get(0);
	}
	public List<CardCombination> getHighestCardCombinations() {
		return playersHand.getHighestCardCombinations();
	}

	public Card getHighestCard() {
		return playersHand.getHighestCard();
	}

	public List<Card> getCards() {
		return playersHand.getCards();
	}

	public List<CardCombination> getBasicCardCombinationsWithRiver(ArrayList<Card> river) {
		return playersHand.getBasicCardCombinationsWithRiver(river);
	}
	
	List<CardCombination> getHighestCardCombinationsWithRiver(List<Card> river){
		return playersHand.getHighestCardCombinationsWithRiver(river);
	}

	public List<CardCombination> getHighestCardCombinationsWithRiver() {
		return playersHand.getHighestCardCombinationsWithRiver();
	}

	public String showHighestCardCombinations() {
		return name + "s cards:\n" + playersHand.getHighestCardCombinations();
	}
	
	public String showHighestCardCombinationsWithRiver() {
		return name + "s cards with river:\n" + getHighestCardCombinationsWithRiver();
	}

	public void act() {
		// TODO Implement the act logic
		
	}
}
