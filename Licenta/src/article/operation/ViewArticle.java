package article.operation;

import iteme.ArticleAuthor;

import java.util.ArrayList;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import functions.FunctionArticle;

@SuppressWarnings("serial")
public class ViewArticle extends ActionSupport {

	private int page = 1;
	private int numberOfPages;
	private int sizeTabel;
	private String column;
	private String sort = "NON";

	FunctionArticle function =  new FunctionArticle();
	ArrayList<ArticleAuthor> listOfArticles = new ArrayList<ArticleAuthor>();
	Map<String, Object> sessionLogin = ActionContext.getContext().getSession();

	public String execute() {
		if (sessionLogin.get("login") == null) {
			return "noUser";
		}
		int recordsPerPage = 5;
		if (sort.equals("NON") || sort.isEmpty()) {
			listOfArticles = function.fetchAtricle((page - 1) * recordsPerPage,
					recordsPerPage);
			setSizeTabel(listOfArticles.size());
		}else{
			if(column.isEmpty()){
				 column = "id_articol";
			}
			listOfArticles = function.sortArticle((page - 1) * recordsPerPage, recordsPerPage,column , sort);
			setSizeTabel(listOfArticles.size());
		}
		
		int numberOfRecords = function.getNumberOfRecords();
		setNumberOfPages((int) Math
				.ceil(numberOfRecords * 1.0 / recordsPerPage));
		return SUCCESS;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public int getSizeTabel() {
		return sizeTabel;
	}

	public void setSizeTabel(int sizeTabel) {
		this.sizeTabel = sizeTabel;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	public Map<String, Object> getSessionLogin() {
		return sessionLogin;
	}


	public void setSessionLogin(Map<String, Object> sessionLogin) {
		this.sessionLogin = sessionLogin;
	}

	public ArrayList<ArticleAuthor> getListOfArticles() {
		return listOfArticles;
	}

	public void setListOfArticles(ArrayList<ArticleAuthor> listOfArticles) {
		this.listOfArticles = listOfArticles;
	}
}
