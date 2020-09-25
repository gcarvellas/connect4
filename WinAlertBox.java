import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WinAlertBox {
	//Variables
	private int winner;
	
	//Constructor
	public WinAlertBox(int winner) {
		this.winner = winner;
	}
	
	//Alert Display
	public void display(String title, String message) {
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Winner!");
		window.setMinWidth(250);
		
		//Message
		Label label = new Label();
		label.setText("Player " + winner + " wins!");
		
		//Button 
		Button closeButton = new Button("Exit");
		closeButton.setOnAction(e -> window.close());
		
		//Layout
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, closeButton);
		layout.setAlignment(Pos.CENTER);
		
		//Scene
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		
	}
	
}
