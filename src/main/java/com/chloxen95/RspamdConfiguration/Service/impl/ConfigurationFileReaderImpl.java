package com.chloxen95.RspamdConfiguration.Service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import com.chloxen95.RspamdConfiguration.Service.ConfigurationFileReader;

public class ConfigurationFileReaderImpl implements ConfigurationFileReader {

	@Override
	public List<String> getConfigurationFile(String path, Boolean comment, Boolean blankLine) throws IOException {
		List<String> result = new ArrayList<>();
		// �ļ�·�����˴�Ӧ�����·��
		File file = new File(path);
		if (!file.exists() || file.isDirectory())
			throw new FileNotFoundException();
		BufferedReader br = new BufferedReader(new FileReader(file));
		for (String temp = br.readLine(); temp != null; temp = br.readLine()) {
			// �ж��Ƿ�Ϊע�ͻ����
			if (!comment && temp.startsWith("#"))
				continue;
			if (!blankLine && "".equals(temp))
				continue;
			result.add(temp);
		}
		br.close();
		return result;
	}

}
