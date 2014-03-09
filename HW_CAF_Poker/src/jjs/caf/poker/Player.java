package jjs.caf.poker;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author
 * 
 */
public class Player {
	private ArrayList<Card> cards = new ArrayList<Card>();
	private String name;

	Player(String name) {
		this.name = name;
	}

	void addCard(Card card) {
		cards.add(card);
		// Make sure that the deck is sorted in descending order of the card
		// value
		sortHand();
	}

	String showHand() {
		return getName() + "'s Hand: " + cards.toString();
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
		// Get the highest card, which is the first entry
		Card highestCard = cards.get(0);
		return highestCard;
	}

	/**
	 * The method returns the first one pair, three of a kind or four of a kind
	 * of a given hand. The found combination will be returned and also those
	 * cards will be removed from the given list.
	 * 
	 * @param tempHandCards
	 *            the hand cards to analyze and remove combinations from.
	 * @return The found cards with the same card value.
	 */
	static ArrayList<Card> getSameCards(ArrayList<Card> tempHandCards) {
		ArrayList<Card> clonedHandCards = (ArrayList<Card>) tempHandCards
				.clone();
		ArrayList<Card> sameCards = new ArrayList<Card>();

		if (clonedHandCards != null && clonedHandCards.size() > 0) {
			for (int i = 0; i < clonedHandCards.size(); i++) {
				CardValue currentCardValue = clonedHandCards.get(i)
						.getCardValue();
				// Remove the element from the original list, because it is
				// handled now
				tempHandCards.remove(i);

				// Get the next card for the first comparison
				CardValue nextCardValue = clonedHandCards.get(i + 1)
						.getCardValue();
				// Compare the values of the next cards until they differ
				for (int j = i + 1; j < clonedHandCards.size(); j++) {
					nextCardValue = clonedHandCards.get(j).getCardValue();
					if (nextCardValue == currentCardValue) {
						// Add the card to the same card pool
						sameCards.add(clonedHandCards.get(j));
						// Remove the just analyzed and handled card from the
						// original list
						tempHandCards.remove(j);
					} else { // There was no match
						if (sameCards.size() > 0) {
							// Add the current card to the same card list
							sameCards.add(clonedHandCards.get(i));

							// The list of same cards is complete, so return it
							return sameCards;
						} else { // Not even one pair was found, so try the next
									// cards
							break;
						}
					}
				}
			}
		}
		return sameCards;
	}

	/**
	 * The method returns the highest card or first one pair, three of a kind or
	 * four of a kind of a given hand. The found combination will be returned
	 * and also those cards will be removed from the given list.
	 * 
	 * @param tempHandCards
	 *            the hand cards to analyze and remove combinations from.
	 * @return The found cards with the same card value or just the highest
	 *         card.
	 */
	static ArrayList<Card> getSameCardsOrHighestCard(
			ArrayList<Card> tempHandCards) {
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
		return sameCards;
	}

	/**
	 * The method checks a given hand for a flush.
	 * 
	 * @param tempHandCards
	 *            the hand cards to analyze for a flush.
	 * @return <code>true</code> if the cards were a flush or <code>false</code>
	 *         .
	 */
	static boolean isStraight(ArrayList<Card> tempHandCards) {
		boolean isStraight = true;
		int startValue = 0; // If the first card is an ACE and the last one a
							// TWO there might be a "small" straight (ACE, TWO,
							// THREE, ...), so than start with the second
							// highest card at index 1

		// Avoid null pointers
		if (tempHandCards != null && tempHandCards.size() > 1) {
			// If the first card is an ACE and the last one a TWO there might be a
			// "small" straight (ACE, TWO, THREE, ...)
			if (tempHandCards.get(0).getCardValue() == CardValue.ACE
					&& tempHandCards.get(tempHandCards.size() - 1).getCardValue() == CardValue.TWO) {
				startValue = 1;
			}
			
			// Compare the values of the next cards until they differ, if there is a straight starting with an ACE as a ONE, this will also be detected
			for (int j = startValue; j < tempHandCards.size() - 1; j++) {
				Card currentCard = tempHandCards.get(j);
				Card nextCard = tempHandCards.get(j + 1);

				// Compare the cards values for a descending straight, the
				// ordinal values must be one apart for this
				if (currentCard.getCardValue().ordinal() != nextCard
						.getCardValue().ordinal() + 1) {
					isStraight = false;
					break;
				}
			}

			// Check if the first card is an ACE --> there might be a
			// "small"
			// straight (ACE, TWO, THREE, ...)

		} else {
			isStraight = false;
		}
		return isStraight;
	}

	/**
	 * The method checks a given hand for a flush.
	 * 
	 * @param tempHandCards
	 *            the hand cards to analyze for a flush.
	 * @return <code>true</code> if the cards were a flush or <code>false</code>
	 *         .
	 */
	static boolean isFlush(ArrayList<Card> tempHandCards) {
		boolean isFlush = true;

		// Avoid null pointers
		if (tempHandCards != null && tempHandCards.size() > 1) {
			Card currentCard = tempHandCards.get(0);

			// Compare the values of the next cards until they differ
			for (int j = 1; j < tempHandCards.size(); j++) {
				Card nextCard = tempHandCards.get(j);
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
	 * Check the players hand cards for all poker combinations and return it.
	 * 
	 * @return the highest card combination as a <code>CardCombination</code>
	 *         object.
	 */
	CardCombination getCardCombination() {
		CardCombination highestCardCombination = null;
		ArrayList<ArrayList<Card>> cardCombiLists = new ArrayList<ArrayList<Card>>();
		ArrayList<Card> tempHandCards = (ArrayList<Card>) cards.clone();

		boolean isOnePair = false;
		boolean isTwoPair = false;
		boolean isThreeOfAKind = false;
		boolean isStraight = false;
		boolean isFourOfAKind = false;
		boolean isFlush = false;

		// Check the hand cards for a flush
		isFlush = isFlush(tempHandCards);

		// Check the hand cards for a flush
		isStraight = isStraight(tempHandCards);

		if (!isStraight) {
			// Check the deck for highest cards, pairs more of a kind (NOT Full
			// house, Flush or so)
			// and remove already added cards from the temporary deck
			while (tempHandCards.size() > 0) {
				ArrayList<Card> sameCardList = getSameCardsOrHighestCard(tempHandCards);
				cardCombiLists.add(sameCardList);
				switch (sameCardList.size()) {
				case 2:
					if (isOnePair) {
						isTwoPair = true;
						isOnePair = false;
					} else {
						isOnePair = true;
					}
					break;
				case 3:
					isThreeOfAKind = true;
					break;
				case 4:
					isFourOfAKind = true;
					break;
				}
			}
		}

		// Check the found basic combinations with the greater combinations
		for (CardCombination cardCombination : CardCombination.values()) {
			if ((cardCombination.isOnePair() == isOnePair)
					&& (cardCombination.isTwoPair() == isTwoPair)
					&& (cardCombination.isStraight() == isStraight)
					&& (cardCombination.isThreeOfAKind() == isThreeOfAKind)
					&& (cardCombination.isFourOfAKind() == isFourOfAKind)
					&& cardCombination.isFlush() == isFlush) {
				highestCardCombination = cardCombination;
			}
		}
		return highestCardCombination;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}
}
