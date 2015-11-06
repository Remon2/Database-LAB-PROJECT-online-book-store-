package application;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.adapter.JavaBeanDoublePropertyBuilder;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ManagerPageController implements Initializable {
	public static Stage settingsStage;
	@FXML
	private Label showUserName;

	@FXML
	private Label buyError;

	@FXML
	private TextField quantityText;
	@FXML
	private Label quantityError;

	@FXML
	private TextField buyText;
	@FXML
	private TableColumn<BuyTable, String> authorNameColumn4;
	@FXML
	private TableColumn<BuyTable, String> publisherNameColumn4;
	@FXML
	private TableColumn<BuyTable, String> categoryColumn4;
	@FXML
	private TableColumn<BuyTable, BuyTable> placeOrderColumn;
	@FXML
	private TableColumn<BuyTable, BuyTable> quantityColumn4;
	@FXML
	private TableColumn<BuyTable, String> titleColumn4;
	@FXML
	private TableView<BuyTable> placeOrderTable;
	@FXML
	private TableView<Order> confirmOrderTable;
	@FXML
	private TableView<User> viewUserTable;
	@FXML
	private TableColumn<Order, String> isbnColumn5;
	@FXML
	private TableColumn<Order, String> dateColumn5;
	@FXML
	private TableColumn<Order, Order> confirmOrderColumn;
	@FXML
	private TableColumn<Order, String> quantityColumn5;
	@FXML
	private TableColumn<User, String> usernameColumn;
	@FXML
	private TableColumn<User, String> passwordColumn;
	@FXML
	private TableColumn<User, String> firstNameColumn;
	@FXML
	private TableColumn<User, String> lastNameColumn;
	@FXML
	private TableColumn<User, String> emailColumn;
	@FXML
	private TableColumn<User, String> phoneColumn;
	@FXML
	private TableColumn<User, String> shippingAddressColumn;
	@FXML
	private TableColumn<User, User> isManagerColumn;

	@FXML
	private ComboBox<String> search;

	@FXML
	private TableColumn<BuyTable, String> isbnColumn;
	@FXML
	private TableColumn<BuyTable, String> isbnColumn2;
	@FXML
	private TableColumn<BuyTable, String> isbnColumn3;
	@FXML
	private TableColumn<BuyTable, String> isbnColumn4;

	@FXML
	private Tab viewUsersTab;
	@FXML
	private TableColumn<BuyTable, String> priceColumn;
	@FXML
	private TableColumn<BuyTable, String> priceColumn2;
	@FXML
	private TableColumn<BuyTable, String> priceColumn3;
	@FXML
	private TableColumn<BuyTable, String> priceColumn4;

	@FXML
	private TableColumn<BuyTable, String> publicatonYearColumn;
	@FXML
	private TableColumn<BuyTable, String> publicatonYearColumn2;
	@FXML
	private TableColumn<BuyTable, String> publicatonYearColumn3;
	@FXML
	private TableColumn<BuyTable, String> publicatonYearColumn4;

	@FXML
	private TableColumn<BuyTable, String> authorNameColumn;
	@FXML
	private TableColumn<BuyTable, String> authorNameColumn2;
	@FXML
	private TableColumn<BuyTable, String> authorNameColumn3;

	@FXML
	private PasswordField passwordText;
	@FXML
	private TableColumn<BuyTable, String> publisherNameColumn;
	@FXML
	private TableColumn<BuyTable, String> publisherNameColumn2;
	@FXML
	private TableColumn<BuyTable, String> publisherNameColumn3;
	@FXML
	private TextField lastNameText;

	@FXML
	private PasswordField confirmPasswordText;
	@FXML
	private TableColumn<BuyTable, String> categoryColumn;
	@FXML
	private TableColumn<BuyTable, String> categoryColumn2;
	@FXML
	private TableColumn<BuyTable, String> categoryColumn3;
	@FXML
	private TableColumn<BuyTable, BuyTable> buyColumn;
	@FXML
	private TableColumn<BuyTable, BuyTable> modifyColumn;
	@FXML
	private TableColumn<BuyTable, BuyTable> removeColumn;
	@FXML
	private TableColumn<BuyTable, BuyTable> quantityColumn;
	@FXML
	private TableColumn<BuyTable, BuyTable> quantityColumn2;
	@FXML
	private TableColumn<BuyTable, BuyTable> quantityColumn3;
	@FXML
	private TableColumn<BuyTable, Number> totalPriceColumn;
	@FXML
	private TableColumn<BuyTable, String> titleColumn;
	@FXML
	private TableColumn<BuyTable, String> titleColumn2;
	@FXML
	private TableColumn<BuyTable, String> titleColumn3;

	@FXML
	private TableView<BuyTable> buyTable;
	@FXML
	private TableView<BuyTable> cartTable;
	@FXML
	private TableView<BuyTable> modifyTable;

	@FXML
	private TabPane tabPane;
	@FXML
	private Label totalPriceLabel;
	private static ManagerPageController managerPageController;

	@FXML
	private Button editInformationButton;

	@FXML
	private ComboBox<String> ssearchByList;

	@FXML
	private ComboBox<String> ssearchByList2;

	@FXML
	private ComboBox<String> ssearchByList3;
	@FXML
	private ComboBox<String> ssearchByList4;

	@FXML
	private RadioButton newPublisherRadio;

	@FXML
	private TextField emailText;
	@FXML
	private TextField cellingPriceText;

	@FXML
	private ComboBox<String> authorsList;

	@FXML
	private TextField firstNameText;
	@FXML
	private TextField phoneText;

	@FXML
	private RadioButton currentCategoryRadio;

	@FXML
	private TextField ssearchText;
	@FXML
	private TextField ssearchText2;

	@FXML
	private TextField ssearchText3;
	@FXML
	private TextField ssearchText4;
	@FXML
	private Label searcherror2;
	@FXML
	private Label searcherror3;

	@FXML
	private Tab addBookTab;
	@FXML
	private Tab welcomeTab;

	@FXML
	private TextField titleText;

	@FXML
	private TextField publicationYearText;

	@FXML
	private ComboBox<String> categoryList;

	@FXML
	private ToggleGroup publisherGroup;

	@FXML
	private RadioButton currentPublisherRadio;

	@FXML
	private TextField thresholdText;

	@FXML
	private Button addBookButton;
	@FXML
	private Button showReportsButtons;
	@FXML
	private Button logoutButton;

	@FXML
	private Button addAuthor;
	@FXML
	private Button removeAuthor;

	@FXML
	private TextField newCategoryText;

	@FXML
	private TextField publisherAddressText;

	@FXML
	private TextField shippingAddressText;

	@FXML
	private ComboBox<String> publisherList;

	@FXML
	private RadioButton newCategoryRadio;

	@FXML
	private TextField publisherNameText;

	@FXML
	private TextField usernameText;

	@FXML
	private TextField publisherPhoneText;

	@FXML
	private TextField isbnText;

	@FXML
	private ToggleGroup categoryGroup;

	@FXML
	private Label usernameLabel1;
	@FXML
	private Label passwordLabe1;
	@FXML
	private Label confirmLabel1;
	@FXML
	private Label emailLabel1;
	@FXML
	private Label firstnameLabel1;
	@FXML
	private Label lastnameLabel1;
	@FXML
	private Label addressLabel1;
	@FXML
	private Label phoneLabel1;
	@FXML
	private Label authorerrorLabel1;
	@FXML
	private Label invalidPublisher1;
	@FXML
	private Label invalidTitle;
	@FXML
	private Label usernameerrorLabel1;
	@FXML
	private Label passworderrorLabe1;
	@FXML
	private Label confirmerrorLabel1;
	@FXML
	private Label emailerrorLabel1;
	@FXML
	private Label firstnameerrorLabel1;
	@FXML
	private Label lastnameerrorLabel1;
	@FXML
	private Label addresserrorLabel1;
	@FXML
	private Label phoneerrorLabel1;
	@FXML
	private Tab buyBooks;
	@FXML
	private Label usernametext1;
	@FXML
	private PasswordField passworderrortext1;
	@FXML
	private PasswordField confirmerrortext1;
	@FXML
	private TextField emailerrortext1;
	@FXML
	private TextField firstnameerrortext1;
	@FXML
	private TextField lastnameerrortext1;
	@FXML
	private TextField addresserrortext1;
	@FXML
	private TextField phoneerrortext1;

	@FXML
	private Label isbnerrortext1;
	@FXML
	private Label threolderrortext1;
	@FXML
	private Label pricerrortext1;
	@FXML
	private Label yearerrortext1;
	@FXML
	private Label invalidcateogry;

	@FXML
	private Label searchError;
	@FXML
	private Label searchError2;
	@FXML
	private Label showName1;
	@FXML
	private Tab placeOrderTab;
	@FXML
	private Tab confirmOrderTab;
	@FXML
	private Tab modifyBooksTab;

	double total = 0;

	ObservableList<BuyTable> foundRecords = FXCollections.observableArrayList();
	ObservableList<BuyTable> modifyRecords = FXCollections
			.observableArrayList();
	ObservableList<BuyTable> manageRecords = FXCollections
			.observableArrayList();
	ObservableList<User> userRecords = FXCollections.observableArrayList();
	ObservableList<BuyTable> placeOrderRecords = FXCollections
			.observableArrayList();
	ObservableList<Order> confirmOrderRecords = FXCollections
			.observableArrayList();

	private void initializeBuyTable() {
		isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		categoryColumn.setCellValueFactory(new PropertyValueFactory<>(
				"category"));
		authorNameColumn.setCellValueFactory(new PropertyValueFactory<>(
				"authorName"));
		publisherNameColumn.setCellValueFactory(new PropertyValueFactory<>(
				"publisherName"));
		publicatonYearColumn.setCellValueFactory(new PropertyValueFactory<>(
				"publicationYear"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		quantityColumn
				.setCellValueFactory(new Callback<CellDataFeatures<BuyTable, BuyTable>, ObservableValue<BuyTable>>() {
					@Override
					public ObservableValue<BuyTable> call(
							CellDataFeatures<BuyTable, BuyTable> features) {
						return new ReadOnlyObjectWrapper<BuyTable>(features
								.getValue());
					}
				});

		quantityColumn.setCellFactory(new QuantityComboBox());

		buyColumn
				.setCellValueFactory(new Callback<CellDataFeatures<BuyTable, BuyTable>, ObservableValue<BuyTable>>() {
					@Override
					public ObservableValue<BuyTable> call(
							CellDataFeatures<BuyTable, BuyTable> features) {
						return new ReadOnlyObjectWrapper<BuyTable>(features
								.getValue());
					}
				});

		buyColumn.setCellFactory(new AddToCartButton<BuyTable>(0));
		buyColumn.setSortable(false);
		buyTable.setItems(foundRecords);
	}

	private void initializeModifyBookTable() {
		isbnColumn3.setCellValueFactory(new PropertyValueFactory<>("isbn"));
		titleColumn3.setCellValueFactory(new PropertyValueFactory<>("title"));
		categoryColumn3.setCellValueFactory(new PropertyValueFactory<>(
				"category"));
		authorNameColumn3.setCellValueFactory(new PropertyValueFactory<>(
				"authorName"));
		publisherNameColumn3.setCellValueFactory(new PropertyValueFactory<>(
				"publisherName"));
		publicatonYearColumn3.setCellValueFactory(new PropertyValueFactory<>(
				"publicationYear"));
		priceColumn3.setCellValueFactory(new PropertyValueFactory<>("price"));

		modifyColumn
				.setCellValueFactory(new Callback<CellDataFeatures<BuyTable, BuyTable>, ObservableValue<BuyTable>>() {
					@Override
					public ObservableValue<BuyTable> call(
							CellDataFeatures<BuyTable, BuyTable> features) {
						return new ReadOnlyObjectWrapper<BuyTable>(features
								.getValue());
					}
				});

		modifyColumn.setCellFactory(new AddToCartButton<BuyTable>(2));
		modifyColumn.setSortable(false);

		modifyTable.setItems(modifyRecords);
	}

	private void initializePlaceOrderTable() {
		isbnColumn4.setCellValueFactory(new PropertyValueFactory<>("isbn"));
		titleColumn4.setCellValueFactory(new PropertyValueFactory<>("title"));
		categoryColumn4.setCellValueFactory(new PropertyValueFactory<>(
				"category"));
		authorNameColumn4.setCellValueFactory(new PropertyValueFactory<>(
				"authorName"));
		publisherNameColumn4.setCellValueFactory(new PropertyValueFactory<>(
				"publisherName"));
		publicatonYearColumn4.setCellValueFactory(new PropertyValueFactory<>(
				"publicationYear"));
		priceColumn4.setCellValueFactory(new PropertyValueFactory<>("price"));
		quantityColumn4.setCellValueFactory(new PropertyValueFactory<>(
				"bookQuantity"));
		placeOrderColumn
				.setCellValueFactory(new Callback<CellDataFeatures<BuyTable, BuyTable>, ObservableValue<BuyTable>>() {
					@Override
					public ObservableValue<BuyTable> call(
							CellDataFeatures<BuyTable, BuyTable> features) {
						return new ReadOnlyObjectWrapper<BuyTable>(features
								.getValue());
					}
				});

		placeOrderColumn.setCellFactory(new AddToCartButton<BuyTable>(3));
		placeOrderColumn.setSortable(false);

		placeOrderTable.setItems(placeOrderRecords);
	}

	private void initializeConfirmOrderTable() {
		isbnColumn5.setCellValueFactory(new PropertyValueFactory<>("isbn"));
		quantityColumn5.setCellValueFactory(new PropertyValueFactory<>(
				"quantity"));
		dateColumn5.setCellValueFactory(new PropertyValueFactory<>("date"));

		confirmOrderColumn
				.setCellValueFactory(new Callback<CellDataFeatures<Order, Order>, ObservableValue<Order>>() {
					@Override
					public ObservableValue<Order> call(
							CellDataFeatures<Order, Order> features) {
						return new ReadOnlyObjectWrapper<Order>(features
								.getValue());
					}
				});

		confirmOrderColumn.setCellFactory(new AddToCartButton<Order>(4));
		confirmOrderColumn.setSortable(false);

		confirmOrderTable.setItems(confirmOrderRecords);
	}

	private void initializeViewUserTable() {
		usernameColumn.setCellValueFactory(new PropertyValueFactory<>(
				"username"));
		passwordColumn.setCellValueFactory(new PropertyValueFactory<>(
				"password"));
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<>(
				"firstName"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<>(
				"lastName"));
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
		phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
		shippingAddressColumn.setCellValueFactory(new PropertyValueFactory<>(
				"shippingAddress"));

		isManagerColumn
				.setCellValueFactory(new Callback<CellDataFeatures<User, User>, ObservableValue<User>>() {
					@Override
					public ObservableValue<User> call(
							CellDataFeatures<User, User> features) {
						return new ReadOnlyObjectWrapper<User>(features
								.getValue());
					}
				});

		isManagerColumn.setCellFactory(new AddToCartButton<User>(5));
		isManagerColumn.setSortable(false);

		viewUserTable.setItems(userRecords);
	}

	private void initializeCartTable() {
		isbnColumn2.setCellValueFactory(new PropertyValueFactory<>("isbn"));
		titleColumn2.setCellValueFactory(new PropertyValueFactory<>("title"));
		categoryColumn2.setCellValueFactory(new PropertyValueFactory<>(
				"category"));
		authorNameColumn2.setCellValueFactory(new PropertyValueFactory<>(
				"authorName"));
		publisherNameColumn2.setCellValueFactory(new PropertyValueFactory<>(
				"publisherName"));
		publicatonYearColumn2.setCellValueFactory(new PropertyValueFactory<>(
				"publicationYear"));
		priceColumn2.setCellValueFactory(new PropertyValueFactory<>("price"));
		totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>(
				"totalPrice"));

		totalPriceColumn
				.setCellValueFactory(new Callback<CellDataFeatures<BuyTable, Number>, ObservableValue<Number>>() {
					@Override
					public ObservableValue<Number> call(
							CellDataFeatures<BuyTable, Number> data) {
						try {
							final ObservableValue<Number> valueProperty = new JavaBeanDoublePropertyBuilder()
									.bean(data.getValue()).name("totalPrice")
									.build();
							return valueProperty;
						} catch (Exception e) {
							e.printStackTrace();
							throw new RuntimeException(e);
						}
					}
				});

		quantityColumn2
				.setCellValueFactory(new Callback<CellDataFeatures<BuyTable, BuyTable>, ObservableValue<BuyTable>>() {
					@Override
					public ObservableValue<BuyTable> call(
							CellDataFeatures<BuyTable, BuyTable> features) {
						return new ReadOnlyObjectWrapper<BuyTable>(features
								.getValue());
					}
				});

		quantityColumn2.setCellFactory(new QuantityComboBox(1));

		removeColumn
				.setCellValueFactory(new Callback<CellDataFeatures<BuyTable, BuyTable>, ObservableValue<BuyTable>>() {
					@Override
					public ObservableValue<BuyTable> call(
							CellDataFeatures<BuyTable, BuyTable> features) {
						return new ReadOnlyObjectWrapper<BuyTable>(features
								.getValue());
					}
				});

		removeColumn.setCellFactory(new AddToCartButton<BuyTable>(1));
		removeColumn.setSortable(false);

		cartTable.setItems(manageRecords);
	}

	public ManagerPageController() {
		if (managerPageController != null) {
			// return userSettingsController;
		}
	}

	public static ManagerPageController getManagerPageController() {
		if (managerPageController != null) {
			return managerPageController;
		}
		return new ManagerPageController();
	}

	public void display() throws Exception {

		Parent root = FXMLLoader.load(getClass()
				.getResource("ManagerPage.fxml"));

		Scene settingsScene = new Scene(root, 500, 500);
		settingsStage = new Stage();
		settingsStage.setTitle("Libro Online Bookstore");
		settingsStage.setScene(settingsScene);
		settingsStage.setMaximized(true);
		settingsStage.show();
		System.out.println("Hello , " + Utilities.username);

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		showUserName.setText("Hello, " + Utilities.username);
		ImageView imageView2 = new ImageView("\\logout.png");
		imageView2.setFitWidth(20);
		imageView2.setFitHeight(20);
		logoutButton.setPadding(new Insets(5));
		logoutButton.setGraphic(imageView2);
		initializeBuyTable();
		initializeCartTable();
		if (!Utilities.isManager) {
			System.out.println(Utilities.isManager);
			tabPane.getTabs().removeAll(viewUsersTab, confirmOrderTab,
					placeOrderTab, modifyBooksTab, addBookTab);
			showReportsButtons.setVisible(false);
		} else {
			initializeModifyBookTable();
			initializePlaceOrderTable();
			initializeConfirmOrderTable();
			initializeViewUserTable();
			ImageView imageView = new ImageView("\\reports.png");
			imageView.setFitWidth(20);
			imageView.setFitHeight(20);
			showReportsButtons.setPadding(new Insets(5));
			showReportsButtons.setGraphic(imageView);

			// authorsList.setOnAction(e -> System.out.println(authorsList
			// .getItems().toString()));
			publisherGroup.selectToggle(currentPublisherRadio);
			currentPublisherRadio.setSelected(true);
			categoryGroup.selectToggle(currentCategoryRadio);
			currentCategoryRadio.setSelected(true);
		}
	}

	public void getPersonalInformation() {

		usernametext1.setText(Utilities.username);
		passworderrortext1.setText(Utilities.password);
		emailerrortext1.setText(Utilities.email);
		phoneerrortext1.setText(Utilities.phone);
		firstnameerrortext1.setText(Utilities.firstName);
		lastnameerrortext1.setText(Utilities.lastName);
		addresserrortext1.setText(Utilities.shoppingAddress);
		showUserName.setText("Hello , " + Utilities.username);

	}

	public void edit() throws SQLException {

		int x = 0;
		String username = usernametext1.getText().trim();
		String password = passworderrortext1.getText().trim();
		String confirmPassword = confirmerrortext1.getText().trim();
		String firstName = firstnameerrortext1.getText().trim();
		String lastName = lastnameerrortext1.getText().trim();
		String email = emailerrortext1.getText().trim();
		String phone = phoneerrortext1.getText().trim();
		String shoppingAddress = addresserrortext1.getText().trim();
		if (email.isEmpty()) {
			emailerrorLabel1.setText("You must insert your email.");
			x = 1;
		} else if (!email.isEmpty()) {
			if (!isValidEmailAddress(email)) {
				emailerrorLabel1.setText("not valid email.");
				x = 1;
			} else {
				emailerrorLabel1.setText("");
			}
		}
		if (password.isEmpty()) {
			passworderrorLabe1.setText("You must insert your password.");
			x = 1;
		} else if (!password.isEmpty()) {
			if (password.length() < 6) {
				passworderrorLabe1
						.setText("password must be more than 6 letters.");
				x = 1;
			} else {
				passworderrorLabe1.setText("");
			}
		}
		if (confirmPassword.isEmpty()) {
			confirmerrorLabel1.setText("You must re-enter your password.");
			x = 1;
		} else if (!confirmPassword.isEmpty()) {
			if (!password.equals(confirmPassword)) {
				confirmerrorLabel1.setText("password doesn't match.");
				x = 1;
			} else {
				confirmerrorLabel1.setText("");
			}
		}
		if (firstName.isEmpty()) {
			firstnameerrorLabel1.setText("You must enter your first name.");
			x = 1;
		} else if (!firstName.isEmpty()) {
			firstnameerrorLabel1.setText("");
		}
		if (lastName.isEmpty()) {
			lastnameerrorLabel1.setText("You must enter your last name.");
			x = 1;
		} else if (!lastName.isEmpty()) {
			lastnameerrorLabel1.setText("");
		}
		if (shoppingAddress.isEmpty()) {
			addresserrorLabel1.setText("You must enter your shoppingAddress.");
			x = 1;
		} else if (!shoppingAddress.isEmpty()) {
			addresserrorLabel1.setText("");
		}
		if (phone.isEmpty()) {
			phoneerrorLabel1.setText("You must enter your phonenumber.");
			x = 1;
		} else if (!phone.isEmpty()) {
			boolean isValid = phone.matches("\\+\\d(-\\d{3}){2}-\\d{4}");
			if (isValid) {
				phoneerrorLabel1.setText("not valid phonenumber.");
				x = 1;
			} else {
				phoneerrorLabel1.setText("");
			}
		}
		if (x != 0) {

		} else {

			String update = "UPDATE users SET password = '" + password
					+ "', first_Name = '" + firstName + "', last_Name = '"
					+ lastName + "', email = '" + email + "', phone = '"
					+ phone + "' , shopping_Address = '" + shoppingAddress
					+ "' where username = '" + username + "'";
			DatabaseConnection.execute2(update);
		}
	}

	public void searchBooks() throws SQLException {
		int x = 0;
		String selectedItem = ssearchByList.getValue();
		String select = "";
		String[] arr = new String[2];
		ResultSet result;
		if (selectedItem == null) {
			buyError.setText("");
			select = "SELECT Book.ISBN, Book.Title, category.Category_Name, publisher.Name, Book.Publication_Year, "
					+ "Book.Selling_Price, book_quantity.quantity "
					+ "FROM Book, category, publisher, book_quantity "
					+ "Where Book.Category_ID = category.Category_ID "
					+ "AND Book.Publisher_ID = publisher.Publisher_ID AND Book.ISBN = book_quantity.ISBN";
		} else if (selectedItem.equals("ISBN")) {
			try {
				Integer.parseInt(buyText.getText());
				if (buyText.getText().isEmpty()) {
					buyError.setText("Enter value of search");
					x = 1;
				} else {
					buyError.setText("");
					select = "SELECT Book.ISBN, Book.Title, category.Category_Name, publisher.Name, Book.Publication_Year, Book.Selling_Price, "
							+ "book_quantity.quantity "
							+ "FROM Book, category, publisher, book_quantity "
							+ "Where Book.ISBN = "
							+ buyText.getText().trim()
							+ " AND Book.Category_ID = category.Category_ID AND Book.Publisher_ID = publisher.Publisher_ID AND "
							+ "Book.ISBN = book_quantity.ISBN";

				}
			} catch (Exception e) {
				buyError.setText("invalid ISBN");
				x = 1;
			}
		}

		else if (selectedItem.equals("Title")) {
			if (buyText.getText().isEmpty()) {
				buyError.setText("Enter value of search");
				x = 1;
			} else {

				buyError.setText("");
				select = "SELECT Book.ISBN, Book.Title, category.Category_Name, publisher.Name, Book.Publication_Year, Book.Selling_Price, "
						+ "quantity "
						+ "FROM Book, category, publisher, book_quantity "
						+ "Where Book.Title LIKE '%"
						+ buyText.getText().trim()
						+ "%' AND Book.Category_ID = category.Category_ID  AND Book.Publisher_ID = publisher.Publisher_ID AND "
						+ "Book.ISBN = book_quantity.ISBN;";
			}
		} else if (selectedItem.equals("Category")) {
			if (buyText.getText().isEmpty()) {
				buyError.setText("Enter value of search");
				x = 1;
			} else {

				buyError.setText("");
				select = "SELECT Book.ISBN, Book.Title, category.Category_Name, publisher.Name, Book.Publication_Year, Book.Selling_Price, "
						+ "quantity "
						+ "FROM Book, category, publisher, book_quantity "
						+ "Where category.Category_Name LIKE '%"
						+ buyText.getText().trim()
						+ "%'  AND Book.Category_ID = category.Category_ID  AND Book.Publisher_ID = publisher.Publisher_ID "
						+ "AND Book.ISBN = book_quantity.ISBN;";
			}
		} else if (selectedItem.equals("Author")) {
			if (buyText.getText().isEmpty()) {
				buyError.setText("Enter value of search");
				x = 1;
			} else {

				buyError.setText("");
				select = "SELECT Book.ISBN, Book.Title, category.Category_Name, publisher.Name, Book.Publication_Year, Book.Selling_Price, "
						+ "quantity "
						+ "FROM  Book, book_authors, category, publisher, book_quantity "
						+ "Where book_authors.Author_Name LIKE '%"
						+ buyText.getText().trim()
						+ "%' AND book_authors.ISBN = Book.ISBN AND Book.Category_ID = category.Category_ID  AND Book.Publisher_ID = publisher.Publisher_ID AND "
						+ "Book.ISBN = book_quantity.ISBN "
						+ "GROUP BY Book.ISBN";
			}
		} else if (selectedItem.equals("Publisher")) {
			if (buyText.getText().isEmpty()) {
				buyError.setText("Enter value of search");
				x = 1;
			} else {
				buyError.setText("");
				select = "SELECT Book.ISBN, Book.Title, category.Category_Name, publisher.Name, Book.Publication_Year, Book.Selling_Price, "
						+ "quantity "
						+ "FROM Book, category, publisher, book_quantity "
						+ "Where publisher.Name LIKE '%"
						+ buyText.getText().trim()
						+ "%' AND Book.Category_ID = category.Category_ID  AND Book.Publisher_ID = publisher.Publisher_ID AND "
						+ "Book.ISBN = book_quantity.ISBN;";

			}
		} else if (selectedItem.equals("ISBN and Title")) {
			if (buyText.getText().isEmpty()) {
				buyError.setText("Enter value of search");
				x = 1;
			} else {
				arr = new String[2];
				arr = buyText.getText().trim().split(",");
				try {
					Integer.parseInt(arr[0]);
					if (arr.length == 2 && arr[0].length() != 0
							&& arr[1].length() != 0) {
						buyError.setText("");
						select = "SELECT Book.ISBN, Book.Title, category.Category_Name, publisher.Name, Book.Publication_Year, Book.Selling_Price, "
								+ "quantity "
								+ "FROM Book, category, publisher, book_quantity "
								+ " Where Book.ISBN = "
								+ arr[0].trim()
								+ " AND Book.Title LIKE '%"
								+ arr[1].trim()
								+ "%' AND Book.Category_ID = category.Category_ID  AND Book.Publisher_ID = publisher.Publisher_ID AND "
								+ "Book.ISBN = book_quantity.ISBN "
								+ "GROUP BY Book.ISBN";
					}

					else {
						buyError.setText("Enter two value of search");
						x = 1;
					}
				} catch (Exception e) {
					buyError.setText("invalid ISBN");
					x = 1;
				}
			}
		}
		if (x == 0) {
			result = DatabaseConnection.execute(select);
			foundRecords = FXCollections.observableArrayList();
			while (result.next()) {
				select = "SELECT Author_Name " + "FROM book_authors "
						+ "Where ISBN = " + result.getString("Book.ISBN");
				ResultSet resultSet2 = DatabaseConnection.execute(select);
				String authors = "";
				while (resultSet2.next()) {
					// System.out.println("*** Author = "
					// + resultSet2.getString("Author_Name"));
					authors += resultSet2.getString("Author_Name");
					if (!resultSet2.isLast()) {
						authors += ", ";
					}
				}
				foundRecords.add(new BuyTable(result.getString("Book.ISBN"),
						result.getString("Book.Title"), result
								.getString("category.Category_Name"), authors,
						result.getString("publisher.Name"), result
								.getString("Book.Publication_Year"), result
								.getDouble("Book.Selling_Price"), result
								.getInt("quantity")));
			}
			buyTable.setItems(foundRecords);
		}

	}

	public void searchBooks2() throws SQLException {
		int x = 0;
		String selectedItem = ssearchByList2.getValue();
		String select = "";
		String[] arr = new String[2];
		ResultSet result;
		if (selectedItem == null) {
			searcherror2.setText("");
			select = "SELECT Book.ISBN, Book.Title, category.Category_Name,"
					+ " publisher.Name, Book.Publication_Year, Book.Selling_Price, book_quantity.quantity"
					+ " FROM Book, category, publisher, book_quantity "
					+ "Where Book.Category_ID = category.Category_ID "
					+ " AND Book.Publisher_ID = publisher.Publisher_ID AND Book.ISBN = book_quantity.ISBN";
		} else if (selectedItem.equals("ISBN")) {
			try {
				Integer.parseInt(ssearchText2.getText());
				if (ssearchText2.getText().isEmpty()) {
					searcherror2.setText("Enter value of search");
					x = 1;
				} else {

					searcherror2.setText("");
					select = "SELECT Book.ISBN, Book.Title, category.Category_Name, publisher.Name, "
							+ "Book.Publication_Year, Book.Selling_Price, book_quantity.quantity "
							+ "FROM Book, category, publisher, book_quantity "
							+ "Where Book.ISBN = "
							+ ssearchText2.getText().trim()
							+ " AND Book.Category_ID = category.Category_ID AND "
							+ "Book.Publisher_ID = publisher.Publisher_ID AND Book.ISBN = book_quantity.ISBN";

				}
			}

			catch (Exception e) {
				searcherror2.setText("invalue of search");
				x = 1;
			}
		} else if (selectedItem.equals("Title")) {
			if (ssearchText2.getText().isEmpty()) {
				searcherror2.setText("Enter value of search");
				x = 1;
			} else {

				searcherror2.setText("");
				select = "SELECT Book.ISBN, Book.Title, category.Category_Name, publisher.Name, "
						+ "Book.Publication_Year, Book.Selling_Price, quantity "
						+ "FROM Book, category, publisher, book_quantity "
						+ "Where Book.Title LIKE '%"
						+ ssearchText2.getText().trim()
						+ "%' AND Book.Category_ID = category.Category_ID  AND "
						+ "Book.Publisher_ID = publisher.Publisher_ID AND Book.ISBN = book_quantity.ISBN;";

			}
		} else if (selectedItem.equals("Category")) {
			if (ssearchText2.getText().isEmpty()) {
				searcherror2.setText("Enter value of search");
				x = 1;
			} else {

				searcherror2.setText("");
				select = "SELECT Book.ISBN, Book.Title, category.Category_Name, publisher.Name, "
						+ "Book.Publication_Year, Book.Selling_Price, quantity "
						+ "FROM Book, category, publisher, book_quantity "
						+ "Where category.Category_Name LIKE '%"
						+ ssearchText2.getText().trim()
						+ "%' AND Book.Category_ID = category.Category_ID AND "
						+ "Book.Publisher_ID = publisher.Publisher_ID AND Book.ISBN = book_quantity.ISBN;";

			}
		} else if (selectedItem.equals("Author")) {
			if (ssearchText2.getText().isEmpty()) {
				searcherror2.setText("Enter value of search");
				x = 1;
			} else {

				searcherror2.setText("");

				select = "SELECT Book.ISBN, Book.Title, category.Category_Name, publisher.Name, Book.Publication_Year, Book.Selling_Price, "
						+ "quantity "
						+ "FROM  Book, book_authors, category, publisher, book_quantity "
						+ "Where book_authors.Author_Name LIKE '%"
						+ ssearchText2.getText().trim()
						+ "%' AND book_authors.ISBN = Book.ISBN AND Book.Category_ID = category.Category_ID  AND Book.Publisher_ID = publisher.Publisher_ID AND "
						+ "Book.ISBN = book_quantity.ISBN "
						+ "GROUP BY Book.ISBN";

			}
		} else if (selectedItem.equals("Publisher")) {
			if (ssearchText2.getText().isEmpty()) {
				searcherror2.setText("Enter value of search");
				x = 1;
			} else {
				searcherror2.setText("");
				select = "SELECT Book.ISBN, Book.Title, category.Category_Name, publisher.Name, "
						+ "Book.Publication_Year, Book.Selling_Price, quantity "
						+ "FROM Book, category, publisher, book_quantity"
						+ " Where publisher.Name LIKE '%"
						+ ssearchText2.getText().trim()
						+ "%' AND Book.Category_ID = category.Category_ID  AND "
						+ "Book.Publisher_ID = publisher.Publisher_ID AND Book.ISBN = book_quantity.ISBN;";

			}
		} else if (selectedItem.equals("ISBN and Title")) {
			if (ssearchText2.getText().isEmpty()) {
				searcherror2.setText("Enter value of search");
				x = 1;
			} else {
				arr = new String[2];
				arr = ssearchText2.getText().trim().split(",");
				try {
					Integer.parseInt(arr[0]);
					if (arr.length == 2 && arr[0].length() != 0
							&& arr[1].length() != 0) {
						searcherror2.setText("");
						select = "SELECT Book.ISBN, Book.Title, category.Category_Name, publisher.Name, "
								+ "Book.Publication_Year, Book.Selling_Price, quantity "
								+ "FROM Book, book_authors, category, publisher, book_quantity"
								+ " Where Book.ISBN = '"
								+ arr[0]
								+ "' AND Book.Title LIKE '%"
								+ arr[1]
								+ "%' AND Book.Category_ID = category.Category_ID  AND "
								+ "Book.Publisher_ID = publisher.Publisher_ID AND Book.ISBN = book_quantity.ISBN "
								+ "GROUP BY Book.ISBN";
					}

					else {
						searcherror2.setText("Enter two value of search");
						x = 1;
					}
				}

				catch (Exception e) {
					searcherror2.setText("invalid ISbn");
					x = 1;
				}
			}
		}
		if (x == 0) {
			result = DatabaseConnection.execute(select);
			modifyRecords = FXCollections.observableArrayList();
			while (result.next()) {
				select = "SELECT Author_Name " + "FROM book_authors "
						+ "Where ISBN = " + result.getString("Book.ISBN");
				ResultSet resultSet2 = DatabaseConnection.execute(select);
				String authors = "";
				while (resultSet2.next()) {
					// System.out.println("*** Author = "
					// + resultSet2.getString("Author_Name"));
					authors += resultSet2.getString("Author_Name");
					if (!resultSet2.isLast()) {
						authors += ", ";
					}
				}
				modifyRecords.add(new BuyTable(result.getString("Book.ISBN"),
						result.getString("Book.Title"), result
								.getString("category.Category_Name"), authors,
						result.getString("publisher.Name"), result
								.getString("Book.Publication_Year"), result
								.getDouble("Book.Selling_Price"), result
								.getInt("quantity")));
			}
			modifyTable.setItems(modifyRecords);
		}

	}

	public void searchBooks3() throws SQLException {
		int x = 0;
		String selectedItem = ssearchByList3.getValue();
		String select = "";
		String[] arr = new String[2];
		ResultSet result;
		if (selectedItem == null) {
			searchError2.setText("");
			select = "SELECT Book.ISBN, Book.Title, category.Category_Name, publisher.Name,"
					+ " Book.Publication_Year, Book.Selling_Price, book_quantity.quantity "
					+ "FROM Book, category, publisher, book_quantity "
					+ "Where Book.Category_ID = category.Category_ID AND "
					+ "Book.Publisher_ID = publisher.Publisher_ID AND Book.ISBN = book_quantity.ISBN";

		} else if (selectedItem.equals("ISBN")) {
			try {
				Integer.parseInt(ssearchText3.getText());
				if (ssearchText3.getText().isEmpty()) {
					searchError2.setText("Enter value of search");
					x = 1;
				} else {

					searchError2.setText("");
					select = "SELECT Book.ISBN, Book.Title, category.Category_Name, publisher.Name, "
							+ "Book.Publication_Year, Book.Selling_Price, book_quantity.quantity "
							+ "FROM Book, category, publisher, book_quantity "
							+ "Where Book.ISBN = "
							+ ssearchText3.getText().trim()
							+ " AND Book.Category_ID = category.Category_ID AND "
							+ "Book.Publisher_ID = publisher.Publisher_ID AND Book.ISBN = book_quantity.ISBN";

				}
			} catch (Exception e) {
				searchError2.setText("invalid ISBN");
				x = 1;
			}
		}

		else if (selectedItem.equals("Title")) {
			try {
				if (ssearchText3.getText().isEmpty()) {
					searchError2.setText("Enter value of search");
					x = 1;
				} else {

					searchError2.setText("");
					select = "SELECT Book.ISBN, Book.Title, category.Category_Name, publisher.Name, "
							+ "Book.Publication_Year, Book.Selling_Price, quantity "
							+ "FROM Book, category, publisher, book_quantity "
							+ "Where Book.Title LIKE '%"
							+ ssearchText3.getText().trim()
							+ "%' AND Book.Category_ID = category.Category_ID  AND "
							+ "Book.Publisher_ID = publisher.Publisher_ID AND Book.ISBN = book_quantity.ISBN;";

				}
			} catch (Exception e) {
				searchError2.setText("invalid ISBN");
				x = 1;
			}
		} else if (selectedItem.equals("Category")) {
			if (ssearchText3.getText().isEmpty()) {
				searchError2.setText("Enter value of search");
				x = 1;
			} else {

				searchError2.setText("");
				select = "SELECT Book.ISBN, Book.Title, category.Category_Name, publisher.Name, "
						+ "Book.Publication_Year, Book.Selling_Price, quantity "
						+ "FROM Book, category, publisher, book_quantity "
						+ "Where category.Category_Name LIKE '%"
						+ ssearchText3.getText().trim()
						+ "%' AND Book.Category_ID = category.Category_ID  AND "
						+ "Book.Publisher_ID = publisher.Publisher_ID AND Book.ISBN = book_quantity.ISBN;";

			}
		} else if (selectedItem.equals("Author")) {
			if (ssearchText3.getText().isEmpty()) {
				searchError2.setText("Enter value of search");
				x = 1;
			} else {

				searchError2.setText("");
				select = "SELECT Book.ISBN, Book.Title, category.Category_Name, publisher.Name, Book.Publication_Year, Book.Selling_Price, "
						+ "quantity "
						+ "FROM  Book, book_authors, category, publisher, book_quantity "
						+ "Where book_authors.Author_Name LIKE '%"
						+ ssearchText3.getText().trim()
						+ "%' AND book_authors.ISBN = Book.ISBN AND Book.Category_ID = category.Category_ID  AND Book.Publisher_ID = publisher.Publisher_ID AND "
						+ "Book.ISBN = book_quantity.ISBN "
						+ "GROUP BY Book.ISBN";
			}
		} else if (selectedItem.equals("Publisher")) {
			if (ssearchText3.getText().isEmpty()) {
				searchError2.setText("Enter value of search");
				x = 1;
			} else {
				searchError2.setText("");
				select = "SELECT Book.ISBN, Book.Title, category.Category_Name, publisher.Name, "
						+ "Book.Publication_Year, Book.Selling_Price, quantity "
						+ "FROM Book, category, publisher, book_quantity "
						+ "Where publisher.Name LIKE '%"
						+ ssearchText3.getText().trim()
						+ "%' AND Book.Category_ID = category.Category_ID  AND "
						+ "Book.Publisher_ID = publisher.Publisher_ID AND Book.ISBN = book_quantity.ISBN;";

			}
		} else if (selectedItem.equals("ISBN and Title")) {
			if (ssearchText3.getText().isEmpty()) {
				searchError2.setText("Enter value of search");
				x = 1;
			} else {
				arr = new String[2];
				arr = ssearchText3.getText().trim().split(",");
				try {
					Integer.parseInt(arr[0]);
					if (arr.length == 2 && arr[0].length() != 0
							&& arr[1].length() != 0) {
						searchError2.setText("");
						select = "SELECT Book.ISBN, Book.Title, category.Category_Name, publisher.Name, "
								+ "Book.Publication_Year, Book.Selling_Price, quantity "
								+ "FROM Book, category, publisher, book_quantity "
								+ "Where Book.ISBN = '"
								+ arr[0]
								+ "' AND Book.Title LIKE '%"
								+ arr[1]
								+ "%' AND Book.Category_ID = category.Category_ID  AND "
								+ "Book.Publisher_ID = publisher.Publisher_ID AND Book.ISBN = book_quantity.ISBN "
								+ "GROUP BY Book.ISBN";
					}

					else {
						searchError2.setText("Enter two value of search");
						x = 1;
					}
				}

				catch (Exception e) {
					searchError2.setText("invalid Isbn");
					x = 1;
				}
			}
		}
		if (x == 0) {
			result = DatabaseConnection.execute(select);
			placeOrderRecords = FXCollections.observableArrayList();
			while (result.next()) {
				select = "SELECT Author_Name " + "FROM book_authors "
						+ "Where ISBN = " + result.getString("Book.ISBN");
				ResultSet resultSet2 = DatabaseConnection.execute(select);
				String authors = "";
				while (resultSet2.next()) {
					// System.out.println("*** Author = "
					// + resultSet2.getString("Author_Name"));
					authors += resultSet2.getString("Author_Name");
					if (!resultSet2.isLast()) {
						authors += ", ";
					}
				}
				placeOrderRecords.add(new BuyTable(result
						.getString("Book.ISBN"),
						result.getString("Book.Title"), result
								.getString("category.Category_Name"), authors,
						result.getString("publisher.Name"), result
								.getString("Book.Publication_Year"), result
								.getDouble("Book.Selling_Price"), result
								.getInt("quantity")));
			}
			placeOrderTable.setItems(placeOrderRecords);
		}

	}

	public void manageCartBook() throws SQLException {
		ResultSet result;
		String select = "SELECT Book.ISBN, Book.Title, category.Category_Name , publisher.Name, "
				+ "Book.Publication_Year, Book.Selling_Price, shipping_cart.Quantity, book_quantity.Quantity "
				+ "FROM Book, category, publisher,shipping_cart, book_quantity "
				+ "Where shipping_cart.ISBN = Book.ISBN AND shipping_cart.Username = '"
				+ Utilities.username
				+ "'AND Book.Category_ID = category.Category_ID AND "
				+ "Book.Publisher_ID = publisher.Publisher_ID AND book_quantity.ISBN = Book.ISBN;";

		result = DatabaseConnection.execute(select);
		manageRecords = FXCollections.observableArrayList();
		while (result.next()) {
			select = "SELECT Author_Name " + "FROM book_authors "
					+ "Where ISBN = " + result.getString("Book.ISBN");
			ResultSet resultSet2 = DatabaseConnection.execute(select);
			String authors = "";
			while (resultSet2.next()) {
				// System.out.println("*** Author = "
				// + resultSet2.getString("Author_Name"));
				authors += resultSet2.getString("Author_Name");
				if (!resultSet2.isLast()) {
					authors += ", ";
				}
			}
			// System.out.println("--- Customer = "
			// + result.getInt("shipping_cart.Quantity"));
			manageRecords.add(new BuyTable(result.getString("Book.ISBN"),
					result.getString("Book.Title"), result
							.getString("category.Category_Name"), authors,
					result.getString("publisher.Name"), result
							.getString("Book.Publication_Year"), result
							.getDouble("Book.Selling_Price"), result
							.getInt("book_quantity.Quantity"), result
							.getInt("shipping_cart.Quantity")));
		}
		cartTable.setItems(manageRecords);

	}

	public void searchBooks4() throws SQLException {
		int x = 0;
		String select = "";
		ResultSet result;

		if (ssearchText4.getText().isEmpty()) {
			select = "SELECT * from book_orders ";
		} else {

			select = "SELECT * from book_orders  Where book_orders.isbn = "
					+ ssearchText4.getText().trim();
		}
		if (x == 0) {
			result = DatabaseConnection.execute(select);
			confirmOrderRecords = FXCollections.observableArrayList();
			while (result.next()) {
				confirmOrderRecords.add(new Order(result.getString("isbn"),
						result.getString("quantity"), result
								.getString("Order_Date")));
			}
			confirmOrderTable.setItems(confirmOrderRecords);
		}

	}

	public void addAuthorToList() {
		if (authorsList.getSelectionModel().getSelectedItem() != null
				&& !authorsList.getSelectionModel().getSelectedItem().trim()
						.isEmpty()
				&& !authorsList.getValue().trim().isEmpty()
				&& !authorsList.getItems().contains(
						authorsList.getValue().trim())) {
			authorsList.getItems().add(authorsList.getValue().trim());
			authorsList.setValue("");
		}
	}

	public void removeAuthorFromList() {
		if (authorsList.getSelectionModel().getSelectedItem() != null
				&& !authorsList.getSelectionModel().getSelectedItem().trim()
						.isEmpty() && !authorsList.getValue().trim().isEmpty()) {
			authorsList.getItems().remove(
					authorsList.getSelectionModel().getSelectedIndex());
			authorsList.setValue("");
		}
	}

	public void addBook() throws SQLException {
		String isbn = isbnText.getText().trim();
		String threshold = thresholdText.getText();
		String price = cellingPriceText.getText();
		String year = publicationYearText.getText();
		String quantity = quantityText.getText();
		String publisherName = "";
		String publisherAddress = "";
		String publisherPhone = "";
		String cateogryName = "";
		String title = titleText.getText().trim();
		int PID = 0;
		int CID = 0;

		int x = 0;
		if (isbn.isEmpty()) {
			isbnerrortext1.setText("you must enter ISBN");
			x = 1;
		} else if (!isbn.isEmpty()) {
			try {
				String select = "SELECT isbn  from book where " + "isbn ='"
						+ isbn + "'";
				ResultSet result = DatabaseConnection.execute(select);
				if (result.next()) {
					isbnerrortext1.setText("This ISBN name exists before");
					x = 1;
				}

				else {
					isbnerrortext1.setText("");
				}
			} catch (Exception e) {
				isbnerrortext1.setText("Invalid ISBN");
				x = 1;
			}
		}
		if (title.isEmpty()) {
			invalidTitle.setText("Enter title name");
			x = 1;
		} else {
			invalidTitle.setText("");
		}
		if (threshold.isEmpty()) {
			threolderrortext1.setText("you must enter Threshold");
			x = 1;
		} else if (!threshold.isEmpty()) {
			try {
				if (Integer.parseInt(threshold.toString()) < 0) {
					threolderrortext1.setText("Invalid threshold");
					x = 1;
				} else {
					threolderrortext1.setText("");
				}
			} catch (Exception e) {
				threolderrortext1.setText("Invalid threshold");
				x = 1;
			}

		}

		if (quantity.isEmpty()) {
			quantityError.setText("you must enter quantity");
			x = 1;
		} else if (!quantity.isEmpty()) {
			try {
				if (Integer.parseInt(quantity.toString()) < 0) {
					quantityError.setText("Invalid quantity");
					x = 1;
				} else {
					quantityError.setText("");
				}
			} catch (Exception e) {
				quantityError.setText("Invalid quantity");
				x = 1;
			}

		}
		if (price.isEmpty()) {
			pricerrortext1.setText("you must enter sellingPrice");
			x = 1;
		} else if (!price.isEmpty()) {
			try {
				if (Integer.parseInt(price.toString()) < 0) {
					pricerrortext1.setText("Invalid sellingPrice");
					x = 1;
				} else {
					pricerrortext1.setText("");
				}
			} catch (Exception e) {
				pricerrortext1.setText("Invalid sellingPrice");
				x = 1;
			}

		}
		if (year.isEmpty()) {
			yearerrortext1.setText("you must enter year");
			x = 1;
		} else if (!year.isEmpty()) {
			try {
				if (year.length() != 4 || Integer.parseInt(year) < 1900
						|| Integer.parseInt(year) > 2015) {
					yearerrortext1.setText("Invalid year");
					x = 1;
				} else {
					yearerrortext1.setText("");
				}
			} catch (Exception e) {
				yearerrortext1.setText("Invalid year");
				x = 1;
			}

		}
		ObservableList<String> arr = authorsList.getItems();
		if (arr.size() == 0) {
			authorerrorLabel1.setText("you must enter author name");
			x = 1;
		} else if (arr.size() != 0) {
			authorerrorLabel1.setText("");

		}
		if (publisherGroup.getSelectedToggle() == currentPublisherRadio) {
			String selectedItem = publisherList.getValue();

			if (selectedItem != null && !selectedItem.isEmpty()) {
				invalidPublisher1.setText("");
				String select = "SELECT publisher_id , phone  from publisher where name ='"
						+ selectedItem + "'";
				ResultSet result = DatabaseConnection.execute(select);
				result.next();
				PID = result.getInt("Publisher_ID");
			} else {
				invalidPublisher1.setText("Select Publisher");
			}
		} else if (publisherGroup.getSelectedToggle() == newPublisherRadio) {
			publisherName = publisherNameText.getText().trim();
			publisherAddress = publisherAddressText.getText().trim();
			publisherPhone = publisherPhoneText.getText().trim();
			if (publisherName.isEmpty() || publisherAddress.isEmpty()
					|| publisherPhone.isEmpty()) {
				invalidPublisher1.setText("Enter all fields");
				x = 1;
			} else {
				invalidPublisher1.setText("");
			}

		}
		if (categoryGroup.getSelectedToggle() == currentCategoryRadio) {
			String selectedItem = categoryList.getValue();
			if (selectedItem != null && !selectedItem.isEmpty()) {
				invalidcateogry.setText("");
				String select = "SELECT Category_ID  from Category where Category_name ='"
						+ selectedItem + "'";

				ResultSet result = DatabaseConnection.execute(select);
				result.next();
				CID = result.getInt("Category_ID");
			} else {
				invalidcateogry.setText("Select Category");
			}
		} else if (categoryGroup.getSelectedToggle() == newCategoryRadio) {
			cateogryName = newCategoryText.getText().trim();

			if (cateogryName.isEmpty()) {
				invalidcateogry.setText("you must enter cateogry name");
				x = 1;
			} else {
				invalidcateogry.setText("");
			}

		}
		if (x == 0) {

			if (publisherGroup.getSelectedToggle() == newPublisherRadio) {
				String insert = "insert into publisher values( 0 ,'"
						+ publisherName + "', '" + publisherAddress + "', '"
						+ publisherPhone + "')";
				DatabaseConnection.execute2(insert);
				String select = "SELECT publisher_id  from publisher where "
						+ "name ='" + publisherName + "'";
				ResultSet result = DatabaseConnection.execute(select);
				result.next();
				PID = result.getInt("publisher_id");
			}
			if (categoryGroup.getSelectedToggle() == newCategoryRadio) {
				String insert = "insert into category values(  0 , '"
						+ cateogryName + "')";
				DatabaseConnection.execute2(insert);
				String select = "SELECT category_id  from category where "
						+ "category_name ='" + cateogryName + "'";
				ResultSet result = DatabaseConnection.execute(select);
				result.next();
				CID = result.getInt("category_id");
			}
			String insert1 = "insert into book values( " + isbn + ",'" + title
					+ "'," + PID + "," + year + "," + price + "," + CID + ")";
			DatabaseConnection.execute2(insert1);
			for (int i = 0; i < arr.size(); i++) {
				if (!arr.get(i).isEmpty()) {
					String insert = "insert into book_authors values(" + isbn
							+ ", '" + arr.get(i) + "' )";
					DatabaseConnection.execute2(insert);
				}
			}

			String insert2 = "insert into book_quantity values( " + isbn + ","
					+ threshold + "," + quantity + ")";
			DatabaseConnection.execute2(insert2);

			isbnText.setText("");
			thresholdText.setText("");
			cellingPriceText.setText("");
			publicationYearText.setText("");
			quantityText.setText("");
			titleText.setText("");

			publisherNameText.setText("");
			publisherAddressText.setText("");
			publisherPhoneText.setText("");
			publisherList.setValue("");

			newCategoryText.setText("");
			categoryList.setValue("");

			authorsList.setValue("");
			authorsList.getItems().clear();
			publisherGroup.selectToggle(currentPublisherRadio);
			categoryGroup.selectToggle(currentCategoryRadio);
		}

	}

	public void logout() throws Exception {
		String delete = "DELETE  FROM shipping_cart WHERE username = '"
				+ Utilities.username + "'";
		DatabaseConnection.execute2(delete);

		LoginController loginController = LoginController.getLoginController();
		loginController.display();
		settingsStage.close();

	}

	public void addBookTab() throws SQLException {
		publisherList.getItems().clear();
		categoryList.getItems().clear();
		String select = "SELECT name  from publisher ";
		ResultSet result = DatabaseConnection.execute(select);
		while (result.next()) {
			publisherList.getItems().add(result.getString("name"));
		}

		String selec1t = "SELECT category_name  from category ";
		ResultSet result1 = DatabaseConnection.execute(selec1t);
		while (result1.next()) {
			categoryList.getItems().add(result1.getString("category_name"));
		}
		publisherGroup.selectToggle(currentPublisherRadio);
		currentPublisherRadio.setSelected(true);
		categoryGroup.selectToggle(currentCategoryRadio);
		currentCategoryRadio.setSelected(true);
	}

	public void buyBookTabz() {
		ssearchByList.getItems().removeAll("ISBN", "Title", "Category",
				"Author", "Publisher", "ISBN and Title");
		ObservableList<String> arr = FXCollections.observableArrayList();
		arr.addAll("ISBN", "Title", "Category", "Author", "Publisher",
				"ISBN and Title");
		ssearchByList.getItems().addAll(arr);
	}

	public boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}

	public void totalPrice() {
		total = 0;
		for (int i = 0; i < manageRecords.size(); i++) {

			total = total + manageRecords.get(i).getTotalPrice();
		}
		totalPriceLabel.setText(total + "$");
	}

	public void viewUsersTab() {

	}

	public void searchUsers() throws SQLException {
		int x = 0;
		String select = "";
		ResultSet result;

		if (ssearchText.getText().isEmpty()) {
			select = "SELECT * from users";
		} else {
			searchError.setText("");
			select = "SELECT * from users Where users.username  LIKE '"
					+ ssearchText.getText().trim() + "%' ";
		}

		if (x == 0) {
			result = DatabaseConnection.execute(select);
			userRecords = FXCollections.observableArrayList();
			while (result.next()) {

				userRecords.add(new User(result.getString("users.username"),
						result.getString("users.password"), result
								.getString("users.first_name"), result
								.getString("users.last_name"), result
								.getString("users.email"), result
								.getString("users.phone"), result
								.getString("users.Shopping_Address"), result
								.getString("users.Shopping_Address")));
			}
			viewUserTable.setItems(userRecords);
		}

	}

	public void modifyTab() {
		ssearchByList2.getItems().removeAll("ISBN", "Title", "Category",
				"Author", "Publisher", "ISBN and Title");
		ObservableList<String> arr = FXCollections.observableArrayList();
		arr.addAll("ISBN", "Title", "Category", "Author", "Publisher",
				"ISBN and Title");
		ssearchByList2.getItems().addAll(arr);
	}

	public void placeOrderTab() {
		ssearchByList3.getItems().removeAll("ISBN", "Title", "Category",
				"Author", "Publisher", "ISBN and Title");
		ObservableList<String> arr = FXCollections.observableArrayList();
		arr.addAll("ISBN", "Title", "Category", "Author", "Publisher",
				"ISBN and Title");
		ssearchByList3.getItems().addAll(arr);
	}

	public void confirmOrderTab() {

	}

	public void checkOut() throws Exception {
		CheckoutController checkoutController = new CheckoutController();
		String[] data = checkoutController.display();
		ObservableList<BuyTable> cart = cartTable.getItems();

		if (data[0] == "1" && !cart.isEmpty()) {
			try {
				// Random r = new Random();
				// int k = r.nextInt(1000);
				String update;
				String insert;
				DatabaseConnection.conn.setAutoCommit(false);
				for (int i = 0; i < cart.size(); i++) {
					update = "UPDATE book_quantity set Quantity = Quantity - "
							+ cart.get(i).getBookQuantity() + " WHERE ISBN = "
							+ cart.get(i).getIsbn();
					insert = "INSERT INTO sales (Book_Name, Buy_Time, Customer_Name, Selling_Price, Quantity) "
							+ "VALUES( '"
							+ cart.get(i).getTitle()
							+ "', CURDATE() , '"
							+ Utilities.username
							+ "', "
							+ cart.get(i).getTotalPrice()
							+ ", "
							+ cart.get(i).getCustomerQuantity() + ")";
					DatabaseConnection.execute2(update);
					DatabaseConnection.execute2(insert);
					System.out.println("aaaaaaaaaaaaaaaaaa");
				}
				System.out.println(" ana henaaaaaaaaa now");
				if ((data[1].charAt(0) + "").equals("$")) {
					DatabaseConnection.conn.commit();
					System.out.println("COMMITTED");
				} else {
					System.out.println("hhhghgh");
					throw new Exception("Invalid Credit Number");
				}

			} catch (Exception e) {
				System.out.println("Error!!!");
				DatabaseConnection.conn.rollback();
				System.out.println("ROLLED BACK!!!");
				AlertController alertController = new AlertController();
				alertController
						.display("You entered Invalid Credit Card number.");
				e.printStackTrace();
			}
			DatabaseConnection.conn.setAutoCommit(true);

		}
	}

	public void showTheReports() {
		SampleReport.showTheReports();
	}

}