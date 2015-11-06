package application;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BuyTable {
	private String isbn;
	private String title;
	private String category;
	private String authorName;
	private String publisherName;
	private String publicationYear;
	private double price;
	private int bookQuantity;
	private int customerQuantity;
	private double totalPrice;
	private PropertyChangeSupport propertySupport;

	public BuyTable(String isbn, String title, String category,
			String authorName, String publisherName, String publicationYear,
			double price, int bookQuantity) {

		this.isbn = isbn;
		this.title = title;
		this.category = category;
		this.authorName = authorName;
		this.publisherName = publisherName;
		this.publicationYear = publicationYear;
		this.price = price;
		this.customerQuantity = 1;
		this.totalPrice = price * customerQuantity;
		this.propertySupport = new PropertyChangeSupport(this);
		this.bookQuantity = bookQuantity;
	}

	public BuyTable(String isbn, String title, String category,
			String authorName, String publisherName, String publicationYear,
			double price, int bookQuantity, int customerQuantity) {

		this.isbn = isbn;
		this.title = title;
		this.category = category;
		this.authorName = authorName;
		this.publisherName = publisherName;
		this.publicationYear = publicationYear;
		this.price = price;
		this.customerQuantity = customerQuantity;
		this.totalPrice = price * customerQuantity;
		this.propertySupport = new PropertyChangeSupport(this);
		this.bookQuantity = bookQuantity;
	}

	public void updateTotalPrice() {
		double oldtotal = this.totalPrice;
		this.totalPrice = price * customerQuantity;
		propertySupport.firePropertyChange("totalPrice", oldtotal, totalPrice);
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(String publicationYear) {
		this.publicationYear = publicationYear;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getBookQuantity() {
		return bookQuantity;
	}

	public void setBookQuantity(int bookQuantity) {
		this.bookQuantity = bookQuantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public int getCustomerQuantity() {
		return customerQuantity;
	}

	public void setCustomerQuantity(int customerQuantity) {
		this.customerQuantity = customerQuantity;
	}

	public void setTotalPrice(double totalPrice) {
		double oldtotal = this.totalPrice;
		this.totalPrice = totalPrice;
		propertySupport.firePropertyChange("totalPrice", oldtotal, totalPrice);
	}

	public String toString() {
		return isbn + ", " + title + ", " + category + ", " + authorName + ", "
				+ publisherName + ", " + publicationYear + ", " + price
				+ ", qqq = " + bookQuantity;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertySupport.addPropertyChangeListener(listener);
	}

	String insert = "Insert into shopping_cart VALUES(" + Utilities.username
			+ ", " + isbn + ", " + bookQuantity + ", " + Utilities.date + ")";

}
