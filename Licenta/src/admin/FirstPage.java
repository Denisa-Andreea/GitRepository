package admin;

import java.util.Map;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FirstPage extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	Map<String, Object> sessionLogin = ActionContext.getContext().getSession();
	
	public String execute(){
		if(sessionLogin.get("login") == null){
			return "noUser";
		}
		return SUCCESS;
	}

	public Map<String, Object> getSessionLogin() {
		return sessionLogin;
	}

	public void setSessionLogin(Map<String, Object> sessionLogin) {
		this.sessionLogin = sessionLogin;
	}
	
}
