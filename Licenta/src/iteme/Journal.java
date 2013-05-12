package iteme;

public class Journal {

	private int id_journal;
	private String name;
	private String description;
	
	public Journal(){
	}
	
	public Journal(int id_journal, String name, String description){
		this.setId_journal(id_journal);
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId_journal() {
		return id_journal;
	}

	public void setId_journal(int id_journal) {
		this.id_journal = id_journal;
	}
	
}
