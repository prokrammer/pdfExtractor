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

	public void text() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("D:/temp/gggg.txt"));

		String line = br.readLine();
		StringBuilder sb = new StringBuilder();
		int page = 1;
		int numOfEnter;
		while (line != null) {
			System.out.println(line);
			if (line.length() < 40) {
				sb.append(line+"\r\n");
			} else {
				while (line.length() > 40) {
					sb.append(line.substring(0, 40) + "\r\n");
					line = line.substring(40);
					numOfEnter = StringUtils.countOccurrencesOf(sb.toString(), "\r\n");
					if (numOfEnter > 40) {
						BufferedWriter bw = new BufferedWriter(
								new FileWriter(new File("d:/temp/gggg" + page + ".txt")));
						bw.write(sb.toString());
						bw.flush();
						bw.close();
						sb = new StringBuilder();
						page++;
					}
				}
			}
			line = br.readLine();
		}
		System.out.println(sb.toString());
		br.close();
	}

	/*
	 * try {
	 * 
	 * StringBuilder sb = new StringBuilder(); String line = br.readLine(); // while
	 * (line != null) { // sb.append(line); // sb.append("\n"); // line =
	 * br.readLine(); // }
	 * 
	 * while (line != null) { sb.append(line); line = br.readLine(); } // txt \n
	 * 전부삭제 String Builder sb 담기 System.out.println("+++++++++++" + sb);
	 * 
	 * // int a=(int)(sb.length()/3); int a = (int) (Math.ceil(sb.length() /
	 * (double) 3)); System.out.println("+++++++++++" + a); int y = 2; for (int x =
	 * 1; x <= a; x++) { sb.insert((int) (1 + (x * y)), "\n");
	 * 
	 * } // 30자마다 /n 넣기 System.out.println("+++++++++++" + sb); String parag[] =
	 * sb.toString().split("\n");
	 * 
	 * // 30자마다 /n 기중으로 잘라서 스트링 배열에 넣기(/n 삭제됨) for (int i = 0; i < parag.length;
	 * i++) { parag[i] += "\r\n"; } // 30자마다 엔터값 넣기 String bimil =
	 * UUID.randomUUID().toString(); for (int i = 0; i < parag.length; i++) { if ((i
	 * + 1) % 3 == 0) { parag[i] = parag[i] + bimil; } }
	 * 
	 * String ppp = ""; for (int i = 0; i < parag.length; i++) { ppp = ppp +
	 * parag[i]; }
	 * 
	 * String parags[] = ppp.toString().split(bimil);
	 * 
	 * for (int i = 0; i < parags.length; i++) { File file = new
	 * File("D:/temp/Paragraph_" + i + ".txt"); FileWriter writer = new
	 * FileWriter(file, true); PrintWriter output = new PrintWriter(writer);
	 * output.print(parags[i]); output.close(); br.close(); } } finally {
	 * br.close(); }
	 * 
	 * }
	 */
}
