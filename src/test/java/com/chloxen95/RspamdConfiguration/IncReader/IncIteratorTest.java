package com.chloxen95.RspamdConfiguration.IncReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * �������IncLongStringTest.java�Ĺ�����ͬ�����Ƕ�ȡ.inc�ļ�����֮ͬ����ÿ��ѭ�������н�����ȡ
 * 
 * .inc �ļ���ʽ:
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
	String varName = "";

	/**
	 * ��ȡ������check_all_filters = false;
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
		printVar(2);
	}

	/**
	 * ��ȡ�б� classify_headers = [ "User-Agent", "X-Mailer", "Content-Type",
	 * "X-MimeOLE", ];
	 */
	@Test
	public void testReadList() {
		String[] varList = { "classify_headers = [", "		\"User-Agent\",", "		\"X-Mailer\",",
				"		\"Content-Type\",", "		\"X-MimeOLE\",", "];" };

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
		printVar(1);
	}

	/**
	 * ��ȡӳ���ϵ�� dns { timeout = 1s; sockets = 16; retransmits = 5; }
	 * 
	 */
	@Test
	public void testReadMap() {
		String[] varMap = { "dns {", " timeout = 1s;", " sockets = 16;", " retransmits = 5;", "}" };

		for (String t : varMap) {
			String temp = t.trim();
			if (temp.endsWith("{"))
				varName = temp.substring(0, temp.length() - 1).trim();
			else if (temp.startsWith("}"))
				break;
			else { // �˴���testReadVariable()��ͬ
				String[] varKV = temp.split("=");
				String varKey = varKV[0].trim();
				String varValue = varKV[1].trim();
				tranResultMap.put(varKey, varValue);
			}
		}

		printVar(2);

	}
	
	private void printVar(int index){
		System.out.println(varName);
		if(index == 1)
			System.out.println(tranResultList);
		else if(index == 2)
			System.out.println(tranResultMap);
	}

}
