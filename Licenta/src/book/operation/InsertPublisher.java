package book.operation;

import com.opensymphony.xwork2.ActionSupport;

public class InsertPublisher extends ActionSupport {
	private static final long serialVersionUID = 1L;

	InsertBook book = new InsertBook();
	public String execute() {
		return SUCCESS;
	}
}
