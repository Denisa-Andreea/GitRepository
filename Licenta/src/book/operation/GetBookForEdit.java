package book.operation;

import iteme.Books;
import iteme.Publisher;

import java.util.ArrayList;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
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
	Map<String, Object> sessionEdit = ActionContext.getContext().getSession();
	
	private int id;
	private int size;
	private int publisherSelected;
	
	
	public String execute(){
		listPublisher = pub.fetchPublisher();
		monthList = monthListInit.initMonthList();
		bookList = function.selectBook(id);
		publisherSelected = bookList.get(0).getId_publisher();
		size = bookList.get(0).getAutors().size();	
		setSessionEdit(sessionEdit());
		return SUCCESS;
	}
	
	public Map<String, Object> sessionEdit(){
		setSessionEdit(sessionEdit);
		
		sessionEdit.put("id", bookList.get(0).getIdBook());
		sessionEdit.put("publisher", bookList.get(0).getId_publisher());
		sessionEdit.put("title", bookList.get(0).getTitle());
		sessionEdit.put("year", bookList.get(0).getYear());
		sessionEdit.put("volume",bookList.get(0).getVolume());
		sessionEdit.put("authorList", bookList.get(0).getAutors());
		sessionEdit.put("series", bookList.get(0).getSeries());
		sessionEdit.put("edition", bookList.get(0).getSeries());
		sessionEdit.put("month", bookList.get(0).getMonth());
		sessionEdit.put("note", bookList.get(0).getNote());
		sessionEdit.put("size", bookList.get(0).getAutors().size());
		return sessionEdit;
	}

	public void sessionEditUnset(){
		sessionEdit.clear();
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

	public Map<String, Object> getSessionEdit() {
		return sessionEdit;
	}

	public void setSessionEdit(Map<String, Object> sessionEdit) {
		this.sessionEdit = sessionEdit;
	}
	
}
