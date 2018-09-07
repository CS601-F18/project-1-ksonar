package Amazon;

public class QA {
	private String asin;
	private String question;
	private String answer;
	
	public String getAsin() { return asin; }
	public String getQuestion() { return question; }
	public String getAnswer() { return answer; }
	
	@Override
	public String toString() {
		return " ASIN: " + asin + " QUESTION : " + question + " ANSWER : " + answer;
	}

}
