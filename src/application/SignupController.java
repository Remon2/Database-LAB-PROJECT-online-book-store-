package application;

import java.sql.ResultSet;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignupController {

	private static SignupController signupController;
	private static Stage signupStage;

	@FXML
	private TextField usernameText;
	@FXML
	private TextField emailText;
	@FXML
	private PasswordField passwordText;
	@FXML
	private PasswordField confirmPasswordText;
	@FXML
	private TextField firstNameText;
	@FXML
	private TextField lastNameText;
	@FXML
	private TextField shippingAddressText;
	@FXML
	private TextField phoneText;

	@FXML
	private Label usernameLabel;
	@FXML
	private Label emailLabel;
	@FXML
	private Label passwordLabel;
	@FXML
	private Label confirmPasswordLabel;
	@FXML
	private Label firstNameLabel;
	@FXML
	private Label lastNameLabel;
	@FXML
	private Label shippingAddressLabel;
	@FXML
	private Label phoneLabel;

	@FXML
	private Label usererror;
	@FXML
	private Label emailerror;
	@FXML
	private Label passworderror;
	@FXML
	private Label confirmerror;
	@FXML
	private Label firstnameerror;
	@FXML
	private Label lastnameerror;
	@FXML
	private Label addresserror;
	@FXML
	private Label phonenumbererror;

	public SignupController() {
		if (signupController != null) {
			// return userSettingsController;
		}
	}

	public static SignupController getUserSettingsController() {
		if (signupController != null) {
			return signupController;
		}
		return new SignupController();
	}

	public void display() throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
		Scene settingsScene = new Scene(root);
		// settingsScene.getStylesheets().add(
		// getClass().getResource("..\\views\\login.css").toString());
		signupStage = new Stage();
		signupStage.setTitle("Libro Online Bookstore");
		signupStage.setScene(settingsScene);
		signupStage.setMaximized(true);
		signupStage.show();

	}

	public void registerUser() throws Exception {

		/*
		 * Validate Username not exist Validate password not less than 6
		 * characters Validate Mail form Validate Phone not less than 7 and not
		 * have characters All Fields not null
		 */
		int x = 0;
		String username = usernameText.getText().trim();
		String password = passwordText.getText().trim();
		String confirmPassword = confirmPasswordText.getText().trim();
		String firstName = firstNameText.getText().trim();
		String lastName = lastNameText.getText().trim();
		String email = emailText.getText().trim();
		String phone = phoneText.getText().trim();
		String shoppingAddress = shippingAddressText.getText().trim();
		if (username.isEmpty()) {
			usererror.setText("You must insert username.");
			x = 1;
		} else if (!username.isEmpty()) {
			String select = "SELECT username  from users where "
					+ "username ='" + username + "'";
			ResultSet result = DatabaseConnection.execute(select);
			if (result.next()) {
				usererror.setText("This user name exists before");
				x = 1;
			} else {
				usererror.setText("");
			}
		}
		if (email.isEmpty()) {
			emailerror.setText("You must insert your email.");
			x = 1;
		} else if (!email.isEmpty()) {
			if (!isValidEmailAddress(email)) {
				emailerror.setText("not valid email.");
				x = 1;
			} else {
				emailerror.setText("");
			}
		}
		if (password.isEmpty()) {
			passworderror.setText("You must insert your password.");
			x = 1;
		} else if (!password.isEmpty()) {
			if (password.length() < 6) {
				passworderror.setText("password must be more than 6 letters.");
				x = 1;
			} else {
				passworderror.setText("");
			}
		}
		if (confirmPassword.isEmpty()) {
			confirmerror.setText("You must re-enter your password.");
			x = 1;
		} else if (!confirmPassword.isEmpty()) {
			if (!password.equals(confirmPassword)) {
				confirmerror.setText("password doesn't match.");
				x = 1;
			} else {
				confirmerror.setText("");
			}
		}
		if (firstName.isEmpty()) {
			firstnameerror.setText("You must enter your first name.");
			x = 1;
		} else if (!firstName.isEmpty()) {
			firstnameerror.setText("");
		}
		if (lastName.isEmpty()) {
			lastnameerror.setText("You must enter your last name.");
			x = 1;
		} else if (!lastName.isEmpty()) {
			lastnameerror.setText("");
		}
		if (shoppingAddress.isEmpty()) {
			addresserror.setText("You must enter your shoppingAddress.");
			x = 1;
		} else if (!shoppingAddress.isEmpty()) {
			addresserror.setText("");
		}
		if (phone.isEmpty()) {
			phonenumbererror.setText("You must enter your phonenumber.");
			x = 1;
		} else if (!phone.isEmpty()) {
			boolean isValid = phone.matches("\\+\\d(-\\d{3}){2}-\\d{4}");
			if (isValid) {
				phonenumbererror.setText("not valid phonenumber.");
				x = 1;
			} else {
				phonenumbererror.setText("");
			}
		}

		if (x != 0) {

		} else {
			String insert = "INSERT INTO users VALUES ( '" + username + "', '"
					+ password + "', '" + firstName + "', '" + lastName
					+ "', '" + email + "', '" + phone + "', '"
					+ shoppingAddress + "', " + false + ")";
			DatabaseConnection.execute2(insert);
			Utilities.isManager = false;
			Utilities.username = username;
			Utilities.firstName = firstName;
			Utilities.lastName = lastName;
			Utilities.email = email;
			Utilities.password = password;
			Utilities.shoppingAddress = shoppingAddress;
			Utilities.phone = phone;
			ManagerPageController managerPageController = ManagerPageController
					.getManagerPageController();
			Utilities.isManager = false;
			managerPageController.display();
			signupStage.close();
			LoginController.loginStage.close();
		}

	}

	public boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}
}
