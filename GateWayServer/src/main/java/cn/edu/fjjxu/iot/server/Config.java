package cn.edu.fjjxu.iot.server;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class Config {
	private static Properties properties;
	private static final Logger logger = Logger
            .getLogger(Config.class.getName());
	// private static Logger logger = Logger.getLogger(Config.class);
	static {
		try {
			// PropertyConfigurator.configure(System.getProperty("user.dir")
			// + "/config/log4j.properties");
			properties = new Properties();
			// 读取SRC下配置文件 --- 属于读取内部文件
			// properties.load(Config.class.getResourceAsStream("/init.properties"));
			// 读取系统外配置文件 (即Jar包外文件) --- 外部工程引用该Jar包时需要在工程下创建config目录存放配置文件
			String filePath = System.getProperty("user.dir") + "/server.properties";
			logger.info(System.getProperty("user.dir"));
			InputStream in = new BufferedInputStream(new FileInputStream(filePath));
			properties.load(in);
		} catch (IOException e) {
			logger.info("读取配置信息出错！");
			
		}
	}

	public static String getObject(String prepKey) {
		return properties.getProperty(prepKey);
	}

	public static void main(String[] agrs) {
		logger.info(Config.getObject("jdbc.url"));
	}
}
