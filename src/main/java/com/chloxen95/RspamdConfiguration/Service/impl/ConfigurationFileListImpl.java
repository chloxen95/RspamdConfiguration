package com.chloxen95.RspamdConfiguration.Service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

import com.chloxen95.RspamdConfiguration.Service.ConfigurationFileList;

public class ConfigurationFileListImpl implements ConfigurationFileList {

	@Override
	public void getConfigurationFileList(String path, String postfix, Map<String, Object> result) {
		File file = new File(path);
		if (file.exists()) {
			File[] files = file.listFiles();
			if (files.length == 0) {
				result.put("error", "File forder is empty");
			} else {
				int i = 0;
				for (File file2 : files) {
					if (file2.isDirectory()) {
						Map<String, Object> sub = new HashMap<>();
						getConfigurationFileList(file2.getPath(), postfix, sub);
						result.put(file2.getName(), sub);
						// getConfigurationFileList(file2.getPath(), postfix,
						// result);
					} else if (file2.getName().endsWith(postfix)) {
						result.put(new String("#file" + i++), file2.getName());
					}
				}
			}
		} else {
			result.put("error", "File forder is not exist");
		}
		System.out.println(result);
	}

	// @Test
	public void test() {
		Map<String, Object> r = new IdentityHashMap<>();
		getConfigurationFileList("src/test/resources/", "conf", r);
		System.out.println(r);
	}

}
