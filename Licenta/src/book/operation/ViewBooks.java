package book.operation;

import iteme.BookAuthor;
import iteme.Books;

import java.util.ArrayList;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import functions.FunctionBookAuthor;

@SuppressWarnings("serial")
public class ViewBooks extends ActionSupport {

	private int page = 1;
	private int numberOfPages;
	private int sizeTabel;
	private String column;
	private String sort = "NON";
	
	
	FunctionBookAuthor books = new FunctionBookAuthor();
	Books bookAuthor;
	ArrayList<BookAuthor> listBook;
	Map<String, Object> sessionLogin = ActionContext.getContext().getSession();


	public String execute() {
		if(sessionLogin.get("login") == null){
			return "noUser";
		}
		int recordsPerPage = 5;
		if (sort.equals("NON") || sort.isEmpty()) {
			listBook = books.fetchBooks((page - 1) * recordsPerPage,
					recordsPerPage);
			setSizeTabel(listBook.size());
		}else{
			if(column.isEmpty()){
				 column = "id_carte";
			}
			listBook = books.sortBooks((page - 1) * recordsPerPage, recordsPerPage,column , sort);
			setSizeTabel(listBook.size());
		}
		int numberOfRecords = books.getNumberOfRecords();
		setNumberOfPages((int) Math
				.ceil(numberOfRecords * 1.0 / recordsPerPage));
		return SUCCESS;
	}
	

	public Books getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(Books bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public ArrayList<BookAuthor> getListBook() {
		return listBook;
	}

	public void setListBook(ArrayList<BookAuthor> listBook) {
		this.listBook = listBook;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSizeTabel() {
		return sizeTabel;
	}

	public void setSizeTabel(int sizeTabel) {
		this.sizeTabel = sizeTabel;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}


	public Map<String, Object> getSessionLogin() {
		return sessionLogin;
	}


	public void setSessionLogin(Map<String, Object> sessionLogin) {
		this.sessionLogin = sessionLogin;
	}

}
