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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


@SuppressWarnings("restriction")
public class GameApp extends Application
{
	private Button add3,exit,newGame;
	private BorderPane mainPane;
	private GridPane cardGrid;
	private Game game;
	private HBox headPane;
	private HBox buttonPane;
	
	@Override
	public void start(Stage primaryStage)
	{	
		game = new Game();
		
		primaryStage.setTitle("Game of Set");
		
		mainPane = new BorderPane();
		cardGrid=new GridPane();
		
		add3 = new Button("Add 3");
        add3.setOnAction(new EventHandler<ActionEvent>()
        {
	        @Override
	        public void handle(ActionEvent event)
	        {
		        game.add3();
		        drawBoard();
	        }
        });
        
		buttonPane= new HBox(add3);
		mainPane.setBottom(buttonPane);
		
		Text text= new Text("Game of Set");
		headPane = new HBox(text);
		headPane.setAlignment(Pos.CENTER);
		mainPane.setTop(headPane);
		
		mainPane.setAlignment(cardGrid, Pos.CENTER);
		
		cardGrid.setPadding(new Insets(10));
		cardGrid.setHgap(10);
		cardGrid.setVgap(10);
	    mainPane.setCenter(cardGrid);
	    this.drawBoard();
	    Scene scene = new Scene(mainPane,700,700);
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