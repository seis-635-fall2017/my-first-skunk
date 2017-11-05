
public class Player
{
	public int rollScore;
	public int turnScore;
	public int gameScore;
	public int numberChips;
	public Turn latestTurn;
	private UI ui;
	private Kitty kitty;

	public Player(UI ui, Kitty kitty, int startingChipsPerPlayer)
	{
		this.rollScore = 0;
		this.turnScore = 0;
		this.gameScore = 0;
		this.numberChips = 50; // for now
		this.ui = ui;
		this.numberChips = startingChipsPerPlayer;
		this.kitty = kitty;
	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
	}

	public void addToRollScore(int lastTotal)
	{
		rollScore += lastTotal;
	}

	public void setRollScore(int newRollScore)
	{
		this.rollScore = newRollScore;
	}

	public int getRollScore()
	{
		return this.rollScore;
	}

	public int getNumberChips()
	{
		return this.numberChips;
	}

	public void setNumberChips(int newChips)
	{
		this.numberChips = newChips;
	}

	public void setTurnScore(int newScore)
	{
		this.turnScore = newScore;
	}

	public int getTurnScore()
	{
		return this.turnScore;
	}

	public String getName()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void setGameScore(int i)
	{
		this.gameScore = i;
	}

	public int getGameScore()
	{
		return this.gameScore;
	}

	public void takeTurn()
	{
		latestTurn = new Turn(this, kitty, ui);
		latestTurn.playOneTurn();
	}

	public void reportTurnResult()
	{
		ui.println("End of turn for " + this.getName());
		ui.println("Score for this turn is " + getTurnScore() + ", added to...");
		ui.println("Previous round score of " + getGameScore());
		setGameScore(getGameScore() + getTurnScore());
		ui.println("Giving new round score of " + getGameScore());
		ui.println("");

	}

	void reportTurnResult(SkunkDomain skunkDomain)
	{
	}

}
