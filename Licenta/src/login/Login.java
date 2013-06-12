package login;

import java.util.Map;

import validation.LoginValidate;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import forFields.Codification;

@SuppressWarnings("serial")
public class Login extends ActionSupport {
	
	private String user;
	private String password;
	
	Map<String, Object> sessionLogin = ActionContext.getContext().getSession();
	LoginValidate validate;
	Codification codif;
	
	public Login(){
		validate = new LoginValidate();
		codif =  new Codification();
	}
	
	public void validate(){
		if(user.isEmpty()){
			addFieldError("user","User requied");
		}else if(!validate.validateUser(user)){
			addFieldError("user", validate.getMessage());
		}else if(password.isEmpty()){
			addFieldError("password","Password required");
		}else if(!validate.validatePassword(password)){
			addFieldError("password",validate.getMessage());
		}
	}
	
	public String execute(){
		loginSession();
		return "login";
	}
	
	public String cancel(){
		return "cancel";
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
