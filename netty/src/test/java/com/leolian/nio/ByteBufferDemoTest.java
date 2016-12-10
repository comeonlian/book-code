package com.leolian.nio;

import org.junit.Test;

/**
 * 
 * @author Lian
 *
 */
public class ByteBufferDemoTest {
	
	@Test
	public void testByteBufferDemo()throws Exception {
		String fileName = "file/nio.txt";
		ByteBufferDemo.readFileByByteBuffer(fileName);
	}
	
}
