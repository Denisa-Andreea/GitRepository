package login;

import validation.LoginValidate;

import com.opensymphony.xwork2.ActionSupport;

import functions.FunctionChangePassword;

public class ChangePassword extends ActionSupport {
	private static final long serialVersionUID = 1L;

	LoginValidate validate = new LoginValidate();
	FunctionChangePassword function = new FunctionChangePassword();
	
	private String oldPassword;
	private String newPassword;
	
	public String execute(){
		function.changePassword(oldPassword, newPassword);
		return SUCCESS;
	}

	public void validate(){
		if(oldPassword.isEmpty()){
			addFieldError("oldPassword","Please insert the current password!");
		}else if(!validate.validatePassword(oldPassword)){
			addFieldError("oldPassword",validate.getMessage());
		}
		if(newPassword.isEmpty()){
			addFieldError("newPassword","Please insert the new password!");
		}else if(newPassword.length() < 4 || newPassword.length() > 16){
			addFieldError("newPassword","Password must have at least 4 caracters and axim 16");
		}
	}
	
	public String cancel(){
		return "cancel";
	}
	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
}
