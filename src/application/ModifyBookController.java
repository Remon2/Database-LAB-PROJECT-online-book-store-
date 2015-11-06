package application;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ModifyBookController implements Initializable {
	@FXML
	private TextField quantityText;
	@FXML
	private Label quantityError;

	@FXML
	private RadioButton newPublisherRadio;
	@FXML
	private Label authorerrorLabel1;
	@FXML
	private TextField cellingPriceText;
	@FXML
	private Label yearerrortext1;
	@FXML
	private ComboBox<String> authorsList;
	@FXML
	private RadioButton currentCategoryRadio;
	@FXML
	private Label isbnerrortext1;
	@FXML
	private Label threolderrortext1;
	@FXML
	private Button removeAuthor;
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
	private Label invalidPublisher;
	@FXML
	private Button addAuthor;
	@FXML
	private TextField newCategoryText;
	@FXML
	private TextField publisherAddressText;
	@FXML
	private ComboBox<String> publisherList;
	@FXML
	private Label invalidTitle;
	@FXML
	private Label pricerrortext1;
	@FXML
	private Label invalidPublisher1;
	@FXML
	private RadioButton newCategoryRadio;
	@FXML
	private TextField publisherNameText;
	@FXML
	private TextField publisherPhoneText;
	@FXML
	private Label invalidcateogry;
	@FXML
	private Label isbnText;
	@FXML
	private ToggleGroup categoryGroup;

	private static ModifyBookController modifyBookController;
	private static Stage checkoutStage;
	private BuyTable savedBook;

	public ModifyBookController() {
		if (modifyBookController != null) {
			// return userSettingsController;
		}
	}

	public static ModifyBookController getModifyBookController() {
		if (modifyBookController != null) {
			return modifyBookController;
		}
		return new ModifyBookController();
	}

	public void display(BuyTable savvedBook) throws Exception {
		Parent root = FXMLLoader
				.load(getClass().getResource("ModifyBook.fxml"));
		checkoutStage = new Stage();
		Scene checkoutScene = new Scene(root);
		checkoutStage.setTitle("Libro Online Bookstore");
		checkoutStage.setMaximized(true);
		checkoutStage.initModality(Modality.APPLICATION_MODAL);
		checkoutStage.setScene(checkoutScene);
		checkoutStage.showAndWait();
	}

	public void close() {
		checkoutStage.close();
	}

	public void addAuthorToList() {
		if (authorsList.getSelectionModel().getSelectedItem() != null
				&& !authorsList.getSelectionModel().getSelectedItem().trim()
						.isEmpty()) {
			authorsList.getItems().add(authorsList.getValue().trim());
			authorsList.setValue("");
		}
	}

	public void removeAuthorFromList() {
		if (authorsList.getSelectionModel().getSelectedItem() != null
				&& !authorsList.getSelectionModel().getSelectedItem().trim()
						.isEmpty()) {
			authorsList.getItems().remove(
					authorsList.getSelectionModel().getSelectedIndex());
			authorsList.setValue("");
		}
	}

	public void modifyBook() throws SQLException {
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
		if (title.isEmpty()) {
			invalidTitle.setText("Enter title name");
			x = 1;
		}
		if (threshold.isEmpty()) {
			threolderrortext1.setText("you must enter Threshold");
			x = 1;
		} else if (!threshold.isEmpty()) {
			try{
			if (Integer.parseInt(threshold.toString()) < 0) {
				threolderrortext1.setText("Invalid threshold");
				x = 1;
			}
			}
			catch(Exception e){
				threolderrortext1.setText("Invalid threshold");
				x = 1;
			}

		}

		if (quantity.isEmpty()) {
			quantityError.setText("you must enter quantity");
			x = 1;
		} else if (!quantity.isEmpty()) {
			try{
			if (Integer.parseInt(quantity.toString()) < 0) {
				quantityError.setText("Invalid quantity");
				x = 1;
			}
			}
			catch(Exception e){
				quantityError.setText("Invalid quantity");
				x = 1;
			}

		}
		if (price.isEmpty()) {
			pricerrortext1.setText("you must enter sellingPrice");
			x = 1;
		} else if (!price.isEmpty()) {
			try{
			if (Double.parseDouble(price.toString()) < 0) {
				pricerrortext1.setText("Invalid sellingPrice");
				x = 1;
			}
			}
			catch (Exception e){
				pricerrortext1.setText("Invalid sellingPrice");
				x = 1;
			}

		}
		if (year.isEmpty()) {
			yearerrortext1.setText("you must enter year");
			x = 1;
		} else if (!year.isEmpty()) {
			try{
			if (year.length() != 4) {
				yearerrortext1.setText("Invalid year");
				x = 1;
			}
			else{
				yearerrortext1.setText("");
			}
			}
			catch (Exception e){
				yearerrortext1.setText("Invalid year");
				x = 1;
			}

		}
		ObservableList<String> arr = authorsList.getItems();
		if (arr.size() == 0) {
			authorerrorLabel1.setText("you must enter author name");
			x = 1;
		} else if (arr.size() == 0) {
			authorerrorLabel1.setText("");
		}
		if (publisherGroup.getSelectedToggle() == currentPublisherRadio) {
			String selectedItem = publisherList.getValue();
			if(selectedItem!=null && !selectedItem.isEmpty() ){
			String select = "SELECT publisher_id , phone  from publisher where name ='"
					+ selectedItem + "'";
			ResultSet result = DatabaseConnection.execute(select);
			result.next();
			PID = result.getInt("Publisher_ID");
			invalidPublisher1.setText("");
			}
			else {
				invalidPublisher1.setText(" select publisher");
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
			if(selectedItem!=null&&!selectedItem.isEmpty()){
				invalidcateogry.setText("select category");
			String select = "SELECT Category_ID  from Category where Category_name ='"
					+ selectedItem + "'";
			ResultSet result = DatabaseConnection.execute(select);
			result.next();
			CID = result.getInt("Category_ID");
			}
			else{
				invalidcateogry.setText("");
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
				String insert = "insert into publisher values( NULL " + ", '"
						+ publisherName + "', '" + publisherAddress + "', '"
						+ publisherPhone + "')";
				DatabaseConnection.execute2(insert);
				String select = "SELECT publisher_id  from publisher where "
						+ "name ='" + publisherName + "'";
				ResultSet result = DatabaseConnection.execute(select);
				PID = result.getInt("publisher_id");
			}
			if (categoryGroup.getSelectedToggle() == newCategoryRadio) {
				String insert = "insert into cateogry values( NULL " + ", '"
						+ cateogryName + "')";
				DatabaseConnection.execute2(insert);
				String select = "SELECT cateogry_id  from category where "
						+ "cateogry_name ='" + cateogryName + "'";
				ResultSet result = DatabaseConnection.execute(select);
				CID = result.getInt("cateogry_id");
			}
			String update1 = "update book set  title = '" + title
					+ "' , publisher_id =" + PID + ", publication_year ="
					+ year + ", selling_price = " + price + ", category_id = "
					+ CID + " where isbn = " + isbn;
			DatabaseConnection.execute2(update1);

			for (int i = 0; i < arr.size(); i++) {
				String update = "update book_authors set author_name = '"
						+ arr.get(i) + "'where isbn = " + isbn;
				DatabaseConnection.execute2(update);
			}
			String update2 = "update book_quantity set threshold = "
					+ threshold + " , quantity = " + quantity
					+ " where isbn = " + isbn;
			DatabaseConnection.execute2(update2);

		}
		
		checkoutStage.close();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		savedBook = Utilities.book;
		System.out.println(savedBook.getIsbn() + " herrrrrrrrre");
		String select = "SELECT  author_name from book_authors where isbn ="
				+ savedBook.getIsbn();
		ResultSet result = null;
		try {
			result = DatabaseConnection.execute(select);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			while (result.next()) {
				try {
					authorsList.getItems().add(result.getString("author_name"));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String select2 = "SELECT  Threshold from book_quantity where isbn = "
				+ savedBook.getIsbn();
		ResultSet result2 = null;
		try {
			result2 = DatabaseConnection.execute(select2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (result2.next()) {
				thresholdText.setText(result2.getInt("Threshold") + "");
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		isbnText.setText(savedBook.getIsbn());
		titleText.setText(savedBook.getTitle());
		publisherList.setValue(savedBook.getPublisherName());
		categoryList.setValue(savedBook.getCategory());
		cellingPriceText.setText(savedBook.getPrice() + "");
		quantityText.setText(savedBook.getBookQuantity() + "");
		publicationYearText.setText(savedBook.getPublicationYear());

		publisherGroup.selectToggle(currentPublisherRadio);
		categoryGroup.selectToggle(currentCategoryRadio);

	}

}
