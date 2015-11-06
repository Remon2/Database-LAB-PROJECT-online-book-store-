package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CheckoutController implements Initializable {

	@FXML
	private Button checkOutButton;

	@FXML
	private Button closeButton;

	@FXML
	private TextField expiryDateTaxt;

	@FXML
	private TextField creditCardText;

	private static CheckoutController checkoutController;
	private static Stage checkoutStage;
	private static String data[] = new String[3];

	public CheckoutController() {
		if (checkoutController != null) {
			// return userSettingsController;
		}
	}

	public static CheckoutController getCheckoutController() {
		if (checkoutController != null) {
			return checkoutController;
		}
		return new CheckoutController();
	}

	public String[] display() throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("Checkout.fxml"));
		Scene checkoutScene = new Scene(root, 286, 111);
		// settingsScene.getStylesheets().add(
		// getClass().getResource("..\\views\\login.css").toString());
		checkoutStage = new Stage();

		checkoutStage.setTitle("Libro Online Bookstore");
		checkoutStage.setScene(checkoutScene);
		System.out.println("OPENED");
		// checkoutStage.showAndWait();
		checkoutStage.setResizable(false);
		checkoutStage.initModality(Modality.APPLICATION_MODAL);
		checkoutStage.showAndWait();
		return data;
	}

	public void checkOut() {

	}

	public void close() {
		// checkoutStage.close();
		// System.out.println("CLOSED!!!");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		closeButton.setOnAction(e -> {
			data[0] = "0";
			checkoutStage.close();
		});
		checkOutButton.setOnAction(e -> {
			data[0] = "1";
			if (!creditCardText.getText().trim().isEmpty()
					&& !expiryDateTaxt.getText().trim().isEmpty()) {
				data[1] = creditCardText.getText().trim();
				data[2] = expiryDateTaxt.getText().trim();
				checkoutStage.close();
			}else{
				AlertController alertController = new AlertController();
				try {
					alertController
							.display("You entered Invalid Credit Card Number or Expiry Date.");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

}
