package jjs.caf.poker;

import java.util.List;

/**
 * This Comparator is able to compare card combination lists.
 * 
 */
public class CardCombinationsComparator implements java.util.Comparator<List<CardCombination>> {

	@Override
	public int compare(List<CardCombination> cardCombinations, List<CardCombination> cardCombinationsToCompare) {
		// Make sure that the lists are sorted correctly
		CardCombination.sortCardCombinationsDescending(cardCombinations);
		CardCombination.sortCardCombinationsDescending(cardCombinationsToCompare);
		
		for(int i = 0;i < cardCombinations.size() && i < cardCombinationsToCompare.size();i++){
			if (cardCombinations.get(i).compareTo(cardCombinationsToCompare.get(i)) < 0) {
				// The first card combination list is less valuable than the other one
				return -1;
			} else if (cardCombinations.get(i).compareTo(cardCombinationsToCompare.get(i)) > 0) {
				// The first card combination list is more valuable than the other one
				return 1;
			}
		}
		// The card combinations are equally valuable
		return 0;
	}

}
