package book.operation;

import iteme.BookAuthor;
import iteme.Books;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

import functions.FunctionBookAuthor;

public class ViewBooks extends ActionSupport {

	private static final long serialVersionUID = 1L;

	FunctionBookAuthor books = new FunctionBookAuthor();
	Books bookAuthor;
	ArrayList<BookAuthor> listBook;

	private int page ;
	private int numberOfPages;
	
	public String execute() {
		
		int recordsPerPage = 4;
		if(page == 0){
			page = 1;
		}
		listBook = books
				.fetchBooks((page - 1) * recordsPerPage, recordsPerPage);
		
		int numberOfRecords = books.getNumberOfRecords();
		setNumberOfPages((int) Math.ceil(numberOfRecords * 1.0 / recordsPerPage));
//		System.out.println(numberOfPages);
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
	
}
