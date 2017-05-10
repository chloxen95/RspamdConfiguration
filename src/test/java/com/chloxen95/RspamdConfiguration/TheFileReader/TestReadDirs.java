package com.chloxen95.RspamdConfiguration.TheFileReader;

import java.io.File;

import org.junit.Test;

public class TestReadDirs {
	
	public void traverseFolder2(String path) {

        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getPath());
                        traverseFolder2(file2.getPath());
                    } else {
                        System.out.println("文件:" + file2.getPath());
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }

	@Test
	public void test() {
		traverseFolder2("src/test/resources/");
	}

}
