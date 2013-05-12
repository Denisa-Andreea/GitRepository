package functions;

public class FuntionToUse {

	public FuntionToUse() {
	}

	public String multipleSpaceElim(String word) {
		word = word.replaceAll("^ +| +$|( )+", "$1");
		return word;
	}

	
}
