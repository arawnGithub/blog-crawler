package com.arawn.blog.crawler.main;

import com.arawn.blog.crawler.util.DateUtil;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * 下载图片测试类
 */
public class DownLoadImageMainTest {

    private static Logger logger = Logger.getLogger(DownLoadImageMainTest.class);

    private static final String link = "https://images2018.cnblogs.com/blog/1347060/201804/1347060-20180410214304390-1108858918.png";

    public static void main(String[] args) {
        logger.info("开始爬取" + link + "图片");

        RequestConfig config = RequestConfig.custom()
                .setSocketTimeout(10000) // 设置读取超时时间
                .setConnectTimeout(5000) // 设置连接超时时间
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
                    InputStream inputStream = entity.getContent();
                    String imageType = entity.getContentType().getValue();
                    String urlB = imageType.split("/")[1];
                    String uuid = UUID.randomUUID().toString();
                    FileUtils.copyToFile(inputStream, new File(DateUtil.getCurrentDatePath() + "/" + uuid + "." + urlB));
                } else {
                    logger.error("返回状态非200");
                }
            } else {
                logger.error("连接超时");
            }
        } catch (ClientProtocolException e) {
            logger.error("ClientProtocolException", e);
        } catch (IOException e) {
            logger.error("IOException", e);
        } finally {
            // 关闭资源
            closeResource(httpClient, response);
        }

        logger.info("结束爬取" + link + "图片");
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
