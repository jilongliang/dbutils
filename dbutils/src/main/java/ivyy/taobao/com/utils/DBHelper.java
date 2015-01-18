package ivyy.taobao.com.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *@DEMO:dbutils
 *@Java：DBHelper.java
 *@Date:2015-1-15下午9:24:27
 *@Author:liangjilong
 *@Email:jilongliang@sina.com
 *@Weibo:http://weibo.com/jilongliang
 *@Version:1.0
 *@Description：
 */
public class DBHelper {
	
	private static DBHelper instance = null;
	private static Connection conn = null;
	private static String Driver = "com.mysql.jdbc.Driver";// ConfigLoaderUtils.getProperty("jdbc.driver");
	private static String Url = "jdbc:mysql://localhost:3306/test?characterEncoding=utf8";// ConfigLoaderUtils.getProperty("jdbc.url");
	private static String UserName = "root";// ConfigLoaderUtils.getProperty("jdbc.userName");
	private static String PassWord = "root";// ConfigLoaderUtils.getProperty("jdbc.passWord");
	
	/**建立单例模式
	 * Single
	 * @return
	 */
	public static DBHelper getInstance() {
		if (instance == null) {
			synchronized (DBHelper.class) {
				instance = new DBHelper();
			}
		}
		return instance;
	}
	/**
	 * @return
	 */
	public static Connection getConnection() {
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(Url, UserName, PassWord);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
