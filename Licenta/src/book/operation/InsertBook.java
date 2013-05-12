package book.operation;

import iteme.Authors;
import iteme.Publisher;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import validation.BookValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import functions.FunctionBookAuthor;
import functions.FunctionPublisher;
import functions.FuntionToUse;
import functions.MonthInit;

public class InsertBook extends ActionSupport {
	private static final long serialVersionUID = 1L;

	FunctionBookAuthor function = new FunctionBookAuthor();
	FunctionPublisher pub = new FunctionPublisher();
	BookValidation validation = new BookValidation();
	FuntionToUse replace = new FuntionToUse();
	MonthInit forMonth = new MonthInit();

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
		monthList = forMonth.initMonthList();
	}

	public String browse() {
		if(sessionBook.get("login") == null){
			return "noUser";
		}
		setSessionBook(sessionBook());
		setPublisher(0);
		return "publisher";
	}

	public String cancel() {
		sessionBookUnset();
		if(sessionBook.get("login") == null){
			return "noUser";
		}
		return "cancel";
	}

	public String execute() {
		if(sessionBook.get("login") == null){
			return "noUser";
		}
		sessionBookUnset();
		function.insertBook(getTitle(), getAuthorList(), getPublisher(),
				Integer.parseInt(getVolume()), Integer.parseInt(getYear()),
				getSeries(), getEdition(), getMonth(), getNote());
		return SUCCESS;

	}

	/**
	 * validare la nivel de server
	 */
	public void validate() {
		setPublisherSelected(getPublisher());
		sessionBookUnset();
		if (validation.blankString(getTitle())) {
			addFieldError("title", "Is required");
		}else if (validation.littleFirstLetter(getTitle())) {
			setTitle(title.substring(0, 1).toUpperCase() + title.substring(1));
		}
		if (validation.alreadyExistTitle(getTitle())) {
			addFieldError("title", "Already exist!!!");
		}
		
		if (getPublisher() == 0) {
			addFieldError("publisher", "Select the publisher");
		}
		if (validation.blankString(getYear())) {
			addFieldError("year", "Is required");
		} else if (validation.notNumberValidate(getYear())) {
			addFieldError("year", "Insert only numbers!!!");
		} else if (validation.invalidYear(getYear())) {
			addFieldError("year", validation.getMessage());
		}
		if (validation.blankString(getVolume())) {
			setVolume("0");
		}else if(validation.notNumberValidate(getVolume())){
			addFieldError("volume", "Must be a number. Letters are not allowed");
		}
		if(getAuthorList().size() < 1 ){
			addFieldError("authors", "Is required");
		}
		for (int i = 0; i < getAuthorFN().size(); i++) {
			if (!validation.blankString(authorFN.get(i))) {
				if (validation.littleFirstLetter(getAuthorFN().get(i))) {
					authorFN.set(i, authorFN.get(i).substring(0, 1)
							.toUpperCase()
							+ authorFN.get(i).substring(1));
				}
			}
			if (!validation.blankString(authorLN.get(i))) {
				if (validation.littleFirstLetter(getAuthorLN().get(i))) {
					authorLN.set(i, authorLN.get(i).substring(0, 1)
							.toUpperCase()
							+ authorLN.get(i).substring(1));
				}
			}
			if(!getAuthorFN().get(i).isEmpty() && getAuthorLN().get(i).isEmpty()){
				addFieldError("authors", "Last name is required");
			}
		}
	}

	/**
	 * verific care filduri de autor nu sunt goale si le pun intr-un array
	 */

	public ArrayList<Authors> getAuthorList() {
		ArrayList<Authors> authorList = new ArrayList<Authors>();
		for (int i = 0; i < authorFN.size(); i++) {
			if (!getAuthorFN().get(i).isEmpty() && !getAuthorLN().get(i).isEmpty()) {
				authors = new Authors();
				authors.setFirstName(replace.multipleSpaceElim(getAuthorFN().get(i)));
				authors.setLastName(replace.multipleSpaceElim(getAuthorLN().get(i)));
				authorList.add(authors);
			}
		}
		// for (int i = 0; i < authorList.size(); i++) {
		// System.out.println("pe" + i + " "
		// + authorList.get(i).getFirstName() + " "
		// + authorList.get(i).getLastName());
		// }
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
		sessionBook.remove("book");
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
