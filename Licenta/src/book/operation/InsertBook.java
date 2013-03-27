package book.operation;

import iteme.Authors;
import iteme.Publisher;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import functions.FunctionBookAuthor;
import functions.FunctionPublisher;

public class InsertBook extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;

	Authors authors;
	ArrayList<Publisher> listPublisher;
	ArrayList<String> monthList;
	Map<String, Object> session;
	Calendar c = Calendar.getInstance();

	private String title = null;
	private ArrayList<String> authorFN;
	private ArrayList<String> authorLN;
	private int publisher;
	private int volume = 0;
	private int year = 0;
	private String series;
	private String edition;
	private String month;
	private String note;
	private int publisherSelected;
	private int size = 1;

	FunctionBookAuthor function = new FunctionBookAuthor();
	FunctionPublisher pub = new FunctionPublisher();

	public InsertBook() {
		listPublisher = pub.fetchPublisher();
		monthList = initMonthList();
	}

	public String browse() {
		System.out.println(month);
		System.out.println("addPublisher");
		setSession(sessionBook());
		setPublisher(0);
		return "publisher";
	}

	public String cancel() {
		System.out.println("cancel");
		sessionBookUnset();
		return "cancel";
	}

	public String execute() {
		System.out.println("execute");
		sessionBookUnset();
		function.insertBook(getTitle(), getAuthorList(), getPublisher(),
				getVolume(), getYear(),getSeries(),getEdition(),getMonth(),getNote());
		return SUCCESS;

	}

	/**
	 * validare la nivel de server ..este facuta provizoriu ...TREBUIE FACUTA
	 */
	public void validate() {
		setPublisherSelected(getPublisher());
//		for(int i = 0;i < authorFN.size(); i++){
//			System.out.println(authorFN.get(i));
//		}
		if (session.isEmpty()) {
			// System.out.println("publisherSelected " + publisherSelected);
			if (StringUtils.isBlank(getTitle())) {
				addFieldError("title", "Please insert the title");
			}
			if (getPublisher() == 0) {
				addFieldError("publisher", "Please select the publisher");
			}
			if (getYear() == 0) {
				addFieldError("year", "Please insert the year");
			} else if (1000 > getYear()) {
				addFieldError("year", "Year must have 4 numbers");
			} else if (getYear() > c.get(Calendar.YEAR)) {
				addFieldError(
						"year",
						"The year is bigger then the current year("
								+ c.get(Calendar.YEAR) + ")");
			}

		} else {
			sessionBook();
			if (StringUtils.isBlank((String) session.get("title"))) {
				addFieldError("title", "Please insert the title session");
			}
			if (getPublisher() == 0) {
				addFieldError("publisher", "Select the Publisher Session");
			}
			if (Integer.parseInt(session.get("year").toString()) == 0) {
				addFieldError("year", "Please insert the year");
			} else if (1000 > Integer.parseInt(session.get("year").toString())) {
				addFieldError("year", "Year must have 4 numbers");
			} else if (Integer.parseInt(session.get("year").toString()) > c
					.get(Calendar.YEAR)) {
				addFieldError(
						"year",
						"The year is bigger then the current year("
								+ c.get(Calendar.YEAR) + ")");
			}
		}
	}

	/**
	 * verific care filduri de autor nu sunt goale si le pun intr-un array
	 */

	public ArrayList<Authors> getAuthorList() {
		ArrayList<Authors> authorList = new ArrayList<Authors>();
		for(int i = 0; i< authorFN.size(); i++){
			authors = new Authors();
			authors.setFirstName(getAuthorFN().get(i));
			authors.setLastName(getAuthorLN().get(i));
			authorList.add(authors);
		}
		for (int i = 0; i < authorList.size(); i++) {
			System.out.println("pe" + i + " "
					+ authorList.get(i).getFirstName() + " "
					+ authorList.get(i).getLastName());
		}
		setSize(authorList.size());
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
		session.put("series", getSeries());
		session.put("edition", getSeries());
		session.put("month", getMonth());
		session.put("note", getNote());
		session.put("size", getAuthorList().size());
		return session;
	}

	public void sessionBookUnset() {
		System.out.println("unset session");
		session.remove("title");
		session.remove("year");
		session.remove("volume");
		session.remove("authorList");
		session.remove("series");
		session.remove("edition");
		session.remove("month");
		session.remove("note");
		session.remove("size");
	}

	public ArrayList<String> initMonthList() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("January");
		list.add("February");
		list.add("March");
		list.add("April");
		list.add("May");
		list.add("June");
		list.add("July");
		list.add("August");
		list.add("September");
		list.add("October");
		list.add("November");
		list.add("December");
		return list;
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

	public int getPublisher() {
		return publisher;
	}

	public void setPublisher(int publisher) {
		this.publisher = publisher;
	}

	public ArrayList<Publisher> getListPublisher() {
		return listPublisher;
	}

	public void setListPublisher(ArrayList<Publisher> listPublisher) {
		this.listPublisher = listPublisher;
	}

	public ArrayList<String> getMonthList() {
		return monthList;
	}

	public void setMonthList(ArrayList<String> monthList) {
		this.monthList = monthList;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public int getPublisherSelected() {
		return publisherSelected;
	}

	public void setPublisherSelected(int publisherSelected) {
		this.publisherSelected = publisherSelected;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public ArrayList<String> getAuthorFN() {
		return authorFN;
	}

	public void setAuthorFN(ArrayList<String> authorFN) {
		this.authorFN = authorFN;
	}

	public ArrayList<String> getAuthorLN() {
		return authorLN;
	}

	public void setAuthorLN(ArrayList<String> authorLN) {
		this.authorLN = authorLN;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
