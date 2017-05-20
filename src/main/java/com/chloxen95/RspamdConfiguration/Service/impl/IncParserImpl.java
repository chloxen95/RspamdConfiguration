package com.chloxen95.RspamdConfiguration.Service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.chloxen95.RspamdConfiguration.Service.ConfigurationParser;

public class IncParserImpl implements ConfigurationParser {

	@Override
	public void ReadVariable(String varStr, Map<String, Object> result) {
		String[] varKV = varStr.split("=");
		String varKey = varKV[0].trim();
		String varValue = varKV[1].trim().endsWith(";") ? varKV[1].trim().substring(0, varKV[1].trim().length() - 1)
				: varKV[1].trim();
		result.put(new String(varKey), varValue);
		
	}

	@Override
	public void ReadList(String varNameStr, BufferedReader br, Map<String, Object> result) throws IOException {
		List<Object> tranResultList = new ArrayList<>();
		String varName = varNameStr.split("=")[0].trim();

		for (String t = br.readLine(); t != null; t = br.readLine()) {
			if (t.startsWith("#") || "".equals(t))
				continue;
			String temp = t.trim();
			if (temp.startsWith("\"") && temp.endsWith("\","))
				tranResultList.add(temp.substring(0, temp.length() - 1));
			else if (temp.startsWith("\"") && temp.endsWith("\""))
				tranResultList.add(temp);
			else if (temp.startsWith("]"))
				break;
		}
		result.put(new String(varName), tranResultList);

	}

	@Override
	public void ReadMap(String varNameStr, BufferedReader br, Map<String, Object> result) throws IOException {
		Map<String, Object> tranResultMap = new IdentityHashMap<>();
		String varName = varNameStr.substring(0, varNameStr.length() - 1).trim();

		for (String t = br.readLine(); t != null; t = br.readLine()) {
			// ºöÂÔ×¢ÊÍ
			if (t.startsWith("#") || "".equals(t))
				continue;
			String temp = t.trim();
			if (temp.startsWith("}"))
				break;
			else if (temp.endsWith("{"))
				ReadMap(temp, br, tranResultMap);
			else
				ReadVariable(new String(temp), tranResultMap);
		}
		result.put(new String(varName), tranResultMap);
		
	}

	@Override
	public void ConfParser(BufferedReader br, Map<String, Object> result) throws IOException {
		for (String temp = br.readLine(); temp != null; temp = br.readLine()) {
			// ºöÂÔ×¢ÊÍ
			if (temp.startsWith("#") || "".equals(temp))
				continue;
			// É¾³ý#ºóµÄ×¢ÊÍ
			String curString = temp.trim().replaceAll("#|(?<=#)[\\s\\S]*$", "");
			if (curString.endsWith("["))
				ReadList(curString, br, result);
			else if (curString.endsWith("{"))
				ReadMap(curString, br, result);
			else if (curString.contains("="))
				ReadVariable(curString, result);
			else
				result.put(new String("#variable"), curString);
		}
		
	}

	@Override
	public Map<String, Object> getFileContent(String path) throws IOException {
		Map<String, Object> resultMap = new IdentityHashMap<>();
		File file = new File(path);
		if (!file.exists() || file.isDirectory())
			throw new FileNotFoundException();
		BufferedReader br = new BufferedReader(new FileReader(file));
		ConfParser(br, resultMap);
		br.close();
		return resultMap;
	}
	
	// @Test
	public void test() throws IOException{
		System.out.println(getFileContent("src/test/resources/options.inc"));
	}

}
