package com.arawn.blog.cms.service.impl;

import com.arawn.blog.cms.dao.ArticleDao;
import com.arawn.blog.cms.entity.Article;
import com.arawn.blog.cms.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 文章Service实现类
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleDao articleDao;

    public Integer countByTypeId(Integer typeId) {
        return articleDao.countByTypeId(typeId);
    }

    public List<Article> listForAdmin(Map<String, Object> map) {
        return articleDao.listForAdmin(map);
    }

    public Long countForAdmin(Map<String, Object> map) {
        return articleDao.countForAdmin(map);
    }

    public List<Article> listHot() {
        return articleDao.listHot();
    }

    public int insert(Article article) {
        return articleDao.insert(article);
    }

    public int delete(Integer id) {
        return articleDao.delete(id);
    }

    public int update(Article article) {
        return articleDao.update(article);
    }

    public Article getById(Integer id) {
        return articleDao.getById(id);
    }

    public List<Article> list(Map<String, Object> map) {
        return articleDao.list(map);
    }

    public Long count(Map<String, Object> map) {
        return articleDao.count(map);
    }

    public Article getLastArticle(Integer id) {
        return articleDao.getLastArticle(id);
    }

    public Article getNextArticle(Integer id) {
        return articleDao.getNextArticle(id);
    }

}
