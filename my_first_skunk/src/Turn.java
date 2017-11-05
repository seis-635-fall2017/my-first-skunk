
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
	}

	public void playOneTurn()
	{
		String wantsToRollStr = ui.promptReadAndReturn("Roll? [true or false]");
		boolean wantsToRoll = Boolean.parseBoolean(wantsToRollStr);
		setTurnScore(0);

		while (wantsToRoll)
		{
			activePlayer.setRollScore(0);
			skunkDice.roll();
			if (skunkDice.isTwoSkunks())
			{
				ui.println("Two Skunks! You lose the turn, the round score, plus pay 4 chips to the kitty");
				kitty.addChips(4);
				activePlayer.setNumberChips(activePlayer.getNumberChips() - 4);
				activePlayer.setTurnScore(0);
				activePlayer.setGameScore(0);
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
				break;
			}
			else if (skunkDice.isSkunkOnly())
			{
				ui.println("One Skunk! You lose the turn, the turn score, plus pay 1 chip to the kitty");
				kitty.addChips(1);
				activePlayer.setNumberChips(activePlayer.getNumberChips() - 1);
				activePlayer.setTurnScore(0);
				wantsToRoll = false;
				break;

			}

			activePlayer.setRollScore(skunkDice.getLastTotal());
			activePlayer.setTurnScore(activePlayer.getTurnScore() + skunkDice.getLastTotal());
			ui.println("Roll of " + skunkDice.toString() + ", gives new turn score of " + activePlayer.getTurnScore());

			wantsToRollStr = ui.promptReadAndReturn("Roll again? [true or false]");
			wantsToRoll = Boolean.parseBoolean(wantsToRollStr);

		}

	}

	private void setTurnScore(int i)
	{
		// TODO Auto-generated method stub

	}

}
