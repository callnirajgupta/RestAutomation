package com.restful.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class RestfulUtil {
	private static final Logger LOGGER= LogManager.getLogger(RestfulUtil.class);
	private static Properties configProperties;
	private static final String configFile = System.getProperty("user.dir")+ "//src//test//resources//config//Config.properties";

	
	static {
		LOGGER.info("Inside static block");
		configProperties = new Properties();
		try {
			FileInputStream fisConfig = new FileInputStream(configFile);
			configProperties.load(fisConfig);
		} catch (Exception e) {
			LOGGER.info("!!!!!!!!!!!!!!!!!!!!Inside catch block" + e.getMessage());

		}
	}

	public static void cleanFolder(String folder) {
		LOGGER.info("delete log from logs folder");
		File file = new File(System.getProperty("user.dir") + "//" + folder + "//");
		for (File file1 : file.listFiles()) {

			file1.delete();
			LOGGER.debug("Logs deleted");
		}

	}
	
	public static Properties getConfigProperties() {
		LOGGER.info("Inside getConfigProperties Method");
		return configProperties;
	}
}
