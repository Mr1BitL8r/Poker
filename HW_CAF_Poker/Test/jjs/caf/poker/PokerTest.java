package jjs.caf.poker;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
	public void t00_AliceHandSort() {
		// Draw 5 cards
		player1.addCard(poker.drawCard());
		player1.addCard(poker.drawCard());
		player1.addCard(poker.drawCard());
		player1.addCard(poker.drawCard());
		player1.addCard(poker.drawCard());

		// Show the result
		System.out.println(player1.showHand());

		// Show the highest card
		System.out.println("Highest card: " + player1.getHighestCard());

	}
		
	@Test
	public void t02_onePairTest() {
		// Build a one pair hand
		player1.addCard(new Card(CardColor.SPADES, CardValue.ACE));
		player1.addCard(new Card(CardColor.HEARTS, CardValue.ACE));
		player1.addCard(new Card(CardColor.SPADES, CardValue.QUEEN));
		player1.addCard(new Card(CardColor.SPADES, CardValue.KNAVE));
		player1.addCard(new Card(CardColor.SPADES, CardValue.TEN));

		// Check for the combination
		Assert.assertEquals(player1.getCardCombination(),
				CardCombination.ONE_PAIR);
	}

	@Test
	public void t03_twoPairsTest() {
		// Build a two pair hand
		player1.addCard(new Card(CardColor.SPADES, CardValue.ACE));
		player1.addCard(new Card(CardColor.HEARTS, CardValue.ACE));
		player1.addCard(new Card(CardColor.SPADES, CardValue.QUEEN));
		player1.addCard(new Card(CardColor.HEARTS, CardValue.QUEEN));
		player1.addCard(new Card(CardColor.SPADES, CardValue.TEN));

		// Check for the combination
		Assert.assertEquals(player1.getCardCombination(),
				CardCombination.TWO_PAIR);
	}

	@Test
	public void t04_threeOfAKindTest() {
		// Build a three of a kind hand
		player1.addCard(new Card(CardColor.SPADES, CardValue.ACE));
		player1.addCard(new Card(CardColor.HEARTS, CardValue.ACE));
		player1.addCard(new Card(CardColor.CLUBS, CardValue.ACE));
		player1.addCard(new Card(CardColor.HEARTS, CardValue.QUEEN));
		player1.addCard(new Card(CardColor.SPADES, CardValue.TEN));

		// Check for the combination
		Assert.assertEquals(player1.getCardCombination(),
				CardCombination.THREE_OF_A_KIND);
	}
	@Test
	public void t05_straightHandCardsAceTest() {
		// Build a straight
		player1.addCard(new Card(CardColor.SPADES, CardValue.ACE));
		player1.addCard(new Card(CardColor.SPADES, CardValue.TWO));
		player1.addCard(new Card(CardColor.SPADES, CardValue.THREE));
		player1.addCard(new Card(CardColor.CLUBS, CardValue.FOUR));
		player1.addCard(new Card(CardColor.SPADES, CardValue.FIVE));

		// Check for a straight card hand
		Assert.assertTrue(Player.isStraight(player1.getCards()));

		// Test the card combination
		Assert.assertEquals(player1.getCardCombination(),
				CardCombination.STRAIGHT);
	}

	@Test
	public void t05_straightHandCardsTest() {
		// Build a straight
		player1.addCard(new Card(CardColor.SPADES, CardValue.ACE));
		player1.addCard(new Card(CardColor.SPADES, CardValue.KING));
		player1.addCard(new Card(CardColor.SPADES, CardValue.QUEEN));
		player1.addCard(new Card(CardColor.CLUBS, CardValue.TEN));
		player1.addCard(new Card(CardColor.SPADES, CardValue.KNAVE));

		// Check for a straight card hand
		Assert.assertTrue(Player.isStraight(player1.getCards()));

		// Test the card combination
		Assert.assertEquals(player1.getCardCombination(),
				CardCombination.STRAIGHT);
	}
	@Test
	public void t06_flushTest() {
		// Build a flush
		player1.addCard(new Card(CardColor.SPADES, CardValue.ACE));
		player1.addCard(new Card(CardColor.SPADES, CardValue.KING));
		player1.addCard(new Card(CardColor.SPADES, CardValue.QUEEN));
		player1.addCard(new Card(CardColor.SPADES, CardValue.KNAVE));
		player1.addCard(new Card(CardColor.SPADES, CardValue.NINE));

		// Check for a flush
		Assert.assertTrue(Player.isFlush(player1.getCards()));

		// Test the card combination
		Assert.assertEquals(player1.getCardCombination(), CardCombination.FLUSH);
	}
	
	@Test
	public void t07_fullHouseTest() {
		// Build a four of a kind hand
		player1.addCard(new Card(CardColor.SPADES, CardValue.ACE));
		player1.addCard(new Card(CardColor.HEARTS, CardValue.ACE));
		player1.addCard(new Card(CardColor.CLUBS, CardValue.ACE));
		player1.addCard(new Card(CardColor.DIAMONDS, CardValue.KING));
		player1.addCard(new Card(CardColor.SPADES, CardValue.KING));

		// Check for the combination
		Assert.assertEquals(player1.getCardCombination(),
				CardCombination.FULL_HOUSE);
	}
	
	@Test
	public void t08_fourOfAKindTest() {
		// Build a four of a kind hand
		player1.addCard(new Card(CardColor.SPADES, CardValue.ACE));
		player1.addCard(new Card(CardColor.HEARTS, CardValue.ACE));
		player1.addCard(new Card(CardColor.CLUBS, CardValue.ACE));
		player1.addCard(new Card(CardColor.DIAMONDS, CardValue.ACE));
		player1.addCard(new Card(CardColor.SPADES, CardValue.TEN));

		// Check for the combination
		Assert.assertEquals(player1.getCardCombination(),
				CardCombination.FOUR_OF_A_KIND);
	}

		@Test
	public void t09_straightflushTest() {
		// Build a straight flush
		player1.addCard(new Card(CardColor.SPADES, CardValue.ACE));
		player1.addCard(new Card(CardColor.SPADES, CardValue.KING));
		player1.addCard(new Card(CardColor.SPADES, CardValue.QUEEN));
		player1.addCard(new Card(CardColor.SPADES, CardValue.KNAVE));
		player1.addCard(new Card(CardColor.SPADES, CardValue.TEN));

		// Check for a straight
		Assert.assertTrue(Player.isStraight(player1.getCards()));
		// Check for a flush
		Assert.assertTrue(Player.isFlush(player1.getCards()));

		// Test the card combination
		Assert.assertEquals(player1.getCardCombination(),
				CardCombination.STRAIGHT_FLUSH);
	}
}
