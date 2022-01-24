package ia_tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Tests {
	public static void test1(File picture, String filename, int width, int length) {
		IAInterface ai = new IA();
		File diagnosis = ai.generateDiagnosis(picture);
		FileReader fr = null;
		BufferedReader br = null;
		
		FileReader fr2 = null;
		BufferedReader br2 = null;
		
		boolean test1 = true;
	    try {
	        fr = new FileReader(filename);
	        br = new BufferedReader(fr);
	        
	        fr2 = new FileReader(diagnosis);
	        br2 = new BufferedReader(fr2);
	        String strCurrentLine;
	        
	        int j = 0;
	        char[][] diag = new char[length][width];
	        while ((strCurrentLine = br2.readLine()) != null) {
	          System.out.println(strCurrentLine);
	          for(int i = 0;i<strCurrentLine.length();i++) {
	        	  char c = strCurrentLine.charAt(i);
	        	  diag[j][i] = c;
	          }
	        }
	        
	        j = 0;
	        while ((strCurrentLine = br.readLine()) != null) {
	          System.out.println(strCurrentLine);
	          for(int i = 0;i<strCurrentLine.length();i++) {
	        	  char c = strCurrentLine.charAt(i);
	        	  if(c!=diag[j][i]) {
	        		  test1 = false;
	        	  }
	          }
	        }
	      } catch (IOException e) {
	        e.printStackTrace();
	      } finally {
	        try {
	          if (br != null)
	            br.close();
	          if (fr != null)
	            fr.close();
	        } catch (IOException e) {
	          e.printStackTrace();
	        }
	      }
	    System.out.println(test1);
	}
	
	public static void test2(File picture, String filename, int width, int length) {
		IAInterface ai = new IA();
		File diagnosis = ai.generateDiagnosis(picture);
		FileReader fr = null;
		BufferedReader br = null;
		
		FileReader fr2 = null;
		BufferedReader br2 = null;
		
		boolean test1 = true;
	    try {
	        fr = new FileReader(filename);
	        br = new BufferedReader(fr);
	        
	        fr2 = new FileReader(diagnosis);
	        br2 = new BufferedReader(fr2);
	        String strCurrentLine;
	        
	        int j = 0;
	        char[][] diag = new char[length][width];
	        while ((strCurrentLine = br2.readLine()) != null) {
	          System.out.println(strCurrentLine);
	          for(int i = 0;i<strCurrentLine.length();i++) {
	        	  char c = strCurrentLine.charAt(i);
	        	  System.out.print(c);
	          }
	          System.out.println();
	        }
	        
	        j = 0;
	        while ((strCurrentLine = br.readLine()) != null) {
	          System.out.println(strCurrentLine);
	          for(int i = 0;i<strCurrentLine.length();i++) {
	        	  char c = strCurrentLine.charAt(i);
	        	  System.out.print(c);
	          }
	          System.out.println();
	        }
	      } catch (IOException e) {
	        e.printStackTrace();
	      } finally {
	        try {
	          if (br != null)
	            br.close();
	          if (fr != null)
	            fr.close();
	        } catch (IOException e) {
	          e.printStackTrace();
	        }
	      }
	    System.out.println(test1);
	}
}

