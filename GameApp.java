import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.scene.control.Label;


@SuppressWarnings("restriction")//gets rid of annoying yellow underlines for warnings 
public class GameApp extends Application
{
	//initialize private instance variables
	private Button add3,exit,newGame;
	private BorderPane mainPane;
	private GridPane cardGrid;
	private Game game;
	private HBox headPane;
	private HBox buttonPane;
	private Label cardsRemaining;
	private Color selected = Color.web("801515");
	private Color unselectedBase = Color.web("D46A6A");
	
	/**
	 * The start method for application that runs and sets up the primary stage,scene, and panes
	 */
	@Override
	public void start(Stage primaryStage)
	{	
		game = new Game();// create new game to run the program with
		mainPane = new BorderPane();
		cardGrid=new GridPane();
		
		primaryStage.setTitle("Game of Set");//sets the title of the window
		
		
		//----------------------------------------------------------------
		//Create buttons and event handlers
		
		//Add 3 button
		add3 = new Button("Add 3");//assign add3 button
        add3.setOnAction(new EventHandler<ActionEvent>()
        {
	        @Override
	        public void handle(ActionEvent event)//event handler for add3 button
	        {
	        	if(game.getBoard().getCols()*game.getBoard().getRows()<15)//control number of cards user can have on game board
	        	{
			        game.add3();//add 3 cards to the game
			        drawBoard();//redraw the board with 3 more cards
	        	}
	        	else 
	        	{
	        		game.add3();//add 3 cards to the game
			        drawBoard();//redraw the board with 3 more cards
	        		add3.setDisable(true);//disable the add3 button if the user can no longer add 3 cards
	        	}
	        }
        });

        
        //Exit Button
        exit = new Button("Exit");//assign new button "exit"
		exit.setOnAction(this::handleExit);//event handler
		
		//New Game Button
		newGame= new Button("New Game");//assign new button "newGame"
		newGame.setOnAction(this::handleNewGame);//event handler
		
		//END BUTTONS
		//-------------------------------------------------------------
		
		//Cards Remaining Label
		cardsRemaining=new Label(String.format("Cards Remaining: %d", game.getDeck().getSize()));//create new label 
		cardsRemaining.setFont(Font.font("Courier",20));
		cardsRemaining.setTranslateX(0);
		cardsRemaining.setPadding(new Insets(1));
		cardsRemaining.setStyle("-fx-color: Black;"+"-fx-background-color: D46A6A;"+"-fx-border-color: Black;"+"-fx-border-width: 3;"+"-fx-border-radius: 3");
		
        
		buttonPane= new HBox();
		mainPane.setBottom(buttonPane);
		
		
		buttonPane= new HBox();
		buttonPane.getChildren().addAll(exit,add3,newGame,cardsRemaining);
		mainPane.setBottom(buttonPane);
		
		Text text= new Text("Game of Set");
		text.setFont(Font.font("Courier",20));
		headPane = new HBox(text);
		headPane.setAlignment(Pos.CENTER);
		mainPane.setTop(headPane);
		
		buttonPane.setSpacing(10);
		buttonPane.setAlignment(Pos.CENTER_LEFT);
		
		
		
		cardGrid.setPadding(new Insets(10));
		cardGrid.setHgap(10);
		cardGrid.setVgap(10);
	    mainPane.setCenter(cardGrid);
	    this.drawBoard();
	    
	    mainPane.setStyle("-fx-background-color: AA3939");
	    
	    
	    
	    Scene scene = new Scene(mainPane,600,600);
	    primaryStage.setScene(scene);
	    primaryStage.getIcons().add(new Image("file:icon.png"));
	    primaryStage.show();

	}
	
	
	public void drawBoard()
	{
		cardGrid.getChildren().clear();
		cardsRemaining.setText(String.format("Cards remaining: %d", game.getDeck().getSize()));
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
	
	
	private void handleExit(ActionEvent event) 
	{
        Platform.exit();
    }
	
	private void handleNewGame(ActionEvent event)
	{
		game = new Game();
        this.drawBoard();
        cardsRemaining.setText(String.format("Cards remaining: %d", game.getDeck().getSize()));
        add3.setDisable(false);
        
	}
	
	private void clickedEventHandler(MouseEvent click)
	{
		CardPane cp2= (CardPane) click.getSource();
		//5Pane clicked = (Pane) click.getSource();
		BoardSquare bs= cp2.getBoardSquare();
		
		if (bs.getSelected()==true)
		{
			System.out.println("deselecting card");
			bs.setSelected(false);
			game.removeSelected(bs.getRow(), bs.getCol());
			cp2.setBackground(new Background(new BackgroundFill(unselectedBase, CornerRadii.EMPTY, Insets.EMPTY)));
		}
		else 
		{
			System.out.println("selecting card");
			cp2.setBackground(new Background(new BackgroundFill(selected, CornerRadii.EMPTY, Insets.EMPTY)));
			game.addToSelected(bs.getRow(), bs.getCol());
		}
		
		if (game.numSelected()==3)
		{
			System.out.println("testing selected");
			cp2.setBackground(new Background(new BackgroundFill(selected, CornerRadii.EMPTY, Insets.EMPTY)));

			game.testSelected();
			this.drawBoard();
		}
	}
	
	public static void main(String[]args)
	{
		launch(args);
	}
}