package Amazon;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
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
	
	HashMap<String, ArrayList<Reviews>> asinReview = new HashMap<String, ArrayList<Reviews>>();
	HashMap<String, ArrayList<QA>> asinQA = new HashMap<String, ArrayList<QA>>();
	Object inst;
	//HashMap h;
	public Processing(String reviewFile, String qaFile) throws IOException {
		
		long sTime = System.currentTimeMillis();
		Reviews r = new Reviews();
		QA qa = new QA();
		readAndMap(reviewFile, r);
		long eTime = System.currentTimeMillis();
		System.out.println("REVIEWFILE TIME : " + (eTime-sTime)+"millisecs\n");
		sTime = System.currentTimeMillis();
		readAndMap(qaFile, qa);
		eTime = System.currentTimeMillis();
		System.out.println("QAFILE TIME : " + (eTime-sTime)+"millisecs\n");

		//display();

	}
	
	public void readAndMap(String file, Object s) throws IOException {
		BufferedReader f = Files.newBufferedReader(Paths.get(file), Charset.forName("ISO-8859-1"));
		Gson gson = new GsonBuilder().create();
		String line;
		//s.ge

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
				updateHashMap(inst);
				
			}
			catch (JsonSyntaxException i) {
				System.out.printf("MESSAGE : %s\n", i.getStackTrace());
			}
		}

	}
	
	public void updateHashMap(Object o) {
		//if(h.containsKey(o.))
		
		
		if ( o instanceof Reviews) {
			Reviews r = (Reviews) o;
			if (asinReview.get(r.getAsin()) != null) {
				asinReview.get(r.getAsin()).add(r);
			}
			else {
				asinReview.put(r.getAsin(), new ArrayList<Reviews>());
				asinReview.get(r.getAsin()).add(r);
			}
		}
		
		else {
			QA q = (QA) o;
			if (asinQA.containsKey(q.getAsin())) {
				asinQA.get(q.getAsin()).add(q);
			}
			else {
				asinQA.put(q.getAsin(), new ArrayList<QA>());
				asinQA.get(q.getAsin()).add(q);
			}
		}
	}
	
	public void display() {
		for (Map.Entry<String, ArrayList<QA>> item : asinQA.entrySet()) {
			System.out.println(item);
		}
	}
	
	public void findAsin(String asin) {
		System.out.println("Entered...");
		StringBuffer sb = new StringBuffer();
		int counter = 0;
		if(asinReview.containsKey(asin)) {
			//System.out.println("FOUND");
			for(Reviews arr : asinReview.get(asin)) {
				sb.append(counter+"."+arr.toString()+'\n');
				counter++;
			}

		}
		if(asinQA.containsKey(asin)) {
			//System.out.println("FOUND");
			for(QA arr : asinQA.get(asin)) {
				sb.append(counter+"."+arr.toString()+'\n');
				counter++;
			}

		}
		
		System.out.println(sb);
	}

}
