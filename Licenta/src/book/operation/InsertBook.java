package book.operation;

import iteme.Authors;
import iteme.Publisher;

import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import functions.FunctionBookAuthor;
import functions.FunctionPublisher;

public class InsertBook extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;

	private String title = null;
	private String authorFN0 = "Unknown";
	private String authorLN0;
	private String authorFN1;
	private String authorLN1;
	private String authorFN2;
	private String authorLN2;
	private String authorFN3;
	private String authorLN3;
	private String authorFN4;
	private String authorLN4;
	private String authorFN5;
	private String authorLN5;
	private String publisher;
	private int volume;
	private int year;

	FunctionBookAuthor function = new FunctionBookAuthor();
	FunctionPublisher pub = new FunctionPublisher();

	Authors authors;
	ArrayList<Publisher> listPublisher;
	Map<String, Object> session;

	public InsertBook() {
		listPublisher = pub.fetchPublisher();
		//getAuthorList();
	}

	public String browse() {
		System.out.println("addPublisher");
		setSession(sessionBook());
		return "publisher";
	}

	public String cancel() {
		System.out.println("cancel");
		return "cancel";
	}

	public String execute() {
		sessionBookUnset();
		function.insertBook(getTitle(), getAuthorList(), Integer.parseInt(getPublisher()),
				getVolume(), getYear());
		return SUCCESS;

	}

	public void validate() {
		if(session.isEmpty()){
			if (StringUtils.isBlank(getTitle())) {
				addFieldError("title", "Please insert the title");
			}
			if (getPublisher().equals("-Select a Publisher-")) {
				addFieldError("publisher", "Please insert the publisher");
			}
		}else if(StringUtils.isBlank((String) session.get("title"))){
			addFieldError("title", "Please insert the title session");
		}
	
	}

	/**
	 * verific care filduri de autor nu sunt goale si le pun intr-un array
	 */

	public ArrayList<Authors> getAuthorList() {
		ArrayList<Authors> authorList = new ArrayList<Authors>();
		if (getAuthorFN0() != null) {
			authors = new Authors();
			authors.setFirstName(getAuthorFN0());
			authors.setLastName(getAuthorLN0());
			authorList.add(authors);
		}
		if (getAuthorFN1() != null) {
			authors = new Authors();
			authors.setFirstName(getAuthorFN1());
			authors.setLastName(getAuthorLN1());
			authorList.add(authors);
		}
		if (getAuthorFN2() != null) {
			authors = new Authors();
			authors.setFirstName(getAuthorFN2());
			authors.setLastName(getAuthorLN2());
			authorList.add(authors);
		}
		if (getAuthorFN3() != null) {
			authors = new Authors();
			authors.setFirstName(getAuthorFN3());
			authors.setLastName(getAuthorLN3());
			authorList.add(authors);
		}
		if (getAuthorFN4() != null) {
			authors = new Authors();
			authors.setFirstName(getAuthorFN4());
			authors.setLastName(getAuthorLN4());
			authorList.add(authors);
		}
		if (getAuthorFN5() != null) {
			authors = new Authors();
			authors.setFirstName(getAuthorFN5());
			authors.setLastName(getAuthorLN5());
			authorList.add(authors);
		}
		// for (int i = 0; i < authorList.size(); i++) {
		// System.out.println("pe" + i + " "
		// + authorList.get(i).getFirstName() + " "
		// + authorList.get(i).getLastName());
		// }
		return authorList;
	}

	/**
	 * setez sesiunea pentru fildurile din pagina bookInsert
	 * 
	 * @return
	 */
	public Map<String, Object> sessionBook() {
		System.out.println("set sesion");
		setSession(session);
		session.put("title", getTitle());
		session.put("year", getYear());
		session.put("volume", getVolume());
		System.out.println(session.get("title") + " " + session.get("year")
				+ " " + session.get("volume"));
		session.put("authorList", getAuthorList());			
		return session;
	}
	
	public void sessionBookUnset(){
		System.out.println("unset session");
		session.remove("title");
		session.remove("year");
		session.remove("volume");
		session.remove("authorList");
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getAuthorFN0() {
		return authorFN0;
	}

	public void setAuthorFN0(String authorFN0) {
		this.authorFN0 = authorFN0;
	}

	public String getAuthorLN0() {
		return authorLN0;
	}

	public void setAuthorLN0(String authorLN0) {
		this.authorLN0 = authorLN0;
	}

	public String getAuthorFN1() {
		return authorFN1;
	}

	public void setAuthorFN1(String authorFN1) {
		this.authorFN1 = authorFN1;
	}

	public String getAuthorLN1() {
		return authorLN1;
	}

	public void setAuthorLN1(String authorLN1) {
		this.authorLN1 = authorLN1;
	}

	public String getAuthorFN2() {
		return authorFN2;
	}

	public void setAuthorFN2(String authorFN2) {
		this.authorFN2 = authorFN2;
	}

	public String getAuthorFN3() {
		return authorFN3;
	}

	public void setAuthorFN3(String authorFN3) {
		this.authorFN3 = authorFN3;
	}

	public String getAuthorLN2() {
		return authorLN2;
	}

	public void setAuthorLN2(String authorLN2) {
		this.authorLN2 = authorLN2;
	}

	public String getAuthorLN3() {
		return authorLN3;
	}

	public void setAuthorLN3(String authorLN3) {
		this.authorLN3 = authorLN3;
	}

	public String getAuthorFN4() {
		return authorFN4;
	}

	public void setAuthorFN4(String authorFN4) {
		this.authorFN4 = authorFN4;
	}

	public String getAuthorLN4() {
		return authorLN4;
	}

	public void setAuthorLN4(String authorLN4) {
		this.authorLN4 = authorLN4;
	}

	public String getAuthorLN5() {
		return authorLN5;
	}

	public void setAuthorLN5(String authorLN5) {
		this.authorLN5 = authorLN5;
	}

	public String getAuthorFN5() {
		return authorFN5;
	}

	public void setAuthorFN5(String authorFN5) {
		this.authorFN5 = authorFN5;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public ArrayList<Publisher> getListPublisher() {
		return listPublisher;
	}

	public void setListPublisher(ArrayList<Publisher> listPublisher) {
		this.listPublisher = listPublisher;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
