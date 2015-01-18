package ivyy.taobao.com.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *@DEMO:dbutils
 *@Java£ºDataUtil.java
 *@Date:2015-1-15ÏÂÎç10:06:03
 *@Author:liangjilong
 *@Email:jilongliang@sina.com
 *@Weibo:http://weibo.com/jilongliang
 *@Version:1.0
 *@Description£º
 */
public class DataUtil {


	/***
	 * ¸ñÊ½£¨"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss" £©
	 * @param fmt
	 * @return
	 */
	public static String getNowDate(String fmt){
		DateFormat df=new SimpleDateFormat(fmt);
		//String currentTime=df.format(new Date());
		Calendar calendar = Calendar.getInstance();
		
		return df.format(calendar.getTime());
		
	}
	
}
