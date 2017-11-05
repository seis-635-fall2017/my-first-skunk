
public class Turn
{
	private Player activePlayer;
	private int turnScore;
	private Dice skunkDice;
	private UI ui;
	private Kitty kitty;

	public Turn(Player activePlayer, Kitty kitty, UI ui)
	{
		this.activePlayer = activePlayer;
		this.turnScore = 0;
		skunkDice = new Dice();
		this.kitty = kitty;
		this.ui = ui;
	}

	public void playOneTurn()
	{
		// String wantsToRollStr = wantsToRollAgain();
		// boolean wantsToRoll = Boolean.parseBoolean(wantsToRollStr);
		// setTurnScore(0);

		while (wantsToRollAgain())
		{
			// activePlayer.setRollScore(0);
			skunkDice.roll();
			ui.println(skunkDice.toString());

			if (skunkDice.isTwoSkunks())
			{
				ui.println("Two Skunks! You lose the turn, the round score, plus pay 4 chips to the kitty");
				kitty.addChips(4);
				activePlayer.setNumberChips(activePlayer.getNumberChips() - 4);
				setTurnScore(0);
				// activePlayer.setTurnScore(0);
				activePlayer.setGameScore(0);
				// wantsToRoll = false;
				break;
			}
			else if (skunkDice.isSkunkDeuce())
			{
				ui.println("Skunks and Deuce! You lose the turn, the turn score, plus pay 2 chips to the kitty");
				kitty.addChips(2);
				activePlayer.setNumberChips(activePlayer.getNumberChips() - 2);
				setTurnScore(0);
				// activePlayer.setTurnScore(0);
				// wantsToRoll = false;
				break;
			}
			else if (skunkDice.isSkunkOnly())
			{
				ui.println("One Skunk! You lose the turn, the turn score, plus pay 1 chip to the kitty");
				kitty.addChips(1);
				activePlayer.setNumberChips(activePlayer.getNumberChips() - 1);
				setTurnScore(0);
				// activePlayer.setTurnScore(0);
				// wantsToRoll = false;
				break;

			}

			ui.print(" => Turn score is now " + getTurnScore() + " + " + skunkDice.getLastTotal());

			setTurnScore(getTurnScore() + skunkDice.getLastTotal());

			ui.println(" => " + getTurnScore());

			// activePlayer.setRollScore(skunkDice.getLastTotal());

			// wantsToRollStr = ui.promptReadAndReturn("Roll again? [true or
			// false]");
			// wantsToRoll = Boolean.parseBoolean(wantsToRollStr);

		}

	}

	public int getTurnScore()
	{
		return this.turnScore;
	}

	private boolean wantsToRollAgain()
	{
		String wantsToRollStr = ui.promptReadAndReturn("Roll? [true or false]");
		return wantsToRollStr.isEmpty() || Character.toUpperCase(wantsToRollStr.charAt(0)) == 'Y';
	}

	public void setTurnScore(int newTurnScore)
	{
		this.turnScore = newTurnScore;

	}

}
