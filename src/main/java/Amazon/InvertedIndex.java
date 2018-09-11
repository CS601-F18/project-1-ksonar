package Amazon;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class InvertedIndex {
	//private String text;
	private String[] split;
	private String fullText;
	//HashMap<String, >
	/*
	public InvertedIndex(String text) {
		this.text = text;
		splitAndRegex();
		createIndex();
	}
	*/
	ArrayList<Data> d;
	//ArrayList<ArrayList<Integer>Data> d;
	HashMap<String, Integer> mapCount = new HashMap<String, Integer>();
	HashMap<String, ArrayList<Data>> wordIndex = new HashMap<String, ArrayList<Data>>();
	//HashMap<String, Integer> wordCount = new HashMap<String, Integer>();
	HashMap<String, ArrayList<Data>> asinIndex = new HashMap<String, ArrayList<Data>>();
	
	//HashMap<String, ArrayList<Data>> asin = new HashMap<String, ArrayList<Data>>();
	Data inst;
	public void addObjectData(Data o) {
		//inst = o;
		if (o instanceof Reviews) 
			fullText = ((Reviews) o).getReviewText();
		else
			fullText = ((QA) o).getQuestion() + " " + ((QA) o).getAnswer();
		
		splitAndRegex(fullText);
		//System.out.println("BEFORE UPDATE : "+o+'\n');
		updateAsinHashMap(o);
		updateWordIndex(o);
			
		}
			
		
	
	public void splitAndRegex(String fullText) {
		//System.out.println(fullText);
		//fullText.re
		split = fullText.replaceAll("[^A-Za-z0-9 ]", "").toLowerCase().split("\\s+");
	
		//int countOfWord = split.toString().contains(s)
		//fullText = fullText.replaceAll(", replacement)
		
	}
	
	
	public void updateWordIndex(Data a) {
		int countOfWord = 0;
		//for
		int count = 0;
		
		
		HashMap<String, Integer> hh = new HashMap<>();
		ArrayList<String> check = new ArrayList<>();
		//a.setHashMap(split);
		setMap(split);
		//System.out.println(mapCount.toString());
		//System.out.println("\n"+a.getHashMap()+"\n");
		for(String word : split) {
			//System.out.println("\n```````````\n"+wordIndex+"\n```````````\n");
			//System.out.println(word);
			if (hh.containsKey(word))
				continue;
			else {
				hh.put(word, 1);	
				count = mapCount.get(word);
				a.setCount(count);
				final Data d = a;
				//System.out.println("WORD : "+word+" COUNT : "+d.getCount()+'\n'+a+"\n\n");
								
				if(wordIndex.get(word) != null) {

					//System.out.println("WORD : "+word+'\n'+d+"\n\n");

					wordIndex.get(word).add(d);
					//wordIndex.put(word, value)
				}
				else {

					//System.out.println("WORD : "+word+'\n'+d+"\n\n");

					wordIndex.put(word, new ArrayList<Data>());
					wordIndex.get(word).add(d);
				}
				//System.out.println(wordIndex+"\n....\n");
			}
		}
		//System.out.println("\nINVERTED INDEX : "+wordIndex+"\n..........\n");
		
	}
	public void setMap(String[] text) {
		mapCount.clear();
		for(String word : text) {
			if(mapCount.containsKey(word))
				mapCount.put(word, mapCount.get(word) + 1);
			else
				mapCount.put(word, 1);
		}
	}
	
	public void update() {
		
	}
	
	
	public void updateAsinHashMap(Data o) {
	
		if (asinIndex.containsKey(o.getAsin())) 
			asinIndex.get(o.getAsin()).add(o);
		else {
			asinIndex.put(o.getAsin(), new ArrayList<Data>());
			asinIndex.get(o.getAsin()).add(o);
		}

	}
	
	public void findAsin(String asin) {
		System.out.println("Entered...");
		StringBuffer sb = new StringBuffer();
		int counter = 0;
		if(asinIndex.containsKey(asin)) {
			//System.out.println("FOUND");
			for(Data arr : asinIndex.get(asin)) {
				sb.append(counter+"."+arr.toString()+'\n');
				counter++;
			}

		}
	
		System.out.println(sb);
	}
	
	public void search(String term) {
		//ArrayList<Data>
		int count = 0;
		for(Map.Entry<String, ArrayList<Data>> item : wordIndex.entrySet()) {
			if(item.getKey().equals(term)) {
				for(Data d : item.getValue()) {
					System.out.println(d + "\n");
					count++;
				}
			}
		}
		System.out.println("\n\n"+count);
	}

			
	
	public String display() {
		StringWriter s = new StringWriter();
		
		for(Map.Entry<String, ArrayList<Data>> item : wordIndex.entrySet()) {
			s.append(item.getKey() +" : "+ String.valueOf(item.getValue().size()) +"\n");
			//System.out.println(item.getKey() + " : " + item.getValue().size());
		}
		return s.toString();
	}
}
