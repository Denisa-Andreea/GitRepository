package article.operation;

import iteme.Article;
import iteme.Journal;

import java.util.ArrayList;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import functions.FunctionArticle;
import functions.FunctionForDelete;
import functions.FunctionJournal;
import functions.MonthInit;

@SuppressWarnings("serial")
public class GetArticleForEdit extends ActionSupport {

	MonthInit monthListInit = new MonthInit();
	FunctionForDelete delete = new FunctionForDelete();
	FunctionJournal functionJourn = new FunctionJournal();
	FunctionArticle function = new FunctionArticle();

	private int id;
	private int size;
	private int journalSelected;
	private String action;
	private int page;
	private int numberOfPages;
	private int sizeTabel;
	private String checkedID;

	ArrayList<Article> articleList;
	ArrayList<Journal> listJournal;
	ArrayList<String> monthList;
	Map<String, Object> sessionEdit = ActionContext.getContext().getSession();

	public String execute() {
		if (sessionEdit.get("login") == null) {
			return "noUser";
		}
		if (action.equals("edit")) {
			listJournal = functionJourn.fetchJournal();
			monthList = monthListInit.initMonthList();

			articleList = function.selectArticle(id, 0);
			journalSelected = articleList.get(0).getId_journal();
			size = articleList.get(0).getAutors().size();
			setSessionEdit(sessionEdit());
			return "edit";
		} else if (action.equals("delete")) {
			if (page == numberOfPages && sizeTabel == 1) {
				setPage(page - 1);
			}
			delete.deleteArticle(id);
			return "delete";
		} else if (action.equals("deleteAll")) {
			
			if (!getCheckedID().isEmpty()) {
				if (page == numberOfPages && sizeTabel == 1) {
					setPage(page - 1);
				}
				delete.deleteAllArticle(getCheckedID());
			}
			return "delete";
		}
		return SUCCESS;
	}

	public Map<String, Object> sessionEdit() {
		setSessionEdit(sessionEdit);

		sessionEdit.put("edit", true);
		sessionEdit.put("title", articleList.get(0).getTitle());
		sessionEdit.put("authorList", articleList.get(0).getAutors());
		sessionEdit.put("size", articleList.get(0).getAutors().size());
		return sessionEdit;
	}
	
	public void sessionEditUnset() {
		sessionEdit.remove("edit");
		sessionEdit.remove("authorList");
		sessionEdit.remove("size");
	}

	public int getJournalSelected() {
		return journalSelected;
	}

	public void setJournalSelected(int journalSelected) {
		this.journalSelected = journalSelected;
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

	public Map<String, Object> getSessionEdit() {
		return sessionEdit;
	}

	public void setSessionEdit(Map<String, Object> sessionEdit) {
		this.sessionEdit = sessionEdit;
	}

	public MonthInit getMonthListInit() {
		return monthListInit;
	}

	public void setMonthListInit(MonthInit monthListInit) {
		this.monthListInit = monthListInit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public int getSizeTabel() {
		return sizeTabel;
	}

	public void setSizeTabel(int sizeTabel) {
		this.sizeTabel = sizeTabel;
	}

	public String getCheckedID() {
		return checkedID;
	}

	public void setCheckedID(String checkedID) {
		this.checkedID = checkedID;
	}

	public ArrayList<Article> getArticleList() {
		return articleList;
	}

	public void setArticleList(ArrayList<Article> articleList) {
		this.articleList = articleList;
	}
}
