//package application;
//
//import java.sql.SQLException;
//import java.util.LinkedList;
//import java.util.Queue;
//
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.TextField;
//import javafx.stage.Stage;
//
//public class UserPageController {
//
//	private static UserPageController userPageController;
//
//	@FXML
//	private TextField searchText;
//	@FXML
//	private ComboBox<String> searchByList;
//
//	public UserPageController() {
//		if (userPageController != null) {
//			// return userSettingsController;
//		}
//	}
//
//	public static UserPageController getUserPageController() {
//		if (userPageController != null) {
//			return userPageController;
//		}
//		return new UserPageController();
//	}
//
//	public void display() throws Exception {
//
//		Parent root = FXMLLoader.load(getClass().getResource("userPage.fxml"));
//		Scene settingsScene = new Scene(root, 500, 500);
//		// settingsScene.getStylesheets().add(
//		// getClass().getResource("..\\views\\login.css").toString());
//		Stage settingsStage = new Stage();
//		// searchByList.getItems().addAll("Good Will Hunting", "St. Vincent",
//		// "Blackhat");
//
//		settingsStage.setTitle("Libro Online Bookstore");
//		settingsStage.setScene(settingsScene);
//		settingsStage.show();
//
//	}
//
//	/*
//	 * Set All information when tab is selected.
//	 */
//	public void getPersonalInformation() {
//
//	}
//
//	/*
//	 * Username is Label not Text because it is not editable.
//	 */
//	public void editInformation() throws SQLException {
//
//		/*
//		 * Validate Username not exist Validate password not less than 6
//		 * characters Validate Mail form Validate Phone not less than 7 and not
//		 * have characters All Fields not null
//		 */
//		String password = "";
//		String firstName = "";
//		String lastName = "";
//		String email = "";
//		String phone = "";
//		String shippingAddress = "";
//
//		int x = 1;
//		if (x != 0) {
//
//		} else {
//			// Update Statement.
//			String update = "UPDATE users SET password = '" + password
//					+ "', firstName = '" + firstName + "', lastName = '"
//					+ lastName + "', email = '" + email + "', phone = '"
//					+ phone + "' , shippingAddress = '" + shippingAddress
//					+ "')";
//			DatabaseConnection.execute(update);
//			// UserPageController userPageController = UserPageController
//			// .getUserPageController();
//			// userPageController.display();
//			// signupStage.close();
//		}
//
//	}
//
//	// private Queue<String> conditions = new LinkedList<>();
//
//	// private Queue<String> tables = new LinkedList<>();
//
//	/*
//	 * Validate Text Fields and if not Null add the condition to the queue.
//	 * After that add all the condition to the select statement and have AND
//	 * between them.
//	 */
//	public void searchBooks() throws SQLException {
//
//		String searchBy = searchByList.getSelectionModel().getSelectedItem();
//		String searchString = searchText.getText().trim();
//		String searchString2 = searchText.getText().trim();
//		// boolean books = false;
//
//		// if (books) {
//		// tables.add("books");
//		// }
//		String select = "";
//		if (searchBy.equals("ISBN & Title")) {
//
//			select = "SELECT Book.ISBN, Book.Title, category.Category_Name, book_authors.Author_Name"
//					+ ", publisher.Name, Book.Publication_Year, Book.Selling_Price, book_quantity"
//					+ "FROM Book, book_author, category, book_publisher, book_quantity "
//					+ "Where Book.ISBN = '"
//					+ searchString
//					+ "' AND Book.Title LIKE '%"
//					+ searchString2
//					+ "%' AND Book.ISBN = book_authors.ISBN AND Book.Category_ID = category.Category_ID "
//					+ "AND Book.Publisher_ID = publisher.Publisher_ID AND Book.ISBN = book_quantity.ISBN;";
//		} else if (searchBy.equals("ISBN")) {
//			select = "SELECT Book.ISBN, Book.Title, category.Category_Name, book_authors.Author_Name"
//					+ ", publisher.Name, Book.Publication_Year, Book.Selling_Price, book_quantity"
//					+ "FROM Book, book_author, category, book_publisher, book_quantity "
//					+ "Where Book.ISBN = '"
//					+ searchString
//					+ "' AND Book.ISBN = book_authors.ISBN AND Book.Category_ID = category.Category_ID "
//					+ "AND Book.Publisher_ID = publisher.Publisher_ID AND Book.ISBN = book_quantity.ISBN;";
//
//		} else if (searchBy.equals("Title")) {
//			select = "SELECT Book.ISBN, Book.Title, category.Category_Name, book_authors.Author_Name"
//					+ ", publisher.Name, Book.Publication_Year, Book.Selling_Price, book_quantity"
//					+ "FROM Book, book_author, category, book_publisher, book_quantity "
//					+ "Where Book.Title LIKE '%"
//					+ searchString
//					+ "%' AND Book.ISBN = book_authors.ISBN AND Book.Category_ID = category.Category_ID "
//					+ "AND Book.Publisher_ID = publisher.Publisher_ID AND Book.ISBN = book_quantity.ISBN;";
//		} else if (searchBy.equals("Category")) {
//			select = "SELECT Book.ISBN, Book.Title, category.Category_Name, book_authors.Author_Name"
//					+ ", publisher.Name, Book.Publication_Year, Book.Selling_Price, book_quantity"
//					+ "FROM Book, book_author, category, book_publisher, book_quantity "
//					+ "Where category.Category_Name  LIKE '%"
//					+ searchString
//					+ "%' AND Book.ISBN = book_authors.ISBN AND Book.Category_ID = category.Category_ID "
//					+ "AND Book.Publisher_ID = publisher.Publisher_ID AND Book.ISBN = book_quantity.ISBN;";
//		} else if (searchBy.equals("Author Name")) {
//			select = "SELECT Book.ISBN, Book.Title, category.Category_Name, book_authors.Author_Name"
//					+ ", publisher.Name, Book.Publication_Year, Book.Selling_Price, book_quantity"
//					+ "FROM Book, book_author, category, book_publisher, book_quantity "
//					+ "Where book_authors.Author_Name LIKE '%"
//					+ searchString
//					+ "%' AND Book.ISBN = book_authors.ISBN AND Book.Category_ID = category.Category_ID "
//					+ "AND Book.Publisher_ID = publisher.Publisher_ID AND Book.ISBN = book_quantity.ISBN;";
//		} else if (searchBy.equals("Publisher Name")) {
//			select = "SELECT Book.ISBN, Book.Title, category.Category_Name, book_authors.Author_Name"
//					+ ", publisher.Name, Book.Publication_Year, Book.Selling_Price, book_quantity"
//					+ "FROM Book, book_author, category, book_publisher, book_quantity "
//					+ "Where publisher.Name LIKE '%"
//					+ searchString
//					+ "%' AND Book.ISBN = book_authors.ISBN AND Book.Category_ID = category.Category_ID "
//					+ "AND Book.Publisher_ID = publisher.Publisher_ID AND Book.ISBN = book_quantity.ISBN;";
//
//		}
//
//		// for (int i = 0; i < conditions.size(); i++) {
//		// if (i != 0) {
//		// select += " AND ";
//		// }
//		// select += conditions.poll();
//		// }
//		DatabaseConnection.execute(select);
//
//		/*
//		 * Show Result with Buy Button.
//		 */
//
//	}
//
//	public void manageCartBook() throws SQLException {
//		String select = "SELECT Book.ISBN, Book.Title, category.Category_Name, book_authors.Author_Name"
//				+ ", publisher.Name, Book.Publication_Year, Book.Selling_Price, shipping_cart.Quantity "
//				+ "FROM Book, book_author, category, book_publisher "
//				+ "Where shipping_cart.ISBN = Book.ISBN AND shipping_cart.Username = '"
//				+ Utilities.username
//				+ "' AND shipping_cart.ISBN = book_authors.ISBN AND Book.Category_ID = category.Category_ID "
//				+ "AND Book.Publisher_ID = publisher.Publisher_ID;";
//
//		DatabaseConnection.execute(select);
//
//		/*
//		 * Show Result with Remove Button. 1- Remove on runtime as video. 2-
//		 * Update total price after remove (in remove button).
//		 */
//
//		String delete = "DELETE FROM shipping_cart Where shipping_cart.Username = "
//				+ Utilities.username + " AND shipping_cart.ISBN = " + "isbn"; // isbn
//
//	}
//
//	public void logout() throws SQLException {
//
//		String delete = "DELETE FROM shipping_cart WHERE username = "
//				+ Utilities.username + ";";
//		DatabaseConnection.execute(delete);
//
//		/*
//		 * Return to Login Page.
//		 */
//
//	}
//
// }
