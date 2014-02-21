/*
 
 Team Name: UtraCoders
 Team Members/GitHub Username: 
 Eliel Ruiz Rodriguez / codehunter11
 Elliam Espinosa / elliamespinosa
 
 Project02: Poker Memory
 Course: ICOM4015
 Professor: Dr. Bienvenido Velez
 Due Date: November 13, 2013
 
 */

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MemoryGame implements ActionListener {

	public static boolean DEBUG = true;
	private JFrame mainFrame;					// top level window
	private Container mainContentPane;			// frame that holds card field and turn counter
	private TurnsTakenCounterLabel turnCounterLabel;
	private ScoreCounter scoreCounter;
	private GameLevel difficulty;

	/**
	 * Make a JMenuItem, associate an action command and listener, add to menu
	 */
	private static void newMenuItem(String text, JMenu menu, ActionListener listener)
	{
		JMenuItem newItem = new JMenuItem(text);
		newItem.setActionCommand(text);
		newItem.addActionListener(listener);
		menu.add(newItem);
	}

	/**
	 * Default constructor loads card images, makes window
	 * @throws IOException 
	 */
	public MemoryGame () throws IOException
	{

		// Make toplevel window
		this.mainFrame = new JFrame("Poker Memory Ultra");
		this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mainFrame.setSize(900,700);
		this.mainFrame.setLocation(190, 0);
		this.mainFrame.setResizable(false);
		this.mainContentPane = this.mainFrame.getContentPane();
		this.mainContentPane.setLayout(new BoxLayout(this.mainContentPane, BoxLayout.PAGE_AXIS));

		// Menu bar
		JMenuBar menuBar = new JMenuBar();
		this.mainFrame.setJMenuBar(menuBar);

		// Game menu
		JMenu gameMenu = new JMenu("Memory");
		menuBar.add(gameMenu);
		newMenuItem("Exit", gameMenu, this);

		// Difficulty menu
		JMenu difficultyMenu = new JMenu("New Game");
		menuBar.add(difficultyMenu);
		newMenuItem("Equal Pair Level", difficultyMenu, this);
		newMenuItem("Same Rank Trio Level", difficultyMenu, this);
		newMenuItem("Two Pair And Third Wheel Level", difficultyMenu, this);
		newMenuItem("Steel Wheel Level", difficultyMenu, this);
		newMenuItem("Combo Level", difficultyMenu, this);


		// Help menu
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		newMenuItem("How To Play", helpMenu, this);
		newMenuItem("About", helpMenu, this);
		newMenuItem("Uncover All Cards", helpMenu, this);
		newMenuItem("Cover All Cards", helpMenu, this);
		

		//this.leaderBoard = new ScoreLeaderBoard("EasyMode");
	}


	/**
	 * Handles menu events.  Necessary for implementing ActionListener.
	 *
	 * @param e object with information about the event
	 */
	public void actionPerformed(ActionEvent e)
	{
		dprintln("actionPerformed " + e.getActionCommand());
		try {
			if(e.getActionCommand().equals("Equal Pair Level")) newGame("medium");
			else if(e.getActionCommand().equals("Same Rank Trio Level")) newGame("trio");
			else if(e.getActionCommand().equals("Two Pair And Third Wheel Level")) newGame("wheel");
			else if(e.getActionCommand().equals("Steel Wheel Level")) newGame("steel");
			else if(e.getActionCommand().equals("Combo Level")) newGame("combo");
			else if(e.getActionCommand().equals("How To Play")) showInstructions();
			else if(e.getActionCommand().equals("About")) showAbout();
			else if(e.getActionCommand().equals("Uncover All Cards")) uncoverAllCards();
			else if(e.getActionCommand().equals("Cover All Cards")) coverAllCards();
			else if(e.getActionCommand().equals("Exit")) System.exit(0);
		} catch (IOException e2) {
			e2.printStackTrace(); 
			throw new RuntimeException("IO ERROR");
		}
	}


	/**
	 * Prints debugging messages to the console
	 *
	 * @param message the string to print to the console
	 */
	static public void dprintln( String message )
	{
		if (DEBUG) System.out.println( message );
	}

	public JPanel showCardDeck()
	{
		// make the panel to hold all of the cards
		JPanel panel = new JPanel(new GridLayout(difficulty.getRowsPerGrid(),difficulty.getCardsPerRow()));

		// this set of cards must have their own manager
		this.difficulty.makeDeck();

		for(int i= 0; i<difficulty.getGrid().size();i++){
			panel.add(difficulty.getGrid().get(i));
		}
		
		panel.setOpaque(false);
		return panel;
	}

	/**
	 * Prepares a new game (first game or non-first game)
	 * @throws IOException 
	 */
	public void newGame(String difficultyMode) throws IOException
	{
		// reset the turn counter to zero
		this.turnCounterLabel = new TurnsTakenCounterLabel();
		this.scoreCounter = new ScoreCounter();

		// make a new card field with cards, and add it to the window

		if(difficultyMode.equalsIgnoreCase("medium")){
			this.difficulty = new EqualPairLevel(this.turnCounterLabel, this.scoreCounter, this.mainFrame);
		}

		else if(difficultyMode.equalsIgnoreCase("trio")){
			this.difficulty = new RankTrioLevel(this.turnCounterLabel, this.scoreCounter, this.mainFrame);
		}
		
		else if(difficultyMode.equalsIgnoreCase("wheel")){
			this.difficulty = new TwoPairsThirdWheelLevel(this.turnCounterLabel, this.scoreCounter, this.mainFrame);
		}
		
		else if(difficultyMode.equalsIgnoreCase("steel")){
			this.difficulty = new SteelWheelLevel(this.turnCounterLabel, this.scoreCounter, this.mainFrame);
		}
		
		else if(difficultyMode.equalsIgnoreCase("combo")){
			this.difficulty = new ComboLevel(this.turnCounterLabel, this.scoreCounter, this.mainFrame);
		}
	
		else {
			throw new RuntimeException("Illegal Game Level Detected");
		}

		this.turnCounterLabel.reset();
		this.scoreCounter.reset();

		// clear out the content pane (removes turn counter label and card field)
		this.mainContentPane.removeAll();
		
		BackgroundImage panel = new BackgroundImage("./images/background.png");
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add(showCardDeck());
		panel.add(this.turnCounterLabel);
		panel.add(this.scoreCounter);
		this.mainContentPane.add(panel);

		
		// show the window (in case this is the first game)
		this.mainFrame.setVisible(true);
	}


	/**
	 * Shows an instructional dialog box to the user
	 */
	private void showInstructions()
	{
		dprintln("MemoryGame.showInstructions()");
		String instructions = "How To Play\r\r\n\n";
		if (difficulty.getMode().equalsIgnoreCase("MediumMode")) {
			instructions += "EQUAL PAIR\r\n"
					+ "------------------------------------------------------------------\n"
					+ "The game consists of 8 pairs of cards.  At the start of the game,\r\n"
					+ "every card is face down.  The object is to find all the pairs and\r\n"
					+ "turn them face up.\r\n"
					+ "\r\n"
					+ "Click on two cards to turn them face up. If the cards are the \r\n"
					+ "same, then you have discovered a pair.  The pair will remain\r\n"
					+ "turned up.  If the cards are different, they will flip back\r\n"
					+ "over automatically after a short delay.  Continue flipping\r\n"
					+ "cards until you have discovered all of the pairs.  The game\r\n"
					+ "is won when all cards are face up.\n"
					+ "------------------------------------------------------------------\n";

		}

		else if (difficulty.getMode().equalsIgnoreCase("TrioMode")) {
			instructions += "SAME RANK TRIO\r\n"
					+ "------------------------------------------------------------------\n"

					+ "The game consists of a grid of distinct cards.  At the start of the game,\r\n"
					+ "every card is face down.  The object is to find all the trios \r\n"
					+ "of cards with the same rank and turn them face up.\r\n"
					+ "\r\n"
					+ "Click on three cards to turn them face up. If the cards have the \r\n"
					+ "same rank, then you have discovered a trio.  The trio will remain\r\n"
					+ "turned up.  If the cards are different, they will flip back\r\n"
					+ "over automatically after a short delay.  Continue flipping\r\n"
					+ "cards until you have discovered all of the trios.  The game\r\n"
					+ "is won when all cards are face up."
					+ "\n------------------------------------------------------------------";

		}
		
		else if (difficulty.getMode().equalsIgnoreCase("TwoTrioMode")) {
			instructions += "TWO PAIR AND THIRD WHEEL\r\n"
					+ "------------------------------------------------------------------\n"

					+ "The game consists of a grid of distinct cards.  At the start of the game,\r\n"
					+ "every card is face down.  A winning hand consists of two pairs of cards \r\n"
					+ "with the same rank or same suits and a 5th one with a suit or rank equal to\r\n"
					+ "one of the pairs.\r\n"
					+ "\r\n"
					+ "For example: (Spade 3, Spade 5, Spade A) and (Heart 6, Diamond 6) \r\n"
		
					+ "\n------------------------------------------------------------------";

		}
		
		else if (difficulty.getMode().equalsIgnoreCase("SteelWheelMode")) {
			instructions += "STEEL WHEEL\r\n"
					+ "------------------------------------------------------------------\n"

					+ "The game consists of a grid of distinct cards.  At the start of the game,\r\n"
					+ "every card is face down.  A winning hand consists of cards A-2-3-4-5 of \r\n"
					+ "the same distinct suit.\r\n"
					+ "\r\n"
		
					+ "\n------------------------------------------------------------------";

		}
		
		else if (difficulty.getMode().equalsIgnoreCase("ComboMode")) {
			instructions += "COMBO\r\n"
					+ "------------------------------------------------------------------\n"

					+ "The game consists of a grid of distinct cards.  At the start of the game,\r\n"
					+ "every card is face down.  There are tons of possibilities here. Uncover \r\n"
					+ "a poker hand and decide whether you stay with it or not. It's very easy.\r\n"
					+ "\r\n"
					+ "Try to find the best poker hand to have a better score.  \r\n"
		
					+ "\n------------------------------------------------------------------";

		}

		JOptionPane.showMessageDialog(this.mainFrame, instructions,
				"How To Play", JOptionPane.PLAIN_MESSAGE);
	}

	/**
	 * Shows an dialog box with information about the program
	 */
	private void showAbout()
	{
		dprintln("MemoryGame.showAbout()");
		final String ABOUTTEXT = "Game Customized at UPRM. Originally written by Mike Leonhard";

		JOptionPane.showMessageDialog(this.mainFrame, ABOUTTEXT
				, "About Memory Game", JOptionPane.PLAIN_MESSAGE);
	}

	protected void uncoverAllCards() {
		for (int i = 0; i < difficulty.totalUniqueCards; i++) {
			difficulty.getGrid().get(i).setIcon(difficulty.getGrid().get(i).getFaceIcon());
			
		}
		this.mainFrame.repaint();
	}

	protected void coverAllCards() {
		for (int i = 0; i < difficulty.totalUniqueCards; i++) {
			if (!difficulty.getGrid().get(i).isFaceUp()) // if card wasn't turned up by the cheat
				difficulty.getGrid().get(i).setIcon(difficulty.getGrid().get(i).getBackIcon());
		}
		this.mainFrame.repaint();

	}

}