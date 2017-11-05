import java.util.ArrayList;

public class SkunkDomain
{
	public SkunkUI skunkUI;
	public UI ui;
	public int numberOfPlayers;
	public String[] playerNames;
	public ArrayList<Player> players;
	public Kitty kitty;

	public Player activePlayer;
	public int activePlayerIndex;

	public boolean wantsToQuit;
	public boolean oneMoreRoll;

	public Dice skunkDice;

	public SkunkDomain(SkunkUI ui)
	{
		this.skunkUI = ui;
		this.ui = ui; // hide behind the interface UI

		this.playerNames = new String[20];
		this.players = new ArrayList<Player>();
		this.skunkDice = new Dice();
		this.wantsToQuit = false;
		this.oneMoreRoll = false;
	}

	public boolean run()
	{
		ui.println("Welcome to Skunk 0.47\n");

		kitty = new Kitty(0);

		initializePlayers();
		activePlayer = getFirstActivePlayer();

		ui.println("Starting game...\n");

		playUntil100GameScore();

		playLastSeries();

		int winner = announceWinner();

		updateWinnerScore(winner);

		ui.println("\nFinal scoreboard for this round:");
		displayScoreboard(ui, kitty, playerNames, numberOfPlayers);

		return true;
	}

	private void initializePlayers()
	{
		String numberPlayersString = skunkUI.promptReadAndReturn("How many players?");
		this.numberOfPlayers = Integer.parseInt(numberPlayersString);

		for (int playerNumber = 0; playerNumber < numberOfPlayers; playerNumber++)
		{
			ui.print("Enter name of player " + (playerNumber + 1) + ": ");
			playerNames[playerNumber] = StdIn.readLine();
			this.players.add(new Player(ui, kitty, 50));
		}
	}

	private void updateWinnerScore(int winner)
	{
		players.get(winner).setNumberChips(players.get(winner).getNumberChips() + kitty.getNumberOfChips());
		ui.println("\nRound winner earns " + kitty + ", finishing with " + players.get(winner).getNumberChips());
	}

	private int announceWinner()
	{
		int winner = 0;
		int winnerScore = 0;

		for (int player = 0; player < numberOfPlayers; player++)
		{
			Player nextPlayer = players.get(player);
			ui.println("Final round score for " + playerNames[player] + " is " + nextPlayer.getGameScore() + ".");
			if (nextPlayer.getGameScore() > winnerScore)
			{
				winner = player;
				winnerScore = nextPlayer.getGameScore();
			}
		}

		ui.println("Round winner is " + playerNames[winner] + " with score of " + players.get(winner).getGameScore());

		return winner;
	}

	private void playUntil100GameScore()
	{
		boolean gameNotOver = true;

		while (gameNotOver)
		{
			activePlayer.takeTurn();

			activePlayer.reportTurnResult();

			if (activePlayer.getGameScore() >= 100)
				gameNotOver = false;

			displayScoreboard(ui, kitty, playerNames, numberOfPlayers);

			activePlayer = advanceToNextActivePlayer();

		}
	}

	// private void takeTurn()
	// {
	//
	// Turn currentTurn = new Turn(activePlayer, kitty, ui);
	//
	// currentTurn.playOneTurn();
	// }

	private void playLastSeries()
	{
		// last round: everyone but last activePlayer gets another shot

		ui.println("Last turn for all...");

		for (int i = activePlayerIndex, count = 0; count < numberOfPlayers - 1; i = (i++) % numberOfPlayers, count++)
		{
			ui.println("Last round for player " + playerNames[activePlayerIndex] + "...");
			activePlayer.setTurnScore(0);

			String wantsToRollStr = ui.promptReadAndReturn("Roll? [true or false]");
			boolean wantsToRoll = Boolean.parseBoolean(wantsToRollStr);

			while (wantsToRoll)
			{
				skunkDice.roll();
				ui.println("Roll is " + skunkDice.toString() + "\n");

				if (skunkDice.isTwoSkunks())
				{
					ui.println("Two Skunks! You lose the turn, the turn score, plus pay 4 chips to the kitty");
					kitty.addChips(4);
					activePlayer.setNumberChips(activePlayer.getNumberChips() - 4);
					activePlayer.setTurnScore(0);
					wantsToRoll = false;
					break;
				}
				else if (skunkDice.isSkunkDeuce())
				{
					ui.println("Skunks and Deuce! You lose the turn, the turn score, plus pay 2 chips to the kitty");
					kitty.addChips(2);
					activePlayer.setNumberChips(activePlayer.getNumberChips() - 2);
					activePlayer.setTurnScore(0);
					wantsToRoll = false;

				}
				else if (skunkDice.isSkunkOnly())
				{
					ui.println("One Skunk! You lose the turn, the turn core, plus pay 1 chip to the kitty");
					kitty.addChips(1);
					activePlayer.setNumberChips(activePlayer.getNumberChips() - 1);
					activePlayer.setTurnScore(0);
					activePlayer.setGameScore(0);
					wantsToRoll = false;
				}
				else
				{
					activePlayer.setTurnScore(activePlayer.getRollScore() + skunkDice.getLastTotal());
					ui.println("Roll of " + skunkDice.toString() + ", giving new turn score of "
							+ activePlayer.getTurnScore());

					displayScoreboard(ui, kitty, playerNames, numberOfPlayers);

					wantsToRollStr = ui.promptReadAndReturn("Roll again? [true or false]");
					wantsToRoll = Boolean.parseBoolean(wantsToRollStr);
				}

			}

			activePlayer.setTurnScore(activePlayer.getRollScore() + skunkDice.getLastTotal());
			ui.println("Last roll of " + skunkDice.toString() + ", giving final round score of "
					+ activePlayer.getRollScore());

		}
	}

	private Player getFirstActivePlayer()
	{
		activePlayerIndex = 0;
		return players.get(activePlayerIndex);
	}

	private Player advanceToNextActivePlayer()
	{
		activePlayerIndex = (activePlayerIndex + 1) % numberOfPlayers;
		return players.get(activePlayerIndex);
	}

	private void displayScoreboard(UI ui, Kitty kitty, String[] playerNames, int numberOfPlayers)
	{
		ui.println("Scoreboard: ");
		ui.println("Kitty has " + kitty);
		ui.println("player name -- turn score -- round score -- chips");
		ui.println("-----------------------");

		for (int i = 0; i < numberOfPlayers; i++)
		{
			ui.println(playerNames[i] + " -- " + players.get(i).turnScore + " -- " + players.get(i).gameScore + " -- "
					+ players.get(i).getNumberChips());
		}
		ui.println("-----------------------");

		ui.println("Turn passes to right...");
	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

}
