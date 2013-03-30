package forFields;

import iteme.Authors;
import iteme.Publisher;

import java.util.ArrayList;
import java.util.Map;

import book.operation.InsertBook;
import book.operation.InsertPublisher;

import com.opensymphony.xwork2.ActionSupport;

import functions.FunctionPublisher;

public class FieldPublisher extends ActionSupport{
	private static final long serialVersionUID = 1L;

	FunctionPublisher dbcon = new FunctionPublisher();
	InsertBook book = new InsertBook();
	InsertPublisher pub = new InsertPublisher();

	Publisher publisher;
	ArrayList<String> monthList;
	ArrayList<Publisher> listPublisher;
	ArrayList<Authors> authorList;
	Authors author;
	int size;
	Map<String, Object> sessionBook = pub.getSessionBook();
	
	int volume = 0;
	int year = 0;


//	private String authorFN0;

	public String execute() {		
		listPublisher = dbcon.fetchPublisher();	
		monthList = book.initMonthList();
		size = book.getSize();
		return SUCCESS;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public ArrayList<Publisher> getListPublisher() {
		return listPublisher;
	}

	public void setListPublisher(ArrayList<Publisher> listPublisher) {
		this.listPublisher = listPublisher;
	}
	
	public ArrayList<String> getMonthList() {
		return monthList;
	}

	public void setMonthList(ArrayList<String> monthList) {
		this.monthList = monthList;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Map<String, Object> getSessionBook() {
		return sessionBook;
	}

	public void setSessionBook(Map<String, Object> sessionBook) {
		this.sessionBook = sessionBook;
	}
	

}
