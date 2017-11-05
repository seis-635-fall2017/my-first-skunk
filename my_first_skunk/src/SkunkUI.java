
public class SkunkUI implements UI
{

	public SkunkController controller;

	public void setDomain(SkunkController skunkController)
	{
		this.controller = skunkController;

	}

	public boolean startNewGame()
	{
		println("Welcome to Skunk 0.47\n");

		String numberPlayersString = promptReadAndReturn("How many players?");
		int numberOfPlayers = Integer.parseInt(numberPlayersString);

		for (int playerNumber = 1; playerNumber <= numberOfPlayers; playerNumber++)
		{
			print("Enter name of player " + playerNumber + ": ");
			String playerName = StdIn.readLine();
			// this.players.add(new Player(ui, kitty, 50));
			controller.addPlayer(playerName);
		}

		return controller.startNewGame();

	}

	public String promptReadAndReturn(String question)
	{
		StdOut.print(question + " => ");
		String result = StdIn.readLine();
		return result;
	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

	public void print(String toPrint)
	{
		StdOut.print(toPrint);

	}

	public void println(String toPrint)
	{
		StdOut.println(toPrint);

	}

}
