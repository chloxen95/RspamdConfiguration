package com.chloxen95.RspamdConfiguration.TheFileReader;

import java.io.File;

import org.junit.Test;

public class TestReadDirs {
	
	public void traverseFolder2(String path) {

        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("�ļ����ǿյ�!");
                return;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("�ļ���:" + file2.getPath());
                        traverseFolder2(file2.getPath());
                    } else {
                        System.out.println("�ļ�:" + file2.getPath());
                    }
                }
            }
        } else {
            System.out.println("�ļ�������!");
        }
    }

	@Test
	public void test() {
		traverseFolder2("src/test/resources/");
	}

}
