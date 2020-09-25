import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main extends Application {
	// Variables
	protected static int row = 6, col = 7, roundCount = 1, playerTurn = 1, gameEnd, counter;
	private static Board board;
	private static Scene startScene, optionsScene, gameScene;
	protected static boolean isComputer = false;
	protected static ArrayList<Player> players;


	public static void main(String[] args) {
		launch(args);
	}

	// JavaFX Menu Initializers

	public void start(Stage primaryStage) throws Exception {
		startMenu(primaryStage);
	}

	public static void startMenu(Stage primaryStage) throws Exception {
		// Chip Image
		FileInputStream input = new FileInputStream("chip.jpg");
		Image image = new Image(input);
		ImageView chipImage = new ImageView(image);

		// Variables
		Button startButton = new Button("Start");
		Button optionsButton = new Button("Options");
		Label startText = new Label("Welcome to Connect 4!", chipImage);
		BorderPane startBorderPane = new BorderPane();
		startScene = new Scene(startBorderPane, 800, 600);

		// Children Settings
		startButton.setPrefSize(200, 100);
		startButton.setFont(new Font("Arial", 15));
		optionsButton.setPrefSize(200, 50);
		optionsButton.setFont(new Font("Arial", 15));
		startText.setFont(new Font("Arial", 30));

		// Top Layout
		StackPane topLayout = new StackPane();
		topLayout.getChildren().addAll(startText);

		// Center Layout
		StackPane centerLayout = new StackPane();
		centerLayout.getChildren().add(startButton);

		// Bottom Layout
		StackPane bottomLayout = new StackPane();
		bottomLayout.getChildren().add(optionsButton);

		// BorderPane
		startBorderPane.setPrefSize(400, 300);
		startBorderPane.setTop(topLayout);
		startBorderPane.setCenter(centerLayout);
		startBorderPane.setBottom(bottomLayout);

		// Button Events
		optionsButton.setOnAction(e -> {
			try {
				optionsMenu(primaryStage);
			} catch (Exception a) {
			}
		});

		startButton.setOnAction(e -> {
			board = new Board(row, col);
			
			players = new ArrayList<Player>();
			players.add(new Player());
			if (isComputer)
				players.add(new Computer(board));
			else
				players.add(new Player());
			
			try {
				startGame(primaryStage);
			} catch (Exception a) {
			}
		});

		// Stage
		primaryStage.setTitle("Connect 4 - Start Menu");

		// Scene
		primaryStage.setScene(startScene);
		primaryStage.show();

		// startup();
		// startGame();

	}

	public static void optionsMenu(Stage primaryStage) throws Exception {
		// Variables
		Button saveButton = new Button("Save");
		Button backButton = new Button("Back");
		Label optionsText = new Label("Options Menu");
		Label boardDimensionsText = new Label("Board Dimensions:");
		Label computerOrHumanText = new Label("Computer?");
		TextField rowInput = new TextField(Integer.toString(row));
		TextField colInput = new TextField(Integer.toString(col));
		CheckBox computerCheckBox = new CheckBox("Yes");
		BorderPane optionsBorderPane = new BorderPane();
		optionsScene = new Scene(optionsBorderPane, 800, 600);

		// Children Settings
		computerCheckBox.setSelected(isComputer);
		saveButton.setPrefSize(100, 100);
		saveButton.setFont(new Font("Arial", 15));
		backButton.setPrefSize(100, 100);
		backButton.setFont(new Font("Arial", 15));
		optionsText.setFont(new Font("Arial", 30));
		boardDimensionsText.setFont(new Font("Arial", 15));
		computerOrHumanText.setFont(new Font("Arial", 15));

		// Top Layout
		StackPane topLayout = new StackPane();
		topLayout.getChildren().add(optionsText);

		// Center Layout
		HBox centerLayout = new HBox();
		centerLayout.getChildren().addAll(boardDimensionsText, rowInput, colInput);

		// Right Layout
		VBox rightLayout = new VBox();
		rightLayout.getChildren().addAll(computerOrHumanText, computerCheckBox);

		// Bottom Layout
		HBox bottomLayout = new HBox();
		bottomLayout.getChildren().addAll(backButton, saveButton);

		// BorderPane
		optionsBorderPane.setPrefSize(400, 300);
		optionsBorderPane.setTop(topLayout);
		optionsBorderPane.setCenter(centerLayout);
		optionsBorderPane.setRight(rightLayout);
		optionsBorderPane.setBottom(bottomLayout);

		// Button Events
		saveButton.setOnAction(e -> {
			row = Integer.parseInt(rowInput.getText());
			col = Integer.parseInt(colInput.getText());
			isComputer = computerCheckBox.isSelected();
		});

		backButton.setOnAction(e -> {
			try {
				startMenu(primaryStage);
			} catch (Exception a) {
			}
		});

		// Stage
		primaryStage.setTitle("Connect 4 - Options");

		// Scene
		primaryStage.setScene(optionsScene);
	}

	// Game Mechanics
	public static void startGame(Stage primaryStage) throws Exception {
		// Variables
		Label roundCounter = new Label("Round " + roundCount);
		Label playerCounter = new Label("   Player " + playerTurn + "'s turn.");
		BorderPane gameBorderPane = new BorderPane();
		gameScene = new Scene(gameBorderPane, 800, 600);

		// Select Button Variables
		ArrayList<Button> buttonList = new ArrayList<Button>();
		for (int i = 0; i < col; i++) {
			buttonList.add(new Button(Integer.toString(i + 1)));
		}

		// Children Settings
		roundCounter.setFont(new Font("Arial", 30));
		playerCounter.setFont(new Font("Arial", 30));
		for(int i = 0; i < col; i++) {
			buttonList.get(i).setPrefSize((800/buttonList.size()), 100);
		}

		// Top Layout
		HBox topLayout = new HBox();
		topLayout.getChildren().addAll(roundCounter, playerCounter);

		// Middle Layout
		TilePane centerLayout = new TilePane();
		centerLayout.setHgap(0);
		centerLayout.setVgap(0);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				centerLayout.getChildren().add(displayChip(i,j));
			}
		}

		// Bottom Layout
		HBox bottomLayout = new HBox();
		for (int i = 0; i < col; i++) {
			bottomLayout.getChildren().add(buttonList.get(i));
		}

		// BorderPane
		gameBorderPane.setPrefSize(400, 300);
		gameBorderPane.setTop(topLayout);
		gameBorderPane.setCenter(centerLayout);
		gameBorderPane.setBottom(bottomLayout);

		// Button Events
		if(gameEnd == 0) {
			for(int i = 0; i < buttonList.size(); i++) {
				ButtonListActionHandler buttonHandler = new ButtonListActionHandler(i, primaryStage);
				buttonList.get(i).setOnAction(buttonHandler);
			}
		}
		
		// Stage
		primaryStage.setTitle("Connect 4");

		// Scene
		primaryStage.setScene(gameScene);
	}
	
	private static Label displayChip(int rowSlot, int colSlot) throws FileNotFoundException{
		// Empty Chip Image
		FileInputStream displayChipInput;	
		if(board.getChip(rowSlot,colSlot) == 0) {
			displayChipInput = new FileInputStream("Empty.jpg");
		}
		else if(board.getChip(rowSlot,colSlot) == 1){
			displayChipInput = new FileInputStream("Red.jpg");
		}
		else {
			displayChipInput = new FileInputStream("Yellow.jpg");
		}
		
		Image image = new Image(displayChipInput);
		ImageView chipImage = new ImageView(image);
		
		Label output = new Label("",chipImage);
		output.setPrefSize(800/(col), 400 /(row));;
		return (output);
	}
	
	public static void playRound(int select) throws RowException {
		board.insertChip(new Chip(playerTurn), select);
		gameEnd = board.isGameOver();
		if (gameEnd == 0){
		if (playerTurn == 1)
			playerTurn++;
		else
			playerTurn--;
		roundCount++;
		}
	}

	protected static void checkGameEnd() {
		gameEnd = board.isGameOver();
		if (gameEnd != 0){
			WinAlertBox alertBox = new WinAlertBox(gameEnd);
			alertBox.display("Connect 4 - Game End", "Player: " + gameEnd + " wins!");
			System.exit(0);
		}
	}
}
