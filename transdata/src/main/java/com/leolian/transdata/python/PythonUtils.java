/**
 * 
 */
package com.leolian.transdata.python;

import org.python.util.PythonInterpreter;

/**
 * Description: 
 * @author lianliang
 * @date 2017年12月20日 下午8:16:38
 */
public class PythonUtils {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PythonInterpreter interpreter = new PythonInterpreter();  
		interpreter.exec("days=('mod','Tue','Wed','Thu','Fri','Sat','Sun'); ");   ///执行python脚本
	}

}
