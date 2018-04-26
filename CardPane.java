import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	private static Rectangle rectCard,r1,rectShape;
	private Polygon poly;
	private int startX,startY; 
	private final static int DEF_RECT_WIDTH = 150; 
	private final static int DEF_RECT_HEIGHT = 200;

	
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
		
		startX=(DEF_RECT_WIDTH*column);
		startX=(DEF_RECT_HEIGHT*row);
		
		r1=drawCardOutline(startX,startY,DEF_RECT_WIDTH,DEF_RECT_HEIGHT);
		
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
			case SQUIGGLE:shape=drawEllipse(outline,fillType);
						break;
			case DIAMOND: shape=drawDiamond(outline,fillType);
						break;
			default:shape=drawEllipse(outline,fillType);
			
			}
			
			this.getChildren().add(shape);
		}
	}
	private static Rectangle drawCardOutline(int x,int y,int width, int height)
	{
		rectCard=new Rectangle(x,y,width,height);
		rectCard.setArcHeight(25);
		rectCard.setStyle("-fx-background-color: #fff;" + "-fx-border-width: 3;" + "-fx-border-color: #000;" + "-fx-border-style: solid;");
		return rectCard;
	}
	
	private static Rectangle drawDiamond(Color outline, Paint fill)
	{
		rectShape=new Rectangle(75,75,fill);
		rectShape.setFill(outline);
		return rectShape;
	}
	
	private static Ellipse drawEllipse(Color outline,Paint fill)
	{
		Ellipse elip =new Ellipse();
		elip.setFill(fill);
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
	
	
	
	
	
}