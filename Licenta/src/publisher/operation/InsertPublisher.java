package publisher.operation;

import iteme.Country;

import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import validation.PublisherValidation;

import com.opensymphony.xwork2.ActionSupport;

import forFields.ForCountryCity;
import functions.FunctionPublisher;
import functions.GetCountryCity;

public class InsertPublisher extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String selectedState;
	private String city;
	private String country;
	private String selectedCity;
	
	
	FunctionPublisher function =new FunctionPublisher();
	GetCountryCity functionCountry = new GetCountryCity();
	ForCountryCity book = new ForCountryCity();
	PublisherValidation validation = new PublisherValidation();
	
	Map<String, Object> sessionBook = book.getSessionBook();
	ArrayList<Country> countryList;
	ArrayList<String> cityList ;
	
	public InsertPublisher(){
		countryList = functionCountry.fetchCountry();
	}
	
	public void validate(){
		setCountry(selectedState);
		cityList = functionCountry.fetchCity(country);
		setSelectedCity(city); 
		
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
		if(validation.alreadyExistPublisher(getName(), getCountry(), getCity())){
			addFieldError("name", "Publisher already exist!!!");
		}
	}


	public String execute() {
//		System.out.println("execute");
		if(sessionBook.get("login") == null){
			return "noUser";
		}
		function.insertPublisher(name, selectedState, city);
		return SUCCESS;
	}
	
	public String execute2(){
//		System.out.println("execute 2");
		if(sessionBook.get("login") == null){
			return "noUser";
		}
		function.insertPublisher(name, selectedState, city);
		return "fetch";
	}

	public String cancel(){
		if(sessionBook.get("login") == null){
			return "noUser";
		}
		return "cancel";
	}
	
	public String back(){
		if(sessionBook.get("login") == null){
			return "noUser";
		}
		book.setSessionBook(sessionBook);
		return "back";
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Object> getSessionBook() {
		return sessionBook;
	}

	public void setSessionBook(Map<String, Object> sessionBook) {
		this.sessionBook = sessionBook;
	}


	public ArrayList<Country> getCountryList() {
		return countryList;
	}

	public void setCountryList(ArrayList<Country> countryList) {
		this.countryList = countryList;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSelectedState() {
		return selectedState;
	}

	public void setSelectedState(String selectedState) {
		this.selectedState = selectedState;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getSelectedCity() {
		return selectedCity;
	}

	public void setSelectedCity(String selectedCity) {
		this.selectedCity = selectedCity;
	}


	public ArrayList<String> getCityList() {
		return cityList;
	}
	
	public void setCityList(ArrayList<String> cityList) {
		this.cityList = cityList;
	}
}
