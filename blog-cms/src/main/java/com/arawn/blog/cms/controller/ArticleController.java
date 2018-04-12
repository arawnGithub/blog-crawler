package com.arawn.blog.cms.controller;

import com.arawn.blog.cms.entity.Article;
import com.arawn.blog.cms.service.ArticleService;
import com.arawn.blog.cms.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 文章Controller
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @RequestMapping("/{id}")
    public ModelAndView details(@PathVariable("id") Integer id, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        Article article = articleService.getById(id);
        article.setClickHit(article.getClickHit() + 1);
        articleService.update(article);
        String tags = article.getTags();
        if (StringUtil.isNotEmpty(tags)) {
            String arr[] = tags.split(" ");
            mav.addObject("tags", StringUtil.filterWhite(Arrays.asList(arr)));
        } else {
            mav.addObject("tags", tags);
        }
        mav.addObject("article", article);
        mav.addObject("pageTitle", article.getTitle());
        mav.addObject("pageCode", this.getUpAndDownPageCode(articleService.getLastArticle(id), articleService.getNextArticle(id), request.getServletContext().getContextPath()));
        mav.addObject("mainPage", "foreground/article/view.jsp");
        mav.setViewName("mainTemp");
        return mav;
    }

    /**
     * 获取上一篇和下一篇文章
     * @param lastArticle
     * @param netArticle
     * @param projectContext
     * @return
     */
    private String getUpAndDownPageCode(Article lastArticle, Article netArticle, String projectContext) {
        StringBuilder pageCode = new StringBuilder();
        if (lastArticle == null) {
            pageCode.append("<p>上一篇：没有了</p>");
        } else {
            pageCode.append("<p>上一篇：<a href='" + projectContext + "/article/" + lastArticle.getId() + ".html'>" + lastArticle.getTitle() + "</a></p>");
        }
        if (netArticle == null) {
            pageCode.append("<p>下一篇：没有了</p>");
        } else {
            pageCode.append("<p>下一篇：<a href='" + projectContext + "/article/" + netArticle.getId() + ".html'>" + netArticle.getTitle() + "</a></p>");
        }
        return pageCode.toString();
    }

}