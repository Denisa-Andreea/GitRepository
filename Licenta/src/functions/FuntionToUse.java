package functions;

import java.util.Map;

public class FuntionToUse {

	public FuntionToUse() {
	}

	public String multipleSpaceElim(String word) {
		word = word.replaceAll("^ +| +$|( )+", "$1");
		return word;
	}

	public void clearSession(Map<String, Object> sessionLogin) {
		if (sessionLogin.get("book") != null
				|| sessionLogin.get("article") != null) {
			sessionLogin.remove("title");
			sessionLogin.remove("year");
			sessionLogin.remove("volume");
			sessionLogin.remove("authorList");
			sessionLogin.remove("number");
			sessionLogin.remove("month");
			sessionLogin.remove("note");
			sessionLogin.remove("size");
			sessionLogin.remove("article");
			sessionLogin.remove("edition");
			sessionLogin.remove("book");
		}
	}
}
