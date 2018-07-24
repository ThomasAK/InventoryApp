import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.xml.bind.JAXB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
/*****************************
 * 
 * 
 * @author Team 3, Thomas Kempton, Jordan Leavitt, Joshua Modglin, Alex O'Dowd
 *
 *****************************/



public class InventoryController {
	ObservableList<String> list = FXCollections.observableArrayList();
	Load load = new Load();
	PerishableItems pItems = load.getPerishableItems();
	NonPerishableItems npItems = load.getNonPerishableItems();
	ValidateInput validate = new ValidateInput();
	Users active = load.getActiveUser();

	@FXML
	private MenuItem menuWelcome;

	@FXML
	private MenuItem menuExit;

	@FXML
	private TextField txtExpirationDate;

	@FXML
	private Button btnAddItem;

	@FXML
	private Button btnManagerAccess;

	@FXML
	private TextField txtCost;

	@FXML
	private Button btnClear;

	@FXML
	private TextField txtQuantity;

	@FXML
	private Button btnUpdateItem;

	@FXML
	private ComboBox<String> itemSelectionBox;

	@FXML
	private TextField txtSKU;

	@FXML
	private TextArea txtManagerNotes;

	@FXML
	private CheckBox checkPerishable;

	@FXML
	private TextField txtItemName;

	@FXML
	private TextField txtDateReceived;

	@FXML
	private TextField txtPrice;

	@FXML
	private Button btnDeleteItem;

	@FXML
	private MenuItem menuSave;

	@FXML
	private MenuItem menuAbout;

	/*****************************
	 * 
	 * 
	 * 
	 * 
	 *****************************/
	String name;
	String sku;
	Double cost;
	Double price;
	int qty;
	String dateReceived;
	String expirationDate;

	public boolean validate() {
		boolean correct = true;
		if (!validate.validateDate(txtDateReceived.getText())) {
			correct = false;
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Date format error");
			alert.setHeaderText(null);
			alert.setContentText("Date format should be 00-00-0000");
			alert.showAndWait();
		}
		if (!validate.validateMoney(txtCost.getText())) {
			correct = false;
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Cost Format Error");
			alert.setHeaderText(null);
			alert.setContentText("Cost format should be anyAmount.00");
			alert.showAndWait();
		}
		if (!validate.validateQuantity(txtQuantity.getText())) {
			correct = false;
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Quantity Format Error");
			alert.setHeaderText(null);
			alert.setContentText("Quantity Format should be any amount of numbers no decimals");
			alert.showAndWait();
		}
		if (!validate.validateSKU(txtSKU.getText())) {
			correct = false;
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Sku format error");
			alert.setHeaderText(null);
			alert.setContentText("Sku format should be 000-0000");
			alert.showAndWait();
		}
		return correct;
	}

	@FXML
	public void initialize() {
		itemSelectionBox.getItems().clear();
		for (int i = 0; i < pItems.getSize(); i++) {
			list.add(pItems.getItem(i).getName());
		}
		for (int i = 0; i < npItems.getSize(); i++) {
			list.add(npItems.getItem(i).getName());
		}
		itemSelectionBox.setItems(list);

		if (active.getUser(0).getAccessLevel().equals("Sales") || active.getUser(0).getAccessLevel().equals("Stock") || active.getUser(0).getAccessLevel().equals("None")) {
			btnManagerAccess.setDisable(true);
			txtPrice.setDisable(true);
			txtManagerNotes.setDisable(true);
		}
		if (active.getUser(0).getAccessLevel().equals("Sales") || active.getUser(0).getAccessLevel().equals("None")) {
			txtItemName.setDisable(true);
			txtSKU.setDisable(true);
			txtCost.setDisable(true);
			txtDateReceived.setDisable(true);
			txtExpirationDate.setDisable(true);
			btnDeleteItem.setDisable(true);
			checkPerishable.setDisable(true);
			btnAddItem.setDisable(true);
		}
		if (active.getUser(0).getAccessLevel().equals("None")) {
			txtQuantity.setDisable(true);
			btnUpdateItem.setDisable(true);
		}

	}

	@FXML
	void clearBtnAction(ActionEvent event) {
		txtItemName.setText("");
		txtSKU.setText("");
		txtCost.setText("");
		txtPrice.setText("");
		txtQuantity.setText("");
		txtDateReceived.setText("");
		txtExpirationDate.setText("");
		txtExpirationDate.setDisable(true);
		txtManagerNotes.setText("");
		btnAddItem.setDisable(false);
		checkPerishable.setSelected(false);
		btnUpdateItem.setDisable(true);
		btnDeleteItem.setDisable(true);
	}

