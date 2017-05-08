package com.chloxen95.RspamdConfiguration.ConfReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.Test;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

/**
 * 弃用，读取不了.conf
 * @author chlox
 *
 */
@Deprecated
public class ConfJsonTest {

	@Test
	public void test() throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		JsonParser parse =new JsonParser();
		JsonObject json=(JsonObject) parse.parse(new FileReader("TestFile/rspamd.conf"));  //创建jsonObject对象
	}

}
