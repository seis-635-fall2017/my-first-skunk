
public class Dice
{
	public Die die1;
	public Die die2;

	public int lastTotal;

	public Dice()
	{
		this.die1 = new Die();
		this.die2 = new Die();

		roll();
	}

	public int getLastTotal()
	{
		return this.lastTotal;
	}

	public void roll()
	{
		die1.roll();
		die2.roll();

		this.lastTotal = die1.getLastRoll() + die2.getLastRoll();
	}

	public String toString()
	{
		return this.getLastTotal() + " == " + die1.getLastRoll() + " + " + die2.getLastRoll();
	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

	public boolean isSkunkOnly()
	{
		if ((die1.getLastRoll() == 1 && die2.getLastRoll() > 2) || (die1.getLastRoll() > 2) && die2.getLastRoll() == 1)
			return true;
		else
			return false;
	}

	public boolean isSkunkDeuce()
	{
		if ((die1.getLastRoll() == 1 && die2.getLastRoll() == 2)
				|| (die1.getLastRoll() == 2 && die2.getLastRoll() == 1))
			return true;
		else
			return false;
	}

	public boolean isTwoSkunks()
	{
		return die1.getLastRoll() == 1 && die2.getLastRoll() == 1;
	}

}
