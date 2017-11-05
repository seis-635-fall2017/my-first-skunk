import java.util.ArrayList;

public class SkunkController
{
	// public SkunkUI skunkUI;
	public UI ui;
	public int numberOfPlayers;
	// public String[] playerNames;
	public ArrayList<Player> players;
	public Kitty kitty;

	public Player activePlayer;
	public int activePlayerIndex;

	public boolean wantsToQuit;
	public boolean oneMoreRoll;

	public Dice skunkDice;

	public SkunkController(SkunkUI ui)
	{
		// this.UI = ui;
		this.ui = ui; // hide behind the interface UI

		// this.playerNames = new String[20];
		this.players = new ArrayList<Player>();
		this.skunkDice = new Dice();
		this.wantsToQuit = false;
		this.oneMoreRoll = false;
		this.kitty = new Kitty(0);
	}

	public void addPlayer(String playerName)
	{
		this.players.add(new Player(playerName, ui, kitty, 50));
		this.numberOfPlayers++;

	}

	// public boolean run()
	public boolean startNewGame()
	{

		// initializePlayers();

		activePlayer = getFirstActivePlayer();

		ui.println("Starting game...\n");

		playUntil100GameScore();

		playLastSeries();

		Player winner = announceWinner();

		updateWinnerScore(winner);

		ui.println("\nFinal scoreboard for this round:");
		displayScoreboard(ui, kitty);

		return true;
	}

	// private void initializePlayers()
	// {
	// String numberPlayersString = ui.promptReadAndReturn("How many players?");
	// this.numberOfPlayers = Integer.parseInt(numberPlayersString);
	//
	// for (int playerNumber = 0; playerNumber < numberOfPlayers;
	// playerNumber++)
	// {
	// ui.print("Enter name of player " + (playerNumber + 1) + ": ");
	// playerNames[playerNumber] = StdIn.readLine();
	// this.players.add(new Player(ui, kitty, 50));
	// }
	// }

	private void updateWinnerScore(Player winner)
	{
		winner.setNumberChips(winner.getNumberChips() + kitty.getNumberOfChips());
		ui.println("\nGame winner earns " + kitty + ", finishing with " + winner.getNumberChips());
	}

	private Player announceWinner()
	{
		Player winner = players.get(0);
		int winnerScore = winner.getGameScore();

		// for (int player = 0; player < numberOfPlayers; player++)
		for (Player player : players)
		{
			// Player nextPlayer = players.get(player);
			ui.println("Final game score for " + player.getName() + " is " + player.getGameScore() + ".");
			if (player.getGameScore() > winnerScore)
			{
				winner = player;
				winnerScore = player.getGameScore();
			}
		}

		ui.println("Game winner is " + winner.getName() + " with score of " + winner.getGameScore());

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

			displayScoreboard(ui, kitty);

			activePlayer = advanceToNextActivePlayer();
			ui.println("Turn passes right to: " + activePlayer.getName());

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

		for (int count = 0; count < numberOfPlayers - 1; count++)
		{
			// ui.println("Last round for player " + activePlayer.getName() +
			// "...");
			// activePlayer.setTurnScore(0);
			//
			// String wantsToRollStr = ui.promptReadAndReturn("Roll? [true or
			// false]");
			// boolean wantsToRoll = Boolean.parseBoolean(wantsToRollStr);
			//
			// while (wantsToRoll)
			// {
			// skunkDice.roll();
			// ui.println("Roll is " + skunkDice.toString() + "\n");
			//
			// if (skunkDice.isTwoSkunks())
			// {
			// ui.println("Two Skunks! You lose the turn, the turn score, plus
			// pay 4 chips to the kitty");
			// kitty.addChips(4);
			// activePlayer.setNumberChips(activePlayer.getNumberChips() - 4);
			// activePlayer.setTurnScore(0);
			// wantsToRoll = false;
			// break;
			// }
			// else if (skunkDice.isSkunkDeuce())
			// {
			// ui.println("Skunks and Deuce! You lose the turn, the turn score,
			// plus pay 2 chips to the kitty");
			// kitty.addChips(2);
			// activePlayer.setNumberChips(activePlayer.getNumberChips() - 2);
			// activePlayer.setTurnScore(0);
			// wantsToRoll = false;
			//
			// }
			// else if (skunkDice.isSkunkOnly())
			// {
			// ui.println("One Skunk! You lose the turn, the turn core, plus pay
			// 1 chip to the kitty");
			// kitty.addChips(1);
			// activePlayer.setNumberChips(activePlayer.getNumberChips() - 1);
			// activePlayer.setTurnScore(0);
			// activePlayer.setGameScore(0);
			// wantsToRoll = false;
			// }
			// else
			// {
			// activePlayer.setTurnScore(activePlayer.getRollScore() +
			// skunkDice.getLastTotal());
			// ui.println("Roll of " + skunkDice.toString() + ", giving new turn
			// score of "
			// + activePlayer.getTurnScore());
			//
			// displayScoreboard(ui, kitty);
			//
			// wantsToRollStr = ui.promptReadAndReturn("Roll again? [true or
			// false]");
			// wantsToRoll = Boolean.parseBoolean(wantsToRollStr);
			// }
			//
			// }

			ui.println("Last turn for " + activePlayer.getName());

			activePlayer.takeTurn();

			activePlayer.reportTurnResult();

			// if (activePlayer.getGameScore() >= 100)
			// gameNotOver = false;

			// activePlayer.setTurnScore(getRollScore() +
			// skunkDice.getLastTotal());

			// ui.println("Last roll of " + skunkDice.toString() + ", giving
			// final round score of "
			// + activePlayer.getRollScore());

			ui.println("Final game score for " + activePlayer.getName() + ": " + activePlayer.getGameScore());
			displayScoreboard(ui, kitty);

			activePlayer = advanceToNextActivePlayer();

			ui.println("Turn passes right to: " + activePlayer.getName());
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

	private void displayScoreboard(UI ui, Kitty kitty)
	{
		ui.println("Scoreboard: ");
		ui.println("----------------------------------------------");
		ui.println("Kitty has " + kitty + " chips");
		ui.println("player\tturn\tgame\tchips");
		ui.println("----------------------------------------------");

		// for (int i = 0; i < numberOfPlayers; i++)
		for (Player player : players)
		{
			ui.println(player.getName() + "\t" + player.getTurnScore() + "\t" + player.getGameScore() + "\t"
					+ player.getNumberChips());
		}
		ui.println("----------------------------------------------");

	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

}
