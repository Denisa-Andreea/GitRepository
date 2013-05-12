package validation;

import iteme.User;

public class LoginValidate {

	DBVerify dbVerify = new DBVerify();
	
	private String message;
	User verify = new User();
	
	public LoginValidate(){
		verify = dbVerify.userDB();
	}
	
	public boolean validateUser(String user){
		if(!verify.getUser().equals(user)){
			message = "User is wrong!!!";
			return false;
		}
		return true;
	}
	
	public boolean validatePassword(String password){
		if(!verify.getPassword().equals(password)){
			message = "Password incorect!!!";
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