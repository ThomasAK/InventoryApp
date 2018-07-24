import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.xml.bind.JAXB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/*****************************
 * 
 * 
 * @author Team 3, Thomas Kempton, Jordan Leavitt, Joshua Modglin, Alex O'Dowd
 *
 *****************************/
public class ManagerAccessController {
	ObservableList<String> list = FXCollections.observableArrayList("None", "Sales", "Stock", "Manager");
	Load load = new Load();
	Users users = load.getUsers();

	@FXML
	private Button btnClose;

	@FXML
	private Button btnAdd;

	@FXML
	private TextField txtUserID;

	@FXML
	private MenuItem menuClose;

	@FXML
	private ComboBox<String> userLevelDrop;

	@FXML
	private TextField txtPassword2;

	@FXML
	private MenuItem menuSave;

	@FXML
	private TextField txtPassword;

	@FXML
	private Button btnUpdate;

	@FXML
	public void initialize() {
		//set default values
		userLevelDrop.setItems(list);
		userLevelDrop.setValue("None");
		txtUserID.setText("");
		txtPassword.setText("");
		txtPassword2.setText("");
	}
	@FXML
	void btnAdd(ActionEvent event) {
		//check if both password fields match
		if (txtPassword.getText().equals(txtPassword2.getText())) {
			try {
				//check if user does not exist
				for (int i = 0; i < users.getSize(); i++) {
					// throw error is user already exists 
					if (users.getUser(i).getId().equals(txtUserID.getText())) {
						throw new IOException();
					}
				}
				//Create user and add user to users
				User user = new User(txtUserID.getText(), txtPassword.getText(), userLevelDrop.getValue().toString());
				users.getUsers().add(user);
				//Show success message 
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("User Added");
				alert.setHeaderText(null);
				alert.setContentText(txtUserID.getText() + "Added as user");
				alert.showAndWait();
				initialize();
			} catch (IOException ioException) {
				//show fail message if there is already a user named that
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("User Already Exists");
				alert.setHeaderText(null);
				alert.setContentText("User already exists please chose different user name");
				alert.showAndWait();
			}
		} else {
			//error shown if both passwords don't match
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Password");
			alert.setHeaderText(null);
			alert.setContentText("Passwords do not match.");
			alert.showAndWait();
		}
	}

	@FXML
	void closeAction(ActionEvent event) throws IOException {
		//create file writer then write Users to xml using file writer
		BufferedWriter output = Files.newBufferedWriter(Paths.get("./src/Users.xml"));
		JAXB.marshal(users, output);
		//get currect stage then close stage
		Stage stageManager = (Stage) btnAdd.getScene().getWindow();
		stageManager.close();
	}

	@FXML
	void btnUpdate(ActionEvent event) throws IOException {
		//check if both password fields match
		if (txtPassword.getText().equals(txtPassword2.getText())) {
			try {
				boolean userExists = false;
				//check if user exists
				for (int i = 0; i < users.getSize(); i++) {
					if (users.getUser(i).getId().equals(txtUserID.getText())) {
						userExists = true;
					}
				}
				// if user does not exist throw error
				if(userExists == false) {
					throw new IOException();
				}
				//if user does exist then update user password and access level
				for (int j = 0; j < users.getSize(); j++) {
					if (users.getUser(j).getId().equals(txtUserID.getText())) {
						users.getUser(j).setPassword(txtPassword.getText());
						users.getUser(j).setAccessLevel(userLevelDrop.getValue().toString());
					}
				}
				//show success message if user was updated
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("User Updated");
				alert.setHeaderText(null);
				alert.setContentText("User"+ txtUserID.getText() +"Updated");
				alert.showAndWait();
				initialize();

			} catch (IOException ioException) {
				//show fail message if user doens't exist
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("User Doesn't Exist");
				alert.setHeaderText(null);
				alert.setContentText("User doesn't exist!");
				alert.showAndWait();
			}
		} else {
			//show fail message if passwords don't match
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Password");
			alert.setHeaderText(null);
			alert.setContentText("Passwords do not match.");
			alert.showAndWait();
		}

	}

	@FXML
	void saveAction(ActionEvent event) throws IOException {
		//create file writer then write Users to xml using file writer
		BufferedWriter output = Files.newBufferedWriter(Paths.get("./src/Users.xml"));
		JAXB.marshal(users, output);
		//update users
		users = load.getUsers();
	}

}
