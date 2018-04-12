package com.arawn.blog.cms.service.impl;

import com.arawn.blog.cms.entity.ArcType;
import com.arawn.blog.cms.entity.Article;
import com.arawn.blog.cms.entity.Link;
import com.arawn.blog.cms.service.ArcTypeService;
import com.arawn.blog.cms.service.ArticleService;
import com.arawn.blog.cms.service.LinkService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * 初始化组件
 */
@Component("initComponent")
public class InitComponent implements ServletContextListener, ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public void refreshSystem(ServletContext application) {
        ArcTypeService arcTypeService = (ArcTypeService) applicationContext.getBean("arcTypeService");
        List<ArcType> arcTypeList = arcTypeService.list(null);
        application.setAttribute("arcTypeList", arcTypeList);

        LinkService linkService = (LinkService) applicationContext.getBean("linkService");
        List<Link> linkList = linkService.list(null);
        application.setAttribute("linkList", linkList);

        ArticleService articleService = (ArticleService) applicationContext.getBean("articleService");
        List<Article> hotArticleList = articleService.listHot();
        application.setAttribute("hotArticleList", hotArticleList);
    }

    public void contextInitialized(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();
        refreshSystem(application);
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
