package article.operation;

import iteme.Article;
import iteme.Authors;
import iteme.Journal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import validation.BookValidation;

import com.opensymphony.xwork2.ActionSupport;

import functions.FunctionForUpdateArticle;
import functions.FunctionJournal;
import functions.FuntionToUse;
import functions.MonthInit;

@SuppressWarnings("serial")
public class EditArticle extends ActionSupport {

	FuntionToUse replace = new FuntionToUse();
	BookValidation validation = new BookValidation();
	MonthInit forMonth = new MonthInit();
	FunctionForUpdateArticle function = new FunctionForUpdateArticle();
	GetArticleForEdit oldList = new GetArticleForEdit();
	FunctionJournal functJorn = new FunctionJournal();

	private ArrayList<String> authorFN;
	private ArrayList<String> authorLN;
	private int id;
	private String title;
	private int journal;
	private String volume;
	private String year;
	private String number;
	private String month;
	private String note;
	private int size = 1;
	private int journalSelected;

	ArrayList<Article> articleList = new ArrayList<Article>();
	Authors authors;
	ArrayList<Journal> listJournal;
	ArrayList<String> monthList;
	ArrayList<Authors> oldAuthorList = new ArrayList<Authors>();
	Map<String, Object> sessionEdit = oldList.getSessionEdit();
	Calendar c = Calendar.getInstance();

	public EditArticle() {
		listJournal = functJorn.fetchJournal();
		monthList = forMonth.initMonthList();
	}

	/**
	 * validare la nivel de server
	 */
	public void validate() {
		articleSet();
		setJournalSelected(getJournal());
		if (validation.blankString(getTitle())) {
			addFieldError("title", "Is required");
		} else if (validation.littleFirstLetter(getTitle())) {
			setTitle(title.substring(0, 1).toUpperCase() + title.substring(1));
		}
		if (validation.alreadyExistTitleCondition(getTitle(), "articole",
				sessionEdit.get("title").toString())) {
			addFieldError("title", "Already Exist");
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
		} else if (validation.notNumberValidate(getVolume())) {
			addFieldError("volume", "Must be a number. Letters are not allowed");
		}

		if (validation.blankString(getNumber())) {
			setNumber("0");
		} else if (validation.notNumberValidate(getNumber())) {
			addFieldError("number", "Must be a number. Letters are not allowed");
		}
		if (getAuthorList().size() < 1) {
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
			if (!getAuthorFN().get(i).isEmpty()
					&& getAuthorLN().get(i).isEmpty()) {
				addFieldError("authors", "Last name is required");
			}
		}
	}

	public String execute() {
		if (sessionEdit.get("login") == null) {
			return "noUser";
		}
		function.editArticle(id, title, articleList.get(0).getAutors(),
				getOldList(), journal, Integer.parseInt(year),
				Integer.parseInt(volume), Integer.parseInt(number), month, note);
		oldList.sessionEditUnset();
		return SUCCESS;
	}

	public String cancel() {
		if (sessionEdit.get("login") == null) {
			return "noUser";
		}
		oldList.sessionEditUnset();
		return "cancel";
	}

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
		setSize(authorList.size());
		return authorList;
	}

	public void articleSet() {
		Article article = new Article();
		article.setAutors(getAuthorList());
		article.setTitle(getTitle());
		article.setId_journal(getJournal());
		article.setYear(Integer.parseInt(getYear()));
		article.setVolume(Integer.parseInt(getVolume()));
		article.setNumber(Integer.parseInt(getNumber()));
		article.setMonth(getMonth());
		article.setNote(getNote());
		article.setIdArticle(getId());
		articleList.add(article);
	}

	public ArrayList<Authors> getOldList() {
		ArrayList<Authors> list = new ArrayList<Authors>();
		@SuppressWarnings("unchecked")
		ArrayList<Authors> arrayList = (ArrayList<Authors>) sessionEdit
				.get("authorList");
		list.addAll(arrayList);
		return list;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<String, Object> getSessionEdit() {
		return sessionEdit;
	}

	public void setSessionEdit(Map<String, Object> sessionEdit) {
		this.sessionEdit = sessionEdit;
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

	public ArrayList<Article> getArticleList() {
		return articleList;
	}

	public void setArticleList(ArrayList<Article> articleList) {
		this.articleList = articleList;
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

	public void setSize(int size) {
		this.size = size;
	}

	public ArrayList<Journal> getListJournal() {
		return listJournal;
	}

	public void setListJournal(ArrayList<Journal> listJournal) {
		this.listJournal = listJournal;
	}

	public ArrayList<String> getMonthList() {
		return monthList;
	}

	public void setMonthList(ArrayList<String> monthList) {
		this.monthList = monthList;
	}
}
