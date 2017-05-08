package com.chloxen95.RspamdConfiguration.TheFileReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

public class TestReadFiles {

	String txtName = "a.txt";
	String incName = "options.inc";
	String confName = "rspamd.conf";

	@Test
	public void testReadFile() throws IOException {
		// 文件路径，此处应用相对路径
		String path = "TestFile/" + confName;
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

}
