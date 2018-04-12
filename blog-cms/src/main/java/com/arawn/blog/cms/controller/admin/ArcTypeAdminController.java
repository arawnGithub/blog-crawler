package com.arawn.blog.cms.controller.admin;

import com.arawn.blog.cms.entity.ArcType;
import com.arawn.blog.cms.entity.PageBean;
import com.arawn.blog.cms.service.ArcTypeService;
import com.arawn.blog.cms.service.ArticleService;
import com.arawn.blog.cms.service.impl.InitComponent;
import com.arawn.blog.cms.util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ContextLoader;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文章类别Controller
 */
@Controller
@RequestMapping("/admin/arcType")
public class ArcTypeAdminController {

    @Resource
    private ArcTypeService arcTypeService;

    @Resource
    private ArticleService articleService;

    @Resource
    private InitComponent initComponent;

    @RequestMapping("/list")
    public String list(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, HttpServletResponse response) throws Exception {
        PageBean pageBean = new PageBean(page, rows);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<ArcType> arcTypeList = arcTypeService.list(map);
        Long total = arcTypeService.count(map);
        JSONObject result = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(arcTypeList);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
        return null;
    }

    @RequestMapping("/save")
    public String save(ArcType arcType, HttpServletResponse response) throws Exception {
        int resultTotal = 0;
        if (arcType.getId() == null) {
            resultTotal = arcTypeService.insert(arcType);
        } else {
            resultTotal = arcTypeService.update(arcType);
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
            if (articleService.countByTypeId(Integer.parseInt(idsStr[i])) > 0) {
                result.put("exist", "文章类别下存在文章，不能删除");
            } else {
                arcTypeService.delete(Integer.parseInt(idsStr[i]));
            }
        }
        initComponent.refreshSystem(ContextLoader.getCurrentWebApplicationContext().getServletContext());
        result.put("success", true);
        ResponseUtil.write(response, result);
        return null;
    }
}
