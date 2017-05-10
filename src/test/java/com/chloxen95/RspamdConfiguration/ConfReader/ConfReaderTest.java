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
	 * 文件读取的模版代码
	 * @throws IOException
	 */
	public void testReadFile() throws IOException {
		// 文件路径，此处应用相对路径
		String path = "src/test/resources/rspamd.conf";
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
	 * 读取.inlcude
	 * .include "$CONFDIR/common.conf"
	 * .include(try=true; priority=1,duplicate=merge) "$LOCAL_CONFDIR/local.d/options.inc"
	 * 解析带有引用关系的字符串，并将其保存到目标Map中
	 * @param includeStr 源字符串
	 * @param result 目标Map
	 */
	public void testReadInclude(String includeStr, Map<String, Object> result) {
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
					testReadVariable(t, include);
				}
			}
		}
		// 读取路径
		if (mPath.find()) {
			include.put(new String("#path"), mPath.group());
		}
		result.put(new String(".include"), include);
	}

	/**
	 * 读取映射关系： dns { timeout = 1s; sockets = 16; retransmits = 5; }
	 * 解析带有映射关系的字符串，并将其保存到目标Map中。
	 * 映射关系需分行，否则将读取为一般字符串
	 * @param varNameStr 源字符串
	 * @param br 文件流，此时应读取到映射关系名称所在的行
	 * @param result 目标Map
	 * @throws IOException
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
	 * 主测试用例
	 * @throws IOException
	 */
	@Test
	public void test() throws IOException {
		// 文件解析结果Map
		Map<String, Object> resultMap = new IdentityHashMap<>();
		// 文件路径
		String path = "src/test/resources/rspamd.conf";
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
