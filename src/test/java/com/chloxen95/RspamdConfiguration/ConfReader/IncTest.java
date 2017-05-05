package com.chloxen95.RspamdConfiguration.ConfReader;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * .inc 文件形式:
 * 
 * check_all_filters = false; dns { timeout = 1s; sockets = 16; retransmits = 5;
 * } classify_headers = [ "User-Agent", "X-Mailer", "Content-Type", "X-MimeOLE",
 * ];
 * 
 * @author chloxen95
 *
 */
public class IncTest {

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
		
		// 需要完成
		for (String t : varList) {
			if (t.endsWith("["))
				varName = t.split("=")[0].trim();
			else if(t.matches("\"([^\"]*)\""))
				tranResultList.add(t.substring(0, t.length() - 1));
			else if("];".equals(t))
				break;
		}
		System.out.println(varName);
		System.out.println(tranResultList);
	}

	@Test
	public void testReadMap() {

	}

}
