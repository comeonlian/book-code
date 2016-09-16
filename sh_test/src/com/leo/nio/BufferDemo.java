package com.leo.nio;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.junit.Test;

public class BufferDemo {

	@Test
	public void testIntBuffer1(){
		IntBuffer buf = IntBuffer.allocate(10);
		System.out.println("1、写入数据之前的position、limit、capacity：");
		System.out.println("position="+buf.position()+", limit="+buf.limit()+", capacity="+buf.capacity());
		buf.put(3);
		int[] temp = {5,2,9,1};
		buf.put(temp);
		System.out.println("2、写入数据之后的position、limit、capacity：");
		System.out.println("position="+buf.position()+", limit="+buf.limit()+", capacity="+buf.capacity());
		buf.flip();
		System.out.println("3、准备输出数据之前的position、limit、capacity：");
		System.out.println("position="+buf.position()+", limit="+buf.limit()+", capacity="+buf.capacity());
		
		System.out.println("4、缓冲区的内容是：");
		while(buf.hasRemaining()){
			System.out.print(buf.get()+"、");
		}
	}
	// 创建子缓冲区
	@Test
	public void testIntBuffer2(){
		IntBuffer buf = IntBuffer.allocate(10);
		IntBuffer sub = null;
		for(int i=0; i<10; i++){
			buf.put(2*i+1);
		}
		//创建子缓冲区
		buf.position(2);
		buf.limit(6);
		sub = buf.slice();
		for(int i=0; i<sub.capacity(); i++){
			int tmp = sub.get(i);
			sub.put(tmp-1);
		}
		
		buf.flip();
		buf.limit(buf.capacity());
		System.out.println("主缓冲区中的内容是：");
		while(buf.hasRemaining()){
			System.out.print(buf.get()+"、");
		}
	}
	// 创建只读缓冲区
	@Test
	public void testIntBuffer3(){
		IntBuffer buf = IntBuffer.allocate(10);
		IntBuffer read = null;
		for(int i=0; i<10; i++){
			buf.put(2*i+1);
		}
		read = buf.asReadOnlyBuffer();
		read.flip();
		System.out.println("主缓冲区中的内容：");
		while(read.hasRemaining()){
			System.out.print(read.get()+"、");
		}
		read.put(23);
	}
	//创建直接缓冲区
	@Test
	public void testByteBuffer(){
		ByteBuffer buf = ByteBuffer.allocateDirect(10);
		byte[] tmp = {5,1,3};
		buf.put(tmp);
		buf.flip();
		System.out.println("直接缓冲区中的内容：");
		while(buf.hasRemaining()){
			System.out.print(buf.get()+"、");
		}
	}
	
	
}
