package com.leo.utils;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;


public class EncryptablePropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	
	private static final String key = "0001000100010001";

	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {
		try {
			String username = props.getProperty("jdbc.username");
			if (username != null) {
				props.setProperty("jdbc.username", Des.Decrypt(username, Des.hex2byte(key)));
			}
			
			String password = props.getProperty("jdbc.password");
			if (password != null) {
				props.setProperty("jdbc.password", Des.Decrypt(password, Des.hex2byte(key)));
			}
			
			String url = props.getProperty("jdbc.url");
			if (url != null) {
				props.setProperty("jdbc.url", Des.Decrypt(url, Des.hex2byte(key)));
			}
			
			String driverClassName = props.getProperty("jdbc.driver");
			if(driverClassName != null){
				props.setProperty("jdbc.driver", Des.Decrypt(driverClassName, Des.hex2byte(key)));
			}
			
			super.processProperties(beanFactory, props);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeanInitializationException(e.getMessage());
		}
	}
}