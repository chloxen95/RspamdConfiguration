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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.chloxen95.RspamdConfiguration.Service.ConfigurationParser;

public class ConfParserImpl implements ConfigurationParser {

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
			// 忽略注释
			if (t.startsWith("#") || "".equals(t))
				continue;
			String temp = t.trim();
			if (temp.startsWith("}"))
				break;
			else if (temp.startsWith(".include"))
				ReadInclude(new String(temp), tranResultMap);
			else if (temp.contains("="))
				ReadVariable(temp, tranResultMap);
			else if (temp.endsWith("{"))
				ReadMap(temp, br, result);
			else
				tranResultMap.put(new String("#variable"), temp);
		}
		result.put(new String(varName), tranResultMap);
		
	}
	
	/**
	 * 读取.inlcude
	 * .include "$CONFDIR/common.conf"
	 * .include(try=true; priority=1,duplicate=merge) "$LOCAL_CONFDIR/local.d/options.inc"
	 * 解析带有引用关系的字符串，并将其保存到目标Map中
	 * @param includeStr 源字符串
	 * @param result 目标Map
	 */
	public void ReadInclude(String includeStr, Map<String, Object> result) {
		Map<String, Object> include = new IdentityHashMap<>();
		Pattern pProp = Pattern.compile("\\((.*)\\)");
		Pattern pPath = Pattern.compile("\"(.*)\"");
		Matcher mProp = pProp.matcher(includeStr);
		Matcher mPath = pPath.matcher(includeStr);
		// 读取括号内的kv
		if (mProp.find()) {
			String prop = mProp.group();
			String[] props = prop.substring(1, prop.length() - 1).split(";|\\s|,");
			for (String t : props) {
				if (!"".equals(t)) {
					ReadVariable(t, include);
				}
			}
		}
		// 读取路径
		if (mPath.find()) {
			include.put(new String("#path"), mPath.group());
		}
		result.put(new String(".include"), include);
		
	}

	@Override
	public void ConfParser(BufferedReader br, Map<String, Object> result) throws IOException {
		for (String temp = br.readLine(); temp != null; temp = br.readLine()) {
			// 忽略注释
			if(temp.startsWith("#") || "".equals(temp))
				continue;
			// 删除#后的注释
			String curString = temp.trim().replaceAll("#|(?<=#)[\\s\\S]*$", "");
			if (curString.startsWith(".include"))
				ReadInclude(curString, result);
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
		System.out.println(getFileContent("src/test/resources/rspamd.conf"));
	}

}
