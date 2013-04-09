package book.operation;

import iteme.Country;

import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.ActionSupport;

import forFields.ForCountryCity;
import functions.FunctionPublisher;
import functions.GetCountryCity;

public class InsertPublisher extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String country;
	private String city;
	
	ArrayList<Country> countryLit;
	ArrayList<String> cityList;
	FunctionPublisher function =new FunctionPublisher();
	GetCountryCity functionCountry = new GetCountryCity();
//	InsertBook book = new InsertBook();
	ForCountryCity book = new ForCountryCity();
	Map<String, Object> sessionBook = book.getSessionBook();
	
	public InsertPublisher(){
		countryLit = functionCountry.fetchCountry();
	}
	
	public void validate(){
		if(StringUtils.isBlank(getName())){
			addFieldError("name", "Please insert the name");
		}
		if(StringUtils.isBlank(getCountry())){
			addFieldError("country", "Please select the country");
		}
	}

	public String execute() {
		System.out.println("execute");
		function.insertPublisher(name, country);
		return SUCCESS;
	}
	
	public String execute2(){
		System.out.println("execute 2");
		function.insertPublisher(name, country);
		return "fetch";
	}

	public String cancel(){
		return "cancel";
	}
	
	public String back(){
		return "back";
	}
	

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public ArrayList<Country> getCountryLit() {
		return countryLit;
	}

	public void setCountryLit(ArrayList<Country> countryLit) {
		this.countryLit = countryLit;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	

}
