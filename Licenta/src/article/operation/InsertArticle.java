package article.operation;

import iteme.Authors;
import iteme.Journal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import validation.BookValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import functions.FunctionArticle;
import functions.FunctionJournal;
import functions.FuntionToUse;
import functions.MonthInit;

@SuppressWarnings("serial")
public class InsertArticle extends ActionSupport {

	FuntionToUse replace = new FuntionToUse();
	BookValidation validation = new BookValidation();
	MonthInit forMonth = new MonthInit();
	FunctionArticle function = new FunctionArticle();
	FunctionJournal functArt = new FunctionJournal();

	private String title = null;
	private ArrayList<String> authorFN;
	private ArrayList<String> authorLN;
	private int journal;
	private String volume;
	private String year;
	private String number;
	private String month;
	private String note;
	private int journalSelected;
	private int size = 1;

	Authors authors;
	ArrayList<Journal> listJournal;
	ArrayList<String> monthList;
	Map<String, Object> sessionArticle = ActionContext.getContext()
			.getSession();
	Calendar c = Calendar.getInstance();

	public InsertArticle() {
		monthList = forMonth.initMonthList();
		listJournal = functArt.fetchJournal();
	}
	
	/**
	 * validare la nivel de server
	 */
	public void validate() {

		setJournalSelected(getJournal());
		sessionArticleUnset();
		if (validation.blankString(getTitle())) {
			addFieldError("title", "Is required");
		}else if (validation.littleFirstLetter(getTitle())) {
			setTitle(title.substring(0, 1).toUpperCase() + title.substring(1));
		}
		if (validation.alreadyExistTitle(getTitle())) {
			addFieldError("title", "Already exist!!!");
		}
		
		if (getJournal() == 0) {
			addFieldError("journal", "Select the journal");
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
		
		if (validation.blankString(getNumber())) {
			setNumber("0");
		}else if(validation.notNumberValidate(getNumber())){
			addFieldError("number", "Must be a number. Letters are not allowed");
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


	public String execute() {
		if (sessionArticle.get("login") == null) {
			return "noUser";
		}
		sessionArticleUnset();
		function.insertArticle(getTitle(), getAuthorList(), getJournal(),
				Integer.parseInt(getVolume()), Integer.parseInt(getYear()),
				Integer.parseInt(getVolume()), getMonth(), getNote());
		return SUCCESS;
	}

	public String cancel() {
		sessionArticleUnset();
		if(sessionArticle.get("login") == null){
			return "noUser";
		}
		return "cancel";
	}
	
	public String browse() {
		if(sessionArticle.get("login") == null){
			return "noUser";
		}
		setSessionArticle(sessionArticle());
		setJournal(0);
		return "journal";
	}
	
	public ArrayList<Authors> getAuthorList() {
		ArrayList<Authors> authorList = new ArrayList<Authors>();
		for (int i = 0; i < authorFN.size(); i++) {
			if (!getAuthorFN().get(i).isEmpty() && !getAuthorLN().isEmpty()) {
				authors = new Authors();
				authors.setFirstName(replace.multipleSpaceElim(getAuthorFN()
						.get(i)));
				authors.setLastName(replace.multipleSpaceElim(getAuthorLN()
						.get(i)));
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
	 * setez sesiunea pentru fild-urile din pagina articleInsert
	 * @return
	 */
	public Map<String, Object> sessionArticle() {
		// System.out.println("set sesion");
		sessionArticle.put("article", true);
		sessionArticle.put("title", getTitle());
		sessionArticle.put("year", getYear());
		sessionArticle.put("volume", getVolume());
		// System.out.println(sessionArticle.get("title") + " " +
		// sessionArticle.get("year")
		// + " " + sessionArticle.get("volume"));
		sessionArticle.put("authorList", getAuthorList());
		sessionArticle.put("number", getNumber());
		sessionArticle.put("month", getMonth());
		sessionArticle.put("note", getNote());
		sessionArticle.put("size", getAuthorList().size());
		return sessionArticle;
	} 

	public void sessionArticleUnset() {
		// System.out.println("unset session");
		sessionArticle.remove("title");
		sessionArticle.remove("year");
		sessionArticle.remove("volume");
		sessionArticle.remove("authorList");
		sessionArticle.remove("number");
		sessionArticle.remove("month");
		sessionArticle.remove("note");
		sessionArticle.remove("size");
		sessionArticle.remove("article");
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public int getJournal() {
		return journal;
	}

	public void setJournal(int journal) {
		this.journal = journal;
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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

	public int getJournalSelected() {
		return journalSelected;
	}

	public void setJournalSelected(int journalSelected) {
		this.journalSelected = journalSelected;
	}

	public int getSize() {
		return size;
	}

	public Map<String, Object> getSessionArticle() {
		return sessionArticle;
	}

	public void setSessionArticle(Map<String, Object> sessionArticle) {
		this.sessionArticle = sessionArticle;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public ArrayList<Journal> getListJournal() {
		return listJournal;
	}

	public void setListJournal(ArrayList<Journal> listJournal) {
		this.listJournal = listJournal;
	}
}
