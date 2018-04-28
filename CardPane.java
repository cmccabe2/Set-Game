import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;


@SuppressWarnings("restriction")
public class CardPane extends VBox
{	
	//create variables
	private Card card;
	private BoardSquare bs1;
	private int row,column;
	private Card.Color color;
	private Card.Shape shape;
	private Card.Fill fill;
	private Card.Num number;
	private static Rectangle squareRect;
	private static Polygon poly;
	private final static int CARD_WIDTH = 95; 
	private final static int CARD_HEIGHT = 135;
	


	/**
	 * Constructor for the cardpane class
	 * gets the card attributes from boardsquare and styles the cardpane
	 * draws shapes on the cards also
	 * @param bs boardsquare to get card information from 
	 */
	public CardPane(BoardSquare bs)
	{
		super();//call super constructor
		
		//assign card attributes to instance variables
		card=bs.getCard();
		row=bs.getRow();
		column=bs.getCol();
		bs1=bs;
		this.color=bs.getCard().getColor();
		this.shape=bs.getCard().getShape();
		this.fill=bs.getCard().getFill();
		this.number=bs.getCard().getNum();
		
		this.setSpacing(10); //sets spaces between shapes
        this.setPadding(new Insets(10)); //space between shapes and border
        this.setPrefSize(CARD_WIDTH, CARD_HEIGHT);//set the dimensions
        this.setAlignment(Pos.CENTER);//set children to the center of the card
        this.setMinWidth(CARD_WIDTH);//set min width
        this.setMinHeight(CARD_HEIGHT);//set min height
        this.setStyle("-fx-background-color: #D46A6A;" + "-fx-border-width: 4;"+ "-fx-border-color: #000;" + "-fx-border-style: solid;"+
        													"-fx-border-radius:3;"+"-fx-background-radius:3;");//style the cardpane
        
        //initialize variables for card attributes
		Color colorOfCard;
		Color outline;
		Paint fillType;
		
		switch(card.getColor())//switch statement to go from card.color to a javafx color
		{
		case RED: colorOfCard =Color.RED; 
					break;
		case GREEN: colorOfCard=Color.GREEN;
					break;
		case PURPLE: colorOfCard=Color.BLUE;
					break;
		default: colorOfCard=Color.TRANSPARENT;
		}
		
	
		switch(card.getFill())//switch statement to store fill type in a variable
		{
		case SOLID:fillType= colorOfCard;
					 outline = colorOfCard;
					 break;
		case HATCHED:fillType=new ImagePattern(makeHatched(colorOfCard),0,0,20,20,false);
					outline = colorOfCard;
					break;
		case OUTLINE: fillType=Color.TRANSPARENT;
					outline=colorOfCard;
					break;
		default: fillType= Color.BLACK;
				outline  = Color.BLACK;
		}
		
		int shapeNumber;
		
		switch(card.getNum())//switch to get integer value of number of shapes
		{
		case ONE: shapeNumber=1;
					break;
		case TWO: shapeNumber=2;
					break;
		case THREE: shapeNumber=3;
					break;
		default:shapeNumber=1;
		}
		
		
		Node shape;
		for (int i=0;i<shapeNumber;i++)//determine what shape the card holds and draw them 
		{
			switch(this.shape)
			{
			case OVAL:shape= drawEllipse(outline,fillType);
						break;
			case SQUIGGLE:shape=drawSquare(outline,fillType);
						break;
			case DIAMOND: shape=drawDiamond(outline,fillType);
						break;
			default:shape=drawEllipse(outline,fillType);
			
			}
			
			this.getChildren().add(shape);//add the shape to the card
		}
	}
	
	
	/**
	 * Draws a square shape to add to the cardpane
	 * @param outline outline color for the shape(stroke)
	 * @param fill fill color for the shape
	 * @return square shape to add to card
	 */
	private static Rectangle drawSquare(Color outline,Paint fill)
	{
		squareRect= new Rectangle();//new rect
		squareRect.setWidth(30);//width
		squareRect.setHeight(30);//height
		squareRect.setFill(fill);//fill
		squareRect.setStrokeWidth(3);//border width
		squareRect.setStroke(outline);//border color
		return squareRect;
	}
	
	/**
	 * Draws a diamond shape to add to the cardpane
	 * @param outline outline color for the shape(stroke)
	 * @param fill fill color for the shape
	 * @return diamond shape to add to card
	 */
	private static Polygon drawDiamond(Color outline, Paint fill)
	{
		Double[]points=new Double[] {0.0, 45/4.0, 90.0/4, 90.0/4, 180.0/4, 45.0/4, 90.0/4, 0.0};//create point array for the diamond
		poly=new Polygon();//new poly
		poly.getPoints().addAll(points);//add all the points to the polygon
		poly.setStrokeWidth(3);//set border width
		poly.setStroke(outline);//set border color
		poly.setFill(fill);//set fill
		return poly;
	}
	
	/**
	 * Draws an ellipse shape to add to the cardpane
	 * @param outline outline color for the shape(stroke)
	 * @param fill fill color for the shape
	 * @return ellipse shape to add to card
	 */
	private static Ellipse drawEllipse(Color outline,Paint fill)
	{
		Ellipse elip =new Ellipse();//new ellipse
		elip.setCenterX(20);//centerx
		elip.setCenterY(20);//centery
		elip.setRadiusX(20);//radiusx
		elip.setRadiusY(10);//radiusy
		elip.setFill(fill);//fill
		elip.setStrokeWidth(3);//border width
		elip.setStroke(outline);//border color
		return elip;
	}

	/**
	 * make a fill type that appears hatched(diagonal lines with white background
	 * @param color color of the lines
	 * @return image of background image
	 */
	 private Image makeHatched(Color color) 
	 {
	        Pane bgp = new Pane();//new pane
	        
	        bgp.setPrefSize(20, 20);//size of pane
	        Line line = new Line(0, 0, 25, 25);//new line
	        
	        line.setRotate(0);//rotate the line
	        line.setStroke(color);//change line color
	        line.setStrokeWidth(5);//change line width
	        bgp.getChildren().addAll(line);//add line to pane
	        
	        new Scene(bgp);//new scene
	        return bgp.snapshot(null, null);//return snapshot of that scene
	}
	 
	 /**
	  * Get the boardSquare
	  * @return boardsquare
	  */
	public BoardSquare getBoardSquare()
	{
		return bs1;
	}
	
	/**
	 * get the row
	 * @return integer value of the row
	 */
	public int getRow()
	{
		return row;
	}
	
	/**
	 * get the column
	 * @return integer value of the column
	 */
	public int getCol()
	{
		return column;
	}
	
	/**
	 * get the color of the card
	 * @return Color of the card 
	 */
	public Color getColor()
	{
		Color colorOfCard;
		switch(this.color)
		{
		case RED: colorOfCard =Color.RED; 
					break;
		case GREEN: colorOfCard=Color.GREEN;
					break;
		case PURPLE: colorOfCard=Color.BLUE;
					break;
		default: colorOfCard=Color.TRANSPARENT;
		}
		return colorOfCard;
	}
}