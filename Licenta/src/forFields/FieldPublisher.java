package forFields;

import iteme.Authors;
import iteme.Publisher;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

import functions.FunctionPublisher;

public class FieldPublisher extends ActionSupport {
	private static final long serialVersionUID = 1L;

	FunctionPublisher dbcon = new FunctionPublisher();

	Publisher publisher;
	ArrayList<Publisher> listPublisher;
	ArrayList<Authors> authorList;

	private String authorFN0;

	public String execute() {
		authorList = null;
		setAuthorFN0("Unknown");
		listPublisher = dbcon.fetchPublisher();
//		for(int i =0; i<listPublisher.size();i++){
//			System.out.println(listPublisher.get(i).getId_publisher());
//		}
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

	public String getAuthorFN0() {
		return authorFN0;
	}

	public void setAuthorFN0(String authorFN0) {
		this.authorFN0 = authorFN0;
	}
}
