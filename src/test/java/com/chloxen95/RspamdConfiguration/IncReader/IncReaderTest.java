package com.chloxen95.RspamdConfiguration.IncReader;

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

public class IncReaderTest {

	Map<String, Object> resultMap = new IdentityHashMap<>();

	@Test
	public void testReadFiles() throws IOException {
		// 文件路径，此处应用相对路径
		String path = "TestFile/options.inc";
		File file = new File(path);
		if (!file.exists() || file.isDirectory())
			throw new FileNotFoundException();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String temp = null;
		temp = br.readLine();
		while (temp != null) {
			// 判断是否为注释或空行
			if (!temp.startsWith("#") && !"".equals(temp))
				System.out.println(temp);
			temp = br.readLine();
		}
		br.close();
	}

	/**
	 * 读取变量：check_all_filters = false;
	 */
	private void testReadVariable(String varStr, Map<String, Object> result) {
		String[] varKV = varStr.split("=");
		String varKey = varKV[0].trim();
		String varValue = varKV[1].trim().endsWith(";") ? varKV[1].trim().substring(0, varKV[1].trim().length() - 1)
				: varKV[1].trim();
		result.put(new String(varKey), varValue);
	}

	/**
	 * 读取列表： classify_headers = [ "User-Agent", "X-Mailer", "Content-Type",
	 * "X-MimeOLE", ];
	 * @throws IOException 
	 */
	private void testReadList(String varNameStr, BufferedReader br) throws IOException {
		List<Object> tranResultList = new ArrayList<>();
		String varName = varNameStr.split("=")[0].trim();

		for (String t = br.readLine(); t != null; t = br.readLine()) {
			String temp = t.trim();
			if (temp.startsWith("\"") && temp.endsWith("\","))
				tranResultList.add(temp.substring(0, temp.length() - 1));
			else if (temp.startsWith("\"") && temp.endsWith("\""))
				tranResultList.add(temp);
			else if (temp.startsWith("]"))
				break;
		}
		resultMap.put(new String(varName), tranResultList);

	}

	/**
	 * 读取映射关系： dns { timeout = 1s; sockets = 16; retransmits = 5; }
	 * @throws IOException 
	 * 
	 */
	private void testReadMap(String varNameStr, BufferedReader br) throws IOException {
		Map<String, Object> tranResultMap = new IdentityHashMap<>();
		String varName = varNameStr.substring(0, varNameStr.length() - 1).trim();

		for (String t = br.readLine(); t != null; t = br.readLine()) {
			String temp = t.trim();
			if (temp.startsWith("}"))
				break;
			else
				testReadVariable(new String(temp), tranResultMap);
		}
		resultMap.put(new String(varName), tranResultMap);
	}

	@Test
	public void test() throws IOException {
		String path = "TestFile/options.inc";
		File file = new File(path);
		if (!file.exists() || file.isDirectory())
			throw new FileNotFoundException();
		BufferedReader br = new BufferedReader(new FileReader(file));
		for (String temp = br.readLine(); temp != null; temp = br.readLine()) {
			if (!temp.startsWith("#") && !"".equals(temp)) {
				// 删除#后的注释
				String curString = temp.trim().replaceAll("#|(?<=#)[\\s\\S]*$","");
				if (curString.endsWith("["))
					testReadList(curString, br);
				else if (curString.endsWith("{"))
					testReadMap(curString, br);
				else if (curString.contains("="))
					testReadVariable(curString, resultMap);
				else
					resultMap.put(new String("#variable"), curString);
			}
		}

		br.close();
		System.out.println(resultMap);
	}

}
