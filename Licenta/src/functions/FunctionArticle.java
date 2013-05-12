package functions;

import iteme.Article;
import iteme.ArticleAuthor;
import iteme.Authors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conections.DBCon;

public class FunctionArticle {

	DBCon dbcon = DBCon.getConnection();
	Connection con = dbcon.getCon();

	FunctionForInsertTies ties = new FunctionForInsertTies();
	FuntionToUse replace = new FuntionToUse();
	FunctionForInsert insertArticle = new FunctionForInsert();
	
	private int numberOfRecords;

	/**
	 * afisarea articolelor
	 * 
	 * @return
	 */
	public ArrayList<ArticleAuthor> fetchAtricle(int beginRecord,
			int numberOfRecords) {
		ResultSet result;
		PreparedStatement statement = null;
		ArrayList<ArticleAuthor> listArticle = new ArrayList<ArticleAuthor>();

		try {
			statement = con
					.prepareStatement("select SQL_CALC_FOUND_ROWS articole.id_article,articole.title,"
							+ "GROUP_CONCAT(CONCAT_WS(' ', autori.lastname, autori.firstname) ORDER BY autori.firstname SEPARATOR ', '),"
							+ "articole.year,journal.name, articole.volume,articole.number,articole.month, articole.note "
							+ "from (articole left join articol_autor on articole.id_article = articol_autor.id_article) "
							+ "left join autori on articol_autor.id_autor = autori.id_autor"
							+ " left join journal on journal.id_journal = articole.id_journal GROUP BY articole.id_article LIMIT "
							+ beginRecord + "," + numberOfRecords);

			result = statement.executeQuery();
			ArticleAuthor articleAuthor;

			while (result.next()) {
				articleAuthor = new ArticleAuthor();

				articleAuthor.setIdArticle(result.getInt("articole.id_article"));
				articleAuthor.setTitle(result.getString("articole.title"));
				articleAuthor
						.setAutors(result
								.getString("GROUP_CONCAT(CONCAT_WS(' ', autori.lastname, autori.firstname) ORDER BY autori.firstname SEPARATOR ', ')"));
				articleAuthor.setYear(result.getInt("articole.year"));
				articleAuthor.setJournal(result.getString("journal.name"));
				articleAuthor.setVolume(result.getInt("articole.volume"));
				articleAuthor.setNumber(result.getInt("articole.number"));
				articleAuthor.setMonth(result.getString("articole.month"));
				articleAuthor.setNote(result.getString("articole.note"));

				listArticle.add(articleAuthor);
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		try {
			statement = con.prepareStatement("SELECT FOUND_ROWS()");
			result = statement.executeQuery();
			if (result.next()) {
				this.numberOfRecords = result.getInt(1);
			}
			statement.close();
			return listArticle;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public ArrayList<ArticleAuthor> sortArticle(int beginRecord,
			int numberOfRecords, String column, String direction) {
		ResultSet result;
		PreparedStatement statement = null;
		ArrayList<ArticleAuthor> listArticle = new ArrayList<ArticleAuthor>();

		try {
			statement = con
					.prepareStatement("select SQL_CALC_FOUND_ROWS articole.id_article,articole.title,"
							+ "GROUP_CONCAT(CONCAT_WS(' ', autori.lastname, autori.firstname) ORDER BY autori.firstname SEPARATOR ', '),"
							+ "articole.year,journal.name, articole.volume,articole.number,articole.month, articole.note "
							+ "from (articole left join articol_autor on articole.id_article = articol_autor.id_article) "
							+ "left join autori on articol_autor.id_autor = autori.id_autor"
							+ " left join journal on journal.id_journal = articole.id_journal GROUP BY articole.id_article ORDER BY articole."
							+ column
							+ " "
							+ direction
							+ " LIMIT "
							+ beginRecord + "," + numberOfRecords);
			result = statement.executeQuery();
			ArticleAuthor articleAuthor;

			while (result.next()) {
				articleAuthor = new ArticleAuthor();

				articleAuthor.setTitle(result.getString("articole.title"));
				articleAuthor
						.setAutors(result
								.getString("GROUP_CONCAT(CONCAT_WS(' ', autori.lastname, autori.firstname) ORDER BY autori.firstname SEPARATOR ', ')"));
				articleAuthor.setYear(result.getInt("articole.year"));
				articleAuthor.setJournal(result.getString("journal.name"));
				articleAuthor.setVolume(result.getInt("articole.volume"));
				articleAuthor.setNumber(result.getInt("articole.number"));
				articleAuthor.setMonth(result.getString("articole.month"));
				articleAuthor.setNote(result.getString("articole.note"));

				listArticle.add(articleAuthor);
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		try {
			statement = con.prepareStatement("SELECT FOUND_ROWS()");
			result = statement.executeQuery();
			if (result.next()) {
				this.numberOfRecords = result.getInt(1);
			}
			statement.close();
			return listArticle;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public int getNumberOfRecords() {
		return numberOfRecords;
	}

	public void setNumberOfRecords(int numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}

	public void insertArticle(String title, ArrayList<Authors> authorList,
			int idJournal, int volume, int year, int number, String month,
			String note) {
		
		int idArticle;
		int idAuthor;
		
		try{
			insertArticle.insertIntoArticle(title, idJournal, year, volume, number, month, note);
			idArticle = ties.getIdArticle(title);
			
			/**
			 * pentru fiecare autor se verifica daca exista si se adauga in baza
			 * de date ..adaugandu's si legatura cu id-ul articolului
			 */
			
			int count = 0;
			while (count < authorList.size()) {
				String authorFN = authorList.get(count).getFirstName();
				String authorLN = authorList.get(count).getLastName();
				authorFN = replace.multipleSpaceElim(authorFN);
				authorLN = replace.multipleSpaceElim(authorLN);
				insertArticle.insertIntoAuthor(authorFN, authorLN);
				idAuthor = ties.getIdAuthor(authorFN, authorLN);
				ties.insertTiesArticle(idArticle, idAuthor);
				count++;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public ArrayList<Article> selectArticle(int idArticle, int exist) {
		ArrayList<Article> articleList = new ArrayList<Article>();
		Article article;
		ArrayList<Authors> authorList = selectAuthorByArticle(idArticle, exist);
		PreparedStatement select = null;

		try {
			select = con
					.prepareStatement("select journal.id_journal, journal.name, articole.title, articole.year,"
							+ "articole.volume,articole.number,articole.month,articole.note FROM "
							+ "(articole LEFT JOIN journal on articole.id_journal = journal.id_journal) WHERE articole.id_article = "
							+ idArticle);
			ResultSet result = select.executeQuery();

			while (result.next()) {
				article = new Article();

				article.setAutors(authorList);
				article.setTitle(result.getString("articole.title"));
				article.setId_journal(Integer.parseInt(result
						.getString("journal.id_journal")));
				article.setJournal(result.getString("journal.name"));
				article.setYear(Integer.parseInt(result.getString("articole.year")));
				article.setVolume(Integer.parseInt(result.getString("articole.volume")));
				article.setNumber(result.getInt("articole.number"));
				article.setMonth(result.getString("articole.month"));
				article.setNote(result.getString("articole.note"));
				article.setIdArticle(idArticle);

				articleList.add(article);
			}
			select.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (select != null) {
					select.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return articleList;
	}
	
	public ArrayList<Authors> selectAuthorByArticle(int idArticle, int exist){
		
		Authors author;
		ArrayList<Authors> authorList = new ArrayList<Authors>();
		PreparedStatement select = null;
		
		try {
			select = con
					.prepareStatement("select autori.id_autor,autori.firstname,autori.lastname from "
							+ "(articol_autor LEFT JOIN autori On articol_autor.id_autor = autori.id_autor) where articol_autor.id_article = "
							+ idArticle + " and autori.id_autor != " + exist);
			ResultSet result = select.executeQuery();

			while (result.next()) {
				author = new Authors();

				author.setFirstName(result.getString("autori.firstname"));
				author.setLastName(result.getString("autori.lastname"));
				authorList.add(author);
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (select != null) {
					select.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return authorList;
	}
}
