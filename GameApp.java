import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
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
	private Scene scene;
	private Stage manipulateStage;
	
	/**
	 * The start method for application that runs and sets up the primary stage,scene, and panes
	 */
	@Override
	public void start(Stage primaryStage)
	{	
		game = new Game();// create new game to run the program with
		mainPane = new BorderPane();// create main border pain
		cardGrid=new GridPane();//new gridpane to hold cards
		manipulateStage=primaryStage;//assign manipulate stage to primaryStage
		
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
		cardsRemaining.setFont(Font.font("Courier",20));//set font of the label
		cardsRemaining.setPadding(new Insets(1));//padding inside the label 
		cardsRemaining.setStyle("-fx-color: Black;"+"-fx-background-color: D46A6A;"+"-fx-border-color: Black;"
										+"-fx-border-width: 3;"+"-fx-border-radius:3;"+"-fx-background-radius:3;");//add style rules to the label
		
		buttonPane= new HBox();//new pane for the bottom of the border pane
		buttonPane.getChildren().addAll(exit,add3,newGame,cardsRemaining);//add all the buttons and the label to the pane
		mainPane.setBottom(buttonPane);//set the bottom of the border pane to the HBox buttonPane
		
		Text text= new Text("Game of Set");//new text for the header title
		text.setFont(Font.font("Courier",20));//set the font of the text
		headPane = new HBox(text);//new header pane
		headPane.setAlignment(Pos.CENTER);//set the header to the center
		mainPane.setTop(headPane);//set the top to the header pane
		
		buttonPane.setSpacing(10);//adjust spacing between buttons
		buttonPane.setAlignment(Pos.CENTER_LEFT);//set the alignment of the buttons
		
		
		
		cardGrid.setPadding(new Insets(10));//add padding to the cards
		cardGrid.setHgap(10);//set a horizontal gap between cards
		cardGrid.setVgap(10);//set a vertical gap between cards
	    mainPane.setCenter(cardGrid);//set the center of the borderpane to the cardgrid
	    this.drawBoard();//draw the board
	    
	    mainPane.setStyle("-fx-background-color: AA3939");//set the background color of the window
	    mainPane.setMargin(cardGrid, new Insets(0,0,0,85));//add margins for initial board draw
	    
	    scene = new Scene(mainPane,600,600);//set the scene to the border pane and give initial dimensions
	    primaryStage.setScene(scene);//set the stage scene to "scene"
	    primaryStage.getIcons().add(new Image("file:icon.png"));//change icon of the window
	    primaryStage.show();//show the application

	}
	
	/**
	 * Draw the board by looping through the deck and adding cardpanes
	 * center the grid in the window
	 */
	public void drawBoard()
	{
		cardGrid.getChildren().clear();//clear the board
		cardsRemaining.setText(String.format("Cards remaining: %d", game.getDeck().getSize()));//reset cards remaining
		Board displayBoard=game.getBoard();
		
		for (int row=0;row<displayBoard.getRows();row++)//loop through cards and add them to the grid
		{
			for(int col=0;col<displayBoard.getCols();col++)
			{
				Pane cp1= new CardPane(displayBoard.getBoardSquare(row, col));
				cp1.setOnMouseClicked(this::clickedEventHandler);
				cardGrid.add(cp1, col, row);
			}
		}
	    int col = game.getBoard().getCols();//get the number of columns currently on the board
	    switch(col)//switch through the number of columns to keep the grid centered in the window
	    {
	    	case 4: mainPane.setMargin(cardGrid, new Insets(0,0,0,85));
	    				break;
	    	case 5: mainPane.setMargin(cardGrid, new Insets(0,0,0,35));
	    				break;
	    	case 6: mainPane.setMargin(cardGrid, new Insets(0,0,0,25));
	    			manipulateStage.setWidth(700);
	    				break;
	    	default: mainPane.setMargin(cardGrid, new Insets(0,0,0,0));
	    }

	}
	
	/**
	 * exit the program when the exit button is clicked
	 * @param event mouse click event
	 */
	private void handleExit(ActionEvent event) 
	{
        Platform.exit();
    }
	
	/**
	 * creates a new version of the game by redrawing the board with a 
	 * new deck and resetting  cardsremaining, add3 disabled, and window width
	 * @param event mouse click event
	 */
	private void handleNewGame(ActionEvent event)
	{
		game = new Game();//create new game
        this.drawBoard();//redraw the board
        cardsRemaining.setText(String.format("Cards remaining: %d", game.getDeck().getSize()));//reset the amount of cards remaining
        add3.setDisable(false);//re-enable the add3 button
        manipulateStage.setWidth(600);//set the window width back to 600 if it was changed in the previous game
	}
	
	/**
	 * Event handler for when the user clicks on a card
	 * @param click mouse event 
	 */
	private void clickedEventHandler(MouseEvent click)
	{
		CardPane cp2= (CardPane) click.getSource();//new cardpane with card source
		BoardSquare bs= cp2.getBoardSquare();//new boardsqure
		
		if (bs.getSelected()==true)//if already selected
		{
			System.out.println("deselecting card");//console output
			bs.setSelected(false);//set selected to false
			game.removeSelected(bs.getRow(), bs.getCol());//remove the card from the arraylist of selected cards
			cp2.setBackground(new Background(new BackgroundFill(unselectedBase, CornerRadii.EMPTY, Insets.EMPTY)));//set the background back to the base color
		}
		else 
		{
			System.out.println("selecting card");//console output
			cp2.setBackground(new Background(new BackgroundFill(selected, CornerRadii.EMPTY, Insets.EMPTY)));//set the card to selected color
			game.addToSelected(bs.getRow(), bs.getCol());//add the card to the selected card arraylist
		}
		
		if (game.numSelected()==3)//if three are selected
		{
			System.out.println("testing selected");//console output
			cp2.setBackground(new Background(new BackgroundFill(selected, CornerRadii.EMPTY, Insets.EMPTY)));//set the card to the selected color

			game.testSelected();//test the 3 selected cards
			this.drawBoard();//redraw the board
		}
	}
	
	public static void main(String[]args)
	{
		launch(args);
	}
}