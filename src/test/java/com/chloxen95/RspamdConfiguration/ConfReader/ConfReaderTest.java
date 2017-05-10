package com.chloxen95.RspamdConfiguration.ConfReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class ConfReaderTest {

	/**
	 * �ļ���ȡ��ģ�����
	 * @throws IOException
	 */
	public void testReadFile() throws IOException {
		// �ļ�·�����˴�Ӧ�����·��
		String path = "src/test/resources/rspamd.conf";
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
	 * ��ȡ.inlcude
	 * .include "$CONFDIR/common.conf"
	 * .include(try=true; priority=1,duplicate=merge) "$LOCAL_CONFDIR/local.d/options.inc"
	 * �����������ù�ϵ���ַ����������䱣�浽Ŀ��Map��
	 * @param includeStr Դ�ַ���
	 * @param result Ŀ��Map
	 */
	public void testReadInclude(String includeStr, Map<String, Object> result) {
		Map<String, Object> include = new IdentityHashMap<>();
		Pattern pProp = Pattern.compile("\\((.*)\\)");
		Pattern pPath = Pattern.compile("\"(.*)\"");
		Matcher mProp = pProp.matcher(includeStr);
		Matcher mPath = pPath.matcher(includeStr);
		// ��ȡ�����ڵ�kv
		if (mProp.find()) {
			String prop = mProp.group();
			String[] props = prop.substring(1, prop.length() - 1).split(";|\\s|,");
			for (String t : props) {
				if (!"".equals(t)) {
					testReadVariable(t, include);
				}
			}
		}
		// ��ȡ·��
		if (mPath.find()) {
			include.put(new String("#path"), mPath.group());
		}
		result.put(new String(".include"), include);
	}

	/**
	 * ��ȡӳ���ϵ�� dns { timeout = 1s; sockets = 16; retransmits = 5; }
	 * ��������ӳ���ϵ���ַ����������䱣�浽Ŀ��Map�С�
	 * ӳ���ϵ����У����򽫶�ȡΪһ���ַ���
	 * @param varNameStr Դ�ַ���
	 * @param br �ļ�������ʱӦ��ȡ��ӳ���ϵ�������ڵ���
	 * @param result Ŀ��Map
	 * @throws IOException
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
			else if (temp.startsWith(".include"))
				testReadInclude(new String(temp), tranResultMap);
			else if (temp.contains("="))
				testReadVariable(temp, tranResultMap);
			else if (temp.endsWith("{"))
				testReadMap(temp, br, result);
			else
				tranResultMap.put(new String("#variable"), temp);
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
			if (curString.startsWith(".include"))
				testReadInclude(curString, result);
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
		String path = "src/test/resources/rspamd.conf";
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
