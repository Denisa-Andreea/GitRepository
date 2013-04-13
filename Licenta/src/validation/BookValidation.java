package validation;

import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;

public class BookValidation {
	
	private String message;
	Calendar c = Calendar.getInstance();
	DBVerify bdVerify = new DBVerify();

	public BookValidation(){
	}
	
	public boolean blankString(String value){
		if(StringUtils.isBlank(value)){
			return true;
		}
		return false;
	}
	
	public boolean notNumberValidate(String value){
		for(char c : value.toCharArray()){
			if(!Character.isDigit(c)){
				return true;
			}
		}
		return false;
	}
	
	public boolean alreadyExistTitle(String value){
		if(bdVerify.titleVerify(value).equals("exista")){
			return true;
		}
		return false;
	}
	
	public boolean invalidYear(String year){
		if(year.substring(0,1).equals("0")){
			message = "Year can't begin with 0";
			return true;
		}else if(Integer.parseInt(year) < 1000){
			message = "Year must have 4 numbers";
			return true;
		}else if (Integer.parseInt(year) > c.get(Calendar.YEAR)) {
			message = "The year is bigger then the current year("
					+ c.get(Calendar.YEAR) + ")";
			return true;
		}
		return false;
	}
	
	public boolean littleFirstLetter(String value){
		if(value.substring(0, 1).equals(value.substring(0,1).toLowerCase())){
			return true;
		}
		return false;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
