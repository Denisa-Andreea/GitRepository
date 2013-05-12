package login;

import java.util.Map;

import validation.LoginValidate;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Login extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	
	private String user;
	private String password;
	
	Map<String, Object> sessionLogin = ActionContext.getContext().getSession();
	LoginValidate validate = new LoginValidate();
	
	public void validate(){
		if(user.isEmpty()){
			addFieldError("user","Please insert the user!");
		}else if(!validate.validateUser(user)){
			addFieldError("user", validate.getMessage());
		}else if(password.isEmpty()){
			addFieldError("password","Please insert the password!");
		}else if(!validate.validatePassword(password)){
			addFieldError("password",validate.getMessage());
		}
	}
	
	public String execute(){
		loginSession();
		return "login";
	}
	
	public String back(){
		logoutSession();
		return "logout";
	}
	
	public String browse(){
		return "changePassword";
	}
	
	public void loginSession(){
		sessionLogin.put("login", true);
		sessionLogin.put("user", user);
	}
	
	public void logoutSession(){
		sessionLogin.clear();
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Map<String, Object> getSessionLogin() {
		return sessionLogin;
	}

	public void setSessionLogin(Map<String, Object> sessionLogin) {
		this.sessionLogin = sessionLogin;
	}
	
}
