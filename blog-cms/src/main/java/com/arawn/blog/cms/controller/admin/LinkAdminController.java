package com.arawn.blog.cms.controller.admin;

import com.arawn.blog.cms.entity.Link;
import com.arawn.blog.cms.entity.PageBean;
import com.arawn.blog.cms.service.LinkService;
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
 * 友情链接后台Controller
 */
@Controller
@RequestMapping("/admin/link")
public class LinkAdminController {

    @Resource
    private LinkService linkService;

    @Resource
    private InitComponent initComponent;

    @RequestMapping("/list")
    public String list(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, HttpServletResponse response) throws Exception {
        PageBean pageBean = new PageBean(page, rows);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<Link> linkList = linkService.list(map);
        Long total = linkService.count(map);
        JSONObject result = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(linkList);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
        return null;
    }

    @RequestMapping("/save")
    public String save(Link link, HttpServletResponse response) throws Exception {
        int resultTotal = 0;
        if (link.getId() == null) {
            resultTotal = linkService.insert(link);
        } else {
            resultTotal = linkService.update(link);
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
            linkService.delete(Integer.parseInt(idsStr[i]));
        }
        initComponent.refreshSystem(ContextLoader.getCurrentWebApplicationContext().getServletContext());
        result.put("success", true);
        ResponseUtil.write(response, result);
        return null;
    }
}
