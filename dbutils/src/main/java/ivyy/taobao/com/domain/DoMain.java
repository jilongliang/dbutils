package ivyy.taobao.com.domain;

import ivyy.taobao.com.entity.User;
import ivyy.taobao.com.utils.DBHelper;
import ivyy.taobao.com.utils.DataUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

/**
 *@DEMO:dbutils
 *@Java：DoMain.java
 *@Date:2015-1-15下午9:20:32
 *@Author:liangjilong
 *@Email:jilongliang@sina.com
 *@Weibo:http://weibo.com/jilongliang
 *@Version:1.0
 *@Description：
 */
@SuppressWarnings("all")
public class DoMain {
 
	Connection  conn=null;
	
	/****
	 * 查询单条的是使用转换BeanHandler
	 */
	@Test
	public void testQueryById(){
		conn=DBHelper.getConnection();
		QueryRunner qr = new QueryRunner();
		String SQL="select * from user where id = ?";
		User u=null;
		try {
			u = (User) qr.query(conn, SQL, new BeanHandler(User.class),1);
			System.out.println(u.getId() + "\t" + u.getUsername() + "\t" + u.getCreateDate()+ "\t" + u.getModifyDate()); 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/****
	 * 查询所有的是使用转换BeanListHandler
	 */
	@Test
	public void testQueryAll(){
		conn=DBHelper.getConnection();
		QueryRunner qr = new QueryRunner();
		String SQL="select * from user";
		List<User> list=null;
		try {
			list = (List) qr.query(conn, SQL, new BeanListHandler(User.class));
			for(User u:list){
				System.out.println(u.getId() + "\t" + u.getUsername() + "\t" + u.getCreateDate()); 
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/***
	 * 通过map的key去查询
	 * 跟jdbc的 java.sql.ResultSet rs=null
	 * rs.getString("key");一样的结果
	 */
	@Test
    public void testQueryByMapKey() { 
		Integer id=1;//id=1查询
        conn = DBHelper.getConnection(); 
        QueryRunner qr = new QueryRunner(); 
        String date=DataUtil.getNowDate("yyyy-MM-dd HH:mm:ss");
        try { 
    	    String Sql="update user set modifyDate ='"+date+"',username ='xiaoliang' where id ="+id;
            //先将两个字段置为null 
            qr.update(conn, Sql); 
            Map<String, Object> map = qr.query(conn, "SELECT * FROM user where id = ?", new MapHandler(), id); 
            User u = new User(); 
            u.setId((Integer)map.get("id")); 
            u.setUsername(map.get("username").toString());  
            u.setModifyDate(map.get("modifyDate").toString());  
            System.out.println(u.getId() + "\t" + u.getUsername() + "\t" + u.getModifyDate());  
        } catch (SQLException e) { 
                e.printStackTrace(); 
        }finally{
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	} 
	
	/****
	 * 添加
	 */
	@Test
	public void testSave() {
		Long id = null;
		conn = DBHelper.getConnection(); 
		String date=DataUtil.getNowDate("yyyy-MM-dd HH:mm:ss");
		String  sql = "INSERT INTO user (id,username, createDate,modifyDate,type) ";
				sql+="VALUES (null, 'liangjilong', '"+date+"','"+date+"','insert')";
		QueryRunner qr = new QueryRunner();
		try {
			int res=qr.update(conn, sql);
			// 获取新增记录的自增主键
			id = (Long) qr.query(conn, "SELECT LAST_INSERT_ID()",new ScalarHandler(1));//获取最后一条id数据
			System.out.println("更新"+res+"条数据"+"更插入的Id为"+id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	} 
	
	/****
	 * 删除
	 */
	@Test
	public void testDelete() {
		Integer id=1;//拿到最后条Id就删除掉
		int res = -1;
		conn = DBHelper.getConnection();
		QueryRunner qr = new QueryRunner();
		try {
			//id=(Integer) qr.query(conn, "SELECT LAST_INSERT_ID()",new ScalarHandler(0));//获取最后一条id数据
			res = qr.update(conn, "DELETE FROM user WHERE id = ?", id);
			System.out.println("删除"+res+"条数据");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	} 
	/****
	 * 修改
	 */
	@Test
	public void testUpdate() { 
		Integer id=1;//id=1查询
        conn = DBHelper.getConnection(); 
        QueryRunner qr = new QueryRunner(); 
        String date=DataUtil.getNowDate("yyyy-MM-dd HH:mm:ss");
        try { 
    	    String Sql="update user set modifyDate ='"+date+"',username ='liangjilong' where id ="+id;
            //先将两个字段置为null 
            int result=qr.update(conn, Sql); 
            System.out.println("更新"+result+"条数据");
        } catch (SQLException e) { 
                e.printStackTrace(); 
        }finally{
			try {
				DbUtils.close(conn);
				//DbUtils.rollback(conn);//回滚
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	} 
	
}
