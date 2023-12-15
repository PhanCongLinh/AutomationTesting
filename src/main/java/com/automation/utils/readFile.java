package com.automation.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class readFile {

	private static String CONFIG_PATH = "E:/Eclipse_workspace/ASM2/configuration/configs.properties";

	public static String getProperty(String key) {
		Properties properties = new Properties();
		String value = null;
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(CONFIG_PATH);
			properties.load(fileInputStream);
			value = properties.getProperty(key);
			return value;
		} catch (Exception ex) {
			System.out.println("Xảy ra lỗi khi đọc giá trị của " + key);
			ex.printStackTrace();
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}

	public static void setProperty(String key, String value) {
		Properties properties = new Properties();
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(CONFIG_PATH);
			properties.setProperty(key, value);
			properties.store(fileOutputStream, "Set new value in properties");
			System.out.println("Set new value in file properties success.");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
