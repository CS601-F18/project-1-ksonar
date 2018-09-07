package Amazon;

public class Reviews {
	private String reviewText;
	private String reviewerID;
	private String asin;
	private float overall;
	
	public String getReviewText() { return reviewText; }
	public String getReviewerID() { return reviewerID; }
	public String getAsin() { return asin; }
	public float getOverall() { return overall; }
	
	
	@Override
	public String toString() {
		return " ASIN : " + asin + " REVIEWER ID : " + reviewerID + " TEXT : " + reviewText + " SCORE : " + overall ;
	}

}
