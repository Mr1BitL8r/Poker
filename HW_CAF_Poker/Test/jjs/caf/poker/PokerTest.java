package jjs.caf.poker;

import jjs.caf.poker.exceptions.*;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PokerTest {
	private Player player1, player2, player3;
	private PokerTable pokerTable;
	private CardCombinationsComparator cardCombinationsComparator = new CardCombinationsComparator();

	@Before
	public void setup() {
		pokerTable = new TexadHoldEmTable();
		// Create a normal deck with 52 cards
		pokerTable.createDeck();
		
		// Set some players
		player1 = new Player("Alice");
		player2 = new Player("Bob");
		player3 = new Player("Charlie");
		// And add them to the game
		pokerTable.addPlayer(player1.getName());
		pokerTable.addPlayer(player2.getName());
		pokerTable.addPlayer(player3.getName());
	}

	@Test
	public void t00_AliceHandSort() {
		// Draw 5 cards
		player1.addCard(pokerTable.drawCard());
		player1.addCard(pokerTable.drawCard());
		player1.addCard(pokerTable.drawCard());
		player1.addCard(pokerTable.drawCard());
		player1.addCard(pokerTable.drawCard());

		// Show the result
		System.out.println(player1.showHand());

		// Show the highest card
		System.out.println("Highest card: " + player1.getHighestCard());

	}
		
	@Test
	public void t10_onePairTest() {
		// Build a one pair hand
		player1.addCard(new Card(CardColorEnum.SPADES, CardValueEnum.ACE));
		player1.addCard(new Card(CardColorEnum.HEARTS, CardValueEnum.ACE));
		player1.addCard(new Card(CardColorEnum.SPADES, CardValueEnum.QUEEN));
		player1.addCard(new Card(CardColorEnum.SPADES, CardValueEnum.KNAVE));
		player1.addCard(new Card(CardColorEnum.SPADES, CardValueEnum.TEN));

		// Check for the combination
		Assert.assertEquals(CardCombinationEnum.ONE_PAIR, player1.getHighestCardCombination().getCardCombinationEnum());
	}

	@Test
	public void t15_twoPairsTest() {
		// Build a two pair hand
		player1.addCard(new Card(CardColorEnum.SPADES, CardValueEnum.ACE));
		player1.addCard(new Card(CardColorEnum.HEARTS, CardValueEnum.ACE));
		player1.addCard(new Card(CardColorEnum.SPADES, CardValueEnum.QUEEN));
		player1.addCard(new Card(CardColorEnum.HEARTS, CardValueEnum.QUEEN));
		player1.addCard(new Card(CardColorEnum.SPADES, CardValueEnum.TEN));

		// Check for the combination
		Assert.assertEquals(CardCombinationEnum.TWO_PAIR, player1.getHighestCardCombination().getCardCombinationEnum());
	}

	@Test
	public void t20_threeOfAKindTest() {
		// Build a three of a kind hand
		player1.addCard(new Card(CardColorEnum.SPADES, CardValueEnum.ACE));
		player1.addCard(new Card(CardColorEnum.HEARTS, CardValueEnum.ACE));
		player1.addCard(new Card(CardColorEnum.CLUBS, CardValueEnum.ACE));
		player1.addCard(new Card(CardColorEnum.HEARTS, CardValueEnum.QUEEN));
		player1.addCard(new Card(CardColorEnum.SPADES, CardValueEnum.TEN));

		// Check for the combination
		Assert.assertEquals(CardCombinationEnum.THREE_OF_A_KIND, player1.getHighestCardCombination().getCardCombinationEnum());
	}
	@Test
	public void t25_straightHandCardsAceTest() {
		// Build a straight
		player1.addCard(new Card(CardColorEnum.SPADES, CardValueEnum.ACE));
		player1.addCard(new Card(CardColorEnum.SPADES, CardValueEnum.TWO));
		player1.addCard(new Card(CardColorEnum.SPADES, CardValueEnum.THREE));
		player1.addCard(new Card(CardColorEnum.CLUBS, CardValueEnum.FOUR));
		player1.addCard(new Card(CardColorEnum.SPADES, CardValueEnum.FIVE));

		// Check for a straight card hand
		Assert.assertTrue(CardCombination.isStraight(player1.getCards()));

		// Test the card combination
		Assert.assertEquals(CardCombinationEnum.STRAIGHT, player1.getHighestCardCombination().getCardCombinationEnum());
	}

	@Test
	public void t30_straightHandCardsTest() {
		// Build a straight
		player1.addCard(new Card(CardColorEnum.SPADES, CardValueEnum.ACE));
		player1.addCard(new Card(CardColorEnum.SPADES, CardValueEnum.KING));
		player1.addCard(new Card(CardColorEnum.SPADES, CardValueEnum.QUEEN));
		player1.addCard(new Card(CardColorEnum.CLUBS, CardValueEnum.TEN));
		player1.addCard(new Card(CardColorEnum.SPADES, CardValueEnum.KNAVE));

		// Check for a straight card hand
		Assert.assertTrue(CardCombination.isStraight(player1.getCards()));

		// Test the card combination
		Assert.assertEquals(CardCombinationEnum.STRAIGHT, player1.getHighestCardCombination().getCardCombinationEnum());
	}
	@Test
	public void t35_flushTest() {
		// Build a flush
		player1.addCard(new Card(CardColorEnum.SPADES, CardValueEnum.ACE));
		player1.addCard(new Card(CardColorEnum.SPADES, CardValueEnum.KING));
		player1.addCard(new Card(CardColorEnum.SPADES, CardValueEnum.QUEEN));
		player1.addCard(new Card(CardColorEnum.SPADES, CardValueEnum.KNAVE));
		player1.addCard(new Card(CardColorEnum.SPADES, CardValueEnum.NINE));

		// Check for a flush
		Assert.assertTrue(CardCombination.isFlush(player1.getCards()));

		// Test the card combination
		Assert.assertEquals(CardCombinationEnum.FLUSH, player1.getHighestCardCombination().getCardCombinationEnum());
	}
	
	@Test
	public void t40_fullHouseTest() {
		// Build a four of a kind hand
		player1.addCard(new Card(CardColorEnum.SPADES, CardValueEnum.ACE));
		player1.addCard(new Card(CardColorEnum.HEARTS, CardValueEnum.ACE));
		player1.addCard(new Card(CardColorEnum.CLUBS, CardValueEnum.ACE));
		player1.addCard(new Card(CardColorEnum.DIAMONDS, CardValueEnum.KING));
		player1.addCard(new Card(CardColorEnum.SPADES, CardValueEnum.KING));

		// Check for the combination
		Assert.assertEquals(CardCombinationEnum.FULL_HOUSE, player1.getHighestCardCombination().getCardCombinationEnum());
	}
	
	@Test
	public void t45_fourOfAKindTest() {
		// Build a four of a kind hand
		player1.addCard(new Card(CardColorEnum.SPADES, CardValueEnum.ACE));
		player1.addCard(new Card(CardColorEnum.HEARTS, CardValueEnum.ACE));
		player1.addCard(new Card(CardColorEnum.CLUBS, CardValueEnum.ACE));
		player1.addCard(new Card(CardColorEnum.DIAMONDS, CardValueEnum.ACE));
		player1.addCard(new Card(CardColorEnum.SPADES, CardValueEnum.TEN));

		// Check for the combination
		Assert.assertEquals(CardCombinationEnum.FOUR_OF_A_KIND, player1.getHighestCardCombination().getCardCombinationEnum());
	}

		@Test
	public void t50_straightflushTest() {
		// Build a straight flush
		player1.addCard(new Card(CardColorEnum.SPADES, CardValueEnum.ACE));
		player1.addCard(new Card(CardColorEnum.SPADES, CardValueEnum.KING));
		player1.addCard(new Card(CardColorEnum.SPADES, CardValueEnum.QUEEN));
		player1.addCard(new Card(CardColorEnum.SPADES, CardValueEnum.KNAVE));
		player1.addCard(new Card(CardColorEnum.SPADES, CardValueEnum.TEN));

		// Check for a straight
		Assert.assertTrue(CardCombination.isStraight(player1.getCards()));
		// Check for a flush
		Assert.assertTrue(CardCombination.isFlush(player1.getCards()));

		// Test the card combination
		Assert.assertEquals(CardCombinationEnum.STRAIGHT_FLUSH, player1.getHighestCardCombination().getCardCombinationEnum());
	}
		
	@Test
	public void t61_pokerHandsNearlyTheSame(){
		// Build a four of a kind hand
		player1.addCard(new Card(CardColorEnum.HEARTS, CardValueEnum.FIVE));
		player1.addCard(new Card(CardColorEnum.SPADES, CardValueEnum.FIVE));
		player1.addCard(new Card(CardColorEnum.SPADES, CardValueEnum.ACE));
		player1.addCard(new Card(CardColorEnum.DIAMONDS, CardValueEnum.KING));
		player1.addCard(new Card(CardColorEnum.HEARTS, CardValueEnum.TEN));
		player1.addCard(new Card(CardColorEnum.CLUBS, CardValueEnum.FOUR));
		player1.addCard(new Card(CardColorEnum.HEARTS, CardValueEnum.TWO));
		
		player2.addCard(new Card(CardColorEnum.DIAMONDS, CardValueEnum.KING));
		player2.addCard(new Card(CardColorEnum.HEARTS, CardValueEnum.KING));
		player2.addCard(new Card(CardColorEnum.SPADES, CardValueEnum.ACE));
		player2.addCard(new Card(CardColorEnum.HEARTS, CardValueEnum.QUEEN));
		player2.addCard(new Card(CardColorEnum.HEARTS, CardValueEnum.TEN));
		player2.addCard(new Card(CardColorEnum.HEARTS, CardValueEnum.FIVE));
		player2.addCard(new Card(CardColorEnum.HEARTS, CardValueEnum.TWO));
		
		Assert.assertEquals(-1, cardCombinationsComparator.compare(player1.getHighestCardCombinations(), player2.getHighestCardCombinations()));		
	}
		
	@Test(expected=PlayerAlreadyExistsException.class)
	public void t60_pokerAddPlayerWhoAlreadyExists(){
		pokerTable.addPlayer(player1.getName());
	}
	
	@Test
	public void t65_pokerGame(){
		pokerTable.runGame();
	}
}

