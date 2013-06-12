package publisher.operation;

import iteme.Country;
import iteme.Publisher;

import java.util.ArrayList;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import functions.FunctionPublisher;
import functions.GetCountryCity;

public class GetPublisherForEdit extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	GetCountryCity functionCountry = new GetCountryCity();
	FunctionPublisher function = new FunctionPublisher();
	Map<String, Object> sessionBook = ActionContext.getContext().getSession();
	
	ArrayList<Country> countryList;
	ArrayList<String> cityList ;
	Publisher publisher = new Publisher();
	
	private int id;
	private String name;
	private String selectedState;
	private String state;
	private String city;
	
	public String execute(){
		if(sessionBook.get("login") == null){
			return "noUser";
		}
		countryList = functionCountry.fetchCountry();
		publisher = function.selectPublisher(id);
		setState(function.countryCode(publisher.getCountry()));
		publisher.setId_publisher(id);
		cityList = functionCountry.fetchCity(state);
		setSessionEdit();
		return "edit";
	}
	
	public void setSessionEdit(){
		sessionBook.put("name", publisher.getName());
		sessionBook.put("country", state);
		sessionBook.put("city", publisher.getCity());
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

	public ArrayList<Country> getCountryList() {
		return countryList;
	}

	public void setCountryList(ArrayList<Country> countryList) {
		this.countryList = countryList;
	}

	public ArrayList<String> getCityList() {
		return cityList;
	}

	public void setCityList(ArrayList<String> cityList) {
		this.cityList = cityList;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Map<String, Object> getSessionBook() {
		return sessionBook;
	}

	public void setSessionBook(Map<String, Object> sessionBook) {
		this.sessionBook = sessionBook;
	}
	
	
}