	@FXML
	void addBtnAction(ActionEvent event) throws IOException {
		double price = Math.round((Double.parseDouble(txtCost.getText())*100))/100 * 1.10;
		if (this.validate()) {
			try {
				for (int i = 0; i < pItems.getSize(); i++) {
					if (pItems.getItem(i).getName().equals(txtItemName.getText())) {
						throw new IOException();
					}
				}
				for (int i = 0; i < npItems.getSize(); i++) {
					if (npItems.getItem(i).getName().equals(txtItemName.getText())) {
						throw new IOException();
					}
				}

				txtPrice.setText(Double.toString(price));
				if (checkPerishable.isSelected()) {
					if (!validate.validateDate(txtExpirationDate.getText())) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Date format error");
						alert.setHeaderText(null);
						alert.setContentText("Date format should be 00-00-0000");
						alert.showAndWait();
					} else {
						PerishableItem pItem = new PerishableItem(txtItemName.getText(), txtSKU.getText(),
								Double.parseDouble(txtCost.getText()), price, Integer.parseInt(txtQuantity.getText()),
								txtDateReceived.getText(), txtExpirationDate.getText());
						pItems.getItems().add(pItem);

						BufferedWriter output = Files.newBufferedWriter(Paths.get("./src/PerishableItem.xml"));
						JAXB.marshal(pItems, output);
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Item Added");
						alert.setHeaderText(null);
						alert.setContentText("Item Added");
						alert.showAndWait();
					}

				} else {
					NonPerishableItem nPItem = new NonPerishableItem(txtItemName.getText(), txtSKU.getText(),
							Double.parseDouble(txtCost.getText()), price, Integer.parseInt(txtQuantity.getText()),
							txtDateReceived.getText());
					npItems.getItems().add(nPItem);

					BufferedWriter output = Files.newBufferedWriter(Paths.get("./src/NonPerishableItem.xml"));
					JAXB.marshal(npItems, output);
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Item Added");
					alert.setHeaderText(null);
					alert.setContentText("Item Added");
					alert.showAndWait();
				}
				for (int i = 0; i < pItems.getSize(); i++) {
					list.add(pItems.getItem(i).getName());
				}
				for (int i = 0; i < npItems.getSize(); i++) {
					list.add(npItems.getItem(i).getName());
				}
				itemSelectionBox.setItems(list);
			} catch (IOException ioException) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Item Already Exists");
				alert.setHeaderText(null);
				alert.setContentText("Item already exists.");
				alert.showAndWait();
			}
		}
	}

	@FXML
	void updateBtnAction(ActionEvent event) {
		boolean existsP = false;
		boolean existsNP = false;
		if (this.validate()) {
			try {
				for (int i = 0; i < pItems.getSize(); i++) {
					if (pItems.getItem(i).getName().equals(txtItemName.getText())) {
						existsP = true;
						if (!validate.validateDate(txtExpirationDate.getText())) {
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Date format error");
							alert.setHeaderText(null);
							alert.setContentText("Date format should be 00-00-0000");
							alert.showAndWait();
						} else {
							pItems.getItem(i).setCost(Double.parseDouble(txtCost.getText()));
							pItems.getItem(i).setDateRecieved(txtDateReceived.getText());
							pItems.getItem(i).setExpirationDate(txtExpirationDate.getText());
							pItems.getItem(i).setQty(Integer.parseInt(txtQuantity.getText()));
							pItems.getItem(i).setPrice(Double.parseDouble(txtPrice.getText()));
							pItems.getItem(i).setSku(txtSKU.getText());
						}
					}
				}
				for (int i = 0; i < npItems.getSize(); i++) {
					if (npItems.getItem(i).getName().equals(txtItemName.getText())) {
						existsNP = true;
						npItems.getItem(i).setCost(Double.parseDouble(txtCost.getText()));
						npItems.getItem(i).setDateRecieved(txtDateReceived.getText());
						npItems.getItem(i).setQty(Integer.parseInt(txtQuantity.getText()));
						npItems.getItem(i).setPrice(Double.parseDouble(txtPrice.getText()));
						npItems.getItem(i).setSku(txtSKU.getText());
					}
				}

				if (existsP && validate.validateDate(txtExpirationDate.getText())) {
					BufferedWriter output = Files.newBufferedWriter(Paths.get("./src/PerishableItem.xml"));
					JAXB.marshal(pItems, output);
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Item Updated");
					alert.setHeaderText(null);
					alert.setContentText("Item Updated");
					alert.showAndWait();
				} else if (existsNP) {
					BufferedWriter output = Files.newBufferedWriter(Paths.get("./src/NonPerishableItem.xml"));
					JAXB.marshal(npItems, output);
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Item Updated");
					alert.setHeaderText(null);
					alert.setContentText("Item Updated");
					alert.showAndWait();

				}
			} catch (IOException ioException) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Item does not exist");
				alert.setHeaderText(null);
				alert.setContentText("Item does not exist");
				alert.showAndWait();
			}
		}
	}

	@FXML
	void deleteBtnAction(ActionEvent event) {
		boolean deleted = false;
		PerishableItems dpItems = new PerishableItems();
		NonPerishableItems dnpItems = new NonPerishableItems();
		for (int i = 0; i < pItems.getSize(); i++) {
			if (!pItems.getItem(i).getName().equals(txtItemName.getText())) {
				dpItems.getItems().add(pItems.getItem(i));
				deleted = true;
			}
		}
		for (int i = 0; i < npItems.getSize(); i++) {
			if (!npItems.getItem(i).getName().equals(txtItemName.getText())) {
				dnpItems.getItems().add(npItems.getItem(i));
				deleted = true;
			}
		}
		pItems = dpItems;
		npItems = dnpItems;
		if (deleted) {
			this.clearBtnAction(event);
			this.initialize();
			itemSelectionBox.setValue("");
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Item deleted");
			alert.setHeaderText(null);
			alert.setContentText("Item ready for deletion but won't be fully deleted untill you save.");
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Item deleted");
			alert.setHeaderText(null);
			alert.setContentText("No Item deleted");
			alert.showAndWait();
		}
	}

	@FXML
	void btnManager(ActionEvent event) throws IOException {
		Stage stageManager = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("ManagerAccess.fxml"));

		Scene scene = new Scene(root); // attach scene graph to scene
		stageManager.setTitle("Manager Access"); // displayed in window's title bar
		stageManager.setScene(scene); // attach scene to stage
		stageManager.show();
	}

	@FXML
	void SaveInventory(ActionEvent event) throws IOException {
		BufferedWriter output = Files.newBufferedWriter(Paths.get("./src/NonPerishableItem.xml"));
		JAXB.marshal(npItems, output);

		BufferedWriter pOutput = Files.newBufferedWriter(Paths.get("./src/PerishableItem.xml"));
		JAXB.marshal(pItems, pOutput);
	}

	@FXML
	void ExitInventory(ActionEvent event) {
		System.exit(0);
	}

	@FXML
	void checkPerishable(ActionEvent event) {
		if (checkPerishable.isSelected()) {
			txtExpirationDate.setDisable(false);
		} else {
			txtExpirationDate.setDisable(true);
		}
	}

	@FXML
	void itemSelected(ActionEvent event) {
		for (int i = 0; i < pItems.getSize(); i++) {
			if (pItems.getItem(i).getName().equals(itemSelectionBox.getValue())) {
				txtItemName.setText(pItems.getItem(i).getName());
				txtSKU.setText(pItems.getItem(i).getSku());
				txtCost.setText(Double.toString(pItems.getItem(i).getCost()));
				txtPrice.setText(Double.toString(pItems.getItem(i).getPrice()));
				txtQuantity.setText(Integer.toString(pItems.getItem(i).getQty()));
				txtDateReceived.setText(pItems.getItem(i).getDateRecieved());
				txtExpirationDate.setText(pItems.getItem(i).getExpirationDate());
				checkPerishable.setSelected(true);
				btnUpdateItem.setDisable(false);
				btnAddItem.setDisable(true);
				btnDeleteItem.setDisable(false);
				if(active.getUser(0).getAccessLevel().equals("Stock")) {
					txtExpirationDate.setDisable(false);
				}
			}
		}
		for (int i = 0; i < npItems.getSize(); i++) {
			if (npItems.getItem(i).getName().equals(itemSelectionBox.getValue())) {
				txtItemName.setText(npItems.getItem(i).getName());
				txtSKU.setText(npItems.getItem(i).getSku());
				txtCost.setText(Double.toString(npItems.getItem(i).getCost()));
				txtPrice.setText(Double.toString(npItems.getItem(i).getPrice()));
				txtQuantity.setText(Integer.toString(npItems.getItem(i).getQty()));
				txtDateReceived.setText(npItems.getItem(i).getDateRecieved());
				checkPerishable.setSelected(false);
				btnUpdateItem.setDisable(false);
				btnAddItem.setDisable(true);
				txtExpirationDate.setText("");
				txtExpirationDate.setDisable(true);
				btnDeleteItem.setDisable(false);
			}
		}
	}
}
