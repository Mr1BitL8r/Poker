package jjs.caf.poker;

/**
 * This enum represents the poker card combinations and is sorted ascending.
 * The internal values show the necessary card combinations.
 *
 */
public enum CardCombinationEnum {
		// NumberOfCards	OnePair		TwoPair	ThreeOfAKind	Straight	FourOfAKind	Flush
	HIGHEST_CARD	(1, 	false, 		false,	false, 			false, 		false,		false),
	ONE_PAIR		(2, 	true, 		false,	false, 			false, 		false,		false),
	TWO_PAIR		(4, 	false, 		true,	false, 			false, 		false,		false),
	THREE_OF_A_KIND	(3, 	false, 		false,	true, 			false, 		false,		false),
	STRAIGHT		(5, 	false, 		false,	false, 			true, 		false,		false),
	FLUSH			(5, 	false, 		false,	false, 			false, 		false,		true),
	FULL_HOUSE		(5, 	true, 		false,	true, 			false, 		false,		false),
	FOUR_OF_A_KIND	(4, 	false, 		false,	false, 			false, 		true,		false),
	STRAIGHT_FLUSH	(5, 	false, 		false,	false, 			true, 		false,		true);

	private final int numberOfCards;
	private final boolean isOnePair;
	private final boolean isTwoPair;
	private final boolean isThreeOfAKind;
	private final boolean isStraight;
	private final boolean isFourOfAKind;
	private final boolean isFlush;

	private CardCombinationEnum(int numberOfCards,
			boolean isOnePair,
			boolean isTwoPair,
			boolean isThreeOfAKind,
			boolean isStraight,
			boolean isFourOfAKind,
			boolean isFlush) {
		this.numberOfCards = numberOfCards;
		this.isOnePair 		= isOnePair;
		this.isTwoPair 		= isTwoPair;
		this.isThreeOfAKind = isThreeOfAKind;
		this.isStraight		= isStraight;
		this.isFourOfAKind	= isFourOfAKind;
		this.isFlush 		= isFlush;
	}

	public int getNumberOfCards() {
		return numberOfCards;
	}

	public boolean isOnePair() {
		return isOnePair;
	}

	public boolean isTwoPair() {
		return isTwoPair;
	}

	public boolean isThreeOfAKind() {
		return isThreeOfAKind;
	}

	public boolean isStraight() {
		return isStraight;
	}

	public boolean isFourOfAKind() {
		return isFourOfAKind;
	}

	public boolean isFlush() {
		return isFlush;
	}
}
