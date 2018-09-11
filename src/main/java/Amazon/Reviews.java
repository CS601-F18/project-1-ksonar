package Amazon;

public class Reviews extends Data {
	private String reviewText;
	private String reviewerID;
	private float overall;
	
	public String getReviewText() { return reviewText; }
	public String getReviewerID() { return reviewerID; }
	//public String getAsin() { return asin; }
	public float getOverall() { return overall; }
	

	@Override
	public String toString() {
		return " ASIN : " + getAsin() + " REVIEWER ID : " + reviewerID + " TEXT : " + reviewText + " SCORE : " + overall + " COUNT : "+count; // + " HASHMAP : "+mapCount ;
	}

}
