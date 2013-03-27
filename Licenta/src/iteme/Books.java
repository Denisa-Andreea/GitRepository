package iteme;

import java.util.ArrayList;

public class Books {
	
	int idBook;
	String title;
	ArrayList<Authors> autors;
	int id_publisher;
	String publisher;
	int year;
	int volume;
	String series;
	String address;
	String edition;
	String month; 
	String note;

	public Books() {
		super();
	}

	public Books(int idBook,String title, ArrayList<Authors> autors, int id_publisher, String publisher,
			int year, int volume, String series, String address,
			String edition, String month, String note) {
		this.idBook = idBook;
		this.title = title;
		this.autors = autors;
		this.id_publisher = id_publisher;
		this.publisher = publisher;
		this.year = year;
		this.volume = volume;
		this.series = series;
		this.address = address;
		this.edition = edition;
		this.month = month;
		this.note = note;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<Authors> getAutors() {
		return autors;
	}

	public void setAutors(ArrayList<Authors> autors) {
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
	
	public int getIdBook() {
		return idBook;
	}
	
	public void setIdBook(int idBook) {
		this.idBook = idBook;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public int getId_publisher() {
		return id_publisher;
	}

	public void setId_publisher(int id_publisher) {
		this.id_publisher = id_publisher;
	}
	
}
