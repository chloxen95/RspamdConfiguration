package com.chloxen95.RspamdConfiguration.ConfReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * 这个类与IncLongStringTest.java的功能相同，都是读取.inc文件。不同之处：每次循环都进行解析读取
 * 
 * .inc 文件形式:
 * 
 * check_all_filters = false; dns { timeout = 1s; sockets = 16; retransmits = 5;
 * } classify_headers = [ "User-Agent", "X-Mailer", "Content-Type", "X-MimeOLE",
 * ];
 * 
 * @author chloxen95
 *
 */
public class IncIteratorTest {

	Map<String, Object> tranResultMap = new HashMap<>();
	List<Object> tranResultList = new ArrayList<>();

	/**
	 * 读取变量：check_all_filters = false;
	 */
	@Test
	public void testReadVariable() {
		String varStr = "check_all_filters = false;";
		// long t1 = System.currentTimeMillis();
		String[] varKV = varStr.split("=");
		String varKey = varKV[0].trim();
		String varValue = varKV[1].trim().endsWith(";") ? varKV[1].trim().substring(0, varKV[1].trim().length() - 1)
				: varKV[1].trim();
		// long t2 = System.currentTimeMillis() - t1;
		tranResultMap.put(varKey, varValue);
		System.out.println(tranResultMap);
	}

	/**
	 * 读取列表： classify_headers = [ "User-Agent", "X-Mailer", "Content-Type",
	 * "X-MimeOLE", ];
	 */
	@Test
	public void testReadList() {
		String[] varList = { "classify_headers = [", "		\"User-Agent\",", "		\"X-Mailer\",",
				"		\"Content-Type\",", "		\"X-MimeOLE\",", "];" };

		String varName = null;

		for (String t : varList) {
			String temp = t.trim();
			if (temp.endsWith("["))
				varName = temp.split("=")[0].trim();
			else if (temp.startsWith("\"") && temp.endsWith("\","))
				tranResultList.add(temp.substring(1, temp.length() - 2));
			else if (temp.startsWith("\"") && temp.endsWith("\""))
				tranResultList.add(temp.substring(1, temp.length() - 1));
			else if (temp.startsWith("]"))
				break;
		}
		System.out.println(varName);
		System.out.println(tranResultList);
	}

	/**
	 * 读取映射关系： dns { timeout = 1s; sockets = 16; retransmits = 5; }
	 * 
	 */
	@Test
	public void testReadMap() {
		String[] varMap = { "dns {", " timeout = 1s;", " sockets = 16;", " retransmits = 5;", "}" };

		String varName = null;

		for (String t : varMap) {
			String temp = t.trim();
			if (temp.endsWith("{"))
				varName = temp.substring(0, temp.length() - 1).trim();
			else if (temp.startsWith("}"))
				break;
			else { // 此处与testReadVariable()相同
				String[] varKV = temp.split("=");
				String varKey = varKV[0].trim();
				String varValue = varKV[1].trim();
				tranResultMap.put(varKey, varValue);
			}
		}

		System.out.println(varName);
		System.out.println(tranResultMap);

	}

}
