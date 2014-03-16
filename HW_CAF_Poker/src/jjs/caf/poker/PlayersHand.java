package jjs.caf.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayersHand {
	private ArrayList<Card> cards = new ArrayList<Card>();
	private List<CardCombination> cardCombinations = new ArrayList<CardCombination>();
	private List<CardCombination> cardCombinationsWithRiver = new ArrayList<CardCombination>();

	void addCard(Card card) {
		cards.add(card);
		// Make sure that the deck is sorted in descending order of the card
		// value
		sortCartsDescending(cards);
	}

	String showHand() {
		return cards.toString();
	}

	Card getHighestCard() {
		// Get the highest card, which is the first entry
		Card highestCard = cards.get(0);
		return highestCard;
	}

	/**
	 * Sort the given hand cards by the given order of the
	 * <code>CardValue</code>-enum.
	 * 
	 * @param unsortedHandCards
	 *            The hand cards to sort.
	 */
	public static void sortCartsDescending(List<Card> unsortedHandCards) {
		Collections.sort(unsortedHandCards);
		Collections.reverse(unsortedHandCards);
	}


	/**
	 * Check the players hand cards for all poker combinations and return it. *
	 * The found card combination is removed from the given card-list.
	 * 
	 * @return the highest card combination as a <code>CardCombination</code>
	 *         object.
	 */
	List<CardCombination> getHighestCardCombinations() {
		cardCombinations = CardCombination.getHighestCardCombinations(cards);
		return cardCombinations;
	}

	/**
	 * Check the river cards + the players hand for all poker combinations and
	 * return it.
	 * 
	 * @return the highest card combination as a <code>CardCombination</code>
	 *         object.
	 */
	List<CardCombination> getBasicCardCombinationsWithRiver(List<Card> river) {
		// Add the river cards to a temporary cloned hand
		ArrayList<Card> riverAndHandCards = (ArrayList<Card>) cards.clone();
		riverAndHandCards.addAll(river);

		// Sort the temporary players hand cards including the river cards in descending order
		PlayersHand.sortCartsDescending(riverAndHandCards);

		// Get the highest card combination
		cardCombinationsWithRiver = CardCombination.getBasicCardCombinations(riverAndHandCards);
		
		return cardCombinationsWithRiver;
	}
	
	CardCombination getCardCombinationWithRiver(List<Card> river){
		return getBasicCardCombinationsWithRiver(river).get(0);
	}
	
	/**
	 * Check the river cards + the players hand for all poker combinations and
	 * return it.
	 * 
	 * @return the highest card combination as a <code>CardCombination</code>
	 *         object.
	 */
	CardCombination getHighestCardCombinationWithRiver(List<Card> river) {
		return getHighestCardCombinationsWithRiver(river).get(0);
	}
	
	/**
	 * Check the river cards + the players hand for all higher poker combinations and
	 * return them.
	 * 
	 * @return the highest card combinations as a <code>List</code>
	 *         object.
	 */
	List<CardCombination> getHighestCardCombinationsWithRiver(List<Card> river) {
		// Add the river cards to a temporary cloned hand
		ArrayList<Card> riverAndHandCards = (ArrayList<Card>) cards.clone();
		riverAndHandCards.addAll(river);

		// Sort the temporary players hand cards including the river cards
		PlayersHand.sortCartsDescending(riverAndHandCards);

		// Get the highest card combinations
		cardCombinationsWithRiver = CardCombination.getHighestCardCombinations(riverAndHandCards);
		
		return cardCombinationsWithRiver;
	}

	//TODO Maybe use a snapshot here...
	List<CardCombination> getHighestCardCombinationsWithRiver() {
		return cardCombinationsWithRiver;
	}
	
	List<Card> getCards() {
		return cards;
	}
}
