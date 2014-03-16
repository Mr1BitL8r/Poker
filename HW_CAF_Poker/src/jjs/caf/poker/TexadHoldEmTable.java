package jjs.caf.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TODO Implement the tie and chips division for complex cases (all-in by a non-chipleader with two players still raising)
public class TexadHoldEmTable extends PokerTable {

	@Override
	void initRound() {
		// TODO: Handle the rounds correctly
		// Texas Hold'em Poker for now, so give every player two cards
		for (int i = 0; i < 2; i++) {
			for (Player player : players) {
				player.addCard(drawCard());
			}
		}
	}

	@Override
	void round() {
		// Add a card to the river
		river.add(drawCard());
		// Show the new river
		System.out.println("River:\t" + showRiver());

		// Offer each player time to act (raise, check, fold)
		for (Player player : players) {
			// TODO: Let the player act
			player.act();
		}
	}

	@Override
	void runNormalRounds() {
		// Draw the river and give players time to act
		for (int i = 0; i < 5; i++) {
			round();
		}
	}

	@Override
	List<Player> determineWinners() {
		// TODO Implement the winner stuff and distribute the pot...
		List<Player> winners = new ArrayList<Player>();
		// Is there a tie?
//		boolean tie = false;
		
		// Get the first player who did not fold
		int firstPlayerWhoDidNotFold = 0;
		for (int i = 0; i < players.size(); i++) {
			if (!players.get(i).isFold()) {
				firstPlayerWhoDidNotFold = i;
				break;
			}
		}
		// Set the winner temporarily to the first player who is still in the
		// game
		winners.add(players.get(firstPlayerWhoDidNotFold));
		
		// Temporarily store the winners card combinations
		List<CardCombination> winnersCardCombinations = winners.get(0).getHighestCardCombinationsWithRiver(getRiver());
		
		//TODO Remove the test output
		System.out.println();
		System.out.println(winners.get(0).showHighestCardCombinationsWithRiver());
		
		// Compare the players hands to determine a or more winners
		for (int i = firstPlayerWhoDidNotFold + 1; i < players.size(); i++) {
			// Is the player still active in that game (so he did not fold)?
			if (!players.get(i).isFold()) {
				// Set the new/old winners card combinations to the ones computed before
				winnersCardCombinations = winners.get(0).getHighestCardCombinationsWithRiver();
				
				List<CardCombination> currentPlayersCardCombinations = players
						.get(i).getHighestCardCombinationsWithRiver(getRiver());
				
				//TODO Remove the test output
				System.out.println(players.get(i).showHighestCardCombinationsWithRiver());

				CardCombinationsComparator cardCombinationsComparator = new CardCombinationsComparator();
				int compareCardCombinationsResult = cardCombinationsComparator.compare(winnersCardCombinations, currentPlayersCardCombinations);
				
				switch(compareCardCombinationsResult){
				case -1:
					// Is the old winners hand smaller than the current players
					// hand?
					// Somebody won against the current winner and so also
					// against the other possible winners
					winners.clear();
					// Add the new winner to the newly emptied list
					winners.add(players.get(i));
					break;
				case 0: // There was a tie
//					tie = true;
					// Add the player to the winners list
					winners.add(players.get(i));
					break;
				case 1:
					// The winner won again
					// Do nothing
					break;
				}
			}
		}
		return winners;
	}
}