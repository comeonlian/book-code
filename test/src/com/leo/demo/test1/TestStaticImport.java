/**
 * 
 */
package com.leo.demo.test1;

import static com.leo.demo.test1.SysContants.*;
/**
 * Description: 
 * @author lianliang
 * @date 2018年3月27日 上午11:33:11
 */
public class TestStaticImport {
	
	boolean flag = false;
	
	String CERTIFICATION_TRACK_PARQUET = flag == true ? "Temp" : "Fail";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(ZM_TRACK);
		
		System.out.println(new TestStaticImport().CERTIFICATION_TRACK_PARQUET);
	}

}
