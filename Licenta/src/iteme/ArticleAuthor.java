package iteme;


public class ArticleAuthor {

	private int idArticle;
	private String title;
	private String autors;
	private String journal;
	private int year;
	private String description;
	private int volume;
	private int number;
	private String month;
	private String note;

	public ArticleAuthor() {
	}

	public ArticleAuthor(int idArticle, String title,String description, String autors,
			String journal, int year, int volume, int number, String month,
			String note) {
		this.idArticle = idArticle;
		this.description = description;
		this.title = title;
		this.autors = autors;
		this.journal = journal;
		this.year = year;
		this.volume = volume;
		this.number = number;
		this.month = month;
		this.note = note;
	}

	public int getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAutors() {
		return autors;
	}

	public void setAutors(String autors) {
		this.autors = autors;
	}


	public String getJournal() {
		return journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
