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
	 * �ļ���ȡ��ģ�����
	 * @throws IOException
	 */
	@Test
	public void testReadFiles() throws IOException {
		// �ļ�·�����˴�Ӧ�����·��
		String path = "src/test/resources/options.inc";
		File file = new File(path);
		if (!file.exists() || file.isDirectory())
			throw new FileNotFoundException();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String temp = null;
		temp = br.readLine();
		while (temp != null) {
			// �ж��Ƿ�Ϊע�ͻ����
			if (!temp.startsWith("#") && !"".equals(temp))
				System.out.println(temp);
			temp = br.readLine();
		}
		br.close();
	}

	/**
	 * ��ȡ������check_all_filters = false;
	 * �������б�����ֵ���ַ����������䱣�浽Ŀ��Map��
	 * @param varStr Դ�ַ���
	 * @param result Ŀ��Map
	 */
	private void testReadVariable(String varStr, Map<String, Object> result) {
		String[] varKV = varStr.split("=");
		String varKey = varKV[0].trim();
		String varValue = varKV[1].trim().endsWith(";") ? varKV[1].trim().substring(0, varKV[1].trim().length() - 1)
				: varKV[1].trim();
		result.put(new String(varKey), varValue);
	}

	/**
	 * ��ȡ�б� classify_headers = [ "User-Agent", "X-Mailer", "Content-Type", "X-MimeOLE", ];
	 * ���������б���ַ����������䱣�浽Ŀ��Map�С�
	 * �б���ÿһ������У����򽫶�ȡΪһ���ַ���
	 * @param varNameStr Դ�ַ���
	 * @param br �ļ�������ʱӦ��ȡ���б��������ڵ���
	 * @param result Ŀ��Map
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
	 * ��ȡӳ���ϵ�� dns { timeout = 1s; sockets = 16; retransmits = 5; }
	 * ��������ӳ���ϵ���ַ����������䱣�浽Ŀ��Map�С�
	 * ӳ���ϵ����У����򽫶�ȡΪһ���ַ���
	 * @param varNameStr Դ�ַ���
	 * @param br �ļ�������ʱӦ��ȡ��ӳ���ϵ�������ڵ���
	 * @param result Ŀ��Map
	 * @throws IOException 
	 * 
	 */
	private void testReadMap(String varNameStr, BufferedReader br, Map<String, Object> result) throws IOException {
		Map<String, Object> tranResultMap = new IdentityHashMap<>();
		String varName = varNameStr.substring(0, varNameStr.length() - 1).trim();

		for (String t = br.readLine(); t != null; t = br.readLine()) {
			// ����ע��
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
	 * �����ַ����������ͣ����������䱣�浽Ŀ��Map�С�
	 * @param br �ļ���
	 * @param result Ŀ��Map
	 * @throws IOException
	 */
	private void ConfParser(BufferedReader br, Map<String, Object> result) throws IOException {
		for (String temp = br.readLine(); temp != null; temp = br.readLine()) {
			// ����ע��
			if(temp.startsWith("#") || "".equals(temp))
				continue;
			// ɾ��#���ע��
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
	 * ����������
	 * @throws IOException
	 */
	@Test
	public void test() throws IOException {
		// �ļ��������Map
		Map<String, Object> resultMap = new IdentityHashMap<>();
		// �ļ�·��
		String path = "TestFile/options.inc";
		// �ļ�ӳ��
		File file = new File(path);
		if (!file.exists() || file.isDirectory())
			throw new FileNotFoundException();
		// �ļ���
		BufferedReader br = new BufferedReader(new FileReader(file));
		// �����ļ�
		ConfParser(br, resultMap);
		// �ر��ļ���
		br.close();
		System.out.println(resultMap);
	}

}
