package application;

public class Order {

	private String isbn;
	private String quantity;
	private String date;

	public Order(String isbn, String quantity, String date) {
		this.isbn = isbn;
		this.quantity = quantity;
		this.date = date;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return isbn + ", " + quantity + ", " + date;
	}
}
