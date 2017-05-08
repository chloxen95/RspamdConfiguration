package com.chloxen95.RspamdConfiguration.IncReader;

import org.junit.Test;

public class ConfigureReaderSpeedTest {

	@Test
	public void ListSpeedTest() {
		IncIteratorTest iit = new IncIteratorTest();
		IncLongStringTest ilst = new IncLongStringTest();
		long iitTime = System.currentTimeMillis();
		for(int i = 0;i<100;i++)
			iit.testReadList();
		iitTime = System.currentTimeMillis() - iitTime;
		
		long ilstTime = System.currentTimeMillis();
		for(int i = 0;i<100;i++)
			ilst.testReadList();
		ilstTime = System.currentTimeMillis() - ilstTime;
		
		System.out.println("Iterator time: " + iitTime);
		System.out.println("LongString Time: " + ilstTime);
		
	}

}
