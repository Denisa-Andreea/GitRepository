package user;

import functions.FunctionForSearch;
import functions.FuntionToUse;
import iteme.Article;
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
	private ArrayList<Article> article;

	FunctionForSearch function = new FunctionForSearch();
	FuntionToUse replace = new FuntionToUse();

	public Search() {
		book = new ArrayList<Books>();
		authors = new HashMap<Authors, ArrayList<Books>>();
		article = new ArrayList<Article>();
	}

	public void validate() {
		word = replace.multipleSpaceElim(word);
	}

	public String execute() {
		if(word.equals(null)){
			word = "%";
		}
		if (category.equals("books")) {
			book = function.searchBook(word);
			return "books";
		}
		if (category.equals("authors")) {
			authors = function.searchAuthor(word, true);
			return "authors";
		}
		if(category.equals("article")){
			article = function.searchArticle(word);
			return "article";
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

	public ArrayList<Article> getArticle() {
		return article;
	}

	public void setArticle(ArrayList<Article> article) {
		this.article = article;
	}

}
