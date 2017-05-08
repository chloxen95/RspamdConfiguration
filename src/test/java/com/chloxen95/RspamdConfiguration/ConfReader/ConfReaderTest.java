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
	
	Map<String, Object> resultMap = new IdentityHashMap<>();
	
	public void testReadFile() throws IOException {
		// 文件路径，此处应用相对路径
		String path = "TestFile/rspamd.conf";
		File file = new File(path);
		if (!file.exists() || file.isDirectory())
			throw new FileNotFoundException();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String temp = null;
		temp = br.readLine();
		// System.out.println(temp);
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
	 * 读取.inlcude
	 * .include "$CONFDIR/common.conf"
	 * .include(try=true; priority=1,duplicate=merge) "$LOCAL_CONFDIR/local.d/options.inc"
	 */
	public void testReadInclude(String includeStr, Map<String, Object> result) {
		Map<String, Object> include = new IdentityHashMap<>();
		// String includeStr = ".include(try=true; priority=1,duplicate=merge) \"$LOCAL_CONFDIR/local.d/options.inc\"";
		// String includeStr = ".include \"$CONFDIR/common.conf\"";
		Pattern pProp = Pattern.compile("\\((.*)\\)");
		Pattern pPath = Pattern.compile("\"(.*)\"");
		Matcher mProp = pProp.matcher(includeStr);
		Matcher mPath = pPath.matcher(includeStr);
		if (mProp.find()) {
			String prop = mProp.group();
			String[] props = prop.substring(1, prop.length() - 1).split(";|\\s|,");
			for (String t : props) {
				if (!"".equals(t)) {
					testReadVariable(t, include);
				}
			}
		}
		if (mPath.find()) {
			include.put(new String("#path"), mPath.group());
		}
		result.put(new String(".include"), include);
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
			else if(temp.startsWith(".include"))
				testReadInclude(new String(temp), tranResultMap);
			else if(temp.contains("="))
				testReadVariable(temp, tranResultMap);
			else
				tranResultMap.put(new String("#variable"), temp);
		}
		resultMap.put(new String(varName), tranResultMap);
	}

	@Test
	public void test() throws IOException {
		String path = "TestFile/rspamd.conf";
		File file = new File(path);
		if (!file.exists() || file.isDirectory())
			throw new FileNotFoundException();
		BufferedReader br = new BufferedReader(new FileReader(file));
		for (String temp = br.readLine(); temp != null; temp = br.readLine()) {
			if (!temp.startsWith("#") && !"".equals(temp)) {
				// 删除#后的注释
				String curString = temp.trim().replaceAll("#|(?<=#)[\\s\\S]*$","");
				if (curString.startsWith(".include"))
					testReadInclude(curString, resultMap);
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
