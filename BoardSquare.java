//Chris McCabe
//CS 110 Final: Set Game: BoardSquare Class

public class BoardSquare
{
	//instance variables
	private int row;
	private int col;
	private Card c;
	private boolean selected=false;
	
	/**
	 * Constructor that sets Card row and col values equal to the values passed in
	 * @param c Card object 
	 * @param row integer value of row position on board
	 * @param col integer value of column position on board
	 */
	public BoardSquare(Card c,int row,int col)
	{
		this.c = c;
		this.row = row;
		this.col = col;
	}
	
	/**
	 * set the row value of the BoardSquare
	 * @param rowNum row int value
	 */
	public void setRow(int rowNum)
	{
		row = rowNum;
	}
	
	/**
	 * Get the row value of a BoardSquare
	 * @return int value of row 
	 */
	public int getRow()
	{
		return row;
	}
	
	/**
	 * sets the column value of the BoardSquare
	 * @param colNum int value of column
	 */
	public void setCol(int colNum)
	{
		col = colNum;
	}
	
	/**
	 * gets the column value of the Board Square
	 * @return int value of col
	 */
	public int getCol()
	{
		return col;
	}
	
	/**
	 * set the card value of the BoardSquare
	 * @param other Card object to set the BoardSquare card
	 */
	public void setCard(Card other) {
		c = other;
	}
	
	/**
	 * get the card object of the board square
	 * @return c Card object
	 */
	public Card getCard()
	{
		return c;
	}
	
	/**
	 * returns whether the boardsquare was selected or not
	 * @return boolean true or false value
	 */
	public boolean getSelected()
	{
		return selected;
	}
	
	/**
	 * sets whether the board square is selected or not
	 * @param s boolean value
	 */
	public void setSelected(boolean s)
	{
		selected=s;
	}
	
	/**
	 * Returns a string value of 
	 */
	@Override
	public String toString()
	{
		return c.toString();
	}
}