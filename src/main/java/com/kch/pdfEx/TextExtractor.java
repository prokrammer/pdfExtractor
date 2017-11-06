package com.kch.pdfEx;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.UUID;import org.omg.CosNaming._BindingIteratorStub;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void text() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("D:/temp/gggg.txt"));
		int numOfOneLine = 3;
		int lineOfOnePage = 3;
		String line = br.readLine();
		StringBuilder sb = new StringBuilder();
		int page = 1;
		int numOfEnter;
		while (line != null) {
			
			if (line.length() < numOfOneLine) {
				sb.append(line + "\r\n");
				line = br.readLine();
			} else {
				sb.append(line.substring(0, numOfOneLine) + "\r\n");
				line = line.substring(numOfOneLine);
				
			}
			
			numOfEnter = StringUtils.countOccurrencesOf(sb.toString(), "\r\n");
			if(numOfEnter >= lineOfOnePage) {
				page = write(page,sb);
				/*BufferedWriter bw = new BufferedWriter(new FileWriter(new File("d:/temp/gggg" + page + ".txt")));
				String line1 = sb.toString().substring(0, sb.toString().lastIndexOf("\r\n"));
				bw.write(line1);
				bw.flush();
				bw.close();
				System.out.println("while¾È sb : " + line1);
				sb = new StringBuilder();
				page++;*/
			}
					
		}
		page = write(page,sb);
		/*BufferedWriter bw = new BufferedWriter(new FileWriter(new File("d:/temp/gggg" + page + ".txt")));
		String line1 = sb.toString().substring(0, sb.toString().lastIndexOf("\r\n"));
		bw.write(line1);
		bw.flush();
		bw.close();
		System.out.println("while¾È sb : " + sb.toString());
		sb = new StringBuilder();
		page++;*/
		
		br.close();
	}
	public static int write(int page, StringBuilder sb) throws Exception{
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
