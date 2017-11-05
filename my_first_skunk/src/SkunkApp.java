
public class SkunkApp
{
	public SkunkUI skunkUI;
	public SkunkController controller;
	public int numberOfPlayers;
	public String[] playerNames;

	public SkunkApp()
	{
		skunkUI = new SkunkUI();
		controller = new SkunkController(skunkUI);
		skunkUI.setDomain(controller);
		this.numberOfPlayers = 0;
		this.playerNames = new String[20];

	}

	/**
	 * Runs the app within an event loop
	 * 
	 * @return
	 */
	public boolean run()
	{
		return skunkUI.startNewGame();
	}

	public static void main(String[] args)
	{
		new SkunkApp().run();
	}

}
