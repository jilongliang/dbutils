package ivyy.taobao.com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class ConfigLoaderUtils {

	protected static Properties pro = null;
	protected static InputStream is = null;
	private static File file = null;
	private static long fileLastModified = 0L;
	private static final String path="/prop/jdbc.properties";
	public static synchronized Properties getInstance() {
		if (pro == null) {
			pro = new Properties();
		}
		return pro;
	}
	/****************************************Properties*Begin******************************************/
	/**
	 * 初始�?
	 */
	private static void init() {
		URL url = ConfigLoaderUtils.class.getClassLoader().getResource(path);
		file = new File(url.getFile());
		fileLastModified = file.lastModified();
		pro = new Properties();
		load();
	}

	/**
	 * 加载
	 */
	private static void load() {
		try {
			pro.load(new FileInputStream(file));
			fileLastModified = file.lastModified();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取key
	 * 
	 * @param key
	 * @return
	 */
	public static String getProperty(String key) {
		if ((file == null) || (pro == null)) {
			init();
		}
		if (file.lastModified() > fileLastModified) {
			load();
		}
		return pro.getProperty(key);
	}

	/**
	 * @param key
	 * @return
	 */
	 
	/*****************************************End******************************************/

	 
}
