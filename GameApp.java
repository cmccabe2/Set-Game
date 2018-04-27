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


@SuppressWarnings("restriction")
public class GameApp extends Application
{
	private Button add3,exit,newGame;
	private BorderPane mainPane;
	private GridPane cardGrid;
	private Game game;
	private HBox headPane;
	private HBox buttonPane;
	private Label cardsRemaining;
	private Color selected = Color.web("801515");
	private Color unselectedBase = Color.web("D46A6A");
	
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
	        	if(game.getBoard().getCols()*game.getBoard().getRows()<18)
	        	{
			        game.add3();
			        drawBoard();
	        	}
	        	else
	        	{
	        		add3.setDisable(true);
	        	}
	        }
        });
        
        add3.setFont(Font.font("Courier",12));
        add3.setTranslateX(0);
        
		buttonPane= new HBox();
		mainPane.setBottom(buttonPane);
		
		exit = new Button("Exit");
		exit.setTranslateX(0);
		exit.setFont(Font.font("Courier",12));
		exit.setOnAction(this::handleExit);
		
		newGame= new Button("New Game");
		 newGame.setOnAction(this::handleNewGame);
		
		cardsRemaining=new Label(String.format("Cards Remaining: %d", game.getDeck().getSize()));
		cardsRemaining.setFont(Font.font("Courier",20));
		cardsRemaining.setTranslateX(0);
		cardsRemaining.setPadding(new Insets(1));
		
		cardsRemaining.setStyle("-fx-color: Black;"+"-fx-background-color: D46A6A;"+"-fx-border-color: Black;"+"-fx-border-width: 3;"+"-fx-border-radius: 3");
		
		
		Text text= new Text("Game of Set");
		text.setFont(Font.font("Courier",20));
		headPane = new HBox(text);
		headPane.setAlignment(Pos.CENTER);
		mainPane.setTop(headPane);
		
		buttonPane.setSpacing(10);
		buttonPane.setAlignment(Pos.CENTER_LEFT);
		
		buttonPane.getChildren().addAll(exit,add3,newGame,cardsRemaining);
		
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
        System.gc();
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