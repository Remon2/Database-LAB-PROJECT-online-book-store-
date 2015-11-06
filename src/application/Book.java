package application;

public class Book {
	private int isbn;
	private String title;
	private int year;
	private double price;

	private String author;
	private String publisher;
	private String category;

	public Book() {
		isbn = 0;
		title = "test";
		year = 9999;
		price = 0;
		author = "@@@@@";
		publisher = "#####";
		category = "%%%%%";
	}

	public Book(int isbn, String title, int year, double price, String author,
			String publisher, String category) {
		this.isbn = isbn;
		this.title = title;
		this.year = year;
		this.price = price;
		this.author = author;
		this.publisher = publisher;
		this.category = category;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
