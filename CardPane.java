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
	private Card card;
	private BoardSquare bs1;
	private int row,column;
	private Card.Color color;
	private Card.Shape shape;
	private Card.Fill fill;
	private Card.Num number;
	//private Image image = new Image("hatch.png");
	private static Rectangle squareRect;
	private static Polygon poly;
	private final static int CARD_WIDTH = 95; 
	private final static int CARD_HEIGHT = 135;
	


	
	public CardPane(BoardSquare bs)
	{
		super();
		card=bs.getCard();
		row=bs.getRow();
		column=bs.getCol();
		bs1=bs;
		
		this.color=bs.getCard().getColor();
		this.shape=bs.getCard().getShape();
		this.fill=bs.getCard().getFill();
		this.number=bs.getCard().getNum();
		
		this.setSpacing(10); //sets spaces between multiple shapes
        this.setPadding(new Insets(10)); //space between shapes and border
        this.setPrefSize(CARD_WIDTH, CARD_HEIGHT); // sets dimensions
        this.setAlignment(Pos.CENTER); // forces children to be centered in the middle of the card
        this.setMinWidth(CARD_WIDTH);
        this.setMinHeight(CARD_HEIGHT);
        this.setStyle("-fx-background-color: #D46A6A;" + "-fx-border-width: 4;"+ "-fx-border-color: #000;" + "-fx-border-style: solid;");
        

		Color colorOfCard;
		Color outline;
		Paint fillType;
		
		switch(card.getColor())
		{
		case RED: colorOfCard =Color.RED; 
					break;
		case GREEN: colorOfCard=Color.GREEN;
					break;
		case PURPLE: colorOfCard=Color.BLUE;
					break;
		default: colorOfCard=Color.TRANSPARENT;
		}
		
	
		switch(card.getFill())
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
		
		switch(card.getNum())
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
		for (int i=0;i<shapeNumber;i++)
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
			
			this.getChildren().add(shape);
		}
	}

	private static Rectangle drawSquare(Color outline,Paint fill)
	{
		squareRect= new Rectangle();
		squareRect.setWidth(30);
		squareRect.setHeight(30);
		squareRect.setFill(fill);
		squareRect.setStrokeWidth(3);
		squareRect.setStroke(outline);
		return squareRect;
	}
	
	
	private static Polygon drawDiamond(Color outline, Paint fill)
	{
		Double[]points=new Double[] {0.0, 45/4.0, 90.0/4, 90.0/4, 180.0/4, 45.0/4, 90.0/4, 0.0};
		poly=new Polygon();
		poly.getPoints().addAll(points);
		poly.setStrokeWidth(3);
		poly.setStroke(outline);
		poly.setFill(fill);
		return poly;
	}
	
	private static Ellipse drawEllipse(Color outline,Paint fill)
	{
		Ellipse elip =new Ellipse();
		elip.setCenterX(20);
		elip.setCenterY(20);
		elip.setRadiusX(20);
		elip.setRadiusY(10);
		elip.setFill(fill);
		elip.setStrokeWidth(3);
		elip.setStroke(outline);
		return elip;
	}

	 private Image makeHatched(Color color) 
	 {
	        Pane bgp = new Pane();
	        
	        bgp.setPrefSize(20, 20);
	        Line line = new Line(0, 0, 25, 25);
	        
	        line.setRotate(0);
	        line.setStroke(color);
	        line.setStrokeWidth(5);
	        bgp.getChildren().addAll(line);
	        
	        new Scene(bgp);
	        return bgp.snapshot(null, null);
	}
	public BoardSquare getBoardSquare()
	{
		return bs1;
	}
	
	public int getRow()
	{
		return row;
	}
	public int getCol()
	{
		return column;
	}
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