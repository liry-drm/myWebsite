package com.example.demo.common.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

/**
 * 配置文件读取工具类
 * 
 * @author liaoyin
 * 
 */
public class ConfigUtils {

	static Properties props = new Properties();

	static {
		try {
			InputStream inputStream = ConfigUtils.class.getClassLoader().getResourceAsStream("properties/config.properties");
			props.load(inputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		return props.getProperty(key);
	}
	
	
	/**
	 * 读取自定义配置文件
	 * @param filePath
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String readProperties(String filePath) {
		Properties tempprops = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(
					filePath));
			tempprops.load(in);
			Enumeration en = tempprops.propertyNames();
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				String property = tempprops.getProperty(key);
				return property;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
