package com.leo.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;

import org.junit.Test;


public class CharsetDemo {
	
	//查询Charset所支持的编码集
	@Test
	public void testCharsetDemo(){
		SortedMap<String, Charset> sm = Charset.availableCharsets();
		Set<Entry<String, Charset>> entrySet = sm.entrySet();
		for (Entry<String, Charset> entry : entrySet) {
			System.out.println(entry.getKey()+" === "+entry.getValue());
		}
	}
	
	//编码、解码操作
	@Test
	public void testCharset()throws Exception{
		Charset chset = Charset.forName("UTF-8");
		CharsetEncoder encoder = chset.newEncoder();
		CharsetDecoder decoder = chset.newDecoder();
		CharBuffer buf = CharBuffer.wrap("呵呵，是吗?");
		ByteBuffer byBuf = encoder.encode(buf);
		System.out.println(decoder.decode(byBuf));
	}
	
}
