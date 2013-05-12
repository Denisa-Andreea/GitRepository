package publisher.operation;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import validation.PublisherValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import functions.FunctionForUpdatePublisher;

public class EditPublisher extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	FunctionForUpdatePublisher function = new FunctionForUpdatePublisher();
	Map<String, Object> sessionBook = ActionContext.getContext().getSession();
	PublisherValidation validation = new PublisherValidation();
	
	private int id;
	private String name;
	private String selectedState;
	private String city;
	
	public void validate(){
		if(StringUtils.isBlank(getName())){
			addFieldError("name", "Please insert the name");
		}
		if(validation.littleFirstLetter(getName())){
			setName(name.substring(0,1).toUpperCase()+name.substring(1));
		}
		if(StringUtils.equals(getSelectedState(), "NON")){
			addFieldError("country", "Please select the country");
		}
		if(StringUtils.equals(getCity(), "NON")){
			addFieldError("city", "Please select the city");
		}
	}
	
	public String execute(){
		if(sessionBook.get("login") == null){
			return "noUser";
		}
		function.editPublisher(id, name, selectedState, city);
		return SUCCESS;
	}
	
	
	public String cancel(){
		if(sessionBook.get("login") == null){
			return "noUser";
		}
		return "cancel";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSelectedState() {
		return selectedState;
	}

	public void setSelectedState(String selectedState) {
		this.selectedState = selectedState;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<String, Object> getSessionBook() {
		return sessionBook;
	}

	public void setSessionBook(Map<String, Object> sessionBook) {
		this.sessionBook = sessionBook;
	}

}
