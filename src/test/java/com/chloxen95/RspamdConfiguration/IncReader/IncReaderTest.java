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

	/**
	 * 文件读取的模版代码
	 * @throws IOException
	 */
	@Test
	public void testReadFiles() throws IOException {
		// 文件路径，此处应用相对路径
		String path = "src/test/resources/options.inc";
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
	 * 解析带有变量赋值的字符串，并将其保存到目标Map中
	 * @param varStr 源字符串
	 * @param result 目标Map
	 */
	private void testReadVariable(String varStr, Map<String, Object> result) {
		String[] varKV = varStr.split("=");
		String varKey = varKV[0].trim();
		String varValue = varKV[1].trim().endsWith(";") ? varKV[1].trim().substring(0, varKV[1].trim().length() - 1)
				: varKV[1].trim();
		result.put(new String(varKey), varValue);
	}

	/**
	 * 读取列表： classify_headers = [ "User-Agent", "X-Mailer", "Content-Type", "X-MimeOLE", ];
	 * 解析带有列表的字符串，并将其保存到目标Map中。
	 * 列表中每一项需分行，否则将读取为一般字符串
	 * @param varNameStr 源字符串
	 * @param br 文件流，此时应读取到列表名称所在的行
	 * @param result 目标Map
	 * @throws IOException 
	 */
	private void testReadList(String varNameStr, BufferedReader br, Map<String, Object> result) throws IOException {
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

	/**
	 * 读取映射关系： dns { timeout = 1s; sockets = 16; retransmits = 5; }
	 * 解析带有映射关系的字符串，并将其保存到目标Map中。
	 * 映射关系需分行，否则将读取为一般字符串
	 * @param varNameStr 源字符串
	 * @param br 文件流，此时应读取到映射关系名称所在的行
	 * @param result 目标Map
	 * @throws IOException 
	 * 
	 */
	private void testReadMap(String varNameStr, BufferedReader br, Map<String, Object> result) throws IOException {
		Map<String, Object> tranResultMap = new IdentityHashMap<>();
		String varName = varNameStr.substring(0, varNameStr.length() - 1).trim();

		for (String t = br.readLine(); t != null; t = br.readLine()) {
			// 忽略注释
			if (t.startsWith("#") || "".equals(t))
				continue;
			String temp = t.trim();
			if (temp.startsWith("}"))
				break;
			else if (temp.endsWith("{"))
				testReadMap(temp, br, tranResultMap);
			else
				testReadVariable(new String(temp), tranResultMap);
		}
		result.put(new String(varName), tranResultMap);
	}

	/**
	 * 分析字符串所属类型，并将并将其保存到目标Map中。
	 * @param br 文件流
	 * @param result 目标Map
	 * @throws IOException
	 */
	private void ConfParser(BufferedReader br, Map<String, Object> result) throws IOException {
		for (String temp = br.readLine(); temp != null; temp = br.readLine()) {
			// 忽略注释
			if(temp.startsWith("#") || "".equals(temp))
				continue;
			// 删除#后的注释
			String curString = temp.trim().replaceAll("#|(?<=#)[\\s\\S]*$", "");
			if (curString.endsWith("["))
				testReadList(curString, br, result);
			else if (curString.endsWith("{"))
				testReadMap(curString, br, result);
			else if (curString.contains("="))
				testReadVariable(curString, result);
			else
				result.put(new String("#variable"), curString);
		}
	}

	/**
	 * 主测试用例
	 * @throws IOException
	 */
	@Test
	public void test() throws IOException {
		// 文件解析结果Map
		Map<String, Object> resultMap = new IdentityHashMap<>();
		// 文件路径
		String path = "TestFile/options.inc";
		// 文件映射
		File file = new File(path);
		if (!file.exists() || file.isDirectory())
			throw new FileNotFoundException();
		// 文件流
		BufferedReader br = new BufferedReader(new FileReader(file));
		// 解析文件
		ConfParser(br, resultMap);
		// 关闭文件流
		br.close();
		System.out.println(resultMap);
	}

}
