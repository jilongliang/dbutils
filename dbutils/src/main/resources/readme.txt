/**
 *@DEMO:dbutils
 *@Java��DoMain.java
 *@Date:2015-1-15����9:20:32
 *@Author:liangjilong
 *@Email:jilongliang@sina.com
 *@Weibo:http://weibo.com/jilongliang
 *@Version:1.0
 *@Description��
 */	
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
1.      ����
	commons-dbutils��Apache��֯�ṩ��һ����Դ JDBC������⣬�������Ǹ��򵥵�ʹ��JDBC������һ���ǳ�С�������
	�������ӵ�ʱ�������������ʹ�á�
	
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
2.      ����
	����apache�ٷ���վ��commons-dbutils��ҳ��http://commons.apache.org/dbutils/
	ѡ����ߵ�������Download��������ҳ�棺
	Binary���Ǳ���õ�jar����source��Դ�����������һ��Binary�����ء��⿪������ͼ��ʾ��
	����commons-dbutils-1.1.jar���ǹ�ʹ�õ�jar����docs���������İ����ĵ��������ͨ��ʾ����������ʹ�á�
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
3.      API����
	commons-dbutils��һ���ǳ�С������� ���軨��̫��ʱ��ȥ�Ķ�����doc��
	����ֻ�������� 
	org.apache.commons.dbutils.DbUtils��
	org.apache.commons.dbutils.QueryRunner��һ���ӿ�org.apache.commons.dbutils.ResultSetHandler��
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

3.1.    DbUtils��
	DbUtils��һ��������һЩ��ر����ӡ�װ��JDBC��������֮��ĳ��湤���ṩ���÷������࣬���������еķ������Ǿ�̬�ġ�
	����������Ҫ�����У�
	public static void close(��) throws java.sql.SQLException����DbUtils���ṩ���������صĹرշ�����
	��Щ����������ṩ�Ĳ����ǲ���NULL��������ǵĻ������Ǿ͹ر�Connection��Statement��ResultSet��
	public static void closeQuietly(��): ��һ�෽����������Connection��Statement��ResultSetΪNULL����±���رգ�
	��������һЩ�ڳ������׳���SQLEeception������㲻�벶׽��Щ�쳣�Ļ���������Ƿǳ����õġ�������CloseQuietly����ʱ��
	�ر����õ�һ��������closeQuietly(Connection conn,Statement stmt,ResultSet rs)��������Ϊ�ڴ��������£����ӡ�����
	�ͽ������ResultSet������Ҫ�õ��������������������Ŀ������ر����ǡ�ʹ����һ�����������Ŀ�Ϳ���ֻ��Ҫ������һ�������ɡ�
	public static void CommitAndCloseQuietly(Connection conn)�� ��һ���������ύ���ӣ�Ȼ��ر����ӣ������ڹر�����ʱ������
	�׳��ڹر�ʱ������һЩSQL�쳣��
	public static boolean loadDriver(java.lang.String driverClassName)����һ����װ�ز�ע��JDBC������������ɹ��ͷ���true��
	ʹ�����ַ������㲻��Ҫȥ��׽����쳣ClassNotFoundException��

