/**
 * 
 */
package com.leolian.transdata.shuyumem;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Description: 
 * @author lianliang
 * @date 2017年12月11日 下午6:51:36
 */
public class Transfer {
	
	private String sourcePath;
	private String targetPath;
	
	/**
	 * @param sourcePath
	 * @param targetPath
	 */
	public Transfer(String sourcePath, String targetPath) {
		this.sourcePath = sourcePath;
		this.targetPath = targetPath;
	}
	
	
	public void execute() throws Exception {
		ArrayBlockingQueue<Member> queue = new ArrayBlockingQueue<Member>(20000);
		Thread read = new Thread(new ReadTask(sourcePath, queue));
		read.start();
		Thread write = new Thread(new WriteTask(targetPath, queue));
		write.start();
	}
	
}
