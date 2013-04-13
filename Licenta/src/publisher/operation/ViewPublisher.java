package publisher.operation;

import java.util.ArrayList;

import iteme.Publisher;

import com.opensymphony.xwork2.ActionSupport;

import functions.FunctionPublisher;

public class ViewPublisher extends ActionSupport {
	private static final long serialVersionUID = 1L;

	FunctionPublisher publisherFetch = new FunctionPublisher();
	Publisher publisher;
	ArrayList<Publisher> listPublisher;

	public String execute() {
		listPublisher = publisherFetch.fetchPublisher();
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

}
