package jjs.caf.poker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jjs.caf.poker.exceptions.PlayerAlreadyExistsException;

public abstract class PokerTable {
	protected ArrayList<Player> players = new ArrayList<Player>();
	protected ArrayList<Card> deck = new ArrayList<Card>();
	protected ArrayList<Card> river = new ArrayList<Card>();
	
	protected Random rand = new Random();
	// TODO: Implement and use the following variables correctly!
	protected long pot;
	protected long minimalBet;
	protected long smallBlind;
	protected long bigBlind;

	/**
	 * Add a player with the given name. If the name already exists, an
	 * unchecked <code>PlayerAlreadyExistsException</code> exception will be
	 * thrown.
	 * 
	 * @param name
	 *            The name of the new player.
	 */
	public void addPlayer(String name) {
		if (findPlayer(name) == null) {
			players.add(new Player(name));
		} else {
			throw new PlayerAlreadyExistsException("The player with the name "
					+ name + " already exists. Please choose a different name!");
		}
	}

	/**
	 * The player with the given name will be removed from the game.
	 * 
	 * @param name
	 *            The name of the player to remove from the game.
	 */
	public void removePlayer(String name) {
		Player player = findPlayer(name);
		players.remove(player);
	}

	/**
	 * This method returns the <code>Player</code> object with the given name.
	 * 
	 * @param name
	 *            The name to search for as a <code>String</code>.
	 * @return The found <code>Player</code> object or <code>null</code> if no
	 *         player with the given name was found.
	 */
	protected Player findPlayer(String name) {
		for (Player player : players) {
			if (player.getName().equals(name)) {
				return player;
			}
		}
		return null;
	}

	public void runGame() {

		// Determine the names of the Dealer, Small and Big Blind
		// TODO: Charge the corresponding amount of chips for the positions!
		System.out.println("DEALER:\t\t" + getDealerName());
		System.out.println("SMALL BLIND:\t" + getSmallBlindName());
		System.out.println("BIG BLIND:\t" + getBigBlindName());

		// Run the first round
		initRound();
		
		// Play the normal rounds
		runNormalRounds();
		
		// Finish the game (compare the cards and determine the winner/ the winners)
		List<Player> winners = determineWinners();
		
		//TODO Remove the test output
		System.out.println();
				
		if(winners.size() == 1){
			System.out.println("The winner is " + winners.get(0).getName() + " with:");
			System.out.println(winners.get(0).getHighestCardCombinationsWithRiver());
		} else if (winners.size() > 1) {
			System.out.println("The winners are ");
			for(Player winner : winners) {
				System.out.println(winner.getName() + " with:");
				System.out.println(winner.getHighestCardCombinationsWithRiver());
			}
		}
	}

	
	/**
	 * This 
	 */
	abstract void runNormalRounds();
	
	abstract List<Player> determineWinners();
	
	/**
	 * The first round (normally a special one) should be implemented here.
	 */
	abstract void initRound();

	/**
	 * Play the next round.
	 */
	abstract void round();
	
	/**
	 * This method randomly draws a card from the deck and removes it from it.
	 * 
	 * @return The drawn card from the deck.
	 */
	public Card drawCard() {
		int random = rand.nextInt(deck.size());

		Card card = deck.get(random);

		// Remove the card from the deck
		deck.remove(random);
		return card;
	}

	public void createDeck() {
		for (CardColorEnum cardColor : CardColorEnum.values()) {
			for (CardValueEnum cardValue : CardValueEnum.values()) {
				deck.add(new Card(cardColor, cardValue));
			}
		}
	}

	public String showRiver(){
		return river.toString();
	}
	protected ArrayList<Card> getRiver() {
		return river;
	}

	protected void setRiver(ArrayList<Card> river) {
		this.river = river;
	}

	public long getPot() {
		return pot;
	}

	public void setPot(long pot) {
		this.pot = pot;
	}

	public long getMinimalBet() {
		return minimalBet;
	}

	public void setMinimalBet(long minimalBet) {
		this.minimalBet = minimalBet;
	}

	public String getDealerName() {
		return players.get(0).getName();
	}

	public String getSmallBlindName() {
		return players.get(1).getName();
	}

	public String getBigBlindName() {
		return players.get(2).getName();
	}
}