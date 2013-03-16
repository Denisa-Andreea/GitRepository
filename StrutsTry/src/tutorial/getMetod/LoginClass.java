package tutorial.getMetod;

import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LoginClass extends ActionSupport implements ModelDriven<Object>{

	private User user = new User();

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private static final long serialVersionUID = 1L;

	/*
	 * Clasa ActioSupport implementeaza mai multe interfete Pentru a verifica
	 * daca user si password sunt goale implementam metoda validate Strurs2 o
	 * apeeaza inante ca sa apeleze execute()...iar rezultatul implicit al
	 * acestei metote este "input" Struts 2 face validare si afisarea mesajelor
	 * de eraoare fara ca noi sa facem ceva...
	 */

	public void validate() {

		if (StringUtils.isBlank(user.getUserId())) {
			// Daca userId e blank afiseaza mesaj de eroare
			addFieldError("user.userId", "Introduce'ti user-ul");
		}

		if (StringUtils.isBlank(user.getPassword())) {
			// Daca parola e blank afiseaza mesaj de eroare
			addFieldError("user.password", "Introduce'ti parola");
		}

	}

	public String execute() {
		
		if (verifyLogin(user)) {
			return SUCCESS;
		}
		return LOGIN;
	}

	public boolean verifyLogin(User user) {
		if (user.getUserId().equals("userId")
				&& user.getPassword().equals("password")) {
			return true;
		}
		return false;
	}

	 public Object getModel() {
	 return user;
	 }

}
