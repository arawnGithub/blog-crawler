package com.arawn.blog.cms.controller.admin;

import com.arawn.blog.cms.entity.Manager;
import com.arawn.blog.cms.service.ManagerService;
import com.arawn.blog.cms.util.Md5Util;
import com.arawn.blog.cms.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * 管理员后台Controller
 */
@Controller
@RequestMapping("/admin/manager")
public class ManagerAdminController {

    @Resource
    private ManagerService managerService;

    @RequestMapping("/modifyPassword")
    public String modifyPassword(String newPassword, HttpServletResponse response) throws Exception {
        Manager manager = new Manager();
        manager.setUsername((String) SecurityUtils.getSubject().getPrincipal());
        manager.setPassword(Md5Util.md5(newPassword, Md5Util.SALT));
        int resultTotal = managerService.update(manager);
        JSONObject result = new JSONObject();
        if (resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        ResponseUtil.write(response, result);
        return null;
    }

    @RequestMapping("/logout")
    public String logout() throws Exception {
        SecurityUtils.getSubject().logout();
        return "redirect:/login.jsp";
    }
}
