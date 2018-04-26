//Chris McCabe
//CS 110 Final: Set Game: Card Class

public class Card
{
	//Enumerated types for Color, Fill, Shape, and Number
	public enum Color{PURPLE,GREEN,RED}
	public enum Fill{OUTLINE,SOLID,HATCHED}
	public enum Shape{OVAL,SQUIGGLE,DIAMOND}
	public enum Num{ONE,TWO,THREE}
	
	//Instance variables for Color, Fill, Shape, and Number
	private Color color;
	private Fill fill;
	private Shape shape;
	private Num num;
	
	/**
	 * Constructor that sets Color, Fill, Shape, and Number to the values passed in
	 * @param color color value of the card
	 * @param fill fill value of the card
	 * @param shape shape value of the card
	 * @param num number value of the card
	 */
	public Card(Color color,Fill fill,Shape shape,Num num)
	{
		this.color=color;
		this.fill=fill;
		this.shape=shape;
		this.num=num;
	}
	
	/**
	 * Gets the color of the card
	 * @return enum type of color
	 */
	public Color getColor()
	{
		return color;
	}
	
	/**
	 * Gets the fill type of the card
	 * @return enum type of fill
	 */
	public Fill getFill()
	{
		return fill;
	}
	
	/**
	 * Gets the shape of the card
	 * @return enum type of shape
	 */
	public Shape getShape()
	{
		return shape;
	}
	
	/**
	 * Gets the number value of the card
	 * @return enum type of num
	 */
	public Num getNum()
	{
		return num;
	}
	
	
	/**
	 * Returns overriden string with num color shape fill
	 */
	@Override
	public String toString()
	{
		return num.ordinal()+1+"_"+color+"_"+shape+"_"+fill;
	}
	
	
	/**
	 * Static boolean method to determine whether 3 given cards are a set according the game of set rules
	 * @param c1 first card object
	 * @param c2 second card object
	 * @param c3 third card object
	 * @return boolean true or false value
	 */
	public static boolean isSet(Card c1,Card c2,Card c3)
	{ 
		if (!((c1.num == c2.num) && (c2.num == c3.num) || (c1.num != c2.num) && (c2.num != c3.num) && (c2.num != c3.num))) 
            return false;
		
        if (!((c1.shape == c2.shape) && (c2.shape == c3.shape) || (c1.shape != c2.shape) && (c1.shape != c3.shape) && (c2.shape != c3.shape)))
            return false;
        
        if (!((c1.fill == c2.fill) && (c2.fill == c3.fill) || (c1.fill != c2.fill) && (c1.fill != c3.fill) && (c2.fill != c3.fill)))
            return false;
        
        if (!((c1.color == c2.color) && (c2.color == c3.color) || (c1.color != c2.color) && (c1.color != c3.color) && (c2.color != c3.color))) 
            return false;
        
        return true;
	}
}