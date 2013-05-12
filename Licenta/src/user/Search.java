package user;

import functions.FunctionForSearch;
import functions.FuntionToUse;
import iteme.Authors;
import iteme.Books;

import java.util.ArrayList;
import java.util.HashMap;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Search extends ActionSupport {

	private String word;
	private String category;
	private ArrayList<Books> book;
	private HashMap<Authors, ArrayList<Books>> authors;

	FunctionForSearch function = new FunctionForSearch();
	FuntionToUse replace = new FuntionToUse();

	public Search() {
		book = new ArrayList<Books>();
	}

	public void validate() {
		word = replace.multipleSpaceElim(word);
	}

	public String execute() {
		if (category.equals("books")) {
			book.addAll(function.searchBook(word));
			return "books";
		}
		if (category.equals("authors")) {
			authors = function.searchAuthor(word, true);
			// for (Entry<Authors, ArrayList<Books>> entry : authors.entrySet())
			// {
			// Authors key = entry.getKey();
			// ArrayList<Books> value = entry.getValue();
			// System.out
			// .println(key.getFirstName() + " " + key.getLastName());
			// for (int i = 0; i < value.size(); i++) {
			// System.out.println(value.get(i).getTitle());
			// }
			// }
			return "authors";
		}
		if(category.equals("displayOneAuthor")){
			authors = function.searchAuthor(word, false);
			return "authors";
		}
		return SUCCESS;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public ArrayList<Books> getBook() {
		return book;
	}

	public void setBook(ArrayList<Books> book) {
		this.book = book;
	}

	public HashMap<Authors, ArrayList<Books>> getAuthors() {
		return authors;
	}

	public void setAuthors(HashMap<Authors, ArrayList<Books>> authors) {
		this.authors = authors;
	}

}
