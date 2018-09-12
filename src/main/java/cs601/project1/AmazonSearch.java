package cs601.project1;

import java.io.IOException;


/*
 * AmazonSearch
 * @author ksonar
 */

public class AmazonSearch {

	public static void main (String[] args) throws IOException {
		// TODO Auto-generated method stub
		if(args.length != 4 || !(args[0].equals("-reviews")) || !(args[2].equals("-qa")) ) {
			System.out.println("java usage : -reviews <review_file_name> -qa <qa_file_name>");
			System.exit(1);
		}
		
		System.out.println("HELLO");
		String reviewFile = args[1];
		String qaFile = args[3];
		
		double sTime = System.currentTimeMillis();
		Processing p = new Processing(reviewFile, qaFile);
		System.out.println("\n~~~~~PROCESSING DONE~~~~~\n");
		double eTime = System.currentTimeMillis();
		System.out.printf("Total Processing time : %.2f secs\n.....................\n\n", (eTime-sTime)/1000);
		
		p.userInput();
		

	}
	

}
