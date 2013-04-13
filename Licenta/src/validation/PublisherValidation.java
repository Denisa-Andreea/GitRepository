package validation;

public class PublisherValidation {
	
	DBVerify dbVerify = new DBVerify();
	
	public PublisherValidation(){
	}
	
	public boolean alreadyExistPublisher(String value, String country, String city){
		if(dbVerify.publisherVerify(value,country,city).equals("exista")){
			return true;
		}
		return false;
	}
	
	public boolean littleFirstLetter(String value){
		if(value.substring(0, 1).equals(value.substring(0,1).toLowerCase())){
			return true;
		}
		return false;
	}
}
