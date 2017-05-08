package com.chloxen95.RspamdConfiguration.ConfReader;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class ConfIteratorTest {

	Map<String, Object> resultMap = new IdentityHashMap<>();

	@Test
	public void test() {
		String str = "adafsd#dasf";
		// .replaceAll("(?<=#)[\\s\\S]*$","")
		System.out.println(str.replaceAll("#|(?<=#)[\\s\\S]*$", ""));
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
	@Test
	public void testReadInclude() {
		Map<String, Object> include = new IdentityHashMap<>();
		String includeStr = ".include(try=true; priority=1,duplicate=merge) \"$LOCAL_CONFDIR/local.d/options.inc\"";
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
		resultMap.put(new String(".include"), include);
		System.out.println(resultMap);
	}

}
