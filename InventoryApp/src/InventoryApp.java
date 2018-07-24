import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/*****************************
 * 
 * 
 * @author Team 3, Thomas Kempton, Jordan Leavitt, Joshua Modglin, Alex O'Dowd
 *
 *****************************/
public class InventoryApp extends Application{
	  @Override
	   public void start(Stage stage) throws Exception {
	      Parent root =  FXMLLoader.load(getClass().getResource("Login.fxml"));

	      Scene scene = new Scene(root); // attach scene graph to scene
	      stage.setTitle("Login"); // displayed in window's title bar
	      stage.setScene(scene); // attach scene to stage
	      stage.show(); // display the stage
	   }


	public static void main(String[] args) {
		
		 launch(args); 

	}

}
