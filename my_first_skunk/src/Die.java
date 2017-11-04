
public class Die	
{
	public int lastRoll;
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

	public void setLastRoll(int toSet)
	{
		this.lastRoll = toSet;
		
	}
	public void roll()
	{
		this.setLastRoll(1+(int)(Math.random()*6));
		
	}
	public int getLastRoll()
	{
		// TODO Auto-generated method stub
		return this.lastRoll;
	}

}
