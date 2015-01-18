package ivyy.taobao.com.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *@Author:liangjilong
 *@Date:2014-12-7
 *@Email:jilongliang@sina.com
 */
public class ReleaseUtils {
	private static Connection conn = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet rs = null;
	/***
	 * �ͷ���Դ...
	 * 
	 * @param rs
	 * @param st
	 * @param conn
	 */
	public static void releaseAll(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * �ͷ���������
	 */
	public static void releaseAll() {
		if (rs != null) {
			try {
				rs=null;
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (preparedStatement != null) {
			try {
				preparedStatement=null;
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn=null;
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
     * �ͷ���Դ
     */
    public static void free() {
        free(null);
    }
    /**
     * �ͷ���Դ
     * @param rs
     *            �����
     */
    public static void free(ResultSet rs) {
    	free(conn, preparedStatement, rs);
    }
    /**
     * �ͷ�����
     * @param conn
     */
    private static void freeConnection(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    /**
     * �ͷ�statement
     * @param statement
     */
    private static void freeStatement(Statement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    /**
     * �ͷ�resultset
     * @param rs
     */
    private static void freeResultSet(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    /**
     * �ͷ���Դ
     * @param conn
     * @param statement
     * @param rs
     */
    public static void free(Connection conn, Statement statement, ResultSet rs) {
        if (rs != null) {
            freeResultSet(rs);
        }
        if (statement != null) {
            freeStatement(statement);
        }
        if (conn != null) {
            freeConnection(conn);
        }
    }

    /**
     * �ͷ���Դ
     * @param statement
     * @param rs
     */
    public static void free(Statement statement, ResultSet rs) {
    	free(conn, statement, rs);
    }

}
