package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertController implements Initializable {

	private static AlertController alertController;

	@FXML
	private Label alertLabell;

	@FXML
	private Button okButton;

	private static Stage checkoutStage;
	private static String alert;

	public AlertController() {
		if (alertController != null) {
			// return userSettingsController;
		}
	}

	public static AlertController getAlertController() {
		if (alertController != null) {
			return alertController;
		}
		return new AlertController();
	}

	public void display(String textt) throws Exception {
		alert = textt;

		Parent root = FXMLLoader.load(getClass().getResource("Alert.fxml"));
		Scene checkoutScene = new Scene(root, 330, 72);
		// settingsScene.getStylesheets().add(
		// getClass().getResource("..\\views\\login.css").toString());
		checkoutStage = new Stage();

		checkoutStage.setTitle("Libro Online Bookstore");
		checkoutStage.setScene(checkoutScene);
		System.out.println("OPENED");
//		alertLabell.setText(textt);
		// checkoutStage.showAndWait();
		checkoutStage.setResizable(false);
		checkoutStage.initModality(Modality.APPLICATION_MODAL);
		checkoutStage.showAndWait();
	}

	public void close() {
		checkoutStage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		alertLabell.setText(alert);
	}

}
