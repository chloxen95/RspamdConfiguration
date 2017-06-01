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

import org.junit.Test;

import com.chloxen95.RspamdConfiguration.Service.ConfigurationFileReader;

public class ConfigurationFileReaderImpl implements ConfigurationFileReader {

	@Override
	public List<String> getConfigurationFileLineList(String path, Boolean comment, Boolean blankLine) throws IOException {
		List<String> result = new ArrayList<>();
		// 文件路径，此处应用相对路径
		File file = new File(path);
		if (!file.exists() || file.isDirectory())
			throw new FileNotFoundException();
		BufferedReader br = new BufferedReader(new FileReader(file));
		for (String temp = br.readLine(); temp != null; temp = br.readLine()) {
			// 判断是否为注释或空行
			if (!comment && temp.startsWith("#"))
				continue;
			if (!blankLine && "".equals(temp))
				continue;
			result.add(temp);
		}
		br.close();
		return result;
	}

	@Override
	public String getConfigurationFileOneLine(String path) throws IOException {
		File file = new File(path);
		if (!file.exists() || file.isDirectory())
			throw new FileNotFoundException();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str = br.readLine();
		br.close();
		return str;
	}
	
	// @Test
	public void testOneLine() throws IOException{
		System.out.println(getConfigurationFileOneLine("src/test/resources/symbol/symbol.min.json"));
	}

}
