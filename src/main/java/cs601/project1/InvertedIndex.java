package cs601.project1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

/*
 * Builds an inverted index data structure by processing an object and updating the hashmaps. 
 * @author ksonar
 */

public class InvertedIndex {

	private String[] split;
	private String fullText;
	
	HashMap<String, Integer> mapCount = new HashMap<String, Integer>();
	HashMap<String, ArrayList<MetaData>> wordIndex = new HashMap<String, ArrayList<MetaData>>();
	HashMap<String, ArrayList<Data>> asinIndex = new HashMap<String, ArrayList<Data>>();
	
	/*
	 * Takes input as a Data object, splits the text data and calls update methods.
	 * @param o 
	 */
	
	public void addObjectData (Data o) {

		if (o instanceof Reviews) 
			fullText = ((Reviews) o).getReviewText();
		else
			fullText = ((QA) o).getQuestion() + " " + ((QA) o).getAnswer();
		
		splitAndRegex(fullText);
		updateAsinHashMap(o);
		updateWordIndex(o);
			
		}
			
	/*
	 * Splits text data and removes all non-alphanumeric data	
	 * @param fullText
	 */
	
	public void splitAndRegex (String fullText) {

		split = fullText.replaceAll("[^A-Za-z0-9 ]", "").toLowerCase().split("\\s+");
		
	}
	
	/*
	 * Update the wordIndex with a MetaData object which contains a Data object and frequency of a particular word.
	 * @param a
	 */
	
	public void updateWordIndex (Data a) {
		int count = 0;
		MetaData md;
		
		HashMap<String, Integer> hh = new HashMap<>(); //To store unique words
		setMap(split); 

		for(String word : split) {
			if (hh.containsKey(word))
				continue;
			else {
				hh.put(word, 1);	
				count = mapCount.get(word); //get count of a specific word
				md = new MetaData(a,count); //create new instance of MetaData object

				if(wordIndex.get(word) != null) {
					wordIndex.get(word).add(md);
				}
				else {
					wordIndex.put(word, new ArrayList<MetaData>());
					wordIndex.get(word).add(md);
				}
			}
		}
	}
	
	/*
	 * Create a temporary Map of word count of certain Data object
	 * @param text 
	 */
	public void setMap (String[] text) {
		mapCount.clear();
		for(String word : text) {
			if(mapCount.containsKey(word))
				mapCount.put(word, mapCount.get(word) + 1);
			else
				mapCount.put(word, 1);
		}
	}
	
	/*
	 * Update the asinIndex with a Data object
	 * @params o
	 */
	
	public void updateAsinHashMap (Data o) {
	
		if (asinIndex.containsKey(o.getAsin())) 
			asinIndex.get(o.getAsin()).add(o);
		else {
			asinIndex.put(o.getAsin(), new ArrayList<Data>());
			asinIndex.get(o.getAsin()).add(o);
		}

	}
	
	/*
	 * Find and display objects of a particular asin
	 * @param asin
	 */
	
	public void findAsin (String asin) {
		int count = 1;
		Boolean check = false;
		if(asinIndex.containsKey(asin)) {
			check = true;
			for(Data arr : asinIndex.get(asin)) {
				System.out.println(count + "." + arr + '\n');
				count++;
			}
		}
		if(check == false)
			System.out.println("Data does not exist");
	}
	
	/*
	 * Searches and displays all objects that contain a particular word/term with full match.
	 * @param term
	 */
	
	public void search (String term) {
		Boolean check = false;
		int count = 1;
		for(Map.Entry<String, ArrayList<MetaData>> item : wordIndex.entrySet()) {
			if(item.getKey().equals(term)) {
				check = true;
				for(MetaData d : item.getValue()) {
					System.out.println(count + "." + d + "\n");
					count++;
				}
			}
		}
		if(check == false)
			System.out.println("Data for key does not exist");
	}
	
	/*
	 * Searches and displays all objects that contain a particular word/term with partial match.
	 * @param term
	 */
	
	public void partialSearch (String term) {
		Boolean check = false;
		int count = 1;
		for(Map.Entry<String, ArrayList<MetaData>> item : wordIndex.entrySet()) {
			if(item.getKey().contains(term)) {
				check = true;
				for(MetaData d : item.getValue()) {
					System.out.println(count + "." + d + "\n");
					count++;
				}
			}
		}
		if(check == false)
			System.out.println("Data for key does not exist");
	}
	
	/*
	 * Sort all values within a key of wordIndex by countOfWord. Method is called after all JSON objects are read and mapped.
	 */

	public void sortWordIndex() {
		for(Map.Entry<String, ArrayList<MetaData>> item : wordIndex.entrySet()) {
			Collections.sort(item.getValue());
		}
	}
	

}
