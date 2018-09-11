package Amazon;

import java.util.HashMap;

public class Data {
	private String asin;
	//protected int specificCount = 0;
	//HashMap<String, Integer> mapCount = new HashMap<String, Integer>();
	int count;
	//public int SetCountOfWord = 0;

	/*
	public Data(String asin) {
		this.asin = asin;
	}
	*/
	public String getAsin() { return asin;}
	
	//public String getHashMap() { return mapCount.toString(); }
	public int getCount() {return this.count;}
	
	public void setCount(int count) { this.count = count; }// System.out.println("COUNT is :"+count); }
	/*
	public void setHashMap(String[] text) {
		for(String word : text) {
			if(mapCount.containsKey(word))
				mapCount.put(word, mapCount.get(word) + 1);
			else
				mapCount.put(word, 1);
		}

	}
*/

	


}
