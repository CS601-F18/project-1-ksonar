package Amazon;

public class QA extends Data {
	private String question;
	private String answer;

	
	//public String getAsin() { return asin; }
	public String getQuestion() { return question; }
	public String getAnswer() { return answer; }
	
	@Override
	public String toString() {
		return " ASIN: " + getAsin() + " QUESTION : " + question + " ANSWER : " + answer + " COUNT : "+count; // + " HASHMAP : " + mapCount;
	}

}
