package com.arawn.blog.cms.dao;

import com.arawn.blog.cms.entity.Article;

import java.util.List;
import java.util.Map;

/**
 * 文章Dao接口
 */
public interface ArticleDao {

    /**
     * 根据文章类别ID获取记录数
     * @param typeId
     * @return
     */
    Integer countByTypeId(Integer typeId);

    /**
     * 根据条件获取文章列表（后台使用）
     * @param map
     * @return
     */
    List<Article> listForAdmin(Map<String, Object> map);

    /**
     * 根据条件获取总记录数（后台使用）
     * @param map
     * @return
     */
    Long countForAdmin(Map<String, Object> map);

    /**
     * 根据条件获取文章列表（前台使用）
     * @param map
     * @return
     */
    List<Article> list(Map<String, Object> map);

    /**
     * 根据条件获取总记录数（前台使用）
     * @param map
     * @return
     */
    Long count(Map<String, Object> map);

    /**
     * 获取热门文章列表
     * @return
     */
    List<Article> listHot();

    /**
     * 添加文章
     * @param article
     * @return
     */
    int insert(Article article);

    /**
     * 修改文章
     * @param article
     * @return
     */
    int update(Article article);

    /**
     * 根据ID删除文章
     * @param id
     * @return
     */
    int delete(Integer id);

    /**
     * 根据ID获取文章
     * @param id
     * @return
     */
    Article getById(Integer id);

    /**
     * 获取上一篇文章
     * @param id
     * @return
     */
    Article getLastArticle(Integer id);

    /**
     * 获取下一篇文章
     * @param id
     * @return
     */
    Article getNextArticle(Integer id);
}
