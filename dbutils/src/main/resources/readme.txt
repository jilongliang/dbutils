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
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
1.      介绍
	commons-dbutils是Apache组织提供的一个开源 JDBC工具类库，能让我们更简单的使用JDBC。它是一个非常小的类包，
	花几分钟的时间就能掌握它的使用。
	
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
2.      下载
	进入apache官方网站的commons-dbutils网页：http://commons.apache.org/dbutils/
	选择左边导航栏的Download进入下载页面：
	Binary：是编译好的jar包；source是源代码包。下载一份Binary到本地。解开后如下图所示：
	其中commons-dbutils-1.1.jar就是供使用的jar包；docs是这个组件的帮助文档。下面就通过示例介绍它的使用。
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
3.      API介绍
	commons-dbutils是一个非常小的类包， 无需花费太多时间去阅读它的doc，
	核心只是两个类 
	org.apache.commons.dbutils.DbUtils、
	org.apache.commons.dbutils.QueryRunner和一个接口org.apache.commons.dbutils.ResultSetHandler。
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

3.1.    DbUtils类
	DbUtils是一个用来做一些如关闭连接、装载JDBC驱动程序之类的常规工作提供有用方法的类，它里面所有的方法都是静态的。
	这个类里的重要方法有：
	public static void close(…) throws java.sql.SQLException：　DbUtils类提供了三个重载的关闭方法。
	这些方法检查所提供的参数是不是NULL，如果不是的话，它们就关闭Connection、Statement和ResultSet。
	public static void closeQuietly(…): 这一类方法不仅能在Connection、Statement和ResultSet为NULL情况下避免关闭，
	还能隐藏一些在程序中抛出的SQLEeception。如果你不想捕捉这些异常的话，这对你是非常有用的。在重载CloseQuietly方法时，
	特别有用的一个方法是closeQuietly(Connection conn,Statement stmt,ResultSet rs)，这是因为在大多数情况下，连接、声明
	和结果集（ResultSet）是你要用的三样东西，而且在最后的块你必须关闭它们。使用这一方法，你最后的块就可以只需要调用这一方法即可。
	public static void CommitAndCloseQuietly(Connection conn)： 这一方法用来提交连接，然后关闭连接，并且在关闭连接时不向上
	抛出在关闭时发生的一些SQL异常。
	public static boolean loadDriver(java.lang.String driverClassName)：这一方法装载并注册JDBC驱动程序，如果成功就返回true。
	使用这种方法，你不需要去捕捉这个异常ClassNotFoundException。

+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
3.2.    QueryRunner类
	这个类简单化了执行SQL查询，它与ResultSetHandler组合在一起使用就可能完成大部分的数据库操作，它能够大大减少你所的编码量。
	QueryRunner类提供了两个构造方法：一个是默认的构造方法；另一个需要一个 javax.sql.DataSource 来作为参数。因此，在你不用为一个
	方法提供一个数据库连接的情况下，提供给构造器的DataSource)被用来获得一个新的连接并将继续进行下去。
	这个类中的重要方法包括以下这些：
	public Object query(Connection conn, String sql, ResultSetHandler rsh, Object[] params) throws SQLException：执行一个查
	询操作，在这个查询中，对象数组中的每个元素值被用来作为查询语句的置换参数。该方法它会内在地处理PreparedStatement 和ResultSet 的创
	建和关闭。最重要的是参数ResultSetHandler会把从 ResultSet中获得的数据转换成一个更容易的或是应用程序特定的格式供我们使用。
	public Object query(String sql, ResultSetHandler rsh, Object[] params) throws SQLException:　这几乎与第一种方法一样；唯一的
	不同在于它不将数据库连接提供给方法，并且它是从提供给构造方法的数据源(DataSource) 或使用的setDataSource 方法中重新获得的。
	public Object query(Connection conn, String sql, ResultSetHandler rsh) throws SQLException :　执行一个不需要置换参数的查询操作。
	public int update(Connection conn, String sql, Object[] params) throws SQLException:用来执行一个更新（插入、更新或删除）操作。
	public int update(Connection conn, String sql) throws SQLException：用来执行一个更新操作，不需要置换参数。
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
3.3.    ResultSetHandler接口
	正如它的名字所提示的，这一接口执行处理一个jaca.sql.ResultSet，将数据按要求转换为另一种形式。
	ResultSetHandler接口提供了一个单独的方法：Object handle (java.sql.ResultSet .rs)。因此任何ResultSetHandler 的执行需要一个结果集
	（ResultSet）作为参数传入，然后才能处理这个结果集，再返回一个对象。因为返回类型是java.lang.Object，所以除了不能返回一个原始的Java
	类型之外，其它的返回类型并没有什么限制。。
	common-dbutils组件包针对这个接口提供了九个实现类：
	ArrayHandler：把结果集中的第一行数据转成对象数组。
	ArrayListHandler：把结果集中的每一行数据都转成一个对象数组，再存放到List中。
	BeanHandler：将结果集中的第一行数据封装到一个对应的JavaBean实例中。
	BeanListHandler：将结果集中的每一行数据都封装到一个对应的JavaBean实例中，存放到List里。
	ColumnListHandler：将结果集中某一列的数据存放到List中。
	KeyedHandler：将结果集中的每一行数据都封装到一个Map里，然后再根据指定的key把每个Map再存放到一个Map里。
	MapHandler：将结果集中的第一行数据封装到一个Map里，key是列名，value就是对应的值。
	MapListHandler：将结果集中的每一行数据都封装到一个Map里，然后再存放到List
	ScalarHandler：将结果集中某一条记录的其中某一列的数据存成Object
	如果这九个实现类中没有任何一个提供了你想要的服务，你可以自己写一个实现类。
		
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
3.4.    其它类和接口
	org.apache.commons.dbutils.QueryLoader类：属性文件加载器，主要用于加载属性文件中的SQL到内存中。
	org.apache.commons.dbutils.wrappers.SqlNullCheckedResultSet类：这个类是用来对sql语句执行完成之后的的数值进行null的替换。
	org.apache.commons.dbutils.wrappers.StringTrimmedResultSet类: 　去除ResultSet中字段的左右空格。
	org.apache.commons.dbutils.RowProcessor接口：　它提供了把结果集的行数据转换成其它格式的功能。
	它的实现类是org.apache.commons.dbutils.BasicRowProcessor类。
	
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

