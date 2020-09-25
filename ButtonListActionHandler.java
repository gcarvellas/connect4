import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class ButtonListActionHandler extends Main implements EventHandler<ActionEvent> {
	// Variables
	private int index;
	private Stage primaryStage;

	// Constructor
	public ButtonListActionHandler(int index, Stage primaryStage) {
		this.index = index;
		this.primaryStage = primaryStage;
	}

	// Event Handler
	public void handle(ActionEvent event) {
		try {
			super.playRound(index);
			super.startGame(primaryStage);
			super.checkGameEnd();
			if (isComputer) {
				super.playRound(players.get(1).getMove());
				super.startGame(primaryStage);
				super.checkGameEnd();
			}
		} catch (Exception e) {
		}
	}

}
