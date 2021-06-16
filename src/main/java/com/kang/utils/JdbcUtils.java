package com.kang.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Kangshitao
 * @date 2021年6月11日 下午2:51
 */
public class JdbcUtils {

    private static DruidDataSource dataSource;

    private static ThreadLocal<Connection> conns = new ThreadLocal<>();

    static { //连接池只需要一个，因此写在静态代码块中
        try {
            Properties properties = new Properties();
            //读取配置文件
            InputStream resourceAsStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //从流中加载数据
            properties.load(resourceAsStream);
            //创建数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取数据库连接
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = conns.get();
        if (conn == null) { //如果当前线程没有conn对象，则从连接池中获取一个
            try {
                conn = dataSource.getConnection();
                conns.set(conn);  //将当前连接对象放到ThreadLocal对象中，确保一个线程只使用这一个conn对象
                conn.setAutoCommit(false);  //取消自动提交（开始事务）
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 提交事务并关闭连接
     */
    public static void commitAndClose(){
        Connection connection = conns.get();
        if(connection != null){ //如果不为空，说明之前使用过连接，操作过数据库
            try {
                connection.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally{
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        //用完之后，一定要执行ThreadLocal对象的remove操作
        conns.remove();
    }

    /**
     * 回滚事务，并关闭释放连接
     */
    public static void rollbackAndClose(){
        Connection connection = conns.get();
        if(connection != null){ //如果不为空，说明之前使用过连接，操作过数据库
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally{
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        //用完之后，一定要执行ThreadLocal对象的remove操作
        conns.remove();
    }



//    /**
//     * 关闭数据库连接
//     *
//     * @param connection
//     */
//    public static void close(Connection connection) {
//        if (connection != null) {
//            try {
//                connection.close();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//    }
}
