package book.operation;

import functions.FunctionForUpdates;
import functions.FunctionPublisher;
import iteme.Authors;
import iteme.Books;
import iteme.Publisher;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.ActionSupport;

public class EditBooks extends ActionSupport {
	private static final long serialVersionUID = 1L;

	FunctionPublisher pub = new FunctionPublisher();
	InsertBook monthListInit = new InsertBook();
	GetBookForEdit oldList = new GetBookForEdit();
	FunctionForUpdates updateFunction = new FunctionForUpdates();

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
	private int volume = 0;
	private int year = 0;
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

		// System.out.println("old session");
		// oldAuthorList = getOldList();
		// for (int i = 0; i < oldAuthorList.size(); i++) {
		// System.out.println(oldAuthorList.get(i).getFirstName() + " "
		// + oldAuthorList.get(i).getLastName());
		// }
		// System.out.println();
		// System.out.println("new session");
		// for (int i = 0; i < bookList.get(0).getAutors().size(); i++) {
		// System.out.println(bookList.get(0).getAutors().get(i)
		// .getFirstName()
		// + " " + bookList.get(0).getAutors().get(i).getLastName());
		// }

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
			addFieldError("year", "The year is bigger then the current year("
					+ c.get(Calendar.YEAR) + ")");
		}
	}

	public String execute() {
		updateFunction.editBooks(id, title, bookList.get(0).getAutors(),
				getOldList(), publisher, year, volume,
				series, edition, month, note);
		return SUCCESS;
	}

	public String cancel() {
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
		// for (int i = 0; i < authorList.size(); i++) {
		// System.out.println("pe" + i + " "
		// + authorList.get(i).getFirstName() + " "
		// + authorList.get(i).getLastName());
		// }
		setSize(authorList.size());
		return authorList;
	}

	public void bookSet() {
		Books book = new Books();
		book.setAutors(getAuthorList());
		book.setTitle(getTitle());
		book.setId_publisher(getPublisher());
		book.setYear(getYear());
		book.setVolume(getVolume());
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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
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
