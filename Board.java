//Chris McCabe
//CS 110 Final: Set Game: Board Class

import java.util.ArrayList;
//finish return statements
public class Board
{
	//create new array list of arraylist of boardsquares
	ArrayList<ArrayList<BoardSquare>> board= new ArrayList<ArrayList<BoardSquare>>();
	
	
	/**
	 * Constructor that initializes the board of 3 rows 4 columns
	 * @param d deck object that is used to deal cards from onto the board.
	 */
	public Board(Deck d)
	{
		for(int r=0 ; r<3 ; r++)
		{
			board.add(new ArrayList<BoardSquare>());
			for (int c=0 ; c<4 ; c++)
			{
				board.get(r).add(new BoardSquare(d.getTopCard(),r,c));
			}
		}
	}
	
	
	/**
	 * Replaces a card at a given index with the given card
	 * @param c Card object replacing the existing card
	 * @param row row index
	 * @param col column index
	 */
	public void replaceCard(Card c, int row, int col)
	{
		board.get(row).set(col, new BoardSquare(c,row,col));
	}
	
	
	/**
	 * Returns the BoardSquare at the given row and col values
	 * @param row row val
	 * @param col col val
	 * @return BoardSquare 
	 */
	public BoardSquare getBoardSquare(int row,int col)
	{
		return board.get(row).get(col);
	}
	
	
	/**
	 * Adds three cards to the board 
	 * @param d given deck to take cards from
	 */
	public void add3(Deck d)
	{
		for (int i=0 ; i<=2 ; i++)
		{
			board.get(i).add(new BoardSquare(d.getTopCard(),i,0));
		}
	}
	
	
	/**
	 * Get the card object of the BoardSquare at the given row and column values
	 * @param row row val
	 * @param col column val
	 * @return Card Card object
	 */
	public Card getCard(int row, int col) 
	{
		return board.get(row).get(col).getCard();
	}
	
	
	/**
	 * Tells how many rows are on the board
	 * @return int value of how many rows the board has
	 */
	public int getRows()
	{
		return board.size();
	}
	
	/**
	 * Tells how many cols are on the board
	 * @return int value of how many cols the board has
	 */
	public int getCols()
	{
		int biggest=0;
		for(int i=0; i<=board.size()-1;i++)
		{
			int cols=board.get(i).size();
			if (cols>biggest)
				biggest=cols;
		}
		return biggest;
	}
	
	
	/**
	 * The overridden toString method that returns the cards in a board format
	 * @return string of cards positioned on the board
	 */
	@Override
	public String toString()
	{
		String boardString = "";
		for(int r=0;r<board.size();r++)
		{
			for(int c=0;c<board.get(r).size();c++)
			{
				boardString += board.get(r).get(c).getCard().toString()+"        ";
			}
			boardString +="\n";
		}
		return boardString;
	}
	
	public static void main(String[]args)
	{
		Deck d1 = new Deck();
		d1.shuffle();
		Board b = new Board(d1);
		
		System.out.println(b);
		System.out.println("");
		System.out.println(b.getCard(0,0));
		System.out.println("");
		System.out.println(b);
		
		if (Card.isSet(b.getCard(0,0), b.getCard(0,1), b.getCard(0,2)))
			System.out.println("set");
		else
			System.out.println("not a set");
	}
}