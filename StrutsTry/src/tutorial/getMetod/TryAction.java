package tutorial.getMetod;

import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.ActionSupport;


public class TryAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	private String bestSomething;
	private String language;
	
	//citeste explicatiile metodei din clasa LoginClass
	
	public void validate(){
		if(StringUtils.isBlank(getLanguage())){
			addFieldError("language", "Introdu linbajul");
		}
	}

	public String execute() {

		TryGetSomething tryGetSomething = new TryGetSomething();
		setBestSomething(tryGetSomething.getBestSomething(getLanguage()));

		return "success";
	}

	public String getBestSomething() {
		return bestSomething;
	}

	public void setBestSomething(String bestSomething) {
		this.bestSomething = bestSomething;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
