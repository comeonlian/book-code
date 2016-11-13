package com.leolian.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Lian
 */
public class ByteBufferDemo {
	/**
	 * 通过读取文件内容，写到ByteBuffer里面,再从ByteBuffer里面获取数据，打印到控制台
	 * @param fileName
	 */
	public static void readFileByByteBuffer(String fileName)throws Exception{
		RandomAccessFile rac = new RandomAccessFile(fileName, "rw");
		// 获取文件的通道
		FileChannel fileChannel = rac.getChannel();
		//创建缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(30);
		//由通道往缓冲区里面写数据
		int size = fileChannel.read(buffer);
		while(size>0){
			//byte[] bytes = new byte[5];
			buffer.flip();
			//buffer.get(bytes);
			System.out.println(new String(buffer.array()));
			buffer.clear();
			buffer.flip();
			size = fileChannel.read(buffer);
		}
		fileChannel.close();
		rac.close();
	}
	
}
