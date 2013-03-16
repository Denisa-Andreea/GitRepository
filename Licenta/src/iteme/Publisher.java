package iteme;

public class Publisher {

	private int id_publisher;
	private String name;
	private String address;
	
	public Publisher(){
		super();
	}
	
	public Publisher(int id_publisher, String name, String address){
		this.id_publisher = id_publisher;
		this.name = name;
		this.address =address;
	}

	public int getId_publisher() {
		return id_publisher;
	}

	public void setId_publisher(int id_publisher) {
		this.id_publisher = id_publisher;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
