package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class QuantityComboBox
		implements
		Callback<TableColumn<BuyTable, BuyTable>, TableCell<BuyTable, BuyTable>> {
	int type = 0;

	public QuantityComboBox() {
		// TODO Auto-generated constructor stub
	}

	public QuantityComboBox(int type) {
		// TODO Auto-generated constructor stub

		this.type = type;
	}

	@Override
	public TableCell<BuyTable, BuyTable> call(
			TableColumn<BuyTable, BuyTable> btnCol) {
		return new TableCell<BuyTable, BuyTable>() {
			final ComboBox<Integer> quantity = new ComboBox<>();
			{
				quantity.setMinWidth(50);
			}

			@Override
			public void updateItem(final BuyTable book, boolean empty) {
				super.updateItem(book, empty);
				if (book != null) {
					if (quantity.getItems() != null && quantity.getItems().isEmpty() ) {
						quantity.getItems().clear();
					}
					quantity.setPromptText("Quantity");
					if (type == 1) {
						System.out.println(book.getCustomerQuantity());
						quantity.setValue(book.getCustomerQuantity());
					}
					int bookQuantity = book.getBookQuantity();
					// book.setQuantity(30);
					if (bookQuantity > 30) {
						bookQuantity = 30;
					}

					for (int i = 1; i <= bookQuantity; i++) {
						quantity.getItems().add(i);
					}

					setGraphic(quantity);
					quantity.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							
							if (quantity.getValue() != null) {
								book.setCustomerQuantity(quantity.getValue());
								System.out.println(quantity.getValue());
								book.updateTotalPrice();
							}
							System.out.println(quantity.getValue() + " "
									+ book.getTotalPrice());
							System.out.println(book.toString());
						}
					});
				} else {
					setGraphic(null);
				}
			}
		};
	}

}