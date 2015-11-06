package application;
import java.sql.ResultSet;
import java.time.LocalDateTime;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController extends Application {
	public static Stage loginStage;
	private static LoginController loginController;
	
	@FXML
	private TextField usernameText;
	@FXML
	private PasswordField passwordText;
	@FXML
	private Label wrongLabel;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		loginStage = primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene login = new Scene(root);
		login.getStylesheets().add(
				getClass().getResource("login.css").toString());
		primaryStage.setTitle("Libro Online Bookstore");
		primaryStage.setScene(login);
		primaryStage.setMaximized(true);
		primaryStage.show();
		DatabaseConnection.connect();

	}



	public LoginController() {
		if (loginController != null) {
			// return userSettingsController;
		}
	}

	public static LoginController getLoginController() {
		if (loginController != null) {
			return loginController;
		}
		return new LoginController();
	}

	public void display() throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
		Scene settingsScene = new Scene(root);
		loginStage = new Stage();
		loginStage.setTitle("Libro Online Bookstore");
		loginStage.setMaximized(true);
		loginStage.setScene(settingsScene);
		loginStage.show();

	}


	
	
	public StringProperty textProperty(TextField textField) {
		return textField.textProperty();
	}

	public String getText(TextField textField) {
		return textProperty(textField).get();
	}

	public void setText(TextField textField, String value) {
		textProperty(textField).set(value);
	}

	public void login2() throws Exception {
		System.out.println("Login button clicked!");
		ManagerPageController managerPageController = ManagerPageController
				.getManagerPageController();
		managerPageController.display();
		loginStage.close();

	}

	public void login() throws Exception {
		System.out.println("Login button clicked!");
		System.out.println(usernameText.getText());
		Utilities.username = usernameText.getText().trim();
		String password = passwordText.getText();
		if (Utilities.username.isEmpty() || password.isEmpty()) {
			wrongLabel.setText("Wrong username or password.");
		} else {
			Utilities.date = LocalDateTime.now().toString();		
			String select = "SELECT * from users where "
					+ "username ='"
					+ Utilities.username
					+ "' AND password = '"
					+ password + "'";
			System.out.println(select);
			ResultSet result = DatabaseConnection.execute(select);
			if (result.next()) {
				
				wrongLabel.setText("");
				Utilities.isManager = result.getBoolean("is_manager");
				Utilities.username=result.getString("username");
				Utilities.password=result.getString("password");
				Utilities.email=result.getString("Email");
				Utilities.firstName=result.getString("first_name");
				Utilities.lastName=result.getString("last_name");
				Utilities.shoppingAddress=result.getString("shopping_address");
				Utilities.phone=result.getString("phone");
					ManagerPageController managerPageController = ManagerPageController
							.getManagerPageController();
					managerPageController.display();
					loginStage.close();
			} else {
				wrongLabel.setText("Wrong username or password.");
			}

		}

	}

	public void signup() throws Exception {
		System.out.println("Sign up button clicked!");
		SignupController signupController = SignupController
				.getUserSettingsController();
		signupController.display();
	}

}
