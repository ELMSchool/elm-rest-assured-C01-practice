package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

	private static Properties properties;

	static {

		try {
			String path = "configuration.properties";
			FileInputStream file = new FileInputStream(path);

			properties = new Properties();
			properties.load(file);

			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getPropertyValue(String key) {
		// TODO Auto-generated method stub
		return properties.getProperty(key);
	}
}
