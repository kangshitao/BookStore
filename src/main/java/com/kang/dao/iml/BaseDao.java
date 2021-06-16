package com.kang.dao.iml;

import com.kang.bean.Book;
import com.kang.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Kangshitao
 * @date 2021年6月11日 下午4:55
 */
public abstract class BaseDao {
    //使用dbUtils操作数据库
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * 用来执行insert/update/delete操作
     *
     * @return 如果返回-1，说明执行失败；返回其他表示影响的行数
     */
    public int update(String sql, Object... args) {
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.update(connection, sql,args);
        } catch (SQLException throwables) {
            throw new RuntimeException();  //一定要将异常向上抛，确保所有的操作都在同一个连接里
        }
    }

    /**
     * 查询返回一条javaBean的sql语句
     * @param type  返回的对象类型
     * @param sql 执行的sql语句
     * @param args sql对应的参数值
     * @param <T> 返回的类型的泛型
     * @return
     */
    public <T> T queryForOne(Class<T> type, String sql, Object... args){
        Connection connection = JdbcUtils.getConnection();
        try {
            //BeanHandler是ResultSetHandler的一个实现类，将结果集中的第一行数据封装到对应的JavaBean实例中
            return queryRunner.query(connection, sql, new BeanHandler<T>(type), args);
        } catch (SQLException throwables) {
            throw new RuntimeException();
        }
    }

    /**
     * 查询返回多条javaBean的sql语句
     * @param type
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    public <T> List<T> queryForList(Class<T> type, String sql,Object... args){
        Connection connection = JdbcUtils.getConnection();
        try {
            List<T> query = queryRunner.query(connection, sql, new BeanListHandler<T>(type), args);
            return query;
        } catch (SQLException throwables) {
            throw new RuntimeException();
        }
    }

    /**
     * 查询返回单个值的sql语句
     * @param sql 执行的sql语句
     * @param args sql对应的参数值
     * @return
     */
    public Object queryForSingleValue(String sql,Object...args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection,sql,new ScalarHandler(),args);
        } catch (SQLException throwables) {
            throw new RuntimeException();
        }
    }
}
