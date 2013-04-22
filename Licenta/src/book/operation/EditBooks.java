package book.operation;

import iteme.Authors;
import iteme.Books;
import iteme.Publisher;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import validation.BookValidation;

import com.opensymphony.xwork2.ActionSupport;

import functions.FunctionForUpdateBooks;
import functions.FunctionPublisher;

public class EditBooks extends ActionSupport {
	private static final long serialVersionUID = 1L;

	FunctionPublisher pub = new FunctionPublisher();
	InsertBook monthListInit = new InsertBook();
	GetBookForEdit oldList = new GetBookForEdit();
	FunctionForUpdateBooks updateFunction = new FunctionForUpdateBooks();
	BookValidation validation = new BookValidation();

	ArrayList<Books> bookList = new ArrayList<Books>();
	ArrayList<Publisher> listPublisher;
	ArrayList<String> monthList;
	ArrayList<Authors> oldAuthorList = new ArrayList<Authors>();
	Map<String, Object> sessionEdit = oldList.getSessionEdit();
	Authors authors;
	Calendar c = Calendar.getInstance();

	private ArrayList<String> authorFN;
	private ArrayList<String> authorLN;
	private int id;
	private String title;
	private int publisher;
	private String volume;
	private String year;
	private String series;
	private String edition;
	private String month;
	private String note;
	private int size = 1;
	private int publisherSelected;

	public EditBooks() {
		listPublisher = pub.fetchPublisher();
		monthList = monthListInit.initMonthList();
	}

	public void validate() {
		bookSet();
		setPublisherSelected(getPublisher());

		if (validation.blankString(getTitle())) {
			addFieldError("title", "Please insert the title");
		}else if (validation.littleFirstLetter(getTitle())) {
			setTitle(title.substring(0, 1).toUpperCase() + title.substring(1));
		}
		if (validation.alreadyExistTitle(getTitle())) {
			addFieldError("title", "This book already exist!!!");
		}
		
		if (getPublisher() == 0) {
			addFieldError("publisher", "Please select the publisher");
		}
		if (validation.blankString(getYear())) {
			addFieldError("year", "Please insert the year");
		} else if (validation.notNumberValidate(getYear())) {
			addFieldError("year", "Please insert only numbers!!!");
		} else if (validation.invalidYear(getYear())) {
			addFieldError("year", validation.getMessage());
		}
		if (validation.blankString(getVolume())) {
			setVolume("0");
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
		}
	}

	public String execute() {
		if(sessionEdit.get("login") == null){
			return "noUser";
		}
		updateFunction.editBooks(id, title, bookList.get(0).getAutors(),
				getOldList(), publisher, Integer.parseInt(year), Integer.parseInt(volume),
				series, edition, month, note);
		oldList.sessionEditUnset();
		return SUCCESS;
	}

	public String cancel() {
		if(sessionEdit.get("login") == null){
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

	public void bookSet() {
		Books book = new Books();
		book.setAutors(getAuthorList());
		book.setTitle(getTitle());
		book.setId_publisher(getPublisher());
		book.setYear(Integer.parseInt(getYear()));
		book.setVolume(Integer.parseInt(getVolume()));
		book.setSeries(getSeries());
		book.setEdition(getEdition());
		book.setMonth(getMonth());
		book.setNote(getNote());
		book.setIdBook(getId());
		bookList.add(book);
	}

	public ArrayList<Authors> getOldList() {
		ArrayList<Authors> list = new ArrayList<Authors>();
		@SuppressWarnings("unchecked")
		ArrayList<Authors> arrayList = (ArrayList<Authors>) sessionEdit
				.get("authorList");
		list.addAll(arrayList);
		return list;
	}

	public ArrayList<String> getAuthorLN() {
		return authorLN;
	}

	public void setAuthorLN(ArrayList<String> authorLN) {
		this.authorLN = authorLN;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPublisher() {
		return publisher;
	}

	public void setPublisher(int publisher) {
		this.publisher = publisher;
	}

	public ArrayList<String> getAuthorFN() {
		return authorFN;
	}

	public void setAuthorFN(ArrayList<String> authorFN) {
		this.authorFN = authorFN;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
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

	public ArrayList<Books> getBookList() {
		return bookList;
	}

	public void setBookList(ArrayList<Books> bookList) {
		this.bookList = bookList;
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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getPublisherSelected() {
		return publisherSelected;
	}

	public void setPublisherSelected(int publisherSelected) {
		this.publisherSelected = publisherSelected;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
