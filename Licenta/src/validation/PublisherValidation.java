package validation;

public class PublisherValidation {

	DBVerify dbVerify = new DBVerify();

	public PublisherValidation() {
	}

	public boolean alreadyExistPublisher(String value, String country,
			String city) {
		if (dbVerify.publisherVerify(value, country, city).equals("exista")) {
			return true;
		}
		return false;
	}

	public boolean alreadyExistPublisherContition(String value, String country,
			String city, String valueAnt, String countryAnt, String cityAnt) {

		if (dbVerify.publisherVerifyCondito(value, country, city, valueAnt,
				countryAnt, cityAnt).equals("exista")) {
			return true;
		}
		return false;
	}

	public boolean littleFirstLetter(String value) {
		if (value.substring(0, 1).equals(value.substring(0, 1).toLowerCase())) {
			return true;
		}
		return false;
	}

	public boolean alreadyExistJournal(String value) {
		if (dbVerify.journalVerify(value).equals("exista")) {
			return true;
		}
		return false;
	}

	public boolean alreadyExistJournalCondition(String value, String valueAnt) {
		if (dbVerify.journalVerifyCondition(value, valueAnt).equals("exista")) {
			return true;
		}
		return false;
	}

}
