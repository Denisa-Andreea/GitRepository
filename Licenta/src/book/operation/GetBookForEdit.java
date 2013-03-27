package book.operation;

import iteme.Books;
import iteme.Publisher;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

import functions.FunctionBookAuthor;
import functions.FunctionPublisher;

public class GetBookForEdit extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	FunctionBookAuthor function = new FunctionBookAuthor();
	FunctionPublisher pub = new FunctionPublisher();
	InsertBook monthListInit = new InsertBook();
	
	ArrayList<Books> bookList;
	ArrayList<Publisher> listPublisher;
	ArrayList<String> monthList;
	
	private int id;
	private int size;
	private int publisherSelected;
	
	
	public String execute(){
		listPublisher = pub.fetchPublisher();
		monthList = monthListInit.initMonthList();
		bookList = function.selectBook(id);
		publisherSelected = bookList.get(0).getId_publisher();
		size = bookList.get(0).getAutors().size();		
		return SUCCESS;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public ArrayList<Books> getBookList() {
		return bookList;
	}

	public void setBookList(ArrayList<Books> bookList) {
		this.bookList = bookList;
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

	public int getPublisherSelected() {
		return publisherSelected;
	}

	public void setPublisherSelected(int publisherSelected) {
		this.publisherSelected = publisherSelected;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
