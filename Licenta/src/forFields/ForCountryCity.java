package forFields;

import functions.GetCountryCity;
import iteme.Country;

import java.util.ArrayList;
import java.util.Map;

import book.operation.InsertBook;

import com.opensymphony.xwork2.ActionSupport;

public class ForCountryCity extends ActionSupport {
	private static final long serialVersionUID = 1L;

	GetCountryCity function = new GetCountryCity();
	ToGetCity funcCity = new ToGetCity();
	InsertBook book = new InsertBook();
	
	ArrayList<Country> countryList = new ArrayList<Country>();
	Map<String, Object> sessionBook = book.getSessionBook();
	ArrayList<String> cityList = new ArrayList<String>(funcCity.cityList);
	
	public String execute(){
		if(sessionBook.get("login") == null){
			return "noUser";
		}
		countryList = function.fetchCountry();
		return SUCCESS;
	}
	

	public ArrayList<Country> getCountryList() {
		return countryList;
	}

	public void setCountryList(ArrayList<Country> countryList) {
		this.countryList = countryList;
	}

	public Map<String, Object> getSessionBook() {
		return sessionBook;
	}

	public void setSessionBook(Map<String, Object> sessionBook) {
		this.sessionBook = sessionBook;
	}

	public ArrayList<String> getCityList() {
		return cityList;
	}

	public void setCityList(ArrayList<String> cityList) {
		this.cityList = cityList;
	}

	
}
