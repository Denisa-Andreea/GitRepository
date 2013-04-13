package forFields;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

import functions.GetCountryCity;

public class ToGetCity extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	GetCountryCity function = new GetCountryCity();
	ArrayList<String> cityList = new ArrayList<String>();
	String code;
	
	public String execute(){
//		System.out.println(code);
		cityList = function.fetchCity(code);
//		System.out.println(cityList);
		return SUCCESS;
	}

	public ArrayList<String> getCityList() {
		return cityList;
	}

	public void setCityList(ArrayList<String> cityList) {
		this.cityList = cityList;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
