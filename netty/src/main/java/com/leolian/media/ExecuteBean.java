package com.leolian.media;

import java.lang.reflect.Method;

/**
 * 需要执行的bean，以及需要执行的方法
 * @author Lian
 */
public class ExecuteBean {
	private Object bean;
	private Method method;
	
	public ExecuteBean() {
	}

	public ExecuteBean(Object bean, Method method) {
		super();
		this.bean = bean;
		this.method = method;
	}
	
	public Object getBean() {
		return bean;
	}
	public void setBean(Object bean) {
		this.bean = bean;
	}
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	
}
