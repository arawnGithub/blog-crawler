package com.arawn.blog.crawler.dao;

import com.arawn.blog.crawler.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * 文章Dao
 * Created by ArawN on 2018/3/14.
 */
public class ArticleDao {

    /**
     * 插入文章记录
     *
     * @param title
     * @param content
     * @param link
     * @return
     */
    public int insert(String title, String content, String link) {
        int result = 0;

        String sql = "insert into t_article(title,content,crawler_date,original_url) values(?,?,now(),?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, content);
            preparedStatement.setString(3, link);
            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            JdbcUtil.closeResource(connection, preparedStatement);
        }
        return result;
    }

}