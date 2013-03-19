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
	
	public String execute() {
		listBook = books.fetchBooks();
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


}
