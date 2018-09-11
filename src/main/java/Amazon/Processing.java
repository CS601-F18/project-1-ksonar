package Amazon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonStreamParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

public class Processing {
	
	HashMap<String, ArrayList<Data>> asinReview = new HashMap<String, ArrayList<Data>>();
	HashMap<String, ArrayList<Data>> asinQA = new HashMap<String, ArrayList<Data>>();
	Data inst;
	InvertedIndex i1 = new InvertedIndex();
	InvertedIndex i2 = new InvertedIndex();
	HashMap h;
	public Processing(String reviewFile, String qaFile) throws IOException {
		BufferedWriter write = new BufferedWriter(new FileWriter(new File("output.txt")));
		long sTime = System.currentTimeMillis();
		Reviews r = new Reviews();
		QA qa = new QA();
		readAndMap(reviewFile, r);
		long eTime = System.currentTimeMillis();
		System.out.println("REVIEWFILE TIME : " + (eTime-sTime)+"millisecs\n\n\n");
		String s;
		
	
		sTime = System.currentTimeMillis();
		readAndMap(qaFile, qa);
		eTime = System.currentTimeMillis();
		
		System.out.println("\n\n\nQAFILE TIME : " + (eTime-sTime)+"millisecs\n\n\n");
		
		//i1.findAsin("B000C12GH2");
		//i2.findAsin("B000C12GH2");
		//i1.search("beautiful");
		//i2.search("beautiful");
		System.out.println("ASINREVIEW SIZE : "+i1.asinIndex.size());
		System.out.println("ASINQA SIZE : "+i2.asinIndex.size());
		System.out.println("i1 SIZE : "+i1.wordIndex.size());
		System.out.println("i2 SIZE : "+i2.wordIndex.size());
		System.out.println("KEYSET SIZE : "+i2.wordIndex.keySet().size());
		s = i2.display();
		write.write(s);
		
		//display();

	}
	public void add(int n) {}
	public void add(int n, int x) {}
	
	
	public void readAndMap(String file, Object s) throws IOException {

		BufferedReader f = Files.newBufferedReader(Paths.get(file), StandardCharsets.ISO_8859_1);
		Gson gson = new GsonBuilder().create();
		String line;


		if (s instanceof Reviews) {
			inst = (Reviews) s;
			//h = asinReview;
		}
		else {
			inst = (QA) s;
			//h = asinQA;
		}

		int count = 0;
		
		
		while((line = f.readLine()) != null) {
			try {
				inst = gson.fromJson(line, inst.getClass());
				
			}
			catch (JsonSyntaxException i) {
				System.out.printf("MESSAGE : %s\n", i.getStackTrace());
			}
			if ( s instanceof Reviews) {
				
				//inst = (Reviews) s;
				//i1.updateHashMap(inst);
				i1.addObjectData(inst);
			}
			else {
				//inst = (QA) s;
				//i2.updateHashMap(inst);
				i2.addObjectData(inst);
			}
			count++;
		}

	}
	

}
