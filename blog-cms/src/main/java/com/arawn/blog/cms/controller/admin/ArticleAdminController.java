package com.arawn.blog.cms.controller.admin;

import com.arawn.blog.cms.entity.Article;
import com.arawn.blog.cms.entity.PageBean;
import com.arawn.blog.cms.service.ArticleService;
import com.arawn.blog.cms.service.impl.InitComponent;
import com.arawn.blog.cms.util.ResponseUtil;
import com.arawn.blog.cms.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ContextLoader;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文章后台Controller
 */
@Controller
@RequestMapping("/admin/article")
public class ArticleAdminController {

    @Resource
    private ArticleService articleService;

    @Resource
    private InitComponent initComponent;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping("/list")
    public String list(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, Article s_article, HttpServletResponse response) throws Exception {
        PageBean pageBean = new PageBean(page, rows);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        map.put("title", StringUtil.formatLike(s_article.getTitle()));
        List<Article> articleList = articleService.listForAdmin(map);
        Long total = articleService.countForAdmin(map);
        JSONObject result = new JSONObject();
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
        JSONArray jsonArray = JSONArray.fromObject(articleList, jsonConfig);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
        return null;
    }

    @RequestMapping("/save")
    public String save(Article article, HttpServletResponse response) throws Exception {
        int resultTotal = 0;
        if (article.getId() == null) {
            if (article.getState() == 1) {
                article.setReleaseDate(new Date());
            }
            resultTotal = articleService.insert(article);
        } else {
            if (article.getState() == 1) {
                article.setReleaseDate(new Date());
            } else {
                article.setReleaseDate(null);
            }
            resultTotal = articleService.update(article);
        }
        JSONObject result = new JSONObject();
        if (resultTotal > 0) {
            initComponent.refreshSystem(ContextLoader.getCurrentWebApplicationContext().getServletContext());
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        ResponseUtil.write(response, result);
        return null;
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam(value = "ids") String ids, HttpServletResponse response) throws Exception {
        String[] idsStr = ids.split(",");
        JSONObject result = new JSONObject();
        for (int i = 0; i < idsStr.length; i++) {
            articleService.delete(Integer.parseInt(idsStr[i]));
        }
        initComponent.refreshSystem(ContextLoader.getCurrentWebApplicationContext().getServletContext());
        result.put("success", true);
        ResponseUtil.write(response, result);
        return null;
    }

    @RequestMapping("/findById")
    public String findById(@RequestParam(value = "id") Integer id, HttpServletResponse response) throws Exception {
        Article article = articleService.getById(id);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
        JSONObject jsonObject = JSONObject.fromObject(article, jsonConfig);
        ResponseUtil.write(response, jsonObject);
        return null;
    }

}