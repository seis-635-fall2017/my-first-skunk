
public class Kitty
{
	private int numberOfChips;

	public Kitty(int initChips)
	{
		this.numberOfChips = initChips;
	}

	public int getNumberOfChips()
	{
		return numberOfChips;
	}

	public void removeChips(int numberOfChips)
	{
		this.numberOfChips -= numberOfChips;
	}

	public void addChips(int numberOfChips)
	{
		this.numberOfChips += numberOfChips;
	}

	public String toString()
	{
		return "" + getNumberOfChips();
	}

}
