package iteme;

import java.util.ArrayList;

public class Article {

	private int idArticle;
	private String title;
	private ArrayList<Authors> autors;
	private int id_journal;
	private String journal;
	private int year;
	private int volume;
	private int number;
	private String month;
	private String note;

	public Article() {
	}

	public Article(int idArticle, String title, ArrayList<Authors> autors,
			int id_journal, String journal, int year, int volume, int number,
			String month, String note) {
		this.idArticle = idArticle;
		this.id_journal = id_journal;
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

	public ArrayList<Authors> getAutors() {
		return autors;
	}

	public void setAutors(ArrayList<Authors> autors) {
		this.autors = autors;
	}

	public int getId_journal() {
		return id_journal;
	}

	public void setId_journal(int id_journal) {
		this.id_journal = id_journal;
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
}
