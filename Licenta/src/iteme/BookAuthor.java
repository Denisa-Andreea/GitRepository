package iteme;

public class BookAuthor {

	private int idBook;
	private String title;
	private String autors;
	private String publisher;
	private int year;
	private int volume;
	private String series;
	private String city;
	private String country;
	private String edition;
	private String month;
	private String note;

	public BookAuthor() {
	}

	public BookAuthor(int idBook, String title, String autors,
			String publisher, int year, int volume, String series,
			String city, String country, String edition, String month, String note) {
		this.idBook = idBook;
		this.title = title;
		this.autors = autors;
		this.publisher = publisher;
		this.year = year;
		this.volume = volume;
		this.series = series;
		this.city = city;
		this.country = country;
		this.edition = edition;
		this.month = month;
		this.note = note;
	}

	public int getIdBook() {
		return idBook;
	}

	public void setIdBook(int idBook) {
		this.idBook = idBook;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAutors() {
		return autors;
	}

	public void setAutors(String autors) {
		this.autors = autors;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
