package publisher.operation;

import iteme.Country;

import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import validation.PublisherValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import functions.FunctionForUpdatePublisherJournal;
import functions.GetCountryCity;

public class EditPublisher extends ActionSupport {
	private static final long serialVersionUID = 1L;

	FunctionForUpdatePublisherJournal function = new FunctionForUpdatePublisherJournal();
	Map<String, Object> sessionBook = ActionContext.getContext().getSession();
	PublisherValidation validation = new PublisherValidation();
	GetCountryCity functionCountry = new GetCountryCity();

	private int id;
	private String name;
	private String selectedState;
	private String city;
	ArrayList<Country> countryList;
	ArrayList<String> cityList ;

	public EditPublisher(){
		countryList = functionCountry.fetchCountry();
	}
	public void validate() {
		cityList = functionCountry.fetchCity(selectedState);
		if (StringUtils.isBlank(getName())) {
			addFieldError("name", "Insert the name");
		} else if (validation.littleFirstLetter(getName())) {
			setName(name.substring(0, 1).toUpperCase() + name.substring(1));
		}
		if (validation.alreadyExistPublisherContition(getName(),
				getSelectedState(), getCity(), sessionBook.get("name").toString(),
				sessionBook.get("country").toString(), sessionBook.get("city").toString())) {
			addFieldError("name", "Already Exist");
		}
		if (StringUtils.equals(getSelectedState(), "NON")) {
			addFieldError("country", "Select the country");
		}
		if (StringUtils.equals(getCity(), "NON")) {
			addFieldError("city", "Select the city");
		}
	}

	public String execute() {
		if (sessionBook.get("login") == null) {
			return "noUser";
		}
		function.editPublisher(id, name, selectedState, city);
		return SUCCESS;
	}

	public String cancel() {
		if (sessionBook.get("login") == null) {
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

}
