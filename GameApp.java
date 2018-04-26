import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import javafx.scene.paint.Color;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.geometry.Insets;
import javafx.geometry.Pos;



@SuppressWarnings("restriction")
public class GameApp extends Application
{
	private Button add3,exit;
	private BorderPane mainPane;
	private GridPane cardGrid;
	private Game game;
	
	@Override
	public void start(Stage primaryStage)
	{	
		game = new Game();
		
		mainPane = new BorderPane();
		cardGrid=new GridPane();
		
		primaryStage.setTitle("Game of Set");
	    mainPane.setCenter(cardGrid);
	    this.drawBoard();
	    Scene scene = new Scene(mainPane,800,600);
	    primaryStage.setScene(scene);
	    primaryStage.getIcons().add(new Image("file:icon.png"));
	    primaryStage.show();

	}
	
	
	public void drawBoard()
	{
		cardGrid.getChildren().clear();
		Board displayBoard=game.getBoard();
		
		for (int row=0;row<displayBoard.getRows();row++)
		{
			for(int col=0;col<displayBoard.getCols();col++)
			{
				Pane cp1= new CardPane(displayBoard.getBoardSquare(row, col));
				cp1.setOnMouseClicked(this::clickedEventHandler);
				cardGrid.add(cp1, col, row);
			}
		}
		
	}
	private void clickedEventHandler(MouseEvent click)
	{
		CardPane cp2= (CardPane) click.getSource();
		Pane clicked = (Pane) click.getSource();
		BoardSquare bs= cp2.getBoardSquare();
		
		if (bs.getSelected()==true)
		{
			game.removeSelected(bs.getRow(), bs.getCol());
			clicked.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		}
		else 
		{
			clicked.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
			game.addToSelected(bs.getRow(), bs.getCol());
		}
		
		if (game.numSelected()==3)
		{
			clicked.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
			
			game.testSelected();
			this.drawBoard();
		}
	}
	
	public static void main(String[]args)
	{
		launch(args);
	}
}