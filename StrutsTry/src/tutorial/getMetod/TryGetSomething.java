package tutorial.getMetod;

public class TryGetSomething {

	public String getBestSomething(String language) {

		if (language.equals("java")) {
			return "Java tutorial";
		} else {
			if (language.equals("dotnet")) {
				return "Dotnet tutorial";
			}else
				return "Unknow";
		}

	}

}
