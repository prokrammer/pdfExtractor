package com.kch.pdfEx;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import org.springframework.util.StringUtils;

public class TextExtractor {
	private static TextExtractor textExtractor = new TextExtractor();

	public static TextExtractor getInstance() {
		if (textExtractor == null) {
			textExtractor = new TextExtractor();
		}
		return textExtractor;
	}
	
	public static void main(String[] args) {
		try {
			text();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void text() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("D:/temp/gggg.txt")); //Read .txt file
		int numOfOneLine = 3;	// Number of one line
		int lineOfOnePage = 3;	// Number of lines of One page
		int page = 1;	// For count pages
		int numOfEnter;	// For search the number of Enter
		StringBuilder sb = new StringBuilder();
		String line = br.readLine(); // Read one line
		while (line != null) { // While line is exist
			if (line.length() < numOfOneLine) { 
				sb.append(line + "\r\n");
				line = br.readLine();	// Read next line when current line is lesser than numOfOneLine 
			} else {
				sb.append(line.substring(0, numOfOneLine) + "\r\n");
				line = line.substring(numOfOneLine); // Cut line and reunite
				
			}
			numOfEnter = StringUtils.countOccurrencesOf(sb.toString(), "\r\n");
			if(numOfEnter >= lineOfOnePage) {
				page = write(page,sb); // Write and return the number of page for increase
			}
					
		} // Exit when line is not exist
		write(page,sb); // Write the left text 
		br.close();
	}
	public static int write(int page, StringBuilder sb) throws Exception{
		if(sb.toString().equals("\r\n")) {System.out.println("\\r\\n");return page;}
		if(sb.toString().equals("")) {System.out.println("space");return page;}
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("d:/temp/gggg" + page + ".txt")));
		String withoutLastEnter = sb.toString().substring(0, sb.toString().lastIndexOf("\r\n"));
		bw.write(withoutLastEnter);
		bw.flush();
		bw.close();
		System.out.println("while¾È sb : " + withoutLastEnter);
		sb.setLength(0);
		page++;
		return page;
	}

}
