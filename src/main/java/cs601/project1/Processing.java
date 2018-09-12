package cs601.project1;

import java.io.BufferedReader;
import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/*
 * Creates two instances of InvertedIndex and builds the data structure.
 * @author ksonar
 */

public class Processing {
	
	HashMap<String, ArrayList<Data>> asinReview = new HashMap<String, ArrayList<Data>>();
	HashMap<String, ArrayList<Data>> asinQA = new HashMap<String, ArrayList<Data>>();
	Data inst;
	InvertedIndex i1 = new InvertedIndex();
	InvertedIndex i2 = new InvertedIndex();
	
	/*
	 * Constructor to read file, map  and sort data in appropriate manner.
	 * @param reviewFile, qaFile
	 */

	public Processing (String reviewFile, String qaFile) throws IOException {
		Reviews r = new Reviews();
		QA qa = new QA();
		
		readAndMap(reviewFile, r);
		i1.sortWordIndex();
	
		readAndMap(qaFile, qa);
		i2.sortWordIndex();

		
		//System.out.println("ASINREVIEW SIZE : "+i1.asinIndex.size());
		//System.out.println("ASINQA SIZE : "+i2.asinIndex.size());
		//System.out.println("i1 SIZE : "+i1.wordIndex.size());
		//System.out.println("i2 SIZE : "+i2.wordIndex.size());

	}
	
	/*
	 * Read a single file with object type. Store data from GSON object in desired instance of InvertedIndex.
	 * @params file
	 * @params s
	 */
	
	public void readAndMap (String file, Object s) throws IOException {

		BufferedReader f = Files.newBufferedReader(Paths.get(file), StandardCharsets.ISO_8859_1);
		Gson gson = new GsonBuilder().create();
		String line;

		if (s instanceof Reviews) {
			inst = (Reviews) s; //Reference of Data points to type(Reviews) object 
		}
		else {
			inst = (QA) s; //Reference of Data points to type(QA) object
		}
		
		while((line = f.readLine()) != null) {
			try {
				inst = gson.fromJson(line, inst.getClass());
				
			}
			catch (JsonSyntaxException i) {
				System.out.printf("MESSAGE : %s", i.getStackTrace());
			}
			if ( s instanceof Reviews) {

				i1.addObjectData(inst);
			}
			else {

				i2.addObjectData(inst);
			}
		}

	}
	
	/*
	 * User inputs query which is processed and executed.
	 */
	
	public void userInput() {
		System.out.println("Enter operations like :\nfind <asin>\nreviewsearch/qasearch <term>\nreviewpartialsearch/qapartialsearch <term>\n ");
		Scanner sc = new Scanner(System.in);
		String input;
		String[] split;
		
		//while( !(input = sc.nextLine().toLowerCase()).equals("exit"))
		do {
			System.out.print("Enter query : ");
			input = sc.nextLine();
			split = input.split("\\s");
			String function;
			if(split.length == 1 && (split[0].toLowerCase().equals("exit")))
				System.exit(0);
			else if(split.length != 2) {
				System.out.println("Incorrect length of arguments, please try again ");
				continue;
			}
			else {
				function = split[1].replaceAll("[^A-Za-z0-9 ]", "");
			
				switch (split[0].toLowerCase()) {
				case "find" :
					System.out.println("~~~Records from Review objects~~~");
					i1.findAsin(function.toUpperCase());
					System.out.println("~~~Records from QA objects~~~");
					i2.findAsin(function.toUpperCase());
					break;
				case "reviewsearch" :
					i1.search(function);
					break;
				case "qasearch" :
					i2.search(function);
					break;
				case "reviewpartialsearch" :
					i1.partialSearch(function);
					break;
				case "qapartialsearch" :
					i2.partialSearch(function);
					break;
					
				default :
					System.out.println("Incorrect input, please try again...");
				}
			}
				
		} while (true);
		//sc.close();
		
	} 
	

}