+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
3.2.    QueryRunner��
	�����򵥻���ִ��SQL��ѯ������ResultSetHandler�����һ��ʹ�þͿ�����ɴ󲿷ֵ����ݿ���������ܹ������������ı�������
	QueryRunner���ṩ���������췽����һ����Ĭ�ϵĹ��췽������һ����Ҫһ�� javax.sql.DataSource ����Ϊ��������ˣ����㲻��Ϊһ��
	�����ṩһ�����ݿ����ӵ�����£��ṩ����������DataSource)���������һ���µ����Ӳ�������������ȥ��
	������е���Ҫ��������������Щ��
	public Object query(Connection conn, String sql, ResultSetHandler rsh, Object[] params) throws SQLException��ִ��һ����
	ѯ�������������ѯ�У����������е�ÿ��Ԫ��ֵ��������Ϊ��ѯ�����û��������÷����������ڵش���PreparedStatement ��ResultSet �Ĵ�
	���͹رա�����Ҫ���ǲ���ResultSetHandler��Ѵ� ResultSet�л�õ�����ת����һ�������׵Ļ���Ӧ�ó����ض��ĸ�ʽ������ʹ�á�
	public Object query(String sql, ResultSetHandler rsh, Object[] params) throws SQLException:���⼸�����һ�ַ���һ����Ψһ��
	��ͬ�������������ݿ������ṩ���������������Ǵ��ṩ�����췽��������Դ(DataSource) ��ʹ�õ�setDataSource ���������»�õġ�
	public Object query(Connection conn, String sql, ResultSetHandler rsh) throws SQLException :��ִ��һ������Ҫ�û������Ĳ�ѯ������
	public int update(Connection conn, String sql, Object[] params) throws SQLException:����ִ��һ�����£����롢���»�ɾ����������
	public int update(Connection conn, String sql) throws SQLException������ִ��һ�����²���������Ҫ�û�������
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
3.3.    ResultSetHandler�ӿ�
	����������������ʾ�ģ���һ�ӿ�ִ�д���һ��jaca.sql.ResultSet�������ݰ�Ҫ��ת��Ϊ��һ����ʽ��
	ResultSetHandler�ӿ��ṩ��һ�������ķ�����Object handle (java.sql.ResultSet .rs)������κ�ResultSetHandler ��ִ����Ҫһ�������
	��ResultSet����Ϊ�������룬Ȼ����ܴ��������������ٷ���һ��������Ϊ����������java.lang.Object�����Գ��˲��ܷ���һ��ԭʼ��Java
	����֮�⣬�����ķ������Ͳ�û��ʲô���ơ���
	common-dbutils������������ӿ��ṩ�˾Ÿ�ʵ���ࣺ
	ArrayHandler���ѽ�����еĵ�һ������ת�ɶ������顣
	ArrayListHandler���ѽ�����е�ÿһ�����ݶ�ת��һ���������飬�ٴ�ŵ�List�С�
	BeanHandler����������еĵ�һ�����ݷ�װ��һ����Ӧ��JavaBeanʵ���С�
	BeanListHandler����������е�ÿһ�����ݶ���װ��һ����Ӧ��JavaBeanʵ���У���ŵ�List�
	ColumnListHandler�����������ĳһ�е����ݴ�ŵ�List�С�
	KeyedHandler����������е�ÿһ�����ݶ���װ��һ��Map�Ȼ���ٸ���ָ����key��ÿ��Map�ٴ�ŵ�һ��Map�
	MapHandler����������еĵ�һ�����ݷ�װ��һ��Map�key��������value���Ƕ�Ӧ��ֵ��
	MapListHandler����������е�ÿһ�����ݶ���װ��һ��Map�Ȼ���ٴ�ŵ�List
	ScalarHandler�����������ĳһ����¼������ĳһ�е����ݴ��Object
	�����Ÿ�ʵ������û���κ�һ���ṩ������Ҫ�ķ���������Լ�дһ��ʵ���ࡣ
		
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
3.4.    ������ͽӿ�
	org.apache.commons.dbutils.QueryLoader�ࣺ�����ļ�����������Ҫ���ڼ��������ļ��е�SQL���ڴ��С�
	org.apache.commons.dbutils.wrappers.SqlNullCheckedResultSet�ࣺ�������������sql���ִ�����֮��ĵ���ֵ����null���滻��
	org.apache.commons.dbutils.wrappers.StringTrimmedResultSet��: ��ȥ��ResultSet���ֶε����ҿո�
	org.apache.commons.dbutils.RowProcessor�ӿڣ������ṩ�˰ѽ������������ת����������ʽ�Ĺ��ܡ�
	����ʵ������org.apache.commons.dbutils.BasicRowProcessor�ࡣ
	
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

