package com.arawn.blog.crawler.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 数据库工具类
 *
 * @author user
 */
public class JdbcUtil {

    /**
     * 获取数据库连接
     *
     * @return
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        Class.forName(PropertiesUtil.getValue("driver.name"));
        Connection con = DriverManager.getConnection(PropertiesUtil.getValue("db.url"), PropertiesUtil.getValue("db.username"), PropertiesUtil.getValue("db.password"));
        return con;
    }

    /**
     * 关闭资源
     *
     * @param connection
     * @param preparedStatement
     */
    public static void closeResource(Connection connection, PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}