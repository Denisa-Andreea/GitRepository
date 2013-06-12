package publisher.operation;

import java.util.ArrayList;
import java.util.Map;

import iteme.Publisher;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import functions.FunctionPublisher;
import functions.FuntionToUse;

public class ViewPublisher extends ActionSupport {
	private static final long serialVersionUID = 1L;

	FunctionPublisher publisherFetch = new FunctionPublisher();
	FuntionToUse clear =  new FuntionToUse();
	Publisher publisher;
	ArrayList<Publisher> listPublisher;
	Map<String, Object> sessionLogin = ActionContext.getContext().getSession();
	
	private int page = 1;
	private int numberOfPages;
	private int sizeTabel;
	private String column;
	private String sort = "NON";

	public String execute() {
		if(sessionLogin.get("login") == null){
			return "noUser";
		}
		clear.clearSession(sessionLogin);
		int recordsPerPage = 5;
		if (sort.equals("NON") || sort.isEmpty()) {
			column = "id_publisher";
			sort = "ASC";
			listPublisher = publisherFetch.fetchPublisherSort((page - 1) * recordsPerPage,
					recordsPerPage, column ,sort);
			setSizeTabel(listPublisher.size());
		}else{
			if(column.isEmpty()){
				 column = "id_publisher";
			}
			listPublisher = publisherFetch.fetchPublisherSort((page - 1) * recordsPerPage,
					recordsPerPage, column , sort);
			setSizeTabel(listPublisher.size());
		}
		int numberOfRecords = publisherFetch.getNumberOfRecords();
		setNumberOfPages((int) Math
				.ceil(numberOfRecords * 1.0 / recordsPerPage));
//		System.out.println(numberOfPages);
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

}
