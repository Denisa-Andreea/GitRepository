package forFields;

import iteme.Authors;
import iteme.Journal;

import java.util.ArrayList;
import java.util.Map;

import article.operation.InsertArticle;
import article.operation.ViewArticle;

import com.opensymphony.xwork2.ActionSupport;

import functions.FunctionJournal;
import functions.MonthInit;

@SuppressWarnings("serial")
public class FieldJournal extends ActionSupport{
	
	MonthInit forMonth = new MonthInit();
	ViewArticle function =  new ViewArticle();
	FunctionJournal functionJournal = new FunctionJournal();
	InsertArticle functionArt = new InsertArticle();
	
	private Journal journal;
	private ArrayList<String> monthList;
	private ArrayList<Journal> listJournal;
	private ArrayList<Authors> authorList;
	private Authors author;
	private int size;
	
	Map<String, Object> sessionArticle = function.getSessionLogin();
	
	private int volume = 0;
	private int year = 0;
	
	public String execute() {
		if(sessionArticle.get("login") == null){
			return "noUser";
		}
		listJournal = functionJournal.fetchJournal();	
		monthList = forMonth.initMonthList();
		size = functionArt.getSize();
		return SUCCESS;
	}
	
	public Journal getJournal() {
		return journal;
	}

	public void setJournal(Journal journal) {
		this.journal = journal;
	}

	public ArrayList<String> getMonthList() {
		return monthList;
	}

	public void setMonthList(ArrayList<String> monthList) {
		this.monthList = monthList;
	}

	public ArrayList<Journal> getListJournal() {
		return listJournal;
	}

	public void setListJournal(ArrayList<Journal> listJournal) {
		this.listJournal = listJournal;
	}

	public ArrayList<Authors> getAuthorList() {
		return authorList;
	}

	public void setAuthorList(ArrayList<Authors> authorList) {
		this.authorList = authorList;
	}

	public Authors getAuthor() {
		return author;
	}

	public void setAuthor(Authors author) {
		this.author = author;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}


	public Map<String, Object> getSessionArticle() {
		return sessionArticle;
	}

	public void setSessionArticle(Map<String, Object> sessionArticle) {
		this.sessionArticle = sessionArticle;
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


}
