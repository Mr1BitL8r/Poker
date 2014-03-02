package jjs.caf.poker;

import org.junit.Before;
import org.junit.Test;

public class PokerTest {
	private Player player1, player2;
	private Poker poker;
	@Before
	public void setup() {
		player1 = new Player("Alice");
		player2 = new Player("Bob");
		
		poker = new Poker();
		// Create a normal deck with 52 cards
		poker.createDeck();
	}
	@Test
	public void t01_AliceHandSort() {
		// Draw 5 cards
		player1.addCard(poker.drawCard());
		player1.addCard(poker.drawCard());
		player1.addCard(poker.drawCard());
		player1.addCard(poker.drawCard());
		player1.addCard(poker.drawCard());
		
		// Sort the hand and show the result
		player1.sortHand();
		System.out.println(player1.showHand());
		
		// Show the highest card
		System.out.println("Highest card: " + player1.getHighestCard());
	}
}
