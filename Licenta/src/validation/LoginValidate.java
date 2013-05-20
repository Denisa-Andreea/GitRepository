package validation;

import forFields.Codification;
import iteme.User;

public class LoginValidate {

	DBVerify dbVerify = new DBVerify();
	Codification codif =  new Codification();
	
	private String message;
	User verify = new User();
	
	public LoginValidate(){
		verify = dbVerify.userDB();
	}
	
	public boolean validateUser(String user){
		if(!verify.getUser().equals(user)){
			message = "Invalid User";
			return false;
		}
		return true;
	}
	
	public boolean validatePassword(String password){
		if(!verify.getPassword().equals(codif.md5(password))){
			message = "Invalid Password";
			return false;
		}
		return true;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
