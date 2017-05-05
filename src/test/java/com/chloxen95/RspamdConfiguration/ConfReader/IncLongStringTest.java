package com.chloxen95.RspamdConfiguration.ConfReader;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
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
public class IncLongStringTest {

	Map<String, Object> tranResultMap = new HashMap<>();
	List<Object> tranResultList = new ArrayList<>();

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
		System.out.println(tranResultMap);
	}

	/**
	 * ��ȡ�б� classify_headers = [ "User-Agent", "X-Mailer", "Content-Type",
	 * "X-MimeOLE", ];
	 */
	@Test
	public void testReadList() {
		String[] varList = { "classify_headers = [", "		\"User-Agent\",", "		\"X-Mailer\",",
				"		\"Content-Type\",", "		\"X-MimeOLE\",", "];" };

		String varName = null;

	}

	/**
	 * ��ȡӳ���ϵ�� dns { timeout = 1s; sockets = 16; retransmits = 5; }
	 * 
	 */
	@Test
	public void testReadMap() {
		String[] varMap = { "dns {", " timeout = 1s;", " sockets = 16;", " retransmits = 5;", "}" };

		String varName = null;

	}

}
