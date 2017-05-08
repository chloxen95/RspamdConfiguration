package com.chloxen95.RspamdConfiguration.IncReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * ע�⣺����౻���ã���ΪЧ��̫�ͣ������п��Խ���ĵط�
 * 
 * �������IncIteratorTest.java�Ĺ�����ͬ�����Ƕ�ȡ.inc�ļ�����֮ͬ������Map��List��ȡΪ���ַ������ٽ��зָ��ȡ
 * 
 * .inc �ļ���ʽ:
 * 
 * check_all_filters = false; dns { timeout = 1s; sockets = 16; retransmits = 5;
 * } classify_headers = [ "User-Agent", "X-Mailer", "Content-Type", "X-MimeOLE",
 * ];
 * 
 * @author chlox
 *
 */
@Deprecated
public class IncLongStringTest {

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
				"		\"Content-Type\",", "		\"X-MimeOLE\"", "];" };

		StringBuffer sb = new StringBuffer();
		boolean flag = true;
		if (varList[0].endsWith("[")) {
			if(flag){
				varName = varList[0].split("=")[0].trim();
			}
			for (String t : varList) {
				sb.append(t);
				if (t.startsWith("]"))
					break;
			}
			// System.out.println(sb.toString());
			String pattern = "\"(.*)\"";
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(sb.toString());
			// for(;m.find();){
			// System.out.println(m.group());
			// }
			if (m.find()) {
				String[] valueList = m.group().replaceAll("\"|,|\t", " ").split("\\s{2,}");
				// System.out.println(valueList.length);
				for (String t : valueList)
					//System.out.println(t);
					tranResultList.add(t.trim());
			}
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

		System.out.println(varMap.toString());
		
		varName = varMap[0].split("=")[0].trim();
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
