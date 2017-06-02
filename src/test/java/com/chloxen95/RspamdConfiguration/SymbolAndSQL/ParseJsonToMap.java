package com.chloxen95.RspamdConfiguration.SymbolAndSQL;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ParseJsonToMap {

	List<List<String>> result = new ArrayList<>();
	String curGroup = "";

	public void ReadFile() throws IOException {
		// 文件路径，此处应用相对路径
		String path = "src/test/resources/symbol/symbol.json";
		File file = new File(path);
		if (!file.exists() || file.isDirectory())
			throw new FileNotFoundException();
		BufferedReader br = new BufferedReader(new FileReader(file));
		br.readLine();
		// String temp = null;

		for (String line = br.readLine().trim(); line != null; line = br.readLine().trim()) {
			// 此处解析行内容
			if ("{".equals(line)) {
				ReadGroup(br);
				for (line = br.readLine().trim(); !"]".equals(line); line = br.readLine().trim()){
					ReadSymbol(br);
				}
			}
			if(result.size() == 351)
				break;
		}
		br.close();
	}

	public void ReadGroup(BufferedReader br) throws IOException {
		String line = br.readLine();
		String groupName = line.split(":")[1].trim();
		curGroup = groupName.substring(0, groupName.length() - 1);
		br.readLine();
	}

	public void ReadSymbol(BufferedReader br) throws IOException {
		// String temp = br.readLine();
		List<String> symbol = new ArrayList<>();
		for (String temp = br.readLine().trim(); !temp.trim().contains("}"); temp = br.readLine().trim()) {
			String t = temp.split(":")[1].trim();
			if (t.endsWith(","))
				t = t.substring(0, t.length() - 1);
			symbol.add(t);
		}
		symbol.add(curGroup);
		result.add(symbol);
		System.out.println(symbol);
	}

	@Test
	public void test() throws IOException{
		ReadFile();
	}
	
	@Test
	public void testReadGroup() {
		String groupName = "\"group\": \"mailing_list\",".split(":")[1].trim();
		groupName = groupName.substring(0, groupName.length() - 1);
		System.out.println(groupName);
	}

	@Test
	public void testReadSymbol() {
		String[] oneSymbol = { "        \"symbol\": \"ENVFROM_VERP\",", "        \"weight\": 0,",
				"        \"description\": \"Envelope From is a VERP address\",", "        \"frequency\": 0,",
				"        \"frequency_stddev\": 0,", "        \"time\": 0" };
		for (String temp : oneSymbol) {
			String t = temp.split(":")[1].trim();
			if (t.endsWith(","))
				t = t.substring(0, t.length() - 1);
			System.out.println(t);
		}
	}

}
