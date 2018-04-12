package com.arawn.blog.crawler.main;

import com.arawn.blog.crawler.dao.ArticleDao;
import com.arawn.blog.crawler.util.CollectionUtil;
import com.arawn.blog.crawler.util.StringUtil;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Status;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CnBlogMain {

    private static Logger logger = LoggerFactory.getLogger(CnBlogMain.class);

    /**
     * 爬虫起始URL
     */
    private static final String URL = "http://www.cnblogs.com/";

    /**
     * cache缓存对象
     */
    private static Cache cache;

    /**
     * main方法入口
     * @param args
     */
    public static void main(String[] args) {
        start();
    }

    /**
     * 起始方法
     */
    public static void start() {
        parseHomePage();
    }

    /**
     * 解析主页
     */
    private static void parseHomePage() {
        while (true) {
            logger.info("开始爬取" + URL + "网页");
            CacheManager manager = CacheManager.create(CnBlogMain.class.getResourceAsStream("/ehcache.xml"));
            cache = manager.getCache("cnBlog");

            RequestConfig config = RequestConfig.custom()
                    .setSocketTimeout(100000) // 设置读取超时时间
                    .setConnectTimeout(5000)  // 设置连接超时时间
                    .build();
            CloseableHttpClient httpClient = HttpClients.custom()
                    .setDefaultRequestConfig(config)
                    .build(); // 获取HttpClient实例
            HttpGet httpGet = new HttpGet(URL); // 创建httpGet实例
            CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(httpGet);

                if (response != null) {
                    HttpEntity entity = response.getEntity(); // 获取返回实体
                    // 判断返回状态是否为200
                    if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                        String webPageContent = EntityUtils.toString(entity, "utf-8");
                        parseHomeWebPage(webPageContent);
                    } else {
                        logger.error(URL + "-返回状态非200");
                    }
                } else {
                    logger.error(URL + "-连接超时");
                }
            } catch (ClientProtocolException e) {
                logger.error(URL + "-ClientProtocolException", e);
            } catch (IOException e) {
                logger.error(URL + "-IOException", e);
            } finally {
                // 关闭资源
                closeResource(httpClient, response);
            }

            if (cache.getStatus() == Status.STATUS_ALIVE) {
                cache.flush(); // 把缓存写入文件
            }
            manager.shutdown();

            try {
                Thread.sleep(600000); // 每隔10分钟抓取一次网页数据
            } catch (InterruptedException e) {
                logger.error("InterruptedException", e);
            }
            logger.info("结束爬取" + URL + "网页");
        }
    }

    /**
     * 解析首页内容 提取博客link
     *
     * @param webPageContent
     */
    private static void parseHomeWebPage(String webPageContent) {
        if (StringUtil.isEmpty(webPageContent)) {
            return;
        }
        Document doc = Jsoup.parse(webPageContent);
        Elements links = doc.select("#post_list .post_item .post_item_body h3 a");
        if (CollectionUtil.isEmpty(links)) {
            return;
        }

        for (Element link : links) {
            String url = link.attr("href");
            if (cache.get(url) != null) { // 如果缓存中存在就不插入
                logger.info(url + "-缓存中存在");
                continue;
            }
            parseBlogLink(url);
        }
    }

    /**
     * 解析博客链接地址 获取博客内容
     *
     * @param link
     */
    private static void parseBlogLink(String link) {
        logger.info("开始爬取" + link + "网页");
        RequestConfig config = RequestConfig.custom()
                .setSocketTimeout(100000) // 设置读取超时时间
                .setConnectTimeout(5000)  // 设置连接超时时间
                .build();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(config)
                .build(); // 获取HttpClient实例
        HttpGet httpGet = new HttpGet(link); // 创建httpGet实例
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);

            if (response != null) {
                HttpEntity entity = response.getEntity(); // 获取返回实体
                // 判断返回状态是否为200
                if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                    String blogContent = EntityUtils.toString(entity, "utf-8");
                    parseBlogPage(blogContent, link);
                } else {
                    logger.error(URL + "-返回状态非200");
                }
            } else {
                logger.error(URL + "-连接超时");
            }
        } catch (ClientProtocolException e) {
            logger.error(URL + "-ClientProtocolException", e);
        } catch (IOException e) {
            logger.error(URL + "-IOException", e);
        } finally {
            // 关闭资源
            closeResource(httpClient, response);
        }

        logger.info("结束爬取" + link + "网页");
    }

    /**
     * 解析博客内容，提取有效信息
     *
     * @param blogContent
     * @param link
     */
    private static void parseBlogPage(String blogContent, String link) {
        if (StringUtil.isEmpty(blogContent)) {
            return;
        }
        Document doc = Jsoup.parse(blogContent);
        Elements titleElements = doc.select("#cb_post_title_url"); // 获取博客标题
        if (CollectionUtil.isEmpty(titleElements)) {
            logger.error(link + "-未获取到博客标题");
            return;
        }
        String title = titleElements.get(0).text();
        System.out.println("博客标题：" + title);

        Elements contentElements = doc.select("#cnblogs_post_body"); // 获取博客内容
        if (CollectionUtil.isEmpty(contentElements)) {
            logger.error(link + "-未获取到博客内容");
            return;
        }
        String content = contentElements.get(0).html();
        System.out.println("博客内容：" + content);

        // 插入数据库
        ArticleDao articleDao = new ArticleDao();
        int result = articleDao.insert(title, content, link);

        if (result > 0) {
            logger.info(link + "-成功插入数据库");
            cache.put(new net.sf.ehcache.Element(link, link));
            logger.info(link + "-已加入缓存");
        } else {
            logger.info(link + "-插入数据库失败");
        }
    }

    /**
     * 关闭资源
     *
     * @param httpClient
     * @param response
     */
    private static void closeResource(CloseableHttpClient httpClient, CloseableHttpResponse response) {
        if (response != null) {
            try {
                response.close();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }

        if (httpClient != null) {
            try {
                httpClient.close();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }

}