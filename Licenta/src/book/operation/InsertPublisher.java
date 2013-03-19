package book.operation;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import functions.FunctionPublisher;

public class InsertPublisher extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String address;
	FunctionPublisher function =new FunctionPublisher();
	InsertBook book = new InsertBook();
	Map<String, Object> session = book.getSession();
	
	public void validate(){
		System.out.println(" session");
		if(StringUtils.isBlank(getName())){
			addFieldError("name", "Please insert the name");
		}
		if(StringUtils.isBlank(getAddress())){
			addFieldError("address", "Please insert the address");
		}
	}

	public String execute() {
		System.out.println("execute");
		function.insertPublisher(name, address);
		return SUCCESS;
	}
	
	public String execute2(){
		System.out.println("execute 2");
		function.insertPublisher(name, address);
		return "fetch";
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
