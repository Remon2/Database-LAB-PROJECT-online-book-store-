package application;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

public class AddToCartButton<T> implements
		Callback<TableColumn<T, T>, TableCell<T, T>> {
	int type;

	public AddToCartButton(int type) {
		this.type = type;
	}

	// private final Image coffeeImage = new Image(
	// "http://icons.iconarchive.com/icons/archigraphs/collection/48/Coffee-icon.png");
	// private final Image fruitImage = new Image(
	// "http://icons.iconarchive.com/icons/iconicon/veggies/48/bananas-icon.png");
	private final Image addtoCartImage = new Image("\\add.png");
	private final Image removeFromCartImage = new Image("\\remove.png");
	private final Image modifyBookImage = new Image("\\modify.png");
	private final Image confirmOrderImage = new Image("\\confirm.png");

	@Override
	public TableCell<T, T> call(TableColumn<T, T> btnCol) {
		return new TableCell<T, T>() {
			final ImageView buttonGraphic = new ImageView();
			final Button button = new Button();
			{
				buttonGraphic.setFitHeight(25);
				buttonGraphic.setFitWidth(25);
				button.setGraphic(buttonGraphic);
				button.setPadding(new Insets(0));
				if (type == 1) {
					button.setMinWidth(130);
				} else {
					button.setMinWidth(120);
				}
			}

			@Override
			public void updateItem(final T book, boolean empty) {
				super.updateItem(book, empty);
				if (book != null) {
					if (type == 0) {
						button.setText("Add to Cart");
						buttonGraphic.setImage(addtoCartImage);
					} else if (type == 1) {
						button.setText("Remove from Cart");
						buttonGraphic.setImage(removeFromCartImage);
					} else if (type == 2) {
						button.setText("Modify Book");
						buttonGraphic.setImage(modifyBookImage);
					} else if (type == 3) {
						button.setText("Place Order");
						buttonGraphic.setImage(addtoCartImage);
					} else if (type == 4) {
						button.setText("Confirm Order");
						buttonGraphic.setImage(confirmOrderImage);
					} else if (type == 5) {
						button.setText("Be Manager");
						buttonGraphic.setImage(confirmOrderImage);
					}

					setGraphic(button);
					button.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							if (type == 0) {
								String insert = "insert into shipping_cart values( '"
										+ Utilities.username
										+ "' ,"
										+ ((BuyTable) book).getIsbn()
										+ ","
										+ ((BuyTable) book)
												.getCustomerQuantity()
										+ ",CURRENT_TIMESTAMP" + ")";
								try {
									System.out.println(insert);
									DatabaseConnection.execute2(insert);
								} catch (SQLException e) {
									System.out
											.println("you have already ordered it");
								}
							} else if (type == 1) {
								getTableView().getItems().remove(
										getTableRow().getIndex());
								String delete = "delete from shipping_cart  where shipping_cart.Username = '"
										+ Utilities.username
										+ "' AND shipping_cart.ISBN = "
										+ ((BuyTable) book).getIsbn();
								try {
									DatabaseConnection.execute2(delete);
								} catch (SQLException e) {
									System.out.println("not found");
								}

							} else if (type == 2) {
								System.out.println("Modify Book");
								ModifyBookController modifyBookController = ModifyBookController
										.getModifyBookController();
								try {
									Utilities.book = (BuyTable) book;
									modifyBookController
											.display((BuyTable) book);
								} catch (Exception e) {

									// e.printStackTrace();
								}
							} else if (type == 3) {
								System.out.println("Place Order");
								getTableView().getItems().remove(
										getTableRow().getIndex());
								String select = "select * from book_orders where isbn = "
										+ ((BuyTable) book).getIsbn()
										+ " AND order_date = CURRENT_TIMESTAMP ";

								try {
									ResultSet result = DatabaseConnection
											.execute(select);
									if (result.next()) {
										String update = "update  book_orders set Quantity = "
												+ Integer.parseInt(result
														.getString("Quantity"))
												* 2
												+ " where  isbn = "
												+ result.getString("isbn")
												+ " and "
												+ "order_date = CURRENT_TIMESTAMP";
										try {
											DatabaseConnection.execute2(update);
										} catch (SQLException e) {
											System.out
													.println("can not update the value ");
										}
									} else {
										String insert = "insert into book_orders values("
												+ ((BuyTable) book).getIsbn()
												+ ", CURRENT_TIMESTAMP ,"
												+ +(((BuyTable) book)
														.getBookQuantity() * 2)
												+ ")";
										try {
											DatabaseConnection.execute2(insert);
										} catch (SQLException e) {
											System.out
													.println("order already exists");
										}
									}
								} catch (SQLException e1) {

								}

							} else if (type == 4) {
								getTableView().getItems().remove(
										getTableRow().getIndex());
								String delete = "delete from book_orders where isbn ="
										+ (((Order) book).getIsbn());
								try {
									DatabaseConnection.execute2(delete);
								} catch (SQLException e) {
									System.out.println("not found");
								}

							} else if (type == 5) {
								getTableView().getItems().remove(
										getTableRow().getIndex());
								String update = "update  users set is_manager = 1 where users.Username = '"
										+ ((User) book).getUsername() + "'";
								try {
									DatabaseConnection.execute2(update);
								} catch (SQLException e) {

									System.out.println("not found");
								}
							}
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