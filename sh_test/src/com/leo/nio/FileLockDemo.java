package com.leo.nio;

import java.io.File;
import java.io.FileInputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

import org.junit.Test;

public class FileLockDemo {
	
	//读取文件时，获取文件的共享共享锁定
	@Test
	public void testFileLockDemo()throws Exception{
		File file = new File("F:"+File.separator+"filelock.txt");
		FileInputStream fi = new FileInputStream(file);
		FileChannel channel = fi.getChannel();
		FileLock lock = channel.tryLock(0, Integer.MAX_VALUE, true);
		if(lock!=null){
			System.out.println("文件锁定120秒");
			Thread.sleep(120000);
			lock.release();
			System.out.println("文件解除锁定");
		}
		channel.close();
		fi.close();
	}
	
	
}
