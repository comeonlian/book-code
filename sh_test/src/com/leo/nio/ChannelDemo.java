package com.leo.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

public class ChannelDemo {
	//使用通道往外写
	@Test
	public void testChannel1() throws Exception{
		String[] info = {"Leo","呵呵","Lian","弄啥类"};
		File file = new File("F:"+File.separator+"channel.txt");
		FileOutputStream output = new FileOutputStream(file);
		FileChannel channel = output.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(1024);
		for (String s : info) {
			buf.put(s.getBytes());
		}
		buf.flip();
		channel.write(buf);
		channel.close();
		output.close();
	}
	//通道读写
	@Test
	public void testChannel2()throws Exception{
		File file1 = new File("F:"+File.separator+"in.txt");
		File file2 = new File("F:"+File.separator+"out.txt");
		FileInputStream fi = new FileInputStream(file1);
		FileOutputStream fo = new FileOutputStream(file2);
		FileChannel channel1 = fi.getChannel();
		FileChannel channel2 = fo.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(1024);
		int temp = 0;
		while((temp=channel1.read(buf))!=-1){
			buf.flip();
			channel2.write(buf);
			buf.clear();
		}
		channel1.close();
		channel2.close();
		fi.close();
		fo.close();
	}
	//文件内存映射
	@Test
	public void testMappedByteBuffer()throws Exception{
		File file = new File("F:"+File.separator+"map.txt");
		FileInputStream fi = new FileInputStream(file);
		FileChannel channel = fi.getChannel();
		MappedByteBuffer mbb = channel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
		byte[] data = new byte[(int) file.length()];
		int index = 0;
		while(mbb.hasRemaining()){
			data[index++] = mbb.get();
		}
		System.out.println(new String(data,"UTF-8"));
		channel.close();
		fi.close();
	}
	
}
