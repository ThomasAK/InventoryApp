import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.bind.JAXB;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
/*****************************
 * 
 * 
 * @author Team 3, Thomas Kempton, Jordan Leavitt, Joshua Modglin, Alex O'Dowd
 *
 *****************************/
public class LogInController {

	@FXML
	private Button btnCancel;

	@FXML
	private MenuItem menuExit;

	@FXML
	private Button btnLogin;

	@FXML
	private TextField txtUserID;

	@FXML
	private TextField txtPassword;

	@FXML
	void btnLogin(ActionEvent event) throws IOException {
		Users active = new Users();
		// create loader
		Load load = new Load();
		// create users
		Users users = new Users();
		// set users to load get users
		users = load.getUsers();
		// CurrentUser current = new CurrentUser();
		try {
			boolean correct = false;
			// check is user name and pass word match a saved user
			for (int i = 0; i < users.getSize(); i++) {
				// if user name and password match set correct to true
				if (users.getUser(i).getId().equals(txtUserID.getText())
						&& users.getUser(i).getPassword().equals(txtPassword.getText())) {
					correct = true;
					active.getUsers().add(users.getUser(i));
				}
			}
			// if correct = false throw exception
			if (correct == false) {
				throw new Exception();
			}
			//set active user
			BufferedWriter output = Files.newBufferedWriter(Paths.get("./src/Active.xml"));
			JAXB.marshal(active, output);
			
			// create new stage and launch it.
			Stage stageInventory = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("InventoryControll.fxml"));

			Scene scene = new Scene(root); // attach scene graph to scene
			stageInventory.setTitle("Inventory Control"); // displayed in window's title bar
			stageInventory.setScene(scene); // attach scene to stage
			stageInventory.show(); // display the stage

			// get current stage then close current stage
			Stage stageLogin = (Stage) btnLogin.getScene().getWindow();
			stageLogin.close();

		} catch (Exception e) {
			// show message if user name or password did not match
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Unable to login");
			alert.setHeaderText(null);
			alert.setContentText("User name or password incorrect!");
			alert.showAndWait();
		}
	}

	@FXML
	void btnCancel(ActionEvent event) {
		// close program
		System.exit(0);
	}

	@FXML
	void menuExit(ActionEvent event) {
		// close program
		System.exit(0);
	}

}
