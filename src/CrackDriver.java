import java.io.*;
import java.util.*;
import java.security.*;
import java.util.concurrent.*;

public class CrackDriver extends Thread {

    public static ArrayList<String> cleanOutput = new ArrayList<String>();
	public static ArrayList<String> dict = new ArrayList<String>();
	public static HashMap<String, String> pwds = new HashMap<String, String>();
    public static String[] commands = {"oneNumberSolo",//1,189
                                        "twoNumberSolo",//2,341
                                        "threeNumberSolo",//3,717
                                        "fourNumberSolo",//6,051
                                        "fiveNumberSolo",//8,657
                                        "combineSame",//14,662
                                        "noTrans",//46,452
                                        "sixNumberSolo",//49,398
                                        "oneNumberBeg",//62,842
                                        "oneNumberBoth",//64,991
                                        "oneNumberEnd",//74,485
                                        "capitals",//238,091
                                        "symbols",//358,327
                                        "sevenNumberSolo",//386,987
                                        "twoNumberBeg",//619,021
                                        "twoNumberEnd",//630,009
                                        "twoNumberBoth",//649,637
                                        "l33t",//1,270,990
                                        "eightNumberSolo",//3,788,402
                                        "threeNumberBeg",//6,157,056
                                        "threeNumberEnd",//6,166,286
                                        
                                        "capitalNumber",// 

                                        "fourNumberEnd", // 
                                        "fourNumberBeg", //

                                        "nineNumberSolo", //
                                        "tenNumberSolo", // 
                                        "elevenNumberSolo", //

                                        "combineTwoWordsOne",// 
                                        "combineTwoWordsTwo", //
                                        "combineTwoWordsThree",//
                                        "combineTwoWordsFour",

                                        "symbolsBoth" };// 

    public CrackDriver() {
    	BufferedReader br = null;

    	try {
    		String line;
    		br = new BufferedReader(new FileReader("dict2.txt"));

    		while ((line = br.readLine()) != null){
    			dict.add(line);
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    	} finally {
    		try {
    			if(br != null){
    				br.close();
    			}
    		}catch (IOException ex){
    			ex.printStackTrace();
    		}
    	}

    	try {
    		String line;
    		br = new BufferedReader(new FileReader("password.txt"));

    		while ((line = br.readLine()) != null){

    			String[] split = line.split(":");
    			pwds.put((split[1]), split[0]);
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    	} finally {
    		try {
    			if(br != null){
    				br.close();
    			}
    		}catch (IOException ex){
    			ex.printStackTrace();
    		}
    	}

    System.out.println(dict.size());

    }

    public static void main (String[] args) {
        new CrackDriver();
        File output = new File("output.txt");

        ExecutorService executor = Executors.newFixedThreadPool(8);
        for (int i = 0; i < commands.length; i++) {
            Runnable worker = new WorkerThread(commands[i], dict, pwds);
            executor.execute(worker);
          }


        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }

}