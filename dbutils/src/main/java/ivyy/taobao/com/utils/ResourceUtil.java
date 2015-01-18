package ivyy.taobao.com.utils;

import java.util.ResourceBundle;

/**
 *@Author:liangjilong
 *@Date:2014-12-21
 *@Email:jilongliang@sina.com
 *@Version:1.0
 *@Description
 */
public class ResourceUtil {

	private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("prop/jdbc");
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public static final String getConfigByKey(String name) {
		return bundle.getString(name);
	}
	
	public static void main(String[] args) {
		String UserName=getConfigByKey("Driver");
		System.out.println(UserName);
	}
	

}
