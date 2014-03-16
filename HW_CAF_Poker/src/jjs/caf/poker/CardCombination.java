package jjs.caf.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardCombination implements Comparable<CardCombination> {
	private List<Card> cards = new ArrayList<Card>();
	private CardCombinationEnum cardCombinationEnum;

	public CardCombination(ArrayList<Card> cards) {
		CardCombination tempCardCombination = CardCombination
				.getHighestCardCombinations(cards).get(0);
		this.cardCombinationEnum = tempCardCombination.getCardCombinationEnum();
		this.cards = cards;
	}

	/**
	 * This constructor is private because it normally is only needed for
	 * special cases, when a deck has more than the usual 52 cards so that a
	 * flush can be combined with other combinations like e.g. a ONE PAIR.
	 * 
	 * @param cardCombinationEnum
	 */
	private CardCombination(CardCombinationEnum cardCombinationEnum,
			ArrayList<Card> cards) {
		this.cardCombinationEnum = cardCombinationEnum;
		this.cards = cards;
	}

	/**
	 * Check the players hand cards for all poker combinations and return it.
	 * The found card combination is removed from the given card-list.
	 * 
	 * @param cardsToSearchForCardCombinations
	 *            The cards to search for a special card combination (will be
	 *            removed from the cards-list!).
	 * @return the highest card combinations as a <code>List</code>
	 *         object.
	 */
	public static ArrayList<CardCombination> getBasicCardCombinations(
			ArrayList<Card> cardsToSearchForCardCombinations) {
		// Make sure that the list 
		// Store a list of the temporary hand cards so that the given list can
		// be manipulated
		ArrayList<Card> tempHandCards = (ArrayList<Card>) (cardsToSearchForCardCombinations
				.clone());
		// Will store the card combinations including their cards
		ArrayList<CardCombination> cardCombinationList = new ArrayList<CardCombination>();

		// Check the hand cards for a flush
		boolean isFlush = isFlush(tempHandCards);
		// Check the hand cards for a flush
		boolean isStraight = isStraight(tempHandCards);

		// Add the card combination to the list depending on the card
		// combination
		if (isFlush && isStraight) {
			cardCombinationList.add(new CardCombination(
					CardCombinationEnum.STRAIGHT_FLUSH, tempHandCards));
		} else if (isFlush && !isStraight) {
			cardCombinationList.add(new CardCombination(
					CardCombinationEnum.FLUSH, tempHandCards));
		} else if (!isFlush && isStraight){
			cardCombinationList.add(new CardCombination(
					CardCombinationEnum.STRAIGHT, tempHandCards));
		}

		if (!isStraight) {
			// Check the deck for highest cards, pairs more of a kind (NOT Full
			// house, Flush or so)
			// and remove already added cards from the temporary deck
			while (tempHandCards.size() > 0) {
				CardCombination cardCombination = getSameCardsOrHighestCard(tempHandCards);
				// TODO TWO PAIR will not be registered right now!
				// Add the card combination to the list
				cardCombinationList.add(cardCombination);
			}
		}
		sortCardCombinationsDescending(cardCombinationList);
		
		return cardCombinationList;
	}

	/**
	 * Sort the given list in a descending order.
	 * 
	 * @param cardCombinations The list to sort in a descending order.
	 */
	public static void sortCardCombinationsDescending(
			List<CardCombination> cardCombinations) {
		// Sort the list descending
		Collections.sort(cardCombinations);
		Collections.reverse(cardCombinations);
	}

	// TODO Exchange that strange code with a sorting algorithm for a
	// List<CardCombination> object. ^^
	static public List<CardCombination> getHighestCardCombinations(
			ArrayList<Card> cardsToSearch) {
		// Stores the ordered card combinations including their cards
		ArrayList<CardCombination> cardCombinationList = getBasicCardCombinations(cardsToSearch);
		sortCardCombinationsDescending(cardCombinationList);
		// Use the cloned list, so that the other list can be modified
		ArrayList<CardCombination> cardCombiListClone = (ArrayList<CardCombination>) cardCombinationList.clone();
		
		// For temporarily storing the first ONE PAIR (for the case if there is a TWO PAIR...)
		CardCombination firstOnePair = null;

		// For temporarily storing the first THREE OF A KIND (for the case if there is a FULL HOUSE...)
		CardCombination firstThreeOfAKind = null;		
		
		for (CardCombination cardCombination : cardCombiListClone) {
			// Check for higher combinations
			switch (cardCombination.getCardCombinationEnum()) {
			case THREE_OF_A_KIND:
				// Temporarily store the first THREE_OF_A_KIND
				firstThreeOfAKind = cardCombination;
				break;
			case ONE_PAIR:
				if (firstOnePair != null) {
					// Build the new card list for the TWO PAIR
					ArrayList<Card> tempCards = new ArrayList<Card>();
					tempCards.addAll(firstOnePair.getCards());
					tempCards.addAll(cardCombination.getCards());
					// Create a new TWO PAIR card combination with the cards
					cardCombinationList.add(new CardCombination(CardCombinationEnum.TWO_PAIR, tempCards));
					
					// Remove the first ONE PAIR from the card combination list
					cardCombinationList.remove(firstOnePair);
					// and also the current ONE_PAIR
					cardCombinationList.remove(cardCombination);
				} else {
					// Temporarily store the first ONE_PAIR
					firstOnePair = cardCombination;
					
					// Check if there is a FULL_HOUSE
					if(firstThreeOfAKind != null){
						// Build the new card list for the FULL_HOUSE
						ArrayList<Card> tempCards = new ArrayList<Card>();
						tempCards.addAll(firstThreeOfAKind.getCards());
						tempCards.addAll(cardCombination.getCards());
						// Create a new FULL_HOUSE card combination with the cards
						cardCombinationList.add(new CardCombination(CardCombinationEnum.FULL_HOUSE, tempCards));
						
						// Remove the first THREE_OF_A_KIND from the card combination list
						cardCombinationList.remove(firstThreeOfAKind);
						// and also the current ONE_PAIR
						cardCombinationList.remove(cardCombination);
					}
				}
				break;
			default:
			}
		}
		sortCardCombinationsDescending(cardCombinationList);
		return cardCombinationList;
	}

	/**
	 * The method returns the highest card or first one pair, three of a kind or
	 * four of a kind
	 * <code>CardCombination<(code> object of a given hand. The found combination will be returned
	 * and also those cards will be removed from the given list.
	 * 
	 * @param tempHandCards
	 *            the hand cards to analyze and remove combinations from.
	 * @return The found <code>CardCombination<(code> object for the cards with
	 *         the same card value or just the highest card.
	 */
	public static CardCombination getSameCardsOrHighestCard(
			ArrayList<Card> tempHandCards) {
		CardCombinationEnum cardCombinationEnum = null;
		// Sort the given hand cards just to be sure that the order is correct
		sortHand(tempHandCards);

		// Make a copy of the list, so that the given list can be manipulated
		ArrayList<Card> clonedHandCards = (ArrayList<Card>) tempHandCards
				.clone();
		ArrayList<Card> sameCards = new ArrayList<Card>();

		// Avoid null pointers
		if (clonedHandCards != null && clonedHandCards.size() >= 1) {
			Card currentCard = clonedHandCards.get(0);

			sameCards.add(currentCard);
			// Remove the element from the original list, because it is handled
			// now
			tempHandCards.remove(currentCard);

			// Compare the values of the next cards until they differ
			for (int j = 1; j < clonedHandCards.size(); j++) {
				Card nextCard = clonedHandCards.get(j);
				if (nextCard.getCardValue() == currentCard.getCardValue()) {
					// Add the card to the same card pool
					sameCards.add(clonedHandCards.get(j));
					// Remove the just analyzed and handled card from the
					// original list
					tempHandCards.remove(nextCard);
				}
			}
		}
		switch (sameCards.size()) {
		case 1:
			cardCombinationEnum = CardCombinationEnum.HIGHEST_CARD;
			break;
		case 2:
			cardCombinationEnum = CardCombinationEnum.ONE_PAIR;
			break;
		case 3:
			cardCombinationEnum = CardCombinationEnum.THREE_OF_A_KIND;
			break;
		case 4:
			cardCombinationEnum = CardCombinationEnum.FOUR_OF_A_KIND;
			break;
		}
		return new CardCombination(cardCombinationEnum, sameCards);
	}

	/**
	 * The method checks a given hand for a flush.
	 * 
	 * @param list
	 *            the hand cards to analyze for a flush.
	 * @return <code>true</code> if the cards were a flush or <code>false</code>
	 * 
	 */
	public static boolean isFlush(List<Card> list) {
		boolean isFlush = true;

		// Avoid null pointers
		if (list != null && list.size() > 1) {
			Card currentCard = list.get(0);

			// Compare the values of the next cards until they differ
			for (int j = 1; j < list.size(); j++) {
				Card nextCard = list.get(j);
				// Compare the cards for the same color
				if (nextCard.getCardColor() != currentCard.getCardColor()) {
					isFlush = false;
					break;
				}
			}
		} else {
			isFlush = false;
		}
		return isFlush;
	}

	/**
	 * The method checks a given hand for a flush.
	 * 
	 * @param cards
	 *            the hand cards to analyze for a flush.
	 * @return <code>true</code> if the cards were a flush or <code>false</code>
	 *         .
	 */
	public static boolean isStraight(List<Card> cards) {
		boolean isStraight = true;
		int startValue = 0; // If the first card is an ACE and the last one a
							// TWO there might be a "small" straight (ACE, TWO,
							// THREE, ...), so than start with the second
							// highest card at index 1

		// Avoid null pointers
		if (cards != null && cards.size() >= 5) {
			// If the first card is an ACE and the last one a TWO there might be
			// a "small" straight (ACE, TWO, THREE, ...)
			if (cards.get(0).getCardValue() == CardValueEnum.ACE
					&& cards.get(cards.size() - 1).getCardValue() == CardValueEnum.TWO) {
				startValue = 1;
			}

			// Compare the values of the next cards until they differ, if there
			// is a straight starting with an ACE as a ONE, this will also be
			// detected
			for (int j = startValue; j < cards.size() - 1; j++) {
				Card currentCard = cards.get(j);
				Card nextCard = cards.get(j + 1);

				// Compare the cards values for a descending straight, the
				// ordinal values must be one apart for this
				if (currentCard.getCardValue().ordinal() != nextCard
						.getCardValue().ordinal() + 1) {
					isStraight = false;
					break;
				}
			}
		} else {
			isStraight = false;
		}
		return isStraight;
	}

	/**
	 * Sort the given cards by the given order of the <code>CardValue</code>
	 * -enum.
	 * 
	 * @param cards
	 *            The cards to sort.
	 */
	static void sortHand(List<Card> cards) {
		Collections.sort(cards);
	}

	protected List<Card> getCards() {
		return cards;
	}

	protected CardCombinationEnum getCardCombinationEnum() {
		return cardCombinationEnum;
	}

	public int compareTo(CardCombination currentPlayersCardCombination) {
		int cardCombinationEnumCompare = this.getCardCombinationEnum()
				.compareTo(
						currentPlayersCardCombination.getCardCombinationEnum());
		if (cardCombinationEnumCompare == 0) {
			// The card combinations have the same level --> Compare the cards
			//TODO Check if this works out for every possible hand (when there are e.g. two THREE OF A KIND with the same cards possible (enhanced deck!)
			int cardValueEnumCompare = this.getCards().get(0)
					.compareTo(currentPlayersCardCombination.getCards().get(0));
			// Just return the result of the value comparison
			return cardValueEnumCompare;
		} else {
			// The card combination was higher or smaller than the given one
			return cardCombinationEnumCompare;
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((cardCombinationEnum == null) ? 0 : cardCombinationEnum
						.hashCode());
		result = prime * result + ((cards == null) ? 0 : cards.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CardCombination other = (CardCombination) obj;
		if (cardCombinationEnum != other.cardCombinationEnum)
			return false;
		if (cards == null) {
			if (other.cards != null)
				return false;
		} else if (!cards.equals(other.cards))
			return false;
		return true;
	}

	public String toString(){
		return cardCombinationEnum.toString() + " and cards " + cards.toString();
	}
}
