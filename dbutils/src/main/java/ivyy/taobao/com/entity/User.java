package ivyy.taobao.com.entity;

import java.io.Serializable;

/**
 *@DEMO:dbutils
 *@Java£ºUser.java
 *@Date:2015-1-15ÏÂÎç9:21:34
 *@Author:liangjilong
 *@Email:jilongliang@sina.com
 *@Weibo:http://weibo.com/jilongliang
 *@Version:1.0
 *@Description£º
 */
public class User implements Serializable{
	 private Integer id;
	 
	 private String username;
	 private String password;
	 
	 private String createDate;
	 
	 private String modifyDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	 
	 
}
