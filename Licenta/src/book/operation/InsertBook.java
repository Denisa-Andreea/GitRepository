package book.operation;

import iteme.Authors;
import iteme.Publisher;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import functions.FunctionBookAuthor;
import functions.FunctionPublisher;

public class InsertBook extends ActionSupport {
	private static final long serialVersionUID = 1L;

	FunctionBookAuthor function = new FunctionBookAuthor();
	FunctionPublisher pub = new FunctionPublisher();

	Authors authors;
	ArrayList<Publisher> listPublisher;
	ArrayList<String> monthList;
	Map<String, Object> sessionBook = ActionContext.getContext().getSession();
	Calendar c = Calendar.getInstance();

	private String title = null;
	private ArrayList<String> authorFN;
	private ArrayList<String> authorLN;
	private int publisher;
	private String volume;
	private String year;
	private String series;
	private String edition;
	private String month;
	private String note;
	private int publisherSelected;
	private int size = 1;

	public InsertBook() {
		listPublisher = pub.fetchPublisher();
		monthList = initMonthList();
	}

	public String browse() {
//		System.out.println(month);
//		System.out.println("addPublisher");
		setSessionBook(sessionBook());
		setPublisher(0);
		return "publisher";
	}

	public String cancel() {
//		System.out.println("cancel");
		sessionBookUnset();
		return "cancel";
	}

	public String execute() {
//		System.out.println("execute");
		sessionBookUnset();
		function.insertBook(getTitle(), getAuthorList(), getPublisher(),
				Integer.parseInt(getVolume()), Integer.parseInt(getYear()), getSeries(), getEdition(), getMonth(),
				getNote());
		return SUCCESS;

	}

	/**
	 * validare la nivel de server ..este facuta provizoriu ...TREBUIE FACUTA
	 */
	public void validate() {
		setPublisherSelected(getPublisher());
		sessionBookUnset();
		// for(int i = 0;i < authorFN.size(); i++){
		// System.out.println(authorFN.get(i));
		// }
//		if (sessionBook.isEmpty()) {
			// System.out.println("publisherSelected " + publisherSelected);
			if (StringUtils.isBlank(getTitle())) {
				addFieldError("title", "Please insert the title");
			}
			if (getPublisher() == 0) {
				addFieldError("publisher", "Please select the publisher");
			}
			if (StringUtils.isBlank(getYear())) {
				addFieldError("year", "Please insert the year");
			} else if (1000 > Integer.parseInt(getYear())) {
				addFieldError("year", "Year must have 4 numbers");
			} else if (Integer.parseInt(getYear()) > c.get(Calendar.YEAR)) {
				addFieldError(
						"year",
						"The year is bigger then the current year("
								+ c.get(Calendar.YEAR) + ")");
			}
			if(StringUtils.isBlank(getVolume())){
				setVolume("0");
			}

//		} else {
//			sessionBook();
//			if (StringUtils.isBlank((String) sessionBook.get("title"))) {
//				addFieldError("title", "Please insert the title session");
//			}
//			if (getPublisher() == 0) {
//				addFieldError("publisher", "Select the Publisher Session");
//			}
//			if (Integer.parseInt(sessionBook.get("year").toString()) == 0) {
//				addFieldError("year", "Please insert the year");
//			} else if (1000 > Integer.parseInt(sessionBook.get("year")
//					.toString())) {
//				addFieldError("year", "Year must have 4 numbers");
//			} else if (Integer.parseInt(sessionBook.get("year").toString()) > c
//					.get(Calendar.YEAR)) {
//				addFieldError(
//						"year",
//						"The year is bigger then the current year("
//								+ c.get(Calendar.YEAR) + ")");
//			}
//		}
	}

	/**
	 * verific care filduri de autor nu sunt goale si le pun intr-un array
	 */

	public ArrayList<Authors> getAuthorList() {
		ArrayList<Authors> authorList = new ArrayList<Authors>();
		for (int i = 0; i < authorFN.size(); i++) {
			if (!getAuthorFN().get(i).isEmpty()) {
				authors = new Authors();
				authors.setFirstName(getAuthorFN().get(i));
				authors.setLastName(getAuthorLN().get(i));
				authorList.add(authors);
			}
		}
//		 for (int i = 0; i < authorList.size(); i++) {
//		 System.out.println("pe" + i + " "
//		 + authorList.get(i).getFirstName() + " "
//		 + authorList.get(i).getLastName());
//		 }
		setSize(authorList.size());
		return authorList;
	}

	/**
	 * setez sesiunea pentru fildurile din pagina bookInsert
	 * 
	 * @return
	 */
	public Map<String, Object> sessionBook() {
		// System.out.println("set sesion");
		sessionBook.put("book", true);
		sessionBook.put("title", getTitle());
		sessionBook.put("year", getYear());
		sessionBook.put("volume", getVolume());
		// System.out.println(sessionBook.get("title") + " " +
		// sessionBook.get("year")
		// + " " + sessionBook.get("volume"));
		sessionBook.put("authorList", getAuthorList());
		sessionBook.put("series", getSeries());
		sessionBook.put("edition", getSeries());
		sessionBook.put("month", getMonth());
		sessionBook.put("note", getNote());
		sessionBook.put("size", getAuthorList().size());
		return sessionBook;
	}

	public void sessionBookUnset() {
		// System.out.println("unset session");
		sessionBook.remove("title");
		sessionBook.remove("year");
		sessionBook.remove("volume");
		sessionBook.remove("authorList");
		sessionBook.remove("series");
		sessionBook.remove("edition");
		sessionBook.remove("month");
		sessionBook.remove("note");
		sessionBook.remove("size");
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

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
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

	public Map<String, Object> getSessionBook() {
		return sessionBook;
	}

	public void setSessionBook(Map<String, Object> sessionBook) {
		this.sessionBook = sessionBook;
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
