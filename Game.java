//Chris McCabe
//CS 110
//program that runs the "backend" of the set game 

import java.util.ArrayList;

public class Game
{
	//instance variables needed to run the game
	Deck gameDeck = new Deck();
	ArrayList<BoardSquare> selectedSquares = new ArrayList<>();
	Board gameBoard;
	
	/**
	 * Game() is the default constructor for the game class that creates a new deck for the game 
	 */
	public Game()
	{
		gameBoard=new Board(gameDeck);
	}
	
	/**
	 * Get the board object being used for the game
	 * @return board object in use
	 */
	public Board getBoard()
	{
		return gameBoard;
	}
	/**
	 * The outOfCards() method determines whether or not there are any cards left in the deck
	 * using the deck method .isEmpty()
	 * @return boolean value 
	 */
	public boolean outOfCards()
	{
		if (gameDeck.isEmpty())
			return true;
		return false;
	}
	
	
	/**
	 * the addToSelected class adds a boardsquare at the given coordinates to 
	 * the arraylist selectedSquares and sets the boardsquares selected value to true
	 * @param r row value of the boardsquare
	 * @param c column value of the boardsquare
	 */
	public void addToSelected(int r, int c)
	{
		
		
		selectedSquares.add(gameBoard.getBoardSquare(r, c));
		gameBoard.getBoardSquare(r, c).setSelected(true);
//		if (r<=gameBoard.getRows()&&c<=gameBoard.getCols())
//		{
//		}
//		else {
//			System.out.println("Please select a coordinates that exist on the board");
//		}
	}
	
	/**
	 * numSelected() returns the number of boardsquares currently selected
	 * @return int value for number of boardsquares 
	 */
	public int numSelected()
	{
		return selectedSquares.size();
	}
	
	/**
	 * the testSelected() method determines if the three cards selected in the arraylist
	 * are a set using the card method .isSet
	 * @return
	 */
	public boolean testSelected()
	{
		boolean isSet = Card.isSet(selectedSquares.get(0).getCard(),selectedSquares.get(1).getCard(),selectedSquares.get(2).getCard());
		for(int i = 0; i<3;i++) {
			//this.removeSelected(selectedSquares.get(i).getRow(),selectedSquares.get(i).getCol());
			if(isSet)
			{
				gameBoard.replaceCard(gameDeck.getTopCard(),selectedSquares.get(i).getRow(),selectedSquares.get(i).getCol());	
			}
			selectedSquares.get(i).setSelected(false);
		}
		
		selectedSquares.remove(2);
		selectedSquares.remove(1);
		selectedSquares.remove(0);

		System.out.println(selectedSquares);
		return isSet;
	}
	
	/**
	 * the removeSelected method removes the boardsquare at the given coordinates 
	 * from the arraylist selectedSquares and sets the selected value of the boardsquare to false
	 * @param row value of the boardsquare
	 * @param col value of the boardsquare
	 */
	public void removeSelected(int row,int col)
	{
		selectedSquares.remove(selectedSquares.indexOf(gameBoard.getBoardSquare(row, col)));
		gameBoard.getBoardSquare(row, col).setSelected(false);
	}
	
	/**
	 * the add3() method adds three cards to the board at the end of the row
	 */
	public void add3()
	{
		gameBoard.add3(gameDeck);
	}
	
	/**
	 * getSelected returns the boardsquares in the arraylist selected squares
	 * using the boardsquare overridden toString method
	 * @return String values of boardsquares that are selected
	 */
	public ArrayList<BoardSquare> getSelected()
	{
		return selectedSquares;
	}
	
	/**
	 * returns the gameboard as a string
	 * @return String value of the board
	 */
	@Override
	public String toString()
	{
		return gameBoard.toString();
	}
	
	/**
	 * returns the deck being used
	 * @return deck object
	 */
	public Deck getDeck()
	{
		return gameDeck;
	}
	
	
}