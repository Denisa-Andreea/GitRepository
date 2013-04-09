package iteme;

public class Publisher {

	private int id_publisher;
	private String name;
	private String country;
	private String city;
	
	public Publisher(){
		super();
	}
	
	public Publisher(int id_publisher, String name, String country, String city){
		this.id_publisher = id_publisher;
		this.name = name;
		this.setCountry(country);
		this.setCity(city);
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
