package com.leolian.media;

import java.lang.reflect.Method;
import java.util.Map;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
/**
 * 扫描包下面所有有Controller注解的类
 * 以及类中所有有Remote注解的方法
 * @author Lian
 */
@Component
public class InitialMedia implements ApplicationListener<ContextRefreshedEvent>,Ordered{
	
	/* ************************** spring 容器启动  ********************************* */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		//带有Controller注解的所有类
		Map<String, Object> beans = event.getApplicationContext().getBeansWithAnnotation(Controller.class);
		Object bean = null;
		Method[] methods = null;
		Remote remote = null;
		String cmd = "";
		for (Map.Entry<String, Object>  entry: beans.entrySet()) {
			bean = entry.getValue();
			methods = bean.getClass().getDeclaredMethods();
			if(null==methods || methods.length==0)
				continue;
			//查找有Remote注解的方法
			for(Method method: methods){
				if(method.isAnnotationPresent(Remote.class)){
					remote = method.getAnnotation(Remote.class);
					cmd = remote.value();
					ExecuteBean executeBean = new ExecuteBean();
					executeBean.setBean(bean);
					executeBean.setMethod(method);
					Media.beanMethods.put(cmd, executeBean);
					//System.out.println("cmd : "+cmd);
				}
			}
		}
	}
	
	@Override
	public int getOrder() {
		return Ordered.HIGHEST_PRECEDENCE;
	}
}
